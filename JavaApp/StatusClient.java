
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
import javax.swing.Timer;
import javax.swing.border.TitledBorder;


import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.JFrame;

public class StatusClient {


    private static final int TIMER_DELAY = 750;
    volatile static TurnState state= TurnState.interruptEvent;
    volatile static int scale = 0;
    static int[] statCodes = {0,0};
    private static String lastText = "";

    public static void main(String[] args) throws Exception {

        // new GameFrame();

        // String hostName = "localhost";
        // int port = 5433;
        // parse args?

        ObserverOutputHandler observerParser = new ObserverOutputHandler();

        // JFrame frame = setupGameFrame();
        Ipguistarter IPGUIfield = new Ipguistarter("Field Client IP and Port Connection");

        System.out.println("Waiting to connect");

        while (IPGUIfield.connectionReady != true) {
            // System.out.println("no");
        }

        String hostName = IPGUIfield.getipaddress();
        int port = IPGUIfield.getport();

        System.out.println(hostName + " " + port);

        Socket clientSocket = new Socket(hostName, port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());

        outToServer.writeBytes("SimpleStatus" + '\n');// cONFIG STRING //change this to renderer specific once implemented
        outToServer.flush();

        String fieldString = "";


        IPGUIfield.close();
        setupFrame();



        String statusString = "";
        
        while (true) {

            // if(inFromServer.hasNextLine()){
            if (clientSocket.getInputStream().available() > 0) {
                // sentence = inFromUser.readLine();

                fieldString = inFromServer.nextLine();
                // outToServer.writeBytes(sentence + '\n');
                // observerParser.deserializeField(field,
                // observerParser.deserializeTopics(fieldString));
                // System.out.println(fieldString);

                // field.printField();
                // panel.setField(field);
                // panel.addToQueue(observerParser.deserializeTopics(fieldString));
                // statusString = observerParser.deserializeTopics(fieldString).get(ObserverTopics.MatchState);
                try{
                    statusString = observerParser.deserializeTopics(fieldString).get(ObserverTopics.MatchState);
                    statCodes = observerParser.deserializeMatchState(statusString)==null?statCodes:observerParser.deserializeMatchState(statusString);
                    // state = getStateFromOrd( statCodes[0]);
                    // scale =  statCodes[1];

                }
                catch(Exception e){
                    e.printStackTrace();
                    statCodes = new int[2];
                    statCodes[0] = 0;
                    statCodes[1] = 0;
                }
                state = getStateFromOrd( statCodes[0]);
                scale =  statCodes[1];

                
            }
            
            // updateText("State: "+state+"\nScale: "+getScaleText(scale));

        }

    }


    private static void updateText(String infoString) {
        if(infoText!=null&&!lastText.equals(infoString)){
            infoText.setText(infoString);
            lastText = infoString;
        }
    
    }
    

    private static TurnState getStateFromOrd(int ordinal){
        int i = 0;
        for(TurnState s : TurnState.values()){//TTODO: sloppy, relies on ordering of enum
            if(i==ordinal){
                return s;
            }
            i++;
        }
        return null;
    }

    private static String bckColor = "#181818";
    private static String butColor = "#710C04";
    private static String txtColor = "#DDDDDD";
    static JFrame frame;
    private static JTextArea infoText = null;

    private static String getScaleText(int x){
        switch(x){
            case 0:
                return x+" [     |     ]";
            case 1:
                return x+" [     |>    ]";
            case 2:
                return x+" [     |=>   ]";
            case 3:
                return x+" [     |==>  ]";
            case 4:
                return x+" [     |===> ]";
            case 5:
                return x+" [     |====>]";
            case -1:
                return x+" [    <|     ]";
            case -2:
                return x+" [   <=|     ]";
            case -3:
                return x+" [  <==|     ]";
            case -4:
                return x+" [ <===|     ]";
            case -5:
                return x+" [<====|     ]";
            default:
                return x+(x<-5?" [<====|     ]":" [     |====>]");
        }
    }
    private static Timer timer = null;
    private static void setupFrame() {
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
        

        TitledBorder panelBorder = BorderFactory.createTitledBorder("STATUS");
        infoPanel.setBorder(panelBorder);
        panelBorder.setTitleColor(Color.decode(txtColor));
        infoPanel.setPreferredSize(new Dimension(300, 80));
        
        infoText = new JTextArea("test");
        infoText.setForeground(Color.decode(txtColor));
        infoPanel.add(infoText);
        infoText.setBackground(Color.decode(bckColor));
        infoText.setLineWrap(true);

        panel.add(infoPanel);
        

        ////////////////////////////////////////////////////
        // Assemble frame
        ////////////////////////////////////////////////////
        
        // panel.add(Box.createRigidArea(new Dimension(0, 1))); // Add spacing between sections
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Status GUI");
        frame.pack();
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);


        timer = new Timer(TIMER_DELAY, 
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    infoText.setText("State: "+state+"\nScale: "+getScaleText(scale));
                }
            }    
        );

        timer.start();
    }

}


