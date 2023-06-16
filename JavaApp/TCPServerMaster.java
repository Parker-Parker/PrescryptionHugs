import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.Enumeration;
import java.util.LinkedList;

class TCPServerMaster extends Thread {

    int socket = 5433;
    UserInputHandler uHandler = null;

    public TCPServerMaster() {
    }

    public TCPServerMaster(int socket) {
        this.socket = socket;
    }

    public TCPServerMaster(UserInputHandler handler) {
        this.uHandler = handler;
    }

    public TCPServerMaster(int socket, UserInputHandler handler) {
        this.socket = socket;
        this.uHandler = handler;
    }

    public void setSocket(int socket) {// might want to check if server running first...
        this.socket = socket;
    }

    public void setUserInputHandler(UserInputHandler handler) {
        this.uHandler = handler;
    }

    public int getSocket() {
        return socket;
    }

    public UserInputHandler getUserInputHandler() {
        return uHandler;
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
                    String cfgString = sc.nextLine();
                    String clientName = connectionSocket.getInetAddress() + ":" + connectionSocket.getPort();
                    System.out.println("Client " + clientName + " sent config command: >" + cfgString);
                    String[] args = cfgString.split(" ");
                    // System.out.println("Parsing..."); //debug
                    if ((args.length >= 1) && (args[0] != null)) {// &&()
                        iNetworkInput networkInput = this.parseConfig(cfgString);//TODO: AAAAAAAAAA //todo: add null handling //new GenericNetworkInput();//
                        TCPServerSlave newClient = new TCPServerSlave(connectionSocket);
                        networkInput.register(newClient);
                        // newClient.start();// maybe we do this in the network input constructor?
                        
                        if(networkInput.hasInputType(InputOutputType.User)){
                            uHandler.register(networkInput.getUserInput());
                        }


                        clients.add(newClient);
                        System.out.println("NewClientAdded... ");
                        System.out.println(clients.toString());

                    }
                    else{
                        //panic, destroy connetion, run
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println("Server networking failed to start...");
            System.out.println(e);
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
                    System.out.println("  > " + i.getHostName() + ":   \t\t" + i.getHostAddress());
                    // if(i.getHostAddress().contains(".") &&
                    // i.getHostAddress().split(".").length==4 &&
                    // i.getHostAddress().split(".")[0].equals("192") &&
                    // i.getHostAddress().split(".")[1].equals("168")){
                    // x = i.getHostAddress();
                    // }
                    if (i.getHostAddress().contains("192.168.")) {
                        x = i.getHostAddress();
                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return x;
    }

}

// run log for later analysis::

// /usr/bin/env /usr/lib/jvm/java-17-openjdk/bin/java
// -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:43963
// -XX:+ShowCodeDetailsInExceptionMessages -cp
// /home/parkerg/.config/Code/User/workspaceStorage/dd955bef094113071848c41723deb16c/redhat.java/jdt_ws/PrescryptionHugs_8649863/bin
// TCPServerTester
// Server Set Up:
// ws.locPort: 5433
// ws.inet: 0.0.0.0/0.0.0.0
// ws.locSoc: 0.0.0.0/0.0.0.0:5433
// ws.chnl: null
// Possible Host Addresses:
// 2601:18f:480:8ee0:0:0:0:1%enp0s31f6
// 2601:18f:480:8ee0:edff:46dd:95a8:cff7%enp0s31f6
// fe80:0:0:0:79:71ff:5d84:cf86%enp0s31f6
// 192.168.0.18
// 0:0:0:0:0:0:0:1%lo
// 127.0.0.1
// NewClientAdded...
// [Thread[Thread-1,5,main]]
// Received: hello on 42272 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:42272 Inet:null
// Received: hello hello on 42272 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:42272 Inet:null
// NewClientAdded...
// [Thread[Thread-1,5,main], Thread[Thread-2,5,main]]
// Received: tiddies on 51174 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:51174 Inet:null
// Received: hi on 51174 Inet:/127.0.0.1 loc:/127.0.0.1 LocSoc:/127.0.0.1:5433
// Rem Soc:/127.0.0.1:51174 Inet:null
// Received: squaids on 42272 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:42272 Inet:null
// NewClientAdded...
// [Thread[Thread-1,5,main], Thread[Thread-2,5,main], Thread[Thread-3,5,main]]
// Received: hello I am a chromebook on 39546 Inet:/192.168.0.16
// loc:/192.168.0.18 LocSoc:/192.168.0.18:5433 Rem Soc:/192.168.0.16:39546
// Inet:null

// ^C[parkerg@Manjaro-Host-OS PrescryptionHugs]$ cd
// /home/parkerg/PrescryptionHugs ; /usr/bin/env
// /usr/lib/jvm/java-17-openjdk/bin/java
// -agentlib:jdwp=transport=dt_socket,server=n,suspend=y,address=localhost:38915
// -XX:+ShowCodeDetailsInExceptionMessages -cp
// /home/parkerg/.config/Code/User/workspaceStorage/dd955bef094113071848c41723deb16c/redhat.java/jdt_ws/PrescryptionHugs_8649863/bin
// TCPServerTester

// Server Set Up:
// ws.locPort: 5433
// ws.inet: 0.0.0.0/0.0.0.0
// ws.locSoc: 0.0.0.0/0.0.0.0:5433
// ws.chnl: null

// Possible Host Addresses:
// > 2601:18f:480:8ee0:0:0:0:1%enp0s31f6: 2601:18f:480:8ee0:0:0:0:1%enp0s31f6
// > 2601:18f:480:8ee0:edff:46dd:95a8:cff7%enp0s31f6:
// 2601:18f:480:8ee0:edff:46dd:95a8:cff7%enp0s31f6
// > fe80:0:0:0:79:71ff:5d84:cf86%enp0s31f6:
// fe80:0:0:0:79:71ff:5d84:cf86%enp0s31f6
// > 192.168.0.18: 192.168.0.18
// > localhost: 0:0:0:0:0:0:0:1%lo
// > localhost: 127.0.0.1

// Java's best guess is: 127.0.1.1

// NewClientAdded...
// [Thread[Thread-1,5,main]]
// NewClientAdded...
// [Thread[Thread-1,5,main], Thread[Thread-2,5,main]]
// Received: hello on 37264 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:37264 Inet:null
// Received: I am localhost client 1! on 37264 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:37264 Inet:null
// Received: Im local 2! >:3 on 37268 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:37268 Inet:null
// NewClientAdded...
// [Thread[Thread-1,5,main], Thread[Thread-2,5,main], Thread[Thread-3,5,main]]
// Received: I'm a chromebook on wifi D: on 39754 Inet:/192.168.0.16
// loc:/192.168.0.18 LocSoc:/192.168.0.18:5433 Rem Soc:/192.168.0.16:39754
// Inet:null
// Received: GooDbYe on 37268 Inet:/127.0.0.1 loc:/127.0.0.1
// LocSoc:/127.0.0.1:5433 Rem Soc:/127.0.0.1:37268 Inet:null
// ^C[parkerg@Manjaro-Host-OS PrescryptionHugs]$ ^C
// [parkerg@Manjaro-Host-OS PrescryptionHugs]$
