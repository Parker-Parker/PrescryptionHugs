import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.LinkedList;

class TCPServerMaster extends Thread {

    int socket = 5433;
    // UserInputHandler uHandler = null;
    InputOutputHandler ioHandler = null;

    public TCPServerMaster() {
    }

    public TCPServerMaster(int socket) {
        this.socket = socket;
    }

    // public TCPServerMaster(UserInputHandler handler) {
    //     this.uHandler = handler;
    // }
    public TCPServerMaster(InputOutputHandler handler) {
        this.ioHandler = handler;
    }

    // public TCPServerMaster(int socket, UserInputHandler handler) {
    //     this.socket = socket;
    //     this.uHandler = handler;
    // }

    public TCPServerMaster(int socket, InputOutputHandler handler) {
        this.socket = socket;
        this.ioHandler = handler;
    }

    public void setSocket(int socket) {// might want to check if server running first...
        this.socket = socket;
    }

    // public void setUserInputHandler(UserInputHandler handler) {
    //     this.uHandler = handler;
    // }

    public void setUserInputHandler(InputOutputHandler handler) {
        this.ioHandler = handler;
    }

    public int getSocket() {
        return socket;
    }

    // public UserInputHandler getUserInputHandler() {
    //     return uHandler;
    // }
    
    public InputOutputHandler getInputOutputHandler() {
        return ioHandler;
    }

    public void run() {

        try {
            ServerSocket welcomeSocket = new ServerSocket(socket);
            LinkedList<TCPServerSlave> clients = new LinkedList<>();
            System.out.println("\nServer Set Up: " + "\nws.locPort: " + welcomeSocket.getLocalPort()
                    + "\nws.inet: " + welcomeSocket.getInetAddress()
                    + "\nws.locSoc: " + welcomeSocket.getLocalSocketAddress()
                    + "\nws.chnl: " + welcomeSocket.getChannel());
            System.out.println("\nMy best guess is: " + printIPs() + ":" + socket);
            System.out.println("\nJava's best guess is: " + InetAddress.getLocalHost().getHostAddress() + "\n");

            while (true) {
                try {
                    Socket connectionSocket = welcomeSocket.accept();

                    // TODO: Immediate: parse config command, make a new networkInput of the correct
                    // type, give new network input the socket, register the new input to the input
                    // handler
                    Scanner sc = new Scanner(connectionSocket.getInputStream());
                    String cfgString = sc.nextLine();//TODO: need to add a timeout to this!!
                    String clientName = connectionSocket.getInetAddress() + ":" + connectionSocket.getPort();
                    System.out.println("Client " + clientName + " sent config command: >" + cfgString);
                    String[] args = cfgString.split(" ");
                    // System.out.println("Parsing..."); //debug
                    if ((args.length >= 1) && (args[0] != null)) {// &&()
                        iNetworkInput networkInput = this.parseConfig(cfgString);//TODO: AAAAAAAAAA //todo: add null handling //new GenericNetworkInput();//
                        TCPServerSlave newClient = new TCPServerSlave(connectionSocket, networkInput);
                        networkInput.register(newClient);
                        // newClient.start();// maybe we do this in the network input constructor?
                        
                        if(networkInput.hasInputType(InputOutputType.User)){
                            ioHandler.getUserInputHandler().register(networkInput.getUserInput());
                        }
                        if(networkInput.hasInputType(InputOutputType.Observer)){
                            ioHandler.getObserverOutputHandler().register(networkInput.getObserverOutput());
                        }
                         newClient.start();// maybe we do this in the network input constructor?
                       

                        clients.add(newClient);
                        System.out.println("NewClientAdded... ");
                        System.out.println(clients.toString());

                    }
                    else{
                        //panic, destroy connetion, run
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.out.println("Server networking failed to start...");
            e.printStackTrace();
        }
    }

    private iNetworkInput parseConfig(String cfgString) {//TODO: this
        if(cfgString.length()>=1){
            String[] args = cfgString.split(" ");
            switch(args[0].toUpperCase()){
                case "SIMPLE":
                    return new GenericNetworkInput();
                    // break;
                case "GENERIC":
                    return new GenericNetworkInput();
                    // break;
                case "ANIMATION":
                    return new AnimationNetworkOutput();
                    // break;
                case "CUSTOM":
                    LinkedList<InputOutputType> ioTypes = new LinkedList<>();
                    for(String x : args){

                        for(InputOutputType t : InputOutputType.values()){
                            if(x.equalsIgnoreCase(t.name())){
                                ioTypes.add(t);
                            }
                        }
                        // if(x.equalsIgnoreCase(InputOutputType.values().){

                        // }
                        // else if(x.equalsIgnoreCase("Custom"))){
                            
                        // }
                        // else{
                        //     break;
                        // }
                    }
                    return new GenericNetworkInput(ioTypes);
                    // break;
                //pubsub observer etc
                case "G"://terse
                    break;
                default:
                    return new GenericNetworkInput();
                    // break;
            }
        }
        return null;
    }

    public String printIPs() {
        String x = null;
        try {
            System.out.println("\nPossible Host Addresses: ");

            Enumeration e = NetworkInterface.getNetworkInterfaces();
            while (e.hasMoreElements()) {
                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    System.out.println("  > " + i.getHostName() + ":   \t\t\t\t" + i.getHostAddress());
                    if (i.getHostAddress().contains("192.168.")) {
                        x = i.getHostAddress();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

}

