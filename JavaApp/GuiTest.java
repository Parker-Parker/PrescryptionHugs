import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GuiTest implements ActionListener{
    
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private GridLayout grid;
    boolean available;
    static String playerCommand;


    public GuiTest() {

        frame = new JFrame();

        JButton drawMainButton = new JButton("Player Draw Main");
        JButton drawSquirrelButton = new JButton("Player Draw Squirrel");
        JButton readyButton = new JButton("Player Ready");
        JButton sacrificeButton = new JButton("Player Sacrifice");
        JButton cancelSacButton = new JButton("Cancel Sacrifice");
        // JButton null2Button = new JButton("");

        //column 0
        // JButton Button3 = new JButton("Other");
        // JButton Button4 = new JButton("Other");
        // JButton Button5 = new JButton("Other");

        // JButton Button6 = new JButton("Other");
        // JButton Button7 = new JButton("Other");
        // JButton Button8 = new JButton("Other");

        JButton sacThisButton0 = new JButton("Sacrifice This Column 0");
        JButton placeCardButton0 = new JButton("Place Card Here Column 0");
        //JButton Button11 = new JButton("Other");

        //column 1
        // JButton Button31 = new JButton("Other");
        // JButton Button41 = new JButton("Other");
        // JButton Button51 = new JButton("Other");

        // JButton Button61 = new JButton("Other");
        // JButton Button71 = new JButton("Other");
        // JButton Button81 = new JButton("Other");

        JButton sacThisButton1 = new JButton("Sacrifice This Column 1");
        JButton placeCardButton1 = new JButton("Place Card Here Column 1");
        //JButton Button11 = new JButton("Other");

        //column 2
        // JButton Button32 = new JButton("Other");
        // JButton Button42 = new JButton("Other");
        // JButton Button52 = new JButton("Other");

        // JButton Button62 = new JButton("Other");
        // JButton Button72 = new JButton("Other");
        // JButton Button82 = new JButton("Other");

        JButton sacThisButton2 = new JButton("Sacrifice This Column 2");
        JButton placeCardButton2 = new JButton("Place Card Here Column 2");
        //JButton Button11 = new JButton("Other");

        //column 3
        // JButton Button33 = new JButton("Other");
        // JButton Button43 = new JButton("Other");
        // JButton Button53 = new JButton("Other");

        // JButton Button63 = new JButton("Other");
        // JButton Button73 = new JButton("Other");
        // JButton Button83 = new JButton("Other");

        JButton sacThisButton3 = new JButton("Sacrifice This Column 3");
        JButton placeCardButton3 = new JButton("Place Card Here Column 3");
        //JButton Button11 = new JButton("Other");

        drawMainButton.addActionListener(this);
        drawSquirrelButton.addActionListener(this);
        readyButton.addActionListener(this);
        sacrificeButton.addActionListener(this);
        cancelSacButton.addActionListener(this);
        sacThisButton0.addActionListener(this);
        sacThisButton1.addActionListener(this);
        sacThisButton2.addActionListener(this);
        sacThisButton3.addActionListener(this);
        placeCardButton0.addActionListener(this);
        placeCardButton1.addActionListener(this);
        placeCardButton2.addActionListener(this);
        placeCardButton3.addActionListener(this);

        
        grid = new GridLayout(4, 4);

        label = new JLabel("");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setLayout(grid);
        // panel.add(null2Button);
        panel.add(sacThisButton0);
        panel.add(sacThisButton1);
        panel.add(sacThisButton2);
        panel.add(sacThisButton3);
        panel.add(placeCardButton0);
        panel.add(placeCardButton1);
        panel.add(placeCardButton2);
        panel.add(placeCardButton3);
        panel.add(drawMainButton);
        panel.add(drawSquirrelButton);
        panel.add(readyButton);
        panel.add(sacrificeButton);
        panel.add(cancelSacButton);
        panel.add(label);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Field Control GUI");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        new GuiTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String actionCommand = e.getActionCommand();
        int cardIndexInHand = 9;        /////////////TODO: have this correspond to his hand and not just be 9 gosh
        JButton bText = (JButton) e.getSource();
        label.setText(bText.getText());        

        switch (actionCommand) {
            case "Player Draw Main":
                playerCommand = "user dra 1";
                label.setText(playerCommand);
                available = true;
                break;
            case "Player Draw Squirrel":
                playerCommand = "user dra 0";
                label.setText(playerCommand);
                available = true;
                break;
            case "Player Ready":
                playerCommand = "user rdy 0";
                label.setText(playerCommand);
                available = true;
                break;
            case "Player Sacrifice":
                playerCommand = "user rdy 2 " + cardIndexInHand;
                label.setText(playerCommand);
                available = true;
                break;
            case "Cancel Sacrifice":
                playerCommand = "user sac 0";
                label.setText(playerCommand);
                available = true;
                break;    
            case "Place Card Here Column 0":
                playerCommand = "user rdy 1 " + cardIndexInHand + " 0";
                label.setText(playerCommand);
                available = true;
                break;
            case "Sacrifice This Column 0":
                playerCommand = "user sac 1 0";
                label.setText(playerCommand);
                available = true;
                break;
            case "Place Card Here Column 1":
                playerCommand = "user rdy 1 " + cardIndexInHand + " 1";
                label.setText(playerCommand);
                available = true;
                break;
            case "Sacrifice This Column 1":
                playerCommand = "user sac 1 1";
                label.setText(playerCommand);
                available = true;
                break;
            case "Place Card Here Column 2":
                playerCommand = "user rdy 1 " + cardIndexInHand + " 2";
                label.setText(playerCommand);
                available = true;
                break;
            case "Sacrifice This Column 2":
                playerCommand = "user sac 1 2";
                label.setText(playerCommand);
                available = true;
                break;
            case "Place Card Here Column 3":
                playerCommand = "user rdy 1 " + cardIndexInHand + " 3";
                label.setText(playerCommand);
                available = true;
                break;
            case "Sacrifice This Column 3":
                playerCommand = "user sac 1 3";
                label.setText(playerCommand);
                available = true;
                break;

            default:
                break;
        }
    }

    public boolean commandAvailable() {
        return this.available;
    }

    public String getCommand() {
        available = false;
        return playerCommand;
    }
}
