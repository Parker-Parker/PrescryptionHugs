import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class CardPickerPanel extends JPanel {
    int gap = 4;
    int columns = 4;
    int numer = 4;
    int denom = 5;
    private static String bckColor = "#181818";
    private static String butColor = "#710C04";
    private static String txtColor = "#DDDDDD";


    int UIwidth = (CARD_WIDTH*numer)/denom;
    int UIheight = (CARD_HEIGHT*numer)/denom;
    LinkedList<Card> cards = new LinkedList<>();
    LinkedList<JButton> buttons = new LinkedList<>();
    public CardPickerPanel(LinkedList<Card> mDeck, JComboBox comboBox) {
        super();
        RenderSetup();
        this.setBackground(Color.decode(bckColor));
        cards = new LinkedList<>(mDeck);
        // this.setPreferredSize(new Dimension((CARD_WIDTH+gap)*columns, (CARD_HEIGHT+gap)*(cards.size()/columns+1)));

        // this.setLayout(new GridLayout(rows, cards.size()/rows+1, gap, gap));
        this.setLayout(new FlowLayout());
        JButton button;
        int i = 0;
        for(Card card : cards){
            button = new JButton(new ImageIcon(renderCard(card).getScaledInstance(UIwidth, UIheight, Image.SCALE_DEFAULT)));
            button.setPreferredSize(new Dimension(UIwidth, UIheight));
            // button.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            // button.setSize(CARD_WIDTH, CARD_HEIGHT);;
            buttons.add(button);

            button.addActionListener(new ActionListenerWithIndex(i, comboBox));
            this.add(button);
            i++;
        }
        
    }

    class ActionListenerWithIndex implements ActionListener {
        int index;
        JComboBox comboBox;
        public ActionListenerWithIndex(int i, JComboBox cBox) {
            index = i;
            comboBox = cBox;
        }
        public void setIndex(int x){
            index = x;
        }
        public int getIndex(){
            return index;
        }
        @Override
        public void actionPerformed(ActionEvent arg0) {
            comboBox.setSelectedIndex(index);

        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////      Renderer Crap           ////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private BufferedImage emptyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
    static final int CARD_WIDTH = 125;
    static final int CARD_HEIGHT = 190;
    HashMap<String, BufferedImage> cardPortraits = new HashMap<>();
    BufferedImage cardBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage cardWhite = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage[] costs = { emptyImage,
            emptyImage,
            emptyImage,
            emptyImage,
            emptyImage };
    EnumMap<Sigils, BufferedImage> sigils = new EnumMap<>(Sigils.class);
    Font fontHeavyWeight;
    Font fontHeavyWeight_Stat;


    public void RenderSetup(){
        try {
            File font_file = new File("JavaApp/resources/HEAVYWEI.TTF");
            fontHeavyWeight = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(27f);
        } catch (Exception e) {
            e.printStackTrace();
            fontHeavyWeight = Font.getFont("Serif");
        }
        fontHeavyWeight_Stat = fontHeavyWeight.deriveFont(42f);


        try {
            cardBase = ImageIO.read(new File("JavaApp/resources/card_empty.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        importPortraits("JavaApp/resources/Portraits");
        importCosts("JavaApp/resources/Costs");
        importSigils("JavaApp/resources/Sigils");

    }

    public BufferedImage renderCard(Card c) {

        BufferedImage cardImage = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) cardImage.getGraphics();
        if (c == null) {
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
            
           

            g.setFont(fontHeavyWeight_Stat);
            g.setColor(Color.BLACK);
            g.drawString("" + c.getAttack(), CARD_WIDTH * 20 / 120 - metrics2.stringWidth(c.getAttack() + "") / 2,
                    CARD_HEIGHT * 91 / 120 + metrics2.getHeight() / 2);// draw attack

            g.setColor(Color.BLACK);
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
        BufferedImage portrait = cardPortraits.get(name.toUpperCase().replace(" ","").replace("_",""));
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
                        fileName = (String) fileName.subSequence("portait_".length() + 1, fileName.length() - ".png".length());
                        fileName = fileName.replace(" ","").replace("_","");

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
}