import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPServerSlave extends Thread{

    String clientSentence;
    String serverSentence;
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
                // if(inFromClient.hasNextLine()){    
                if(connectionSocket.getInputStream().available()>0){        //scanner.hasnextline blocks        
                    clientSentence = inFromClient.nextLine();
                    System.out.println("Received: " + clientSentence + " from " +" Inet:"+ connectionSocket.getInetAddress()+":"+ connectionSocket.getPort());
                    System.out.println("Reporting client message to parent(iNetworkInput)...  ");
                    parent.process(clientSentence);
                }
                ///////////////////////////////////////////////
                // TODO: add some write stuff? maybe... its 3am idk
                ////////////////////////////////////////////
                // System.out.println("received");
                if(parent.hasNextLine()){
                    serverSentence = parent.nextLine();
                    outToClient.writeBytes(serverSentence + '\n');
                    outToClient.flush();
                } 
                


            }
            System.out.println("Closed: ");
                    
 
        } catch(Exception e){
            e.printStackTrace();
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