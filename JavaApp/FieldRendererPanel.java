
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    BufferedImage cardBase = new BufferedImage(CARD_WIDTH, CARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
    BufferedImage[] costs = {new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB),
                            new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB),
                            new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB),
                            new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB)};
    Card testCard = new Card("Stoat");
    


    private int rotation = 0;
    
    Font fontHeavyWeight;

    FieldRendererPanel() {

        try{
            File font_file = new File("JavaApp/resources/HEAVYWEI.TTF");
            fontHeavyWeight = Font.createFont(Font.TRUETYPE_FONT, font_file).deriveFont(27f);
        }
        catch(Exception e){
            e.printStackTrace();
            fontHeavyWeight = Font.getFont("Comic Sans");
        }

        try {
            cardBase = ImageIO.read(new File("JavaApp/resources/card_empty.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    



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
         fieldCanvas.drawImage(renderCard(testCard), null, SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(testCard), null, (CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(null), null, 2*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
         fieldCanvas.drawImage(renderCard(testCard), null, 3*(CARD_WIDTH+GAP)+SCREEN_WIDTH/3, SCREEN_HEIGHT/3);
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
            g.setFont(fontHeavyWeight);
            g.drawImage(cardBase,null,0,0);//draw background

            g.setColor(Color.BLACK);
            g.setFont(fontHeavyWeight);
            FontMetrics metrics = getFontMetrics(fieldCanvas.getFont());
            g.drawString(c.getTitle(), (CARD_WIDTH - metrics.stringWidth(c.getTitle())) / 2, metrics.getHeight());//draw title

            g.drawString(" "+c.getAttack(), metrics.stringWidth(c.getAttack()+"")/2, CARD_HEIGHT-metrics.getHeight());//draw attack
            g.drawString(c.getHealth()+" ", (CARD_WIDTH - metrics.stringWidth(c.getHealth()+" ")*4/3) , CARD_HEIGHT-metrics.getHeight()*3/4);//draw health

            return cardImage;
        }
        
    }

}



// BufferedImage awtImage = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
// Graphics g = awtImage.getGraphics();
// drawPanel.printAll(g);