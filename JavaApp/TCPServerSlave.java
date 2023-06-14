import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPServerSlave extends Thread{

    String clientSentence;
    String capitalizedSentence;
    Socket  connectionSocket;

    public TCPServerSlave(Socket conn){
        this.connectionSocket = conn;
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
            System.out.println(e);
            //should probably do somethig here to satop mem leak
        }

    }
}