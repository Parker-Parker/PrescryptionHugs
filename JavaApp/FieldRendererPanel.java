
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FieldRendererPanel extends JPanel implements ActionListener {
    

    static final int SCREEN_WIDTH = 1920;

    static final int SCREEN_HEIGHT = 1080;

    static final int UNIT_SIZE = 50;

    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    static final int DELAY = 175;

    Timer timer;

    FieldRendererPanel() {
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
         // Score

         g.setColor(Color.red);

         g.setFont(new Font("Ink Free", Font.BOLD, 40));
 
         FontMetrics metrics1 = getFontMetrics(g.getFont());
 
         g.drawString("Score: " + 7, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + 7)) / 2,
                 g.getFont().getSize());
 
         // Game Over text
 
         g.setColor(Color.red);
 
         g.setFont(new Font("Ink Free", Font.BOLD, 75));
 
         FontMetrics metrics2 = getFontMetrics(g.getFont());
 
         g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();//every DELAY ms draw new frame?
    }

}
