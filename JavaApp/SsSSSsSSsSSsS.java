import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import java.util.Arrays;
import java.util.LinkedList;

public class SsSSSsSSsSSsS implements ActionListener {

    private JLabel label;
    private JLabel sacInfo;
    private JFrame frame;
    private JPanel panel;
    private GridLayout grid;
    private GridLayout selGrid;
    boolean available;
    static String playerCommand;
    static String viewedText;
    private JComboBox<String> dropdown;
    Object selDrop;
    int selDropLen;
    String selDropIndex;
    private static String bckColor = "#181818";
    private static String butColor = "#710C04";
    private static String txtColor = "#DDDDDD";

    public SsSSSsSSsSSsS() {

                        frame = new JFrame();



                        selGrid = new GridLayout(0, 2);

        label = new JLabel("");
        label.setForeground(Color.decode(txtColor));






                                                                        panel = new JPanel();
                                                                        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
                                                                        panel.setBackground(Color.decode(bckColor));
                                                                        panel.setLayout(grid);
                                                                // Create the dropdown list
                                                                    LinkedList<Card> mDeckSacCopy = new LinkedList<>(DillDillServer.mDeck);
                                                                    String[] options = new String[mDeckSacCopy.size()];

                                                                    for (int i = 0; i < mDeckSacCopy.size(); i++) {
                                                                        Card card = mDeckSacCopy.get(i);
                                                                        String name = card.title;
                                                                        int sacrificeCost = card.cost;
                                                                        options[i] = i + ": " + name + " (Sacrifice Cost: " + sacrificeCost + ")";
                                                                    }
                                                                    //////// TODO populate mDeck with what tony has for cards
                                                                    dropdown = new JComboBox<>(options);
                                                                    dropdown.setBackground(Color.decode(butColor));
                                                                    dropdown.setForeground(Color.decode(txtColor));

                                                                // Create the confirm button
                                                                            JButton confirmButton = new JButton("Confirm");
                                                                            confirmButton.setBackground(Color.decode(butColor));
                                                            confirmButton.setForeground(Color.decode(txtColor));

                                                ////////////////////////////////////////////////////////////////////
                                                // panel.add(sacrificeSummonPanel);
                                                            ////////////////////////////////////////////////
                                                            JPanel selPanel = new JPanel();
                                                            selPanel.setLayout(selGrid);
                                                            selPanel.setBackground(Color.decode(bckColor));
                                                            selPanel.setForeground(Color.decode(txtColor));
                                                            selPanel.add(dropdown);
                                                            selPanel.add(confirmButton);//TODO DEEZ NUTZ

                                                        confirmButton.addActionListener(this);


        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand1 = e.getActionCommand();
                if (dropdown.getSelectedItem().toString().charAt(selDropLen - 2) == '0') {
                    switch (actionCommand1) {
                        case "Direct Summon Here Column 0":
                            playerCommand = "user rdy 1 " + selDropIndex + " 0";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Direct Summon Here Column 1":
                            playerCommand = "user rdy 1 " + selDropIndex + " 1";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Direct Summon Here Column 2":
                            playerCommand = "user rdy 1 " + selDropIndex + " 2";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Direct Summon Here Column 3":
                            playerCommand = "user rdy 1 " + selDropIndex + " 3";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        default:
                            break;
                    }
                } else {

                }
            }
        };

        // panel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing between
        // sections
        //////////////////////////////////////////////////////////////////
        // Second Section - Sacrifice Which Card////////////////////////////

        ActionListener actionListener2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommand2 = e.getActionCommand();
                if (dropdown.getSelectedItem().toString().charAt(selDropLen - 2) != '0') {
                    switch (actionCommand2) {
                        case "Sacrifice This Card Column 0":
                            playerCommand = "user sac 1 0";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Sacrifice This Card Column 1":
                            playerCommand = "user sac 1 1";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Sacrifice This Card Column 2":
                            playerCommand = "user sac 1 2";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Sacrifice This Card Column 3":
                            playerCommand = "user sac 1 3";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        default:
                            break;
                    }
                } else {

                }
            }
        };


       
                                    panel.add(Box.createRigidArea(new Dimension(0, 1))); // Add spacing between sections
        
                                    frame.add(panel, BorderLayout.CENTER);
                                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                    frame.setTitle("Field Control GUI");
                                    frame.pack();
                                    frame.setVisible(true);
                                    frame.setAlwaysOnTop(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();
        JButton bText = (JButton) e.getSource();
        label.setText(bText.getText());

        switch (actionCommand) {
            case "Confirm":
                selDrop = dropdown.getSelectedItem();
                selDropLen = dropdown.getSelectedItem().toString().length();
                selDropIndex = selDrop.toString().substring(0, selDrop.toString().indexOf(":"));

                if (dropdown.getSelectedItem().toString().charAt(selDropLen - 2) == '0') {
                    viewedText = "Direct Summoning " + selDrop.toString();
                    sacInfo.setText(viewedText);
                    playerCommand = "";
                    available = false;
                    System.out.println(playerCommand);
                    break;
                } else {
                    viewedText = "Sacrificing " + selDrop.toString(); // + " Sacrifices Needed: " +
                                                                      // dropdown.getSelectedItem().toString().charAt(selDropLen
                                                                      // - 2);
                    sacInfo.setText(viewedText);
                    playerCommand = "user rdy 2 " + selDropIndex;
                    label.setText(playerCommand);
                    System.out.println(playerCommand);
                    available = true;
                    break;
                }
            case "Cancel Sacrifice":
                if (dropdown.getSelectedItem().toString().charAt(selDropLen - 2) != '0') {
                    playerCommand = "user sac 0";
                    viewedText = "Sacrifice Cancelled";
                    sacInfo.setText(viewedText);
                    label.setText(playerCommand);
                    available = true;
                    System.out.println(playerCommand);
                } else {

                }
                break;
            case "Player Draw Main":
                playerCommand = "user dra 1";
                label.setText(playerCommand);
                available = true;
                System.out.println(playerCommand);
                break;
            case "Player Draw Squirrel":
                playerCommand = "user dra 0";
                label.setText(playerCommand);
                available = true;
                System.out.println(playerCommand);
                break;
            case "Player Ready":
                playerCommand = "user rdy 0";
                label.setText(playerCommand);
                available = true;
                System.out.println(playerCommand);
                break;

            default:
                break;
        }
    }


}







