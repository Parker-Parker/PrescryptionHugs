import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPServerSlave extends Thread{

    String clientSentence;
    String capitalizedSentence;
    Socket  connectionSocket;
    iNetworkInput parent;
    String clientName = "unnamed GenNet??";

    public TCPServerSlave(Socket conn){
        this.connectionSocket = conn;
        this.setClientName();
    }
    public TCPServerSlave(Socket conn, iNetworkInput parent){
        this.connectionSocket = conn;
        this.setClientName();
        this.parent = parent;
        this.parent.register(this);//really should change this name//also redundant but thats ok
    }

    public void setClientName(){
        if((connectionSocket!=null)){
            clientName = connectionSocket.getInetAddress()+":"+connectionSocket.getPort();
        }
    }

    public String getClientName(){
        return this.clientName;
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
                    System.out.println("Received: " + clientSentence + " on " + connectionSocket.getPort() +" Inet:"+ connectionSocket.getInetAddress()                     ///////////////////////////////////////////////////
                                                                                                            +" loc:"+ connectionSocket.getLocalAddress()                    //all this stuff has to be  swapped out for a write/call to parent //gayyyyyy
                                                                                                            +" LocSoc:"+ connectionSocket.getLocalSocketAddress()           // 
                                                                                                            +" Rem Soc:"+ connectionSocket.getRemoteSocketAddress()
                                                                                                            +" Inet:"+ connectionSocket.getChannel());
                    System.out.println("Reporting client message to parent(iNetworkInput)...  ");
                    parent.process(clientSentence);
                    capitalizedSentence = clientSentence.toUpperCase() + '\n';  //these will have to go soon :(
                    outToClient.writeBytes(capitalizedSentence);                //// :(
                }

                ///////////////////////////////////////////////
                // TODO: add some write stuff? maybe... its 3am idk
                ////////////////////////////////////////////
                        
            }
            System.out.println("Closed: ");
                    
 
        } catch(Exception e){
            System.out.println(e);
            //should probably do somethig here to satop mem leak
        }

    }
    public void setParent(iNetworkInput networkInput) {
        this.parent = networkInput;
    }
    public iNetworkInput getParent() {
        return this.parent;
    }
}