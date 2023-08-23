import java.io.*;
import java.net.*;
import java.util.Scanner;

class DillDillClient {
    public static void main(String argv[]) throws Exception {

        // parse args?
        String sentence;
        // String modifiedSentence;

        Field field = new Field();
        ObserverOutputHandler observerParser = new ObserverOutputHandler();

        // SimpleGui GUI = new SimpleGui();
        SketchyGui GUI = new SketchyGui();
        Ipguistarter IPGUI = new Ipguistarter("Controller Client IP and Port Connection");

        // BufferedReader inFromUser = new BufferedReader(new
        // InputStreamReader(System.in));
        // Scanner inFromUser = new Scanner(System.in);

        System.out.println("Waiting to connect");
        // sentence = inFromUser.readLine();
        // sentence = inFromUser.nextLine();

        while (IPGUI.connectionReady != true) {
            // System.out.println("no");
        }

        String hostName = IPGUI.getipaddress();
        int port = IPGUI.getport();

        System.out.println(hostName + " " + port);
        Socket clientSocket = new Socket(hostName, port);
        System.out.println("yoooooooooooooooooo");
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());

        String fieldString = "";
        sentence = "bloop bloop please connect";
        outToServer.writeBytes(sentence + '\n');
        outToServer.flush();

        IPGUI.close();
        while (true) {

            // if(inFromUser.hasNextLine()){
            if (GUI.commandAvailable()) {

                System.out.println(System.in.available() + ":" + clientSocket.getInputStream().available()); // debug
                // sentence = inFromUser.readLine();
                sentence = GUI.getCommand();
                outToServer.writeBytes(sentence + '\n');
                outToServer.flush();
            }
            // if(inFromServer.hasNextLine()){
            if (clientSocket.getInputStream().available() > 0) {
                // sentence = inFromUser.readLine();

                fieldString = inFromServer.nextLine();
                // outToServer.writeBytes(sentence + '\n');
                observerParser.deserializeField(field, observerParser.deserializeTopics(fieldString));
                // System.out.println(fieldString);

                // for(ObserverTopics top : ObserverTopics.values()){
                // System.out.println(top.name()+":
                // "+observerParser.deserializeTopics(fieldString).get(top));
                // }

                // System.out.println( field.getEnemyCardsBack( ) );
                // System.out.println( field.getEnemyCards( ) );
                // System.out.println( field.getPlayerCards( ) );
                // System.out.println( field.getHand( ) );
                // System.out.println( field.getSacrifices( ) );
                // System.out.println( field.getMainDeck( ) );
                // System.out.println( field.getSideDeck( ) );
                // System.out.println( field.getEnemyPlannedMoves( ) );

                field.printField();

            }

        }
        // clientSocket.close();
    }
}