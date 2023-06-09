import java.util.LinkedList;
import java.util.Scanner;
import java.util.EnumMap;
import java.io.PrintStream;
import java.net.*;

public class UserInputClient {

    // InputBufferStuffer networkBuffer;
    // static InputBufferStuffer terminalBuffer;
    // static Socket server = null;

    public static void main(String []args)
    {

        System.out.println("Welcome to Inscryption. Network Client running");
        InputBufferStuffer terminalBuffer = new InputBufferStuffer(new Scanner(System.in));
        terminalBuffer.start();
        
        while(true){
            System.out.println("Specify Host server IP and port");
            System.out.println("EX) >192.168.1.12:5433");
            System.out.print(" >");
            
            String in = null;
            while(in==null){
                in = terminalBuffer.readLine();    
            }

            String[] split = in.split(":");
            try(Socket server = new Socket(split[0], Integer.getInteger(split[1]));
                PrintStream s = new PrintStream(server.getOutputStream());){
                System.out.println("Connected To: "+server.getInetAddress().getHostAddress()+":"+server.getPort());
                InputBufferStuffer networkBuffer = new InputBufferStuffer(new Scanner(server.getInputStream()));
                networkBuffer.start();

                while(server!=null&&server.isConnected()){
                    //main code here
                    while(networkBuffer.checkAvailable()){
                        System.out.println(networkBuffer.readLine()+"  <----"+server.getInetAddress().getHostAddress()+":"+server.getPort());
                    }
                    while(terminalBuffer.checkAvailable()){
                        String cmd = terminalBuffer.readLine();

                        System.out.println(cmd+"  ---->"+server.getInetAddress().getHostAddress()+":"+server.getPort());
                        s.println(cmd);
                        s.flush();
                        System.out.println("Debug: Flushed");
                        
                    }



                }
                System.out.println("connection closed peacefully");

            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        

        // networkBuffer =  new InputBufferStuffer(new Scanner(System.in));
        // networkBuffer.start();


    }

    public UserInputClient(){
    }

    
    



}
