import java.util.Scanner;

//only needed for userInputLocal

public class InputBufferStuffer extends Thread { //this is a buffer class to allow asynchronous access to input streams without  blocking (via threads) 
    String[] buffer = {null,null,null,null,null,null,null,null,null,null};//array of size 10 //Sometimes... i fucking hate java
    int head = 0;
    int tail = 0;
    boolean busy = false;//mutex
    // Scanner sc = new Scanner(System.in);
    Scanner sc;


    public InputBufferStuffer(Scanner scanner){
        sc = scanner;
    }


    public void run(){//loop in thread
        while(true){
            String next = sc.nextLine();
            while(busy){
                //wait
            }
            busy = true;
            buffer[head] = next;
            head=(head+1)%10;
            busy = false;
            if(head==tail){
                System.out.println("oops, the buffer stuffer overflowed");
            }
        }
    }

    public boolean checkAvailable(){
        return head!=tail;
    }
    
    public String readLine(){//does not block? I lied

        String next = null;
        while(busy){
            //wait
        }
        if(checkAvailable()) {
            busy = true;
            next = buffer[tail];
            buffer[tail] = null;
            tail=(tail+1)%10;
            busy = false;
        
        }
        return next;
    }


    public Object peek() {
        return null;
    }


        


}
