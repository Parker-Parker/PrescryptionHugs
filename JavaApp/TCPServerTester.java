
public class TCPServerTester {
    
    public static void main(String []args)
    {
        // InputProtocolString parser = new InputProtocolString();
        // System.out.println("Beginning string input parser test...");
        // // String[] testIOTStrings= {"User","Enemy","Admin","System","Observer","Render","Spaghetti",};
        // String[] testIOTStrings= {"User","Render","Spaghetti",};
        // String[] testTSStrings= {"User","Enemy", "DRA", "RDY", "SAC", "SUM"}; 
        // String testCmd = null;
        // for(String iot : testIOTStrings)
        // {
        //     for(String ts : testTSStrings){
        //         for(int p0 = 0; p0<7; p0++){
        //             for(int p1 = 0; p1<7; p1++){
                    
        //                 for(int p2 = 0; p2<7; p2++){
                    
        //                     for(int p3 = 0; p3<7; p3++){
        //                         testCmd = iot+" "+ts+" "+p0+" "+p1+" "+p2+" "+p3;
        //                         System.out.println("Testing Parser: >"+testCmd);
        //                         System.out.println(" Parser Output: >"+fix(parser.parse(testCmd)));
                                
        //                     }
        //                 }
        //             }
                
        //         }
        //     }
        // }


        TCPServerMaster host = new TCPServerMaster();
        host.start();
        while(host.isAlive()){
            
        }
        System.out.println("Server Networking closed?");


    }

    
    // public static String fix(int[] x){//sloppy and jenk
    //     String k = "";
    //     int[] f = {};
    //     for (int i : (x==null?f:x)){
    //         k = k+i+" ";
    //     }
    //     return k;
    // }


}
