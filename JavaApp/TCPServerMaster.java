import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.LinkedList;

class TCPServerMaster extends Thread {

    int socket = 5433;

    public TCPServerMaster(int socket){
        this.socket = socket;
    }
    public TCPServerMaster(){
    }

    public void run() {


        try{
            ServerSocket welcomeSocket = new ServerSocket(socket);
            LinkedList<TCPServerSlave> clients = new LinkedList<>();
            System.out.println("Server Set Up: "    + "\nws.locPort: "+ welcomeSocket.getLocalPort()
                                                    + "\nws.inet: "+ welcomeSocket.getInetAddress()
                                                    + "\nws.locSoc: "+ welcomeSocket.getLocalSocketAddress()
                                                    + "\nws.chnl: "+ welcomeSocket.getChannel()
            );
            printIPs();
            System.out.println("\nJava's best guess is: "+InetAddress.getLocalHost().getHostAddress()+"\n");
                            
            while (true) {
                try
                {  Socket connectionSocket = welcomeSocket.accept();
                
                    TCPServerSlave newClient = new TCPServerSlave(connectionSocket);

                    newClient.start();
                    clients.add(newClient);
                    System.out.println("NewClientAdded... ");
                    System.out.println(clients.toString());
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println("Server networking failed to start...");
            System.out.println(e);
        }
    }

    public void printIPs() {
        try {
            System.out.println("Possible Host Addresses: ");
            
            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    System.out.println(i.getHostAddress());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    // public void printIPs() {
    // for(NetworkInterface n : NetworkInterface.getNetworkInterfaces()){
    // for()
    // }
    // while (e.hasMoreElements()) {
    // NetworkInterface n = (NetworkInterface) e.nextElement();
    // Enumeration ee = n.getInetAddresses();
    // while (ee.hasMoreElements()) {
    // InetAddress i = (InetAddress) ee.nextElement();
    // System.out.println(i.getHostAddress());
    // }
    // }
    // }
}
