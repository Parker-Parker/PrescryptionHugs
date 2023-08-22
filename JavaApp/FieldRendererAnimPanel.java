
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FieldRendererAnimPanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 1920;

    static final int SCREEN_HEIGHT = 1080;

    static final int CARD_WIDTH = 125;

    static final int CARD_HEIGHT = 190;

    static final int ANIMATED_LAYER = 2;

    static final int IDLE_LAYER = 1;

    static final int DELAY = 15;
    // static final int GAP = 15;
    // static final int GAP = 50;
    volatile int GAP = 50;

    JFrame frame;

    boolean DRAW_SLOTS = false;
    // boolean OPAQUE_HAND_CARDS = false;
    int HAND_CARDS_DRAW_MODE = 0;
    float HAND_CARD_OPACITY = 0.3f;

    Timer timer;
    float debugTime = 0;

    // ArrayList<LinkedList<BufferedImage>> zBuffer = new ArrayList<>();

    ArrayList<BufferedImage> zBuffer = new ArrayList<>();

    // BufferedImage fieldImage = new BufferedImage(this.getWidth(),
    // this.getHeight(), BufferedImage.TYPE_INT_RGB);
    BufferedImage fieldImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    // Graphics fieldCanvas = fieldImage.getGraphics();
    Graphics2D fieldCanvas = (Graphics2D) fieldImage.getGraphics();

    private BufferedImage emptyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    HashMap<String, BufferedImage> cardPortraits = new HashMap<>();
    BufferedImage cardBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage cardWhite = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage slotBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage slotBaseBlank = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage[] costs = { emptyImage,
            emptyImage,
            emptyImage,
            emptyImage,
            emptyImage };
    EnumMap<Sigils, BufferedImage> sigils = new EnumMap<>(Sigils.class);

    Card testCard0 = new Card("Stoat");
    Card testCard1 = new Card("mole");
    Card testCard2 = new Card("moose");
    Card testCard3 = new Card("DEBIL");

    private volatile int animTimer = 0;

    Font fontHeavyWeight;
    Font fontHeavyWeight_Stat;

    private Field currentField = new Field();// need to change this to buffer
    Animations[][] currentAnimations = { { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null } };

    SafeRingBuffer<EnumMap<ObserverTopics, String>> animQueue = new SafeRingBuffer<>(250);

    ObserverOutputHandler observerParser = new ObserverOutputHandler();

    EnumMap<Animations, int[]> xValMap = new EnumMap<>(Animations.class);
    EnumMap<Animations, int[]> xTimeMap = new EnumMap<>(Animations.class);
    EnumMap<Animations, int[]> yValMap = new EnumMap<>(Animations.class);
    EnumMap<Animations, int[]> yTimeMap = new EnumMap<>(Animations.class);
    EnumMap<Animations, int[]> rValMap = new EnumMap<>(Animations.class);
    EnumMap<Animations, int[]> rTimeMap = new EnumMap<>(Animations.class);

    private volatile int animLength = 0;

    volatile boolean scalingEnabled = false;
    volatile float sf = 1.0f;// scaling factor
    volatile BufferedImageOp scaleOp = new AffineTransformOp(makeScaleTF(sf), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    static final int DECORATION_OFFSET_Y = 29;
    static final int DECORATION_OFFSET_x = 0;

    protected RenderWindowAdjustmentGUI tweaker;

    FieldRendererAnimPanel(JFrame framein) {
        this();
        this.frame = framein;

    }

    FieldRendererAnimPanel() {

        addAnimation(Animations.Idle, new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                // new int[]{0, 5, 2, -5, 1, 3, 2, -1, -4, 1, -1, -2, 0},
                // new int[]{0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600,});
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.PlayerAttack, new int[] { 0 },
                new int[] { 0 },
                new int[] { 0, -CARD_HEIGHT * 20 / 800, -CARD_HEIGHT * 70 / 800, -CARD_HEIGHT * 150 / 800,
                        -CARD_HEIGHT * 180 / 800, -CARD_HEIGHT * 190 / 800, -CARD_HEIGHT * 195 / 800,
                        -CARD_HEIGHT * 180 / 800, -CARD_HEIGHT * 160 / 800, -CARD_HEIGHT * 120 / 800,
                        -CARD_HEIGHT * 80 / 800, -CARD_HEIGHT * 45 / 800, -CARD_HEIGHT * 10 / 800,
                        -CARD_HEIGHT * 0 / 800 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.Attack, new int[] { 0 },
                new int[] { 0 },
                new int[] { 0, -3, -17, -20, -21, -19, -8, 20, 70, 150, 180, 190, 195, 180, 160, 120, 80, 45, 10, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900,
                        950 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.AttackLeft,
                new int[] { 0, 0, 0, 0, 0, 0, 0, -5, -15, -33, -65, -82, -90, -70, -50, -35, -15, -10, -5, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 490, 540, 600, 650, 700, 750, 800, 850, 900,
                        950 },

                new int[] { 0, -3, -17, -20, -21, -19, -8, 20, 70, 150, 170, 175, 180, 170, 155, 120, 80, 45, 10, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900,
                        950 },

                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 32, 37, 40, 30, 20, 5, 0, 0, 0, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900,
                        950 });

        addAnimation(Animations.AttackRight,
                new int[] { 0, 0, 0, 0, 0, 0, 0, 5, 15, 33, 65, 82, 90, 70, 50, 35, 15, 10, 5, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 490, 540, 600, 650, 700, 750, 800, 850, 900,
                        950 },

                new int[] { 0, -3, -17, -20, -21, -19, -8, 20, 70, 150, 170, 175, 180, 170, 155, 120, 80, 45, 10, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900,
                        950 },

                new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, -10, -32, -37, -40, -30, -20, -5, 0, 0, 0, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800, 850, 900,
                        950 });

        addAnimation(Animations.Die, new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0, 10, 25, 80, 95, 110, 95, 90 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350 });

        addAnimation(Animations.EnterTop, new int[] { 0 },
                new int[] { 0 },
                new int[] { -CARD_HEIGHT * 2, -CARD_HEIGHT * 2 * 9 / 10, -CARD_HEIGHT * 2 * 7 / 10,
                        -CARD_HEIGHT * 2 * 4 / 10, -CARD_HEIGHT * 2 * 2 / 10, -CARD_HEIGHT * 2 / 10, -CARD_HEIGHT / 10,
                        0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.Hurt, new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0, 5, 25, 33, 15, -5, -15, -10, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350, 400 });

        // addAnimation(Animations.Wiggle, new
        // int[]{0,-CARD_WIDTH/20,-CARD_WIDTH*4/20,-CARD_WIDTH*5/20,-CARD_WIDTH*3/20,CARD_WIDTH/20,CARD_WIDTH*4/20,CARD_WIDTH*6/20,CARD_WIDTH*5/20,CARD_WIDTH/20,-CARD_WIDTH*2/20,-CARD_WIDTH*5/20,-CARD_WIDTH*3/20,0},
        addAnimation(Animations.Wiggle,
                new int[] { 0, -CARD_WIDTH / 30, -CARD_WIDTH * 4 / 30, -CARD_WIDTH * 5 / 30, -CARD_WIDTH * 3 / 30,
                        CARD_WIDTH / 30, CARD_WIDTH * 4 / 30, CARD_WIDTH * 6 / 30, CARD_WIDTH * 5 / 30, CARD_WIDTH / 30,
                        -CARD_WIDTH * 2 / 30, -CARD_WIDTH * 5 / 30, -CARD_WIDTH * 3 / 30, 0 },
                // addAnimation(Animations.Wiggle, new
                // int[]{0,-CARD_WIDTH/40,-CARD_WIDTH*4/40,-CARD_WIDTH*5/40,-CARD_WIDTH*3/40,CARD_WIDTH/40,CARD_WIDTH*4/40,CARD_WIDTH*6/40,CARD_WIDTH*5/40,CARD_WIDTH/40,-CARD_WIDTH*2/40,-CARD_WIDTH*5/40,-CARD_WIDTH*3/40,0},
                // new int[]{0, 30, 60, 90, 120, 150, 180, 210, 240, 270, 300, 330, 360, 390},
                new int[] { 0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220, 240, 260 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 });
        // new int[]{0, 5, 20, 22, 15,-3, -20, -24,-12,12, 19, 22, 14, 6, 0},
        // new int[]{0, 10,20, 30, 40, 50,60,70,80,90,100,110,120,130,140});

        addAnimation(Animations.MoveDown, new int[] { 0 },
                new int[] { 0 },
                new int[] { -(CARD_HEIGHT + GAP), -(CARD_HEIGHT + GAP) * 9 / 10, -(CARD_HEIGHT + GAP) * 7 / 10,
                        -(CARD_HEIGHT + GAP) * 3 / 10, -(CARD_HEIGHT + GAP) * 1 / 10, -(CARD_HEIGHT + GAP) * 1 / 20,
                        0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.YeetIn, new int[] { 0 },
                new int[] { 0 },
                new int[] { -CARD_HEIGHT * 5, -CARD_HEIGHT * 5 * 9 / 10, -CARD_HEIGHT * 5 * 7 / 10,
                        -CARD_HEIGHT * 5 * 4 / 10, -CARD_HEIGHT * 5 * 2 / 10, -CARD_HEIGHT * 5 * 2 / 10,
                        -CARD_HEIGHT * 5 / 10, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.YeetOut, new int[] { 0 },
                new int[] { 0 },
                new int[] { 0, -CARD_HEIGHT * 5 / 10, -CARD_HEIGHT * 5 * 2 / 10, -CARD_HEIGHT * 5 * 2 / 10,
                        -CARD_HEIGHT * 5 * 4 / 10, -CARD_HEIGHT * 5 * 7 / 10, -CARD_HEIGHT * 5 * 9 / 10,
                        -CARD_HEIGHT * 5 },
                new int[] { 0, 50, 100, 150, 200, 250, 300, 350 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.MoveRight,
                new int[] { -(CARD_WIDTH + GAP), -(CARD_WIDTH + GAP) * 9 / 10, -(CARD_WIDTH + GAP) * 7 / 10,
                        -(CARD_WIDTH + GAP) * 3 / 10, -(CARD_WIDTH + GAP) * 1 / 10, -(CARD_WIDTH + GAP) * 1 / 20, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 });

        addAnimation(Animations.MoveLeft,
                new int[] { (CARD_WIDTH + GAP), (CARD_WIDTH + GAP) * 9 / 10, (CARD_WIDTH + GAP) * 7 / 10,
                        (CARD_WIDTH + GAP) * 3 / 10, (CARD_WIDTH + GAP) * 1 / 10, (CARD_WIDTH + GAP) * 1 / 20, 0 },
                new int[] { 0, 50, 100, 150, 200, 250, 300 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 },
                new int[] { 0 });

        // for(int i =0; i<10;i++){
        // zBuffer.add(i, new LinkedList<BufferedImage>());
        // }

        for (int i = 0; i < 3; i++) {
            zBuffer.add(i, new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB));
        }

        try {
            File font_file = new File("JavaApp/resources/HEAVYWEI.TTF");
            fontHeavyWeight = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(27f);
        } catch (Exception e) {
            e.printStackTrace();
            fontHeavyWeight = Font.getFont("Serif");
        }
        fontHeavyWeight_Stat = fontHeavyWeight.deriveFont(42f);

        Graphics2D tempGR = cardWhite.createGraphics();
        tempGR.setColor(Color.decode("#F0A966"));
        tempGR.drawRect(0, 0, CARD_WIDTH, CARD_HEIGHT);

        try {
            cardBase = ImageIO.read(new File("JavaApp/resources/card_empty.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            slotBase = ImageIO.read(new File("JavaApp/resources/Slot/card_slot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // if (!DRAW_SLOTS){
        // slotBase = new BufferedImage(slotBase.getWidth(), slotBase.getHeight(),
        // BufferedImage.TYPE_INT_ARGB);
        // }
        slotBaseBlank = new BufferedImage(slotBase.getWidth(), slotBase.getHeight(), BufferedImage.TYPE_INT_ARGB);

        // testCard0.setCost(1);
        // testCard1.setCost(2);
        // testCard2.setCost(3);
        // testCard3.setCost(4);

        // testCard2.setAttack(3);
        // testCard0.setAttack(1);
        // testCard3.setAttack(-1);

        // testCard2.setHealth(2);
        // testCard0.setHealth(3);
        // testCard3.setHealth(69);

        importPortraits("JavaApp/resources/Portraits");
        importCosts("JavaApp/resources/Costs");
        importSigils("JavaApp/resources/Sigils");

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        this.setBackground(Color.black);

        this.setFocusable(true);

        // startGame();

        timer = new Timer(DELAY, this);

        timer.start();

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent event) {
                // // System.out.println(event.getButton());
                // if(event.getClickCount()==2 && event.getButton() == MouseEvent.BUTTON1){
                // OPAQUE_HAND_CARDS = !OPAQUE_HAND_CARDS;
                // System.out.println("OPAQUE_HAND_CARDS: "+ OPAQUE_HAND_CARDS);

                // }
                // System.out.println(event.getButton());
                if (frame != null) {

                    frame.setLocationRelativeTo(null);// Uncomment me in production
                }
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    HAND_CARDS_DRAW_MODE = (HAND_CARDS_DRAW_MODE + 1) % 4;
                    // System.out.println("OPAQUE_HAND_CARDS: "+ OPAQUE_HAND_CARDS);

                }
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON3) {
                    DRAW_SLOTS = !DRAW_SLOTS;
                    System.out.println("DRAW_SLOTS: " + DRAW_SLOTS);
                    // System.out.println(frame.getInsets());

                    if (frame != null) {
                        frame.dispose();
                        frame.setUndecorated(!DRAW_SLOTS);
                        // frame.pack();
                        frame.setVisible(true);
                    }
                    if (DRAW_SLOTS) {
                        // Create debug tweaker
                        tweaker = new RenderWindowAdjustmentGUI(FieldRendererAnimPanel.this);
                    } else {
                        if (tweaker != null) {
                            tweaker.close();
                            tweaker = null;
                        }
                    }
                }

            }

            @Override
            public void mouseEntered(MouseEvent event) {
            }

            @Override
            public void mouseExited(MouseEvent event) {
            }

            @Override
            public void mousePressed(MouseEvent event) {
            }

            @Override
            public void mouseReleased(MouseEvent event) {
            }

        });

        // this.addMouseListener(new MouseAdapter(){
        // @Override
        // public void mouseClicked(MouseEvent e){
        // if(e.getClickCount()==2){
        // // your code here
        // }
        // }
        // });

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Score

        fieldCanvas.setColor(Color.BLACK);
        fieldCanvas.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        // fieldCanvas.setColor(Color.red);

        // fieldCanvas.setFont(new Font("Ink Free", Font.BOLD, 40));

        // fieldCanvas.setFont(fontHeavyWeight);

        // FontMetrics metrics1 = getFontMetrics(fieldCanvas.getFont());

        // fieldCanvas.drawString("Score: animTimer*Math.PI/180.0 = " +
        // animTimer*Math.PI/180.0, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + 7))
        // / 2, fieldCanvas.getFont().getSize());

        // Game Over text

        // fieldCanvas.setColor(Color.red);

        // fieldCanvas.setFont(new Font("Ink Free", Font.BOLD, 75));
        // fieldCanvas.setFont(fontHeavyWeight);

        // FontMetrics metrics2 = getFontMetrics(fieldCanvas.getFont());

        // fieldCanvas.drawString("Game Over", (SCREEN_WIDTH -
        // metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        renderField(fieldCanvas, currentField);
        if (scalingEnabled) {
            scaleImage(fieldImage);

        }
        // renderFieldZBuffer(currentField);
        // drawBuffer(fieldCanvas);

        // Animations test=null;
        // for (Animations[] test2: currentAnimations){
        // for (Animations test3: test2){
        // test = test3==null||test3==Animations.Idle?test:test3;
        // }
        // }
        // fieldCanvas.drawString("Anim: "+test, 50,50);
        // fieldCanvas.drawString("AnimLen: "+animLength, 50,100);
        // fieldCanvas.drawString("DeserTime: "+debugTime, 50,150);
        // fieldCanvas.drawString("waiting for: "+waitCause, 50,150);

        // drawDebug(fieldCanvas);
        int yInset = DRAW_SLOTS ? DECORATION_OFFSET_Y : 0;
        if (frame != null) {
            g2d.drawImage(fieldImage, null, (frame.getWidth() - fieldImage.getWidth()) / 2,
                    (frame.getHeight() - fieldImage.getHeight()) / 2 - yInset);

        } else {
            g2d.drawImage(fieldImage, null, 0, 0 - yInset);

        }

    }

    private void scaleImage(BufferedImage img) {
        BufferedImage temp = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // Graphics2D tempGfx = (Graphics2D)temp.getGraphics();
        temp.createGraphics().drawImage(img, scaleOp, (img.getWidth() - Math.round(sf * img.getWidth())) / 2,
                (img.getHeight() - Math.round(sf * img.getHeight())) / 2);
        img.createGraphics().drawImage(temp, null, 0, 0);

        // img.createGraphics().drawImage(img, new AffineTransformOp(makeScaleTF(sf),
        // AffineTransformOp.TYPE_NEAREST_NEIGHBOR),
        // (img.getWidth()-Math.round(sf*img.getWidth()))/2,(img.getHeight()-Math.round(sf*img.getHeight()))/2);
        // BufferedImage temp = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
        // BufferedImage.TYPE_INT_RGB);
        // Graphics2D tempGfx = (Graphics2D)temp.getGraphics();

        // tempGfx.drawImage(img, new AffineTransformOp(makeScaleTF(1.0),
        // AffineTransformOp.TYPE_NEAREST_NEIGHBOR), 0,0);
        // return temp;
    }

    private void drawDebug(Graphics2D canvas) {
        canvas.setColor(Color.green);
        canvas.setFont(Font.getFont("Serif"));

        // Animations anim = (currentAnimations[row])[column];
        // int time = animTimer*DELAY;//~time in ms?

        // System.out.println("Anim_debug: Col:"+column+"Row:"+row+"
        // anim:"+(anim==null?null:anim.name())+" x:"+xValMap+" y:"+yValMap+"
        // r:"+rValMap);

        canvas.drawString(
                "Animations:\n0) 0:" + ((currentAnimations[0])[0] == null ? null : (currentAnimations[0])[0].name()) +
                        " 1:" + ((currentAnimations[0])[1] == null ? null : (currentAnimations[0])[1].name()) +
                        " 2:" + ((currentAnimations[0])[2] == null ? null : (currentAnimations[0])[2].name()) +
                        " 3:" + ((currentAnimations[0])[3] == null ? null : (currentAnimations[0])[3].name()) +
                        "\n1) 0:" + ((currentAnimations[1])[0] == null ? null : (currentAnimations[1])[0].name()) +
                        " 1:" + ((currentAnimations[1])[1] == null ? null : (currentAnimations[1])[1].name()) +
                        " 2:" + ((currentAnimations[1])[2] == null ? null : (currentAnimations[1])[2].name()) +
                        " 3:" + ((currentAnimations[1])[3] == null ? null : (currentAnimations[1])[3].name()) +
                        "\n2) 0:" + ((currentAnimations[2])[0] == null ? null : (currentAnimations[2])[0].name()) +
                        " 1:" + ((currentAnimations[2])[1] == null ? null : (currentAnimations[2])[1].name()) +
                        " 2:" + ((currentAnimations[2])[2] == null ? null : (currentAnimations[2])[2].name()) +
                        " 3:" + ((currentAnimations[2])[3] == null ? null : (currentAnimations[2])[3].name()),
                20, 20);

    }

    private void renderField(Graphics2D canvas, Field field) {
        for (int i = 0; i < 4; i++) {
            if ((canvas != null) && (field != null) && (field.getEnemyCardsBack() != null)
                    && (field.getEnemyCardsBack().length == 4)) {
                renderAtPos(canvas, renderCardSlot(renderCard(field.getEnemyCardsBack()[i]), getAnimation(i, 0)), i, 0);

            }

        }
        for (int i = 0; i < 4; i++) {
            if ((canvas != null) && (field != null) && (field.getPlayerCards() != null)
                    && (field.getPlayerCards().length == 4)) {
                renderAtPos(canvas, renderCardSlot(renderCard(field.getPlayerCards()[i]), getAnimation(i, 2)), i, 2);
            }
        }
        for (int i = 0; i < 4; i++) {
            if ((canvas != null) && (field != null) && (field.getEnemyCards() != null)
                    && (field.getEnemyCards().length == 4)) {
                renderAtPos(canvas, renderCardSlot(renderCard(field.getEnemyCards()[i]), getAnimation(i, 1)), i, 1);// change
                                                                                                                    // this
                                                                                                                    // to
                                                                                                                    // show
                                                                                                                    // slot
                                                                                                                    // upside
                                                                                                                    // down
            }
        }
    }

    private void renderFieldZBuffer(Field field) {

        zBuffer.set(IDLE_LAYER, new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB));
        Graphics2D idleCanvas = (Graphics2D) zBuffer.get(IDLE_LAYER).getGraphics();
        zBuffer.set(ANIMATED_LAYER, new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB));
        Graphics2D animCanvas = (Graphics2D) zBuffer.get(ANIMATED_LAYER).getGraphics();
        Graphics2D canvas;
        for (int i = 0; i < 4; i++) {
            canvas = (((currentAnimations[0])[i] == null) || ((currentAnimations[0])[i] == Animations.Idle) ? idleCanvas
                    : animCanvas);
            if ((canvas != null) && (field != null) && (field.getEnemyCardsBack() != null)
                    && (field.getEnemyCardsBack().length == 4)) {
                renderAtPos(canvas, renderCardSlot(renderCard(field.getEnemyCardsBack()[i]), getAnimation(i, 0)), i, 0);

            }

        }
        for (int i = 0; i < 4; i++) {
            canvas = (((currentAnimations[1])[i] == null) || ((currentAnimations[1])[i] == Animations.Idle) ? idleCanvas
                    : animCanvas);
            if ((canvas != null) && (field != null) && (field.getEnemyCards() != null)
                    && (field.getEnemyCards().length == 4)) {
                renderAtPos(canvas, renderCardSlot(renderCard(field.getEnemyCards()[i]), getAnimation(i, 1)), i, 1);// change
                                                                                                                    // this
                                                                                                                    // to
                                                                                                                    // show
                                                                                                                    // slot
                                                                                                                    // upside
                                                                                                                    // down
            }
        }
        for (int i = 0; i < 4; i++) {
            canvas = (((currentAnimations[2])[i] == null) || ((currentAnimations[2])[i] == Animations.Idle) ? idleCanvas
                    : animCanvas);
            if ((canvas != null) && (field != null) && (field.getPlayerCards() != null)
                    && (field.getPlayerCards().length == 4)) {
                renderAtPos(canvas, renderCardSlot(renderCard(field.getPlayerCards()[i]), getAnimation(i, 2)), i, 2);
            }
        }

        // for(int i = 0;i<4;i++){
        // if((zBuffer!=null)&&(zBuffer.get(2)!=null)&&(zBuffer.get(5)!=null)&&(field!=null)&&(field.getEnemyCardsBack()!=null)&&(field.getEnemyCardsBack().length==4)){
        // BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
        // BufferedImage.TYPE_INT_ARGB);
        // renderAtPos(((Graphics2D)image.getGraphics()),
        // renderCardSlot(renderCard(field.getEnemyCardsBack()[i]), getAnimation(i,0)),
        // i,0);//change this to show slot upside down
        // zBuffer.get(((currentAnimations[0])[i]==null)||((currentAnimations[0])[i]==Animations.Idle)
        // ? IDLE_LAYER : ANIMATED_LAYER).add(image);
        // }
        // }
        // for(int i = 0;i<4;i++){
        // if((zBuffer!=null)&&(zBuffer.get(2)!=null)&&(zBuffer.get(5)!=null)&&(field!=null)&&(field.getEnemyCards()!=null)&&(field.getEnemyCards().length==4)){
        // BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
        // BufferedImage.TYPE_INT_ARGB);
        // renderAtPos(((Graphics2D)image.getGraphics()),
        // renderCardSlot(renderCard(field.getEnemyCards()[i]), getAnimation(i,1)),
        // i,1);//change this to show slot upside down
        // zBuffer.get(((currentAnimations[1])[i]==null)||((currentAnimations[1])[i]==Animations.Idle)
        // ? IDLE_LAYER : ANIMATED_LAYER).add(image);
        // }
        // }
        // for(int i = 0;i<4;i++){
        // if((zBuffer!=null)&&(zBuffer.get(2)!=null)&&(zBuffer.get(5)!=null)&&(field!=null)&&(field.getPlayerCards()!=null)&&(field.getPlayerCards().length==4)){
        // BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
        // BufferedImage.TYPE_INT_ARGB);
        // renderAtPos(((Graphics2D)image.getGraphics()),
        // renderCardSlot(renderCard(field.getPlayerCards()[i]), getAnimation(i,2)),
        // i,2);//change this to show slot upside down
        // zBuffer.get(((currentAnimations[2])[i]==null)||((currentAnimations[2])[i]==Animations.Idle)
        // ? IDLE_LAYER : ANIMATED_LAYER).add(image);
        // }
        // }
    }

    static int rand = 0;

    private AffineTransform getAnimation(int column, int row) {
        Animations anim = (currentAnimations[row])[column];
        int time = animTimer * DELAY;// ~time in ms?

        // System.out.println("Anim_debug: Col:"+column+"Row:"+row+"
        // anim:"+(anim==null?null:anim.name())+" x:"+xValMap+" y:"+yValMap+"
        // r:"+rValMap);

        return (anim == null) || (anim == Animations.Idle) ? null
                : makeSlotTF(linearInterpolatePoints(time, rValMap.get(anim), rTimeMap.get(anim)),
                        linearInterpolatePoints(time, xValMap.get(anim), xTimeMap.get(anim)),
                        linearInterpolatePoints(time, yValMap.get(anim), yTimeMap.get(anim)));

        // //////////////////////////////////////////////////
        // switch(currentAnimations[row][column]){
        // case Attack:
        // return attackAnim(time);
        // // break;
        // case Die:
        // break;
        // case EnterTop:
        // break;
        // case Hurt:
        // break;
        // case Idle:
        // break;
        // case MoveDown:
        // break;
        // case MoveFail:
        // break;
        // case MoveLeft:
        // break;
        // case MoveRight:
        // break;
        // default:
        // break;

        // }

        //////////////////////////////////////////////////////

        // // rand = (rand+5+7)%10-5;
        // rand = (rand+3)%20;
        // return makeSlotTF((rand-10)/3, 0,0);
    }

    // private AffineTransform getAnimation(int column, int row) {
    // // rand = (rand+5+7)%10-5;
    // rand = (rand+3)%20;
    // return makeSlotTF((rand-10)/3, 0,0);
    // }
    // private AffineTransform attackAnim(int time){
    // int rot = linearInterpolatePoints(time,
    // );
    // int x = 0;
    // int y = 0;
    // }
    private void addAnimation(Animations anim,
            int[] xvalues, int[] xtimes,
            int[] yvalues, int[] ytimes,
            int[] rvalues, int[] rtimes) {
        xValMap.put(anim, xvalues);
        xTimeMap.put(anim, xtimes);
        yValMap.put(anim, yvalues);
        yTimeMap.put(anim, ytimes);
        rValMap.put(anim, rvalues);
        rTimeMap.put(anim, rtimes);
    }

    private int linearInterpolatePoints(int time, int[] val, int[] times) {
        if ((val != null) && (times != null) && (val.length == times.length)) {
            int i = 0;
            while (i < val.length) {
                if (time <= times[i]) {
                    return i == 0 ? val[i]
                            : (((val[i] - val[i - 1]) * (time - times[i - 1])) * 1000 / (times[i] - times[i - 1]))
                                    / 1000 + val[i - 1];
                }
                i++;
            }
            return val[val.length - 1];
        }
        return 0;
    }

    private float linearInterpolatePointsf(int time, int[] val, int[] times) {
        if ((val != null) && (times != null) && (val.length == times.length)) {
            int i = 0;
            while (i < val.length) {
                if (time <= times[i]) {
                    return i == 0 ? 1.0f * val[i]
                            : (((1.0f * (val[i] - val[i - 1])) * (time - times[i - 1])) / (times[i] - times[i - 1]))
                                    + val[i - 1];
                }
                i++;
            }
            return 1.0f * val[val.length - 1];
        }
        return 0f;
    }

    private void renderAtPos(Graphics2D g, BufferedImage img, int column, int row) {// TODO: make this work with
                                                                                    // zbuffer, so animated cards can
                                                                                    // appear on top even if in back

        g.drawImage(img, null,
                (SCREEN_WIDTH / 2) - ((CARD_WIDTH + GAP) * 3 / 2) + column * (CARD_WIDTH + GAP) - img.getWidth() / 2,
                (SCREEN_HEIGHT / 2) - ((CARD_HEIGHT + GAP)) + row * (CARD_HEIGHT + GAP) - img.getHeight() / 2);// make
                                                                                                               // this
                                                                                                               // more
                                                                                                               // parameterized
    }

    private AffineTransform makeSlotTF(double degreesCW, double dx, double dy) {
        AffineTransform transform = new AffineTransform();
        transform.translate(dx, dy);
        transform.rotate(degreesCW * Math.PI / 180.0, CARD_WIDTH * 4 / 2.0, CARD_HEIGHT * 4 / 2.0);
        return transform;
    }

    private AffineTransform makeScaleTF(double scaleFactor) {
        AffineTransform transform = new AffineTransform();
        transform.scale(scaleFactor, scaleFactor);
        return transform;
    }

    private BufferedImage renderCardSlot(BufferedImage card, AffineTransform animationTransform) {
        BufferedImage slot = new BufferedImage(CARD_WIDTH * 4, CARD_HEIGHT * 4, BufferedImage.TYPE_INT_ARGB);
        BufferedImage cardAnimated = new BufferedImage(CARD_WIDTH * 4, CARD_HEIGHT * 4, BufferedImage.TYPE_INT_ARGB);
        Graphics2D slotGraphics2d = (Graphics2D) slot.getGraphics();
        Graphics2D cardAnimatedGraphics2d = (Graphics2D) cardAnimated.getGraphics();
        if (DRAW_SLOTS) {
            slotGraphics2d.drawImage(slotBase, null, (slot.getWidth() - slotBase.getWidth()) / 2,
                    (slot.getHeight() - slotBase.getHeight()) / 2);

        } else {
            slotGraphics2d.drawImage(slotBaseBlank, null, (slot.getWidth() - slotBaseBlank.getWidth()) / 2,
                    (slot.getHeight() - slotBaseBlank.getHeight()) / 2);

        }

        cardAnimatedGraphics2d.drawImage(card, null, (slot.getWidth() - card.getWidth()) / 2,
                (slot.getHeight() - card.getHeight()) / 2);
        // if(animationTransform!=null){
        // // System.out.println("Did a tf: "+animationTransform.toString());
        // BufferedImage cardAnimatedTransformed = new BufferedImage(CARD_WIDTH*4,
        // CARD_HEIGHT*4, BufferedImage.TYPE_INT_ARGB);
        // AffineTransformOp affineTransformOp = new
        // AffineTransformOp(animationTransform, AffineTransformOp.TYPE_BILINEAR);
        // affineTransformOp.filter(cardAnimated, cardAnimatedTransformed );
        // cardAnimated = cardAnimatedTransformed;
        // // cardAnimatedGraphics2d.transform(animationTransform);
        // }
        // slotGraphics2d.drawImage(cardAnimated, null, 0,0);

        if (animationTransform == null) {// I think this is supposed to be faster...
            slotGraphics2d.drawImage(cardAnimated, null, 0, 0);

        } else {
            slotGraphics2d.drawImage(cardAnimated,
                    new AffineTransformOp(animationTransform, AffineTransformOp.TYPE_BILINEAR), 0, 0);
        }

        return slot;// returns a transparent image with a slot at the exaft center and the
                    // corresponding card placed relative to the slot

    }

    // private void drawBuffer(Graphics2D g) {
    // // for(LinkedList<BufferedImage> list : zBuffer)
    // for(int i = 0; i<zBuffer.size();i++){
    // for(BufferedImage image : zBuffer.get(i)){
    // g.drawImage(image, null, 0, 0);
    // }
    // }
    // }
    private void drawBuffer(Graphics2D g) {
        // for(LinkedList<BufferedImage> list : zBuffer)
        for (int i = 0; i < zBuffer.size(); i++) {
            g.drawImage(zBuffer.get(i), null, 0, 0);
        }
    }

    // String waitCause = "";
    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();// every DELAY ms draw new frame?
        animTimer++;
        if (animTimer > animLength) {
            if (animQueue.checkAvailable()) {
                // long startTime = System.currentTimeMillis();
                EnumMap<ObserverTopics, String> topicStrings = animQueue.popOld();
                // observerParser.deserializeField(currentField, topicStrings);
                observerParser.deserializeAnim(topicStrings, currentField, currentAnimations);
                // long finTime =System.currentTimeMillis();
                // debugTime = (finTime-startTime);//1000f;
                animTimer = 0;
                int x = 0;
                animLength = 3;
                for (Animations[] arr : currentAnimations) {
                    for (Animations anim : arr) {
                        x = getAnimLength(anim);
                        animLength = x > animLength ? x : animLength;
                    }
                }
                // waitCause = "unknown";
            }
            // else{waitCause = "emptyQueue";}
        }
        // else{waitCause = "animRunning";}
    }

    private int getAnimLength(Animations anim) {
        if (anim == null) {
            return 0;
        }
        switch (anim) {
            case Attack:
                return 900 / DELAY;
            case AttackLeft:
                return 900 / DELAY;
            case AttackRight:
                return 900 / DELAY;
            case Die:
                return 350 / DELAY;
            case EnterTop:
                return 400 / DELAY;
            case Hurt:
                return 400 / DELAY;
            case Idle:
                return 0;
            case MoveDown:
                return 350 / DELAY;
            case MoveFail:
                return 0 / DELAY;
            case MoveLeft:
                return 350 / DELAY;
            case MoveRight:
                return 350 / DELAY;
            case YeetIn:
                return 400 / DELAY;
            case YeetOut:
                return 400 / DELAY;
            case Wiggle:
                return 600 / DELAY;
            case PlayerAttack:
                return 650 / DELAY;
            default:
                return 0;
        }
    }

    Color maroon = new Color(120, 30, 0);
    Color greenatk = new Color(40, 100, 50);// seafoam

    public BufferedImage renderCard(Card c) {

        BufferedImage cardImage = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) cardImage.getGraphics();
        if (c == null) {
            // g.setColor(Color.RED);
            // g.setFont(fontHeavyWeight);
            // FontMetrics metrics = getFontMetrics(fieldCanvas.getFont());
            // g.drawString("NULL", (CARD_WIDTH - metrics.stringWidth("NULL")) / 2,
            // (CARD_HEIGHT - metrics.getHeight()) / 2);//draw title
            return cardImage;
        } else {

            // float opacity = 0.5f;
            // g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            FontMetrics metrics = getFontMetrics(fontHeavyWeight);
            FontMetrics metrics2 = getFontMetrics(fontHeavyWeight_Stat);

            g.drawImage(cardBase, null, 0, 0);// draw background

            g.setColor(Color.BLACK);
            g.setFont(fontHeavyWeight);

            if ((CARD_WIDTH * 9 / 10 < metrics.stringWidth(c.getTitle()))) {
                Font newfont = fontHeavyWeight.deriveFont((22f * CARD_WIDTH) / metrics.stringWidth(c.getTitle()));
                metrics = getFontMetrics(newfont);
                g.setFont(newfont);
            }
            // g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) /
            // 2, metrics.getHeight());//draw title
            // g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) /
            // 2, (CARD_HEIGHT*42)/190-metrics.getHeight()/2);//draw title
            g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) / 2,
                    (CARD_HEIGHT * 14) / 190 + metrics.getHeight() / 2);// draw title
            // System.out.println(metrics.getHeight()/2+":"+(CARD_HEIGHT*42)/190);

            BufferedImage portrait = getCardPortrait(c.getTitle().replaceAll("Elder ", ""));
            g.drawImage(portrait, null, CARD_WIDTH / 2 - portrait.getWidth() / 2,
                    CARD_HEIGHT * 8 / 19 - portrait.getHeight() / 2);// draw portrait

            BufferedImage cost = getCostIndicator(c.cost);
            g.drawImage(cost, null, (CARD_WIDTH - cost.getWidth()), CARD_HEIGHT / 13);// draw cost indicator
            // g.drawImage(cost,null,0,0);//draw cost indicator

            BufferedImage sigil = getSigilImage(c.sigils);
            g.drawImage(sigil, null, (CARD_WIDTH - sigil.getWidth()) / 2,
                    (CARD_HEIGHT * 25 / 30) - sigil.getHeight() / 2);// draw cost indicator
            // g.drawImage(cost,null,0,0);//draw cost indicator

            // opacity = 0.5f;
            // g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

            if (HAND_CARDS_DRAW_MODE > 1 && c.isFromHand()) {
                // g.drawImage(cardWhite,null,0,0);//draw background

                g.setColor(Color.decode("#F0A966"));
                g.fillRect(0, 0, CARD_WIDTH, CARD_HEIGHT);

            }
            // if((!OPAQUE_HAND_CARDS)&&c.isFromHand()){
            if (HAND_CARDS_DRAW_MODE % 2 == 0 && c.isFromHand()) {
                BufferedImage cardImage2 = new BufferedImage(cardImage.getWidth(), cardImage.getHeight(),
                        BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = (Graphics2D) cardImage2.getGraphics();
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, HAND_CARD_OPACITY));
                g2.drawImage(cardImage, null, 0, 0);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));

                cardImage = cardImage2;
                g = g2;

            }

            g.setFont(fontHeavyWeight_Stat);
            // g.drawString(""+c.getAttack(),
            // CARD_WIDTH*19/120-metrics2.stringWidth(c.getAttack()+"")/2,
            // CARD_HEIGHT*23/30+metrics2.getHeight()/2);//draw attack
            // g.drawString(c.getHealth()+"", (CARD_WIDTH*101/120 -
            // metrics2.stringWidth(c.getHealth()+"")/2) ,
            // CARD_HEIGHT*5/6+metrics2.getHeight()/2);//draw health
            g.setColor(Color.BLACK);
            if (c.getAttack() != c.getBaseAttack()) {
                g.setColor(greenatk);
            }
            g.drawString("" + c.getAttack(), CARD_WIDTH * 20 / 120 - metrics2.stringWidth(c.getAttack() + "") / 2,
                    CARD_HEIGHT * 91 / 120 + metrics2.getHeight() / 2);// draw attack

            g.setColor(Color.BLACK);
            if (c.getHealth() != c.getBaseHealth()) {
                g.setColor(maroon);
            }
            g.drawString(c.getHealth() + "", (CARD_WIDTH * 100 / 120 - metrics2.stringWidth(c.getHealth() + "") / 2),
                    CARD_HEIGHT * 5 / 6 + metrics2.getHeight() / 2);// draw health

            return cardImage;
        }

    }

    public BufferedImage getSigilImage(Sigils sig) {
        BufferedImage sigil = sigils.get(sig);
        return sigil == null ? emptyImage : sigil;
    }

    public BufferedImage getSigilImage(EnumMap<Sigils, Boolean> sigs) {
        BufferedImage sigil = null;
        for (Sigils s : sigs.keySet()) {
            if (sigs.get(s)) {
                sigil = sigils.get(s);
            }
        }
        return sigil == null ? emptyImage : sigil;
    }

    public BufferedImage getCardPortrait(String name) {
        BufferedImage portrait = cardPortraits.get(name.toUpperCase());
        return portrait == null ? emptyImage : portrait;
    }

    public BufferedImage getCostIndicator(int cost) {
        if (cost < costs.length) {
            return costs[cost];
        }
        return emptyImage;
    }

    public void importPortraits(String folderPath) {

        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                try {
                    if (listOfFiles[i].isFile()) {
                        // System.out.println("File " + listOfFiles[i].getName());
                        String fileName = listOfFiles[i].getName();
                        fileName = (String) fileName.subSequence("portait_".length() + 1,
                                fileName.length() - ".png".length());

                        // System.out.println(fileName);
                        BufferedImage portrait = null;
                        try {
                            portrait = ImageIO.read(listOfFiles[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        cardPortraits.put(fileName.toUpperCase(), portrait);

                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importSigils(String folderPath) {

        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                try {
                    if (listOfFiles[i].isFile()) {
                        // System.out.println("File " + listOfFiles[i].getName());
                        String fileName = listOfFiles[i].getName();
                        fileName = (String) fileName.subSequence("ability_".length(),
                                fileName.length() - ".png".length());

                        // System.out.println(fileName);
                        BufferedImage sigil = null;
                        try {
                            sigil = ImageIO.read(listOfFiles[i]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("Importing file: " + fileName);
                        for (Sigils s : Sigils.values()) {
                            System.out.println("       checking: " + s.name());
                            if (s.name().equalsIgnoreCase(fileName)) {
                                sigils.put(s, sigil);

                                System.out.println("       SUCCEEDED");

                            }
                        }

                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importCosts(String folderPath) {

        try {
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {
                try {
                    if (listOfFiles[i].isFile()) {
                        String fileName = listOfFiles[i].getName();
                        // System.out.println(fileName);
                        // System.out.println("cost_.length()+1 =
                        // "+"cost_".length()+1+"fileName.length()-blood.png.length() =
                        // "+(fileName.length()-"blood.png".length()));

                        fileName = (String) fileName.subSequence("cost_".length(), "cost_".length() + 1);

                        // System.out.println(fileName);
                        BufferedImage portrait = null;
                        try {
                            int cost = Integer.parseInt(fileName);
                            portrait = ImageIO.read(listOfFiles[i]);
                            costs[cost] = portrait;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setField(Field newField) {
        this.currentField = newField;
        // currentField.bulkInit();
    }

    public void addToQueue(EnumMap<ObserverTopics, String> topics) {
        animQueue.push(topics);
    }

    public void makeScaleOp() {
        scaleOp = new AffineTransformOp(makeScaleTF(sf), AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    }
}

// BufferedImage awtImage = new BufferedImage(drawPanel.getWidth(),
// drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
// Graphics g = awtImage.getGraphics();
// drawPanel.printAll(g);