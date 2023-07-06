
import java.io.*;
import java.net.*;
import java.util.Scanner;


import javax.swing.JFrame;


public class FieldRendererWindowAnimClient {

    // static Field field = new Field();
    static FieldRendererAnimPanel panel;

    public static void main(String[] args) throws Exception {

        // new GameFrame();
        
        String hostName = "localhost";
        int port = 5433;
        //parse args?



        ObserverOutputHandler observerParser = new ObserverOutputHandler();
        
        JFrame frame = setupGameFrame();
        
        
        System.out.println("Press enter to connect to "+hostName+":"+port);
        (new Scanner(System.in)).nextLine();///I can make this a GUI Element, comment these out then add a blocking menu to setupgameframe()


        Socket clientSocket = new Socket(hostName,port);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());

        outToServer.writeBytes("Animation" + '\n');//cONFIG STRING //change this to renderer specific once implemented
        outToServer.flush();

        String fieldString = "";

        while(true){

            // if(inFromServer.hasNextLine()){
            if(clientSocket.getInputStream().available()>0){
                // sentence = inFromUser.readLine();

                fieldString = inFromServer.nextLine();
                // outToServer.writeBytes(sentence + '\n');
                // observerParser.deserializeField(field, observerParser.deserializeTopics(fieldString));
                // System.out.println(fieldString);


                // field.printField();
                // panel.setField(field);
                panel.addToQueue(observerParser.deserializeTopics(fieldString));


            }

        }

    }


    private static JFrame setupGameFrame(){
        JFrame frame = new JFrame();
        panel = new FieldRendererAnimPanel();
        // panel.setField(field);

 		frame.add(panel);
 		frame.setTitle("Inscryption Network Renderer Client");
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.setResizable(false);
        // frame.setUndecorated(true);
        
 		frame.pack();
 		frame.setVisible(true);
 		// frame.setLocationRelativeTo(null);// Uncomment me in production

        return frame;
    }





}




