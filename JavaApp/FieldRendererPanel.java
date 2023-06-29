
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

public class FieldRendererPanel extends JPanel implements ActionListener {
    

    static final int SCREEN_WIDTH = 1920;

    static final int SCREEN_HEIGHT = 1080;

    static final int CARD_WIDTH = 125;

    static final int CARD_HEIGHT = 190;

    static final int DELAY = 15;
    static final int GAP = 15;

    Timer timer;


    // BufferedImage fieldImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
    BufferedImage fieldImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);
    // Graphics fieldCanvas = fieldImage.getGraphics();
    Graphics2D fieldCanvas = (Graphics2D)fieldImage.getGraphics();


    private BufferedImage emptyImage = new BufferedImage(100, 10, BufferedImage.TYPE_INT_RGB);

    HashMap<String,BufferedImage> cardPortraits = new HashMap<>();
    BufferedImage cardBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    BufferedImage[] costs = {emptyImage,
                            emptyImage,
                            emptyImage,
                            emptyImage,
                            emptyImage};
    Card testCard0 = new Card("Stoat");
    Card testCard1 = new Card("mole");
    Card testCard2 = new Card("moose");
    Card testCard3 = new Card("Spud");
    


    private int rotation = 0;
    
    Font fontHeavyWeight;
    Font fontHeavyWeight_Stat;


    FieldRendererPanel() {

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
        testCard0.setCost(1);
        testCard1.setCost(2);
        testCard2.setCost(3);
        testCard3.setCost(0);

        testCard2.setAttack(3);
        testCard0.setAttack(1);
        testCard0.setAttack(2);

        testCard2.setHealth(2);
        testCard0.setHealth(3);
        testCard3.setHealth(4);
    
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
 
         fieldCanvas.drawString("Score: rotation*Math.PI/180.0 = " + rotation*Math.PI/180.0, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + 7)) / 2,
         fieldCanvas.getFont().getSize());
 
         // Game Over text
 
         fieldCanvas.setColor(Color.red);
 
        //  fieldCanvas.setFont(new Font("Ink Free", Font.BOLD, 75));
         fieldCanvas.setFont(fontHeavyWeight);
 
         FontMetrics metrics2 = getFontMetrics(fieldCanvas.getFont());
 
         fieldCanvas.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);


         AffineTransform rTransform = new AffineTransform();
        //  rTransform.rotate(rotation*DELAY*Math.PI/180000.0,SCREEN_WIDTH/2.0, SCREEN_HEIGHT/2.0);

        //  ((Graphics2D)fieldCanvas).transform(rTransform);
         fieldCanvas.transform(rTransform);
         fieldCanvas.drawImage(renderCard(testCard0), null, SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(testCard1), null, (CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(null), null, 2*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(testCard2), null, 3*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(testCard3), null, 4*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
        //  fieldImage.
         g2d.drawImage(fieldImage, null, 0,0);

    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();//every DELAY ms draw new frame?
        rotation++;
    }

    public BufferedImage renderCard(Card c){
        
        BufferedImage cardImage = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
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
                        System.out.println(fileName);
                        System.out.println("cost_.length()+1 = "+"cost_".length()+1+"fileName.length()-blood.png.length() = "+(fileName.length()-"blood.png".length()));
                        
                        fileName = (String)fileName.subSequence("cost_".length(), "cost_".length()+1);
                        
                        System.out.println(fileName);
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
}



// BufferedImage awtImage = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
// Graphics g = awtImage.getGraphics();
// drawPanel.printAll(g);