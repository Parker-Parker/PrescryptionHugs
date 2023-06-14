
public class TCPServerTester {
    
    public static void main(String []args)
    {

        TCPServerMaster host = new TCPServerMaster();
        host.start();
        while(host.isAlive()){
            
        }
        System.out.println("Server Networking closed?");


    }



}
