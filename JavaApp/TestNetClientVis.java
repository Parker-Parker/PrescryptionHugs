import java.io.*;
import java.net.*;
import java.util.Scanner;

class TestNetClientVis {
    public static void main(String argv[]) throws Exception {
        String hostName = "localhost";
        int port = 5433;
        //parse args?
        String sentence;
        // String modifiedSentence;

        Field field = new Field();
        ObserverOutputHandler observerParser = new ObserverOutputHandler();
        

        
        // BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Scanner inFromUser = new Scanner(System.in);
        
        System.out.println("Press enter to connect to "+hostName+":"+port);
        // sentence = inFromUser.readLine();
        sentence = inFromUser.nextLine();

        Socket clientSocket = new Socket("localhost", 5433);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());
        
        String fieldString = "";

        while(true){

            // if(inFromUser.hasNextLine()){
            if(System.in.available()>0){

                System.out.println(System.in.available()+":"+clientSocket.getInputStream().available()); //debug
                // sentence = inFromUser.readLine();
                sentence = inFromUser.nextLine();
                outToServer.writeBytes(sentence + '\n');
            }
            // if(inFromServer.hasNextLine()){
            if(clientSocket.getInputStream().available()>0){
                // sentence = inFromUser.readLine();

                fieldString = inFromServer.nextLine();
                // outToServer.writeBytes(sentence + '\n');
                observerParser.deserializeField(field, observerParser.deserializeTopics(fieldString));
                // System.out.println(fieldString);



                // for(ObserverTopics top : ObserverTopics.values()){
                //     System.out.println(top.name()+": "+observerParser.deserializeTopics(fieldString).get(top));
                // }


                // System.out.println( field.getEnemyCardsBack(    ) );                     
                // System.out.println( field.getEnemyCards(        ) );             
                // System.out.println( field.getPlayerCards(       ) );           
                // System.out.println( field.getHand(              ) );                          
                // System.out.println( field.getSacrifices(        ) );            
                // System.out.println( field.getMainDeck(          ) );           
                // System.out.println( field.getSideDeck(          ) );   
                // System.out.println( field.getEnemyPlannedMoves( ) );

                field.printField();



            }

        }
        // clientSocket.close();
    }
}