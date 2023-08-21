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

public class GuiTest implements ActionListener {

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

    public GuiTest() {

        frame = new JFrame();

        JButton drawMainButton = new JButton("Player Draw Main");
        drawMainButton.setBackground(Color.decode(butColor));
        drawMainButton.setForeground(Color.decode(txtColor));
        JButton drawSquirrelButton = new JButton("Player Draw Squirrel");
        drawSquirrelButton.setBackground(Color.decode(butColor));
        drawSquirrelButton.setForeground(Color.decode(txtColor));
        JButton readyButton = new JButton("Player Ready");
        readyButton.setBackground(Color.decode(butColor));
        readyButton.setForeground(Color.decode(txtColor));

        JButton cancelSacButton = new JButton("Cancel Sacrifice");
        cancelSacButton.setBackground(Color.decode(butColor));
        cancelSacButton.setForeground(Color.decode(txtColor));

        grid = new GridLayout(0, 1);
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
        JPanel selPanel = new JPanel();
        selPanel.setLayout(selGrid);
        selPanel.setBackground(Color.decode(bckColor));
        selPanel.setForeground(Color.decode(txtColor));
        selPanel.add(dropdown);
        selPanel.add(confirmButton);

        confirmButton.addActionListener(this);

        panel.add(selPanel);
        sacInfo = new JLabel("Sacrifices Needed: " + 0); ////////// TODO link 0 with confirm button click with the
                                                         ////////// actual sac cost of each of the cards in mdeck
                                                         ////////// hashmap? array list?
        sacInfo.setForeground(Color.decode(txtColor));
        JPanel sacNeededPanel = new JPanel();
        sacNeededPanel.setLayout(grid);
        sacNeededPanel.setBackground(Color.decode(bckColor));
        sacNeededPanel.setForeground(Color.decode(txtColor));
        TitledBorder sumInfoBorder = BorderFactory.createTitledBorder("Summoning Info");
        sacNeededPanel.setBorder(sumInfoBorder);
        sumInfoBorder.setTitleColor(Color.decode(txtColor));
        sacNeededPanel.add(sacInfo);
        panel.add(sacNeededPanel);

        // panel.add(Box.createRigidArea(new Dimension(0, 1))); // Add spacing between
        // sections
        ////////////////////////////////////////////////////////////////////
        // First Section - Direct Summon Cards//////////////////////////////

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

        JPanel directSummonPanel = createSectionPanel1("Direct Summon", 4, "Direct Summon Here", 1, actionListener1);
        directSummonPanel.setBackground(Color.decode(bckColor));
        directSummonPanel.setForeground(Color.decode(txtColor));
        panel.add(directSummonPanel);

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

        JPanel sacPanel = new JPanel();
        sacPanel.setLayout(grid);
        sacPanel.setBackground(Color.decode(bckColor));
        sacPanel.setForeground(Color.decode(txtColor));
        TitledBorder sacPanelBorder = BorderFactory.createTitledBorder("Sacrifice");
        sacPanel.setBorder(sacPanelBorder);
        sacPanelBorder.setTitleColor(Color.decode(txtColor));
        JPanel sacrificeWhichCardPanel = createSectionPanel2("Sacrifice Which Cards", 4, "Sacrifice This Card", 0,
                actionListener2);
        sacrificeWhichCardPanel.setBackground(Color.decode(bckColor));
        sacrificeWhichCardPanel.setForeground(Color.decode(txtColor));
        sacPanel.add(sacrificeWhichCardPanel);

        ////////////////////////////////////////////////////////////////////
        // Third Section - Sacrifice Summon Here////////////////////////////

        ActionListener actionListener3 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String actionCommand3 = e.getActionCommand();
                if (dropdown.getSelectedItem().toString().charAt(selDropLen - 2) != '0') {
                    switch (actionCommand3) {
                        case "Sac Summon Here Column 0":
                            playerCommand = "user sum 0";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Sac Summon Here Column 1":
                            playerCommand = "user sum 1";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Sac Summon Here Column 2":
                            playerCommand = "user sum 2";
                            label.setText(playerCommand);
                            available = true;
                            System.out.println(playerCommand);
                            break;
                        case "Sac Summon Here Column 3":
                            playerCommand = "user sum 3";
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

        JPanel sacrificeSummonHerePanel = createSectionPanel3("Summon From Sacrifices", 4, "Sac Summon Here", 0,
                actionListener3);
        sacrificeSummonHerePanel.setBackground(Color.decode(bckColor));
        sacrificeSummonHerePanel.setForeground(Color.decode(txtColor));
        sacPanel.add(sacrificeSummonHerePanel);

        sacPanel.add(cancelSacButton);
        cancelSacButton.addActionListener(this);

        panel.add(sacPanel);

        ////////////////////////////////////////////////////////////////////
        // Bottom Buttons///////////////////////////////////////////////////

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.decode(bckColor));
        bottomPanel.setForeground(Color.decode(txtColor));

        ////////////////////////////////////////////////////////////////////

        panel.add(Box.createRigidArea(new Dimension(0, 1))); // Add spacing between sections
        bottomPanel.add(drawMainButton);
        bottomPanel.add(drawSquirrelButton);
        bottomPanel.add(readyButton);

        drawMainButton.addActionListener(this);
        drawSquirrelButton.addActionListener(this);
        readyButton.addActionListener(this);

        panel.add(bottomPanel);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Field Control GUI");
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);

    }

    public static void main(String[] args) {
        new GuiTest();
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

    // Helper method to create a section panel with specified label, button count,
    // and button label
    private static JPanel createSectionPanel1(String label, int buttonCount, String buttonLabel, int columnLabel,
            ActionListener actionListener1) {
        int rowcount;

        if (columnLabel == 1) {
            rowcount = 2;
        } else {
            rowcount = 1;
        }

        JPanel panel = new JPanel(new GridLayout(rowcount, buttonCount));
        TitledBorder panelBorder = BorderFactory.createTitledBorder(label);
        panel.setBorder(panelBorder);
        panelBorder.setTitleColor(Color.decode(txtColor));
        panel.setPreferredSize(new Dimension(300, 80));
        if (columnLabel == 1) {

            for (int i = 0; i < buttonCount; i++) {
                JLabel labelComponent = new JLabel(Integer.toString(i), SwingConstants.CENTER);
                labelComponent.setForeground(Color.decode(txtColor));
                panel.add(labelComponent);
            }
        }

        for (int i = 0; i < buttonCount; i++) {
            JButton button = new JButton(buttonLabel + " Column " + i);
            button.setBackground(Color.decode(butColor));
            button.setForeground(Color.decode(txtColor));
            panel.add(button);
            button.addActionListener(actionListener1);
        }

        return panel;
    }

    private static JPanel createSectionPanel2(String label, int buttonCount, String buttonLabel, int columnLabel,
            ActionListener actionListener2) {
        int rowcount;

        if (columnLabel == 1) {
            rowcount = 2;
        } else {
            rowcount = 1;
        }

        JPanel panel = new JPanel(new GridLayout(rowcount, buttonCount));
        TitledBorder panelBorder = BorderFactory.createTitledBorder(label);
        panel.setBorder(panelBorder);
        panelBorder.setTitleColor(Color.decode(txtColor));
        panel.setPreferredSize(new Dimension(300, 80));
        if (columnLabel == 1) {

            for (int i = 0; i < buttonCount; i++) {
                JLabel labelComponent = new JLabel(Integer.toString(i), SwingConstants.CENTER);
                panel.add(labelComponent);
            }
        }

        for (int i = 0; i < buttonCount; i++) {
            JButton button = new JButton(buttonLabel + " Column " + i);
            button.setBackground(Color.decode(butColor));
            button.setForeground(Color.decode(txtColor));
            panel.add(button);
            button.addActionListener(actionListener2);
        }

        return panel;
    }

    private static JPanel createSectionPanel3(String label, int buttonCount, String buttonLabel, int columnLabel,
            ActionListener actionListener3) {
        int rowcount;

        if (columnLabel == 1) {
            rowcount = 2;
        } else {
            rowcount = 1;
        }

        JPanel panel = new JPanel(new GridLayout(rowcount, buttonCount));
        TitledBorder panelBorder = BorderFactory.createTitledBorder(label);
        panel.setBorder(panelBorder);
        panelBorder.setTitleColor(Color.decode(txtColor));
        panel.setPreferredSize(new Dimension(300, 80));
        if (columnLabel == 1) {

            for (int i = 0; i < buttonCount; i++) {
                JLabel labelComponent = new JLabel(Integer.toString(i), SwingConstants.CENTER);
                panel.add(labelComponent);
            }
        }

        for (int i = 0; i < buttonCount; i++) {
            JButton button = new JButton(buttonLabel + " Column " + i);
            button.setBackground(Color.decode(butColor));
            button.setForeground(Color.decode(txtColor));
            panel.add(button);
            button.addActionListener(actionListener3);
        }

        return panel;
    }

    public boolean commandAvailable() {
        return this.available;
    }

    public String getCommand() {
        available = false;
        return playerCommand;
    }

}
