import java.io.*;
import java.net.*;

class TestNetClient {
    public static void main(String argv[]) throws Exception {
        String hostName = "localhost";
        int port = 5433;
        //parse args
        String sentence;
        // String modifiedSentence;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("Press enter to connect to "+hostName+":"+port);
        sentence = inFromUser.readLine();

        Socket clientSocket = new Socket("localhost", 5433);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        while(true){
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');
            // modifiedSentence = inFromServer.readLine();
            // System.out.println("FROM SERVER: " + modifiedSentence);
        }
        // clientSocket.close();
    }
}