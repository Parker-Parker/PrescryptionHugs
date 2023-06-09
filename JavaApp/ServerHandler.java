import java.util.Scanner;
import java.net.*;


public class ServerHandler extends Thread{
    //for each handler interface, javaApp should have a list of registered handlers
        //ex) javaApp has user input FIFO. any iUserInput can async put entries into FIFO. during certain executeState in turncontroller, userinputhandler will pull commands off Fifo and update javaAppState vars. //make command line user input prog and network user input prog connected to card/field hardware
        //ex) iUXAnimationQueueHandler javaApp publishes animations to animquehandler which publishes to all iAnimQueReceivers, iAnimQueReceivers can register //maybe animquerec can claim/mark completed items in queue
    //should recieve client messages then implement iUserInputHandler
    //should recieve client messages then implement iUXAnimationQueueHandler 
    //should recieve client messages then implement iAdminInputHandler

    //^^^this whole comment is incorrect and outdated lol^^^

    UserInputHandler user;
    ServerSocket serversocket;
    int listenPort = 5433;
    public ServerHandler(UserInputHandler uih){
        this.user = uih;
    }


    public void run(){//loop in thread
        
        try(
            ServerSocket servsock = new ServerSocket(listenPort);   )
        {
            System.out.println("Server established, listening on: "+servsock.getLocalSocketAddress().toString()+":"+servsock.getLocalPort());

            while(true){
                try(
                    Socket newClient = servsock.accept();
                    Scanner newClientScanner = new Scanner(newClient.getInputStream());
                    )
                    {
                        System.out.println("New client "+newClient.getInetAddress().toString()+":"+newClient.getPort()+" connected to server at "+newClient.getLocalAddress().toString()+":"+newClient.getLocalPort());
                        user.register(new UserInputNetwork(newClientScanner));// TODO:since this is now happening in a new thread, I think I need to Mutex it...
                    }
                catch(Exception e){
                    System.out.println(e);
                }   
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
    }


    
}
