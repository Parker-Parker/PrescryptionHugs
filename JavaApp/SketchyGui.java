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

public class SketchyGui implements ActionListener {

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
    private static String butColor2 = "#295a86";
    private static String butColor3 = "#009b33";
    private static String txtColor = "#DDDDDD";

    public SketchyGui() {

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
        dropdown.addActionListener(this);

        ////////////////////////////////////////////////////////////////////
        // panel.add(sacrificeSummonPanel);
        JPanel selPanel = new JPanel();
        selPanel.setLayout(selGrid);
        selPanel.setBackground(Color.decode(bckColor));
        selPanel.setForeground(Color.decode(txtColor));
        selPanel.add(dropdown);
        // selPanel.add(confirmButton);

        // confirmButton.addActionListener(this);

        panel.add(selPanel);
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        panel.add(new CardPickerPanel(mDeckSacCopy, dropdown));
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////


        sacInfo = new JLabel("Sacrifices Needed: " + 0);
        sacInfo.setForeground(Color.decode(txtColor));


        ////////////////////////////////////////////////////////////////////
        // First Section - Direct Summon Cards//////////////////////////////

        ActionListener actionListenerDirectSummon = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommandDirectSummon = e.getActionCommand();
                switch (actionCommandDirectSummon) {
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
            }
        };

        ActionListener actionListenerDirectSummonSquirrel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommandDirectSummonSquirrel = e.getActionCommand();
                switch (actionCommandDirectSummonSquirrel) {
                    case "Squirrel Here 0":
                        playerCommand = "user rdy 1 0 0";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    case "Squirrel Here 1":
                        playerCommand = "user rdy 1 0 1";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    case "Squirrel Here 2":
                        playerCommand = "user rdy 1 0 2";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    case "Squirrel Here 3":
                        playerCommand = "user rdy 1 0 3";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    default:
                        break;
                }
            }
        };

        ActionListener actionListenerKill = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actionCommandKill = e.getActionCommand();
                switch (actionCommandKill) {
                    case "Kill Here Column 0":
                        playerCommand = "user rdy 2 0 0";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    case "Kill Here Column 1":
                        playerCommand = "user rdy 2 1 0";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    case "Kill Here Column 2":
                        playerCommand = "user rdy 2 2 0";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    case "Kill Here Column 3":
                        playerCommand = "user rdy 2 3 0";
                        label.setText(playerCommand);
                        available = true;
                        System.out.println(playerCommand);
                        break;
                    default:
                        break;
                }
            }
        };

        JPanel directSummonPanel = createSectionPanelDirectSummon("Direct Summon", 4, "Direct Summon Here", 0, actionListenerDirectSummon);
        directSummonPanel.setBackground(Color.decode(bckColor));
        directSummonPanel.setForeground(Color.decode(txtColor));
        panel.add(directSummonPanel);

        JPanel squirrelPanel = createSectionPanelDirectSquirrel("Squirrel", 4, "Squirrel Here", 0, actionListenerDirectSummonSquirrel);
        squirrelPanel.setBackground(Color.decode(bckColor));
        squirrelPanel.setForeground(Color.decode(txtColor));
        panel.add(squirrelPanel);

        JPanel killPanel = createSectionPanelKill("Kill", 4, "Kill Here", 0, actionListenerKill);
        killPanel.setBackground(Color.decode(bckColor));
        killPanel.setForeground(Color.decode(txtColor));
        panel.add(killPanel);

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

    // public static void main(String[] args) {
    //     new SimpleGui();
    // }

    @Override
    public void actionPerformed(ActionEvent e) {

        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "comboBoxChanged":
                selDrop = dropdown.getSelectedItem();
                selDropLen = dropdown.getSelectedItem().toString().length();
                selDropIndex = selDrop.toString().substring(0, selDrop.toString().indexOf(":"));

                // if (dropdown.getSelectedItem().toString().charAt(selDropLen - 2) == '0') {
                viewedText = "Direct Summoning " + selDrop.toString();
                sacInfo.setText(viewedText);
                playerCommand = "";
                available = false;
                System.out.println(playerCommand);
                break;
                // } else {
                //     viewedText = "Sacrificing " + selDrop.toString(); // + " Sacrifices Needed: " +
                //                                                       // dropdown.getSelectedItem().toString().charAt(selDropLen
                //                                                       // - 2);
                //     sacInfo.setText(viewedText);
                //     playerCommand = "user rdy 1 " + selDropIndex;
                //     label.setText(playerCommand);
                //     System.out.println(playerCommand);
                //     available = true;
                //     break;
                // }
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
    private static JPanel createSectionPanelDirectSummon(String label, int buttonCount, String buttonLabel, int columnLabel,
            ActionListener actionListenerDirectSummon) {
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
            button.setBackground(Color.decode(butColor2));
            button.setForeground(Color.decode(txtColor));
            panel.add(button);
            button.addActionListener(actionListenerDirectSummon);
        }

        return panel;
    }

    // Helper method to create a section panel with specified label, button count,
    // and button label
    private static JPanel createSectionPanelDirectSquirrel(String label, int buttonCount, String buttonLabel, int columnLabel,
            ActionListener actionListenerDirectSummonSquirrel) {
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
            JButton button = new JButton(buttonLabel + " " + i);
            button.setBackground(Color.decode(butColor3));
            button.setForeground(Color.decode(txtColor));
            panel.add(button);
            button.addActionListener(actionListenerDirectSummonSquirrel);
        }
        return panel;
    }

    private static JPanel createSectionPanelKill(String label, int buttonCount, String buttonLabel, int columnLabel,
            ActionListener actionListenerKill) {
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
            button.addActionListener(actionListenerKill);
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
