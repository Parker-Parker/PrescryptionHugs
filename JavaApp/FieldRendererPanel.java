
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FieldRendererPanel extends JPanel implements ActionListener {
    

    static final int SCREEN_WIDTH = 1920;

    static final int SCREEN_HEIGHT = 1080;

    static final int CARD_WIDTH = 125;

    static final int CARD_HEIGHT = 190;

    static final int DELAY = 15;
    // static final int GAP = 15;
    static final int GAP = 50;

    Timer timer;


    ArrayList<LinkedList<BufferedImage>> zBuffer = new ArrayList<>();

    // BufferedImage fieldImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
    BufferedImage fieldImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    // Graphics fieldCanvas = fieldImage.getGraphics();
    Graphics2D fieldCanvas = (Graphics2D)fieldImage.getGraphics();


    private BufferedImage emptyImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);

    HashMap<String,BufferedImage> cardPortraits = new HashMap<>();
    BufferedImage cardBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage slotBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    BufferedImage[] costs = {emptyImage,
                            emptyImage,
                            emptyImage,
                            emptyImage,
                            emptyImage};




    Card testCard0 = new Card("Stoat");
    Card testCard1 = new Card("mole");
    Card testCard2 = new Card("moose");
    Card testCard3 = new Card("DEBIL");
    


    private int animTimer = 0;
    
    Font fontHeavyWeight;
    Font fontHeavyWeight_Stat;

    private Field currentField;//need to change this to buffer


    FieldRendererPanel() {

        for(int i =0; i<10;i++){
            zBuffer.add(i, new LinkedList<BufferedImage>());
        }

        try{
            File font_file = new File("JavaApp/resources/HEAVYWEI.TTF");
            fontHeavyWeight = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(27f);
        }
        catch(Exception e){
            e.printStackTrace();
            fontHeavyWeight = Font.getFont("Comic Sans");
        }
        fontHeavyWeight_Stat = fontHeavyWeight.deriveFont(42f);

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
        testCard0.setCost(1);
        testCard1.setCost(2);
        testCard2.setCost(3);
        testCard3.setCost(4);

        testCard2.setAttack(3);
        testCard0.setAttack(1);
        testCard3.setAttack(-1);

        testCard2.setHealth(2);
        testCard0.setHealth(3);
        testCard3.setHealth(69);
    
        importPortraits("JavaApp/resources/Portraits");
        importCosts("JavaApp/resources/Costs");


        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        this.setBackground(Color.black);

        this.setFocusable(true);

        // startGame();

        timer = new Timer(DELAY, this);

        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        draw(g);

    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);


         // Score

         fieldCanvas.setColor(Color.BLACK);
         fieldCanvas.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
         fieldCanvas.setColor(Color.red);

        //  fieldCanvas.setFont(new Font("Ink Free", Font.BOLD, 40));

        fieldCanvas.setFont(fontHeavyWeight);
 
         FontMetrics metrics1 = getFontMetrics(fieldCanvas.getFont());
 
         fieldCanvas.drawString("Score: animTimer*Math.PI/180.0 = " + animTimer*Math.PI/180.0, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + 7)) / 2, fieldCanvas.getFont().getSize());
 
         // Game Over text
 
         fieldCanvas.setColor(Color.red);
 
        //  fieldCanvas.setFont(new Font("Ink Free", Font.BOLD, 75));
         fieldCanvas.setFont(fontHeavyWeight);
 
         FontMetrics metrics2 = getFontMetrics(fieldCanvas.getFont());
 
         fieldCanvas.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);


        //  AffineTransform rTransform = new AffineTransform();
         //  rTransform.rotate(rotation*DELAY*Math.PI/180000.0,SCREEN_WIDTH/2.0, SCREEN_HEIGHT/2.0);

        //  ((Graphics2D)fieldCanvas).transform(rTransform);
        //  fieldCanvas.transform(rTransform);


        //  fieldCanvas.drawImage(renderCard(testCard0), null, SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
        //  fieldCanvas.drawImage(renderCard(testCard1), null, (CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
        //  fieldCanvas.drawImage(renderCard(null), null, 2*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
        //  fieldCanvas.drawImage(renderCard(testCard2), null, 3*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
        //  fieldCanvas.drawImage(renderCard(testCard3), null, 4*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);

        // AffineTransform anim = makeSlotTF(0, 0, 300);    
        // AffineTransform anim = makeSlotTF((120*animTimer/60.0), 0, 0);//(90*animTimer/60f)%CARD_HEIGHT);    
        // AffineTransform anim = makeSlotTF(90, 0, (250*animTimer/60f)%CARD_HEIGHT);    
        // AffineTransform anim = makeSlotTF((180*animTimer/60.0), 0, (900*animTimer/60f)%CARD_HEIGHT);



        // AffineTransform anim = makeSlotTF(0, 0, (200*animTimer/60f)%CARD_HEIGHT);

        // renderAtPos(fieldCanvas, renderCardSlot(renderCard(testCard0), null), 0,0);
        // renderAtPos(fieldCanvas, renderCardSlot(renderCard(testCard1), null), 1,1);
        // renderAtPos(fieldCanvas, renderCardSlot(renderCard(testCard3), null), 2,1);
        // renderAtPos(fieldCanvas, renderCardSlot(renderCard(testCard2), anim), 3,0);


        renderField(fieldCanvas, currentField);



         
        //  fieldImage.
        //  this.drawBuffer(fieldCanvas);
         g2d.drawImage(fieldImage, null, 0,0);

    }


    // private BufferedImage renderCardSlot(Card card, AffineTransform animationTransform){
    //     BufferedImage slot = new BufferedImage(CARD_WIDTH*4, CARD_HEIGHT*4, BufferedImage.TYPE_INT_ARGB);
    //     return slot;        
    // }




    private void renderField(Graphics2D canvas, Field field) {
        for(int i = 0;i<4;i++){
            if((canvas!=null)&&(field!=null)&&(field.getEnemyCardsBack()!=null)&&(field.getEnemyCardsBack().length==4)){
                renderAtPos(canvas, renderCardSlot(renderCard(field.getEnemyCardsBack()[i]), getAnimation(i,0)), i,0);
            }
        }
        for(int i = 0;i<4;i++){
            if((canvas!=null)&&(field!=null)&&(field.getEnemyCards()!=null)&&(field.getEnemyCards().length==4)){
                renderAtPos(canvas, renderCardSlot(renderCard(field.getEnemyCards()[i]), getAnimation(i,1)), i,1);
            }
        }
        for(int i = 0;i<4;i++){
            if((canvas!=null)&&(field!=null)&&(field.getPlayerCards()!=null)&&(field.getPlayerCards().length==4)){
                renderAtPos(canvas, renderCardSlot(renderCard(field.getPlayerCards()[i]), getAnimation(i,2)), i,2);
            }
        }
    }
    static int rand = 0;

    private AffineTransform getAnimation(int column, int row) {
        // rand = (rand+5+7)%10-5;
        rand = (rand+3)%20;
        return makeSlotTF((rand-10)/3, 0,0);
    }

    private void renderAtPos(Graphics2D g, BufferedImage img, int column, int row){

        g.drawImage(img, null, 
                    (SCREEN_WIDTH/2)    -((CARD_WIDTH + GAP)*3/2)   +column*(CARD_WIDTH + GAP)     -img.getWidth()/2, 
                    (SCREEN_HEIGHT/2)   -((CARD_HEIGHT +GAP))       +row*(CARD_HEIGHT + GAP)  -img.getHeight()/2 
                    );//make this more parameterized
    }

    private AffineTransform makeSlotTF(double degreesCW, double dx,double dy){
        AffineTransform transform = new AffineTransform();
        transform.translate(dx, dy);
        transform.rotate(degreesCW*Math.PI/180.0,CARD_WIDTH*4/2.0, CARD_HEIGHT*4/2.0);
        return transform;
    }

    private BufferedImage renderCardSlot(BufferedImage card, AffineTransform animationTransform){
        BufferedImage slot = new BufferedImage(CARD_WIDTH*4, CARD_HEIGHT*4, BufferedImage.TYPE_INT_ARGB);
        BufferedImage cardAnimated = new BufferedImage(CARD_WIDTH*4, CARD_HEIGHT*4, BufferedImage.TYPE_INT_ARGB);
        Graphics2D slotGraphics2d = (Graphics2D)slot.getGraphics();
        Graphics2D cardAnimatedGraphics2d = (Graphics2D)cardAnimated.getGraphics();

        slotGraphics2d.drawImage(slotBase, null, (slot.getWidth()-slotBase.getWidth())/2, (slot.getHeight()-slotBase.getHeight())/2);
        
        cardAnimatedGraphics2d.drawImage(card, null, (slot.getWidth()-card.getWidth())/2, (slot.getHeight()-card.getHeight())/2);
        // if(animationTransform!=null){
        //     // System.out.println("Did a tf: "+animationTransform.toString());
        //     BufferedImage cardAnimatedTransformed = new BufferedImage(CARD_WIDTH*4, CARD_HEIGHT*4, BufferedImage.TYPE_INT_ARGB);
        //     AffineTransformOp affineTransformOp = new AffineTransformOp(animationTransform, AffineTransformOp.TYPE_BILINEAR);
        //     affineTransformOp.filter(cardAnimated, cardAnimatedTransformed );
        //     cardAnimated = cardAnimatedTransformed;
        //     // cardAnimatedGraphics2d.transform(animationTransform);
        // }
        // slotGraphics2d.drawImage(cardAnimated, null, 0,0);


        if(animationTransform==null){//I think this is supposed to be faster...
            slotGraphics2d.drawImage(cardAnimated, null, 0,0);

        }
        else{
            slotGraphics2d.drawImage(cardAnimated, new AffineTransformOp(animationTransform, AffineTransformOp.TYPE_BILINEAR), 0,0);

        }
        

        return slot;//returns a transparent image with a slot at the exaft center and the corresponding card placed relative to the slot
        
    }

    private void drawBuffer(Graphics2D g) {
        // for(LinkedList<BufferedImage> list : zBuffer)
        for(int i = 0; i<zBuffer.size();i++){
            for(BufferedImage image : zBuffer.get(i)){
                g.drawImage(image, null, 0, 0);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();//every DELAY ms draw new frame?
        animTimer++;
        //updatefield
        //updateanim params
    }

    public BufferedImage renderCard(Card c){
        
        BufferedImage cardImage = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) cardImage.getGraphics();
        if(c==null){
            g.setColor(Color.RED);
            g.setFont(fontHeavyWeight);
            FontMetrics metrics = getFontMetrics(fieldCanvas.getFont());
            g.drawString("NULL", (CARD_WIDTH - metrics.stringWidth("NULL")) / 2, (CARD_HEIGHT - metrics.getHeight()) / 2);//draw title
            return cardImage;
        }else{

            FontMetrics metrics = getFontMetrics(fontHeavyWeight);
            FontMetrics metrics2 = getFontMetrics(fontHeavyWeight_Stat);

            g.drawImage(cardBase,null,0,0);//draw background

            g.setColor(Color.BLACK);
            g.setFont(fontHeavyWeight);
            g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) / 2, metrics.getHeight());//draw title

            g.setFont(fontHeavyWeight_Stat);
            // g.drawString(""+c.getAttack(), CARD_WIDTH*19/120-metrics2.stringWidth(c.getAttack()+"")/2, CARD_HEIGHT*23/30+metrics2.getHeight()/2);//draw attack
            // g.drawString(c.getHealth()+"", (CARD_WIDTH*101/120 - metrics2.stringWidth(c.getHealth()+"")/2) , CARD_HEIGHT*5/6+metrics2.getHeight()/2);//draw health
            g.drawString(""+c.getAttack(), CARD_WIDTH*20/120-metrics2.stringWidth(c.getAttack()+"")/2, CARD_HEIGHT*91/120+metrics2.getHeight()/2);//draw attack
            g.drawString(c.getHealth()+"", (CARD_WIDTH*100/120 - metrics2.stringWidth(c.getHealth()+"")/2) , CARD_HEIGHT*5/6+metrics2.getHeight()/2);//draw health
            
            BufferedImage portrait = getCardPortrait(c.getTitle());
            g.drawImage(portrait,null,CARD_WIDTH/2-portrait.getWidth()/2,CARD_HEIGHT*8/19-portrait.getHeight()/2);//draw portrait
            
            BufferedImage cost = getCostIndicator(c.cost);
            g.drawImage(cost,null,(CARD_WIDTH-cost.getWidth()),CARD_HEIGHT/13);//draw cost indicator
            // g.drawImage(cost,null,0,0);//draw cost indicator
            
            return cardImage;
        }
        
    }

    public BufferedImage getCardPortrait(String name){
        BufferedImage portrait = cardPortraits.get(name.toUpperCase());
        return portrait==null? emptyImage:portrait;
    }
    public BufferedImage getCostIndicator(int cost){
        if(cost<costs.length){
            return costs[cost];
        }
        return emptyImage;
    }
    
    public void importPortraits(String folderPath){
        
        try{
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();
            
            for (int i = 0; i < listOfFiles.length; i++) {
                try{
                    if (listOfFiles[i].isFile()) {
                        // System.out.println("File " + listOfFiles[i].getName());
                        String fileName = listOfFiles[i].getName();
                        fileName = (String)fileName.subSequence("portait_".length()+1, fileName.length()-".png".length());

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
                }catch(Exception e){
                    e.printStackTrace();
                }
                
     
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void importCosts(String folderPath){
        
        try{
            File folder = new File(folderPath);
            File[] listOfFiles = folder.listFiles();
            
            for (int i = 0; i < listOfFiles.length; i++) {
                try{
                    if (listOfFiles[i].isFile()) {
                        String fileName = listOfFiles[i].getName();
                        // System.out.println(fileName);
                        // System.out.println("cost_.length()+1 = "+"cost_".length()+1+"fileName.length()-blood.png.length() = "+(fileName.length()-"blood.png".length()));
                        
                        fileName = (String)fileName.subSequence("cost_".length(), "cost_".length()+1);
                        
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
                }catch(Exception e){
                    e.printStackTrace();
                }
                
     
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setField(Field newField) {
        this.currentField = newField;
        // currentField.bulkInit();
    }
}



// BufferedImage awtImage = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
// Graphics g = awtImage.getGraphics();
// drawPanel.printAll(g);