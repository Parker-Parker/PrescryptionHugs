import java.io.*;
import java.net.*;
import java.util.Scanner;

class EchoServer {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(5433);

        System.out.println("Server Set Up: "    + "\nlocPort: "+ welcomeSocket.getLocalPort()
                                                + "\ninet: "+ welcomeSocket.getInetAddress()
                                                + "\nlocSoc: "+ welcomeSocket.getLocalSocketAddress()
                                                + "\nchnl: "+ welcomeSocket.getChannel()
                        );

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            // BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            Scanner inFromClient = new Scanner(connectionSocket.getInputStream());
            
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.nextLine();
            System.out.println("Received: " + clientSentence + " on " + connectionSocket.getPort() +" Inet:"+ connectionSocket.getInetAddress()
                                                                                                    +" loc:"+ connectionSocket.getLocalAddress()
                                                                                                    +" LocSoc:"+ connectionSocket.getLocalSocketAddress()
                                                                                                    +" Rem Soc:"+ connectionSocket.getRemoteSocketAddress()
                                                                                                    +" Inet:"+ connectionSocket.getChannel());
            capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
        }
    }
}