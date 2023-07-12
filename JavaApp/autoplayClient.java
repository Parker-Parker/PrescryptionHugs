import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class autoplayClient {
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

                System.out.println(System.in.available()+":"+clientSocket.getInputStream().available()); //debug
                // sentence = inFromUser.readLine();
                sentence = inFromUser.nextLine();
                outToServer.writeBytes(sentence + '\n');
            }
            // if(inFromServer.hasNextLine()){
            if(clientSocket.getInputStream().available()>0){
                // sentence = inFromUser.readLine();

                System.out.println(inFromServer.nextLine());
                // outToServer.writeBytes(sentence + '\n');
            }

            TimeUnit.SECONDS.sleep(1);
            sentence = "user dra 0";/// << Dillon TODO: put the user input command here
            outToServer.writeBytes(sentence + '\n');
            outToServer.flush();

            TimeUnit.SECONDS.sleep(1);
            sentence = "user rdy 0";/// << Dillon TODO: put the user input command here
            outToServer.writeBytes(sentence + '\n');
            outToServer.flush();


        }
        // clientSocket.close();
    }
}