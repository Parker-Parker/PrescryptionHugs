import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.LinkedList;

class TCPServer3 extends Thread{

    String clientSentence;
    String capitalizedSentence;
    // ServerSocket welcomeSocket;// = new ServerSocket(5433);
    Socket  connectionSocket;

    public TCPServer3(Socket conn){
        this.connectionSocket = conn;
    }

    public static void main(String argv[]) throws Exception {
        // String clientSentence;
        // String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(5433);
        LinkedList<TCPServer3> clients = new LinkedList<>();
        System.out.println("Server Set Up: "    + "\nlocPort: "+ welcomeSocket.getLocalPort()
                                                + "\ninet: "+ welcomeSocket.getInetAddress()
                                                + "\nlocSoc: "+ welcomeSocket.getLocalSocketAddress()
                                                + "\nchnl: "+ welcomeSocket.getChannel()
                        );

        while (true) {
            try
            // (Socket connectionSocket = welcomeSocket.accept();
            // Scanner inFromClient = new Scanner(connectionSocket.getInputStream());
            // DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            // )
            {  Socket connectionSocket = welcomeSocket.accept();
            
                TCPServer3 newClient = new TCPServer3(connectionSocket);

                newClient.start();
                clients.add(newClient);
                System.out.println("NewClientAdded:");
                System.out.println(clients.toString());
                // clientSentence = inFromClient.nextLine();
                // System.out.println("Received: " + clientSentence + " on " + connectionSocket.getPort() +" Inet:"+ connectionSocket.getInetAddress()
                //                                                                                         +" loc:"+ connectionSocket.getLocalAddress()
                //                                                                                         +" LocSoc:"+ connectionSocket.getLocalSocketAddress()
                //                                                                                         +" Rem Soc:"+ connectionSocket.getRemoteSocketAddress()
                //                                                                                         +" Inet:"+ connectionSocket.getChannel());
                // capitalizedSentence = clientSentence.toUpperCase() + '\n';
                // outToClient.writeBytes(capitalizedSentence);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void run() {
        
            // Socket connectionSocket = welcomeSocket.accept();
            // BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        try(    //Socket connectionSocket = welcomeSocket.accept();
                Scanner inFromClient = new Scanner(connectionSocket.getInputStream());
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        ){
            // while(connectionSocket.isConnected()){
            while(!connectionSocket.isClosed()){
                if(inFromClient.hasNextLine()){                
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
            System.out.println("Closed: ");
                    
 
        } catch(Exception e){
            e.printStackTrace();
            //should probably do somethig here to satop mem leak
        }

    }
}