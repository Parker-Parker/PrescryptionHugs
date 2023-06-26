import java.io.*;
import java.net.*;
import java.util.Scanner;

class TestNetClient {
    public static void main(String argv[]) throws Exception {
        String hostName = "localhost";
        int port = 5433;
        //parse args?
        String sentence;
        // String modifiedSentence;
        
        // BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Scanner inFromUser = new Scanner(System.in);
        
        System.out.println("Press enter to connect to "+hostName+":"+port);
        // sentence = inFromUser.readLine();
        sentence = inFromUser.nextLine();

        Socket clientSocket = new Socket("localhost", 5433);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        Scanner inFromServer = new Scanner(clientSocket.getInputStream());
        
        while(true){

            // if(inFromUser.hasNextLine()){
            if(System.in.available()>0){

                System.out.println(System.in.available()+":"+clientSocket.getInputStream().available());
                // sentence = inFromUser.readLine();
                System.out.println("NEW USER LINE");
                sentence = inFromUser.nextLine();
                outToServer.writeBytes(sentence + '\n');
                // outToServer.flush();
                System.out.println("NEW USER LINE SENT");
            }
            // if(inFromServer.hasNextLine()){
            if(clientSocket.getInputStream().available()>0){
                // sentence = inFromUser.readLine();

                System.out.println("NEW SERVER LINE RECEIVED");
                System.out.println(inFromServer.nextLine());
                // outToServer.writeBytes(sentence + '\n');
            }

        }
        // clientSocket.close();
    }
}