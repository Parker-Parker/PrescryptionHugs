
import java.io.*;
import java.net.*;
import java.util.Scanner;


import javax.swing.JFrame;


public class FieldRendererWindowClient {

    public static void main(String[] args) throws Exception {

        // new GameFrame();
        
        String hostName = "localhost";
        int port = 5433;
        //parse args?



        Field field = new Field();
        ObserverOutputHandler observerParser = new ObserverOutputHandler();
        
        JFrame frame = setupGameFrame();
        
        
        System.out.println("Press enter to connect to "+hostName+":"+port);


        Socket clientSocket = new Socket("localhost", 5433);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());

        outToServer.writeBytes("Generic" + '\n');//cONFIG STRING //change this to renderer specific once implemented
        outToServer.flush();

        String fieldString = "";

        while(true){

            // if(inFromServer.hasNextLine()){
            if(clientSocket.getInputStream().available()>0){
                // sentence = inFromUser.readLine();

                fieldString = inFromServer.nextLine();
                // outToServer.writeBytes(sentence + '\n');
                observerParser.deserializeField(field, observerParser.deserializeTopics(fieldString));
                // System.out.println(fieldString);


                field.printField();



            }

        }

    }


    private static JFrame setupGameFrame(){
        JFrame frame = new JFrame();


 		frame.add(new FieldRendererPanel());
 		frame.setTitle("Inscryption Network Renderer Client");
 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.setResizable(false);
        frame.setUndecorated(true);
        
 		frame.pack();
 		frame.setVisible(true);
 		// frame.setLocationRelativeTo(null);// Uncomment me in production

        return frame;
    }





}




