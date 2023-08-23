
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


public class GuiFieldPicker {
    private static String bckColor = "#181818";
    private static String butColor = "#710C04";
    private static String txtColor = "#DDDDDD";
    JFrame frame;
    public volatile Field field = null;
    private volatile boolean ready = false;


    public GuiFieldPicker(int score){



        frame = new JFrame();

        ////////////////////////////////////////////////////
        // Main panel
        ////////////////////////////////////////////////////

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.setBackground(Color.decode(bckColor));
        panel.setLayout(new GridLayout(0, 1));


        ////////////////////////////////////////////////////
        // Info panel
        ////////////////////////////////////////////////////

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0, 1));
        infoPanel.setBackground(Color.decode(bckColor));
        infoPanel.setForeground(Color.decode(txtColor));
        
        String title;
        String info;

        if(score == 0){
            title = "Server Initializing:";
            info = "Select field and press Confirm after all clients have been connected";
        }
        else{
            title = "Last Round's Winner:";
            info = score<0?"Leshy: ("+score+")":"Player: ("+score+")";
        }

        TitledBorder panelBorder = BorderFactory.createTitledBorder(title);
        infoPanel.setBorder(panelBorder);
        panelBorder.setTitleColor(Color.decode(txtColor));
        infoPanel.setPreferredSize(new Dimension(300, 80));
        
        JTextArea infoText = new JTextArea(info);
        infoText.setForeground(Color.decode(txtColor));
        infoPanel.add(infoText);
        infoText.setBackground(Color.decode(bckColor));
        infoText.setLineWrap(true);

        panel.add(infoPanel);
        ////////////////////////////////////////////////////
        // Selection panel
        ////////////////////////////////////////////////////
        
        JPanel selPanel = new JPanel();
        selPanel.setLayout(new GridLayout(0, 2));
        selPanel.setBackground(Color.decode(bckColor));
        selPanel.setForeground(Color.decode(txtColor));
 
        
        String[] options = new String[FieldPresets.values().length];
        for(int i = 0; i<FieldPresets.values().length; i++){
            options[i] = FieldPresets.values()[i].name();
        }
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setBackground(Color.decode(butColor));
        dropdown.setForeground(Color.decode(txtColor));
        selPanel.add(dropdown);


        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.decode(butColor));
        confirmButton.setForeground(Color.decode(txtColor));
        selPanel.add(confirmButton);
        
        confirmButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    setField(FieldPresets.values()[dropdown.getSelectedIndex()]);
                }
            }
        );
        panel.add(selPanel);

        ////////////////////////////////////////////////////
        // Assemble frame
        ////////////////////////////////////////////////////
        
        // panel.add(Box.createRigidArea(new Dimension(0, 1))); // Add spacing between sections
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Match Picker GUI");
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }








    void setField(FieldPresets x){
        switch (x) {
            case Game_1_0:
                this.field = new Game1();
                break;
            case Game_1_1:
                this.field = new Game11();
                break;
            case Game_1_2:
                this.field = new Game12();
                break;
            case Game_1_3:
                this.field = new Game13();
                break;
            case Game_1_4:
                this.field = new Game14();
                break;
            case Game_2_0:
                this.field = new Game2();
                break;
            case Game_2_1:
                this.field = new Game21();
                break;
            case Game_2_2:
                this.field = new Game22();
                break;
            case Game_2_3:
                this.field = new Game23();
                break;
            case Game_2_4:
                this.field = new Game24();
                break;
            case Game_3_0:
                this.field = new Game3();
                break;
            case Game_3_1:
                this.field = new Game31();
                break;
            case Game_3_2:
                this.field = new Game32();
                break;
            case Game_3_3:
                this.field = new Game33();
                break;
            case Game_3_4:
                this.field = new Game34();
                break;
            case Game_4_0:
                this.field = new Game4();
                break;
            case Game_4_1:
                this.field = new Game41();
                break;
            case Game_4_2:
                this.field = new Game42();
                break;
            case Game_4_3:
                this.field = new Game43();
                break;
            case Game_4_4:
                this.field = new Game44();
                break;
            case TonyGame1:
                this.field = new TonyGame1();
                break;
            case TonyGame2:
                this.field = new TonyGame2();
                break;
            case TonyGame3:
                this.field = new TonyGame3();
                break;
            case TonyGame4:
                this.field = new TonyGame4();
                break;
                case TonyGame5:
                    this.field = new TonyGame5();
                    break;
                    case ParkerGame5:
                        this.field = new ParkerGame5();
                        break;
            default:
                this.field = null;
                System.out.println("Something is wrong with the field picker...");
                break;
         }
         
        this.ready = this.field!=null;

    }

    public boolean waiting() {
        return !this.ready;
    }

    public Field getField() {
        return this.field;
    }
    // public void destroyinate(){
    //     this = null;
    // }

    public void close() {
        // frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        frame.dispose();;
    }

}

enum FieldPresets {
    TonyGame1,
    TonyGame2,
    TonyGame3,
    TonyGame4,
    TonyGame5,
    ParkerGame5,
    Game_1_0, 
    Game_1_1, 
    Game_1_2, 
    Game_1_3, 
    Game_1_4, 
    Game_2_0, 
    Game_2_1, 
    Game_2_2, 
    Game_2_3, 
    Game_2_4, 
    Game_3_0, 
    Game_3_1, 
    Game_3_2, 
    Game_3_3, 
    Game_3_4, 
    Game_4_0, 
    Game_4_1, 
    Game_4_2, 
    Game_4_3, 
    Game_4_4
}