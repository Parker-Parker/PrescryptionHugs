import java.util.ArrayList;

public class SafeRingBuffer<E> { // Removed all that threading crap//buffer stuffer replacement to allow safe
                                 // asynchronous access to a buffer(without the buffer needing its own damn
                                 // thread and getting broken)
    // E[] buffer; //Array in which everything is stored //=
    // {null,null,null,null,null,null,null,null,null,null};//array of size 10
    // //Sometimes... i fucking hate java
    ArrayList<E> buffer; // Array in which everything is stored //=
                         // {null,null,null,null,null,null,null,null,null,null};//array of size 10
                         // //Sometimes... i fucking hate java

    // int lastTicket = 0;
    // int nowServing = 1;

    // LinkedList<int> x; //just for demo purposes
    // LinkedList<int[]> y;

    int head = 0;
    int tail = 0;
    // boolean busy = false;//mutex
    // Scanner sc = new Scanner(System.in);


    boolean windowAvail = false; //HOLY SHIT. this is wild. because multiple threads try to act on this, threads can actually end up trying to .push() to the buffer before the constructor even finishes executing...ABSOLUTELY WILD
    int lastTicket = 0;          // ^^ No, me and java are both just dumb. Java only lets you Specify CAPACITY, not SIZE in the arraylist contructor. pretty useless. A for loop is apparently required. BOOO Java 
    int nowServing = 1;

    public SafeRingBuffer() {
        this(10);
        
    }

    public SafeRingBuffer(int size) {
        buffer = new ArrayList<E>(size);
        while(buffer.size() < size) {
            buffer.add(null);
        }
        windowAvail = true;
        System.out.println("buf siz: "+buffer.size());
        // this.buffer = new E[size];
        // for(int i=0;i<size;i++){
        // this.buffer
        // }
    }

    // boolean windowAvail = true;
    // int lastTicket = 0;
    // int nowServing = 1;

    public int takeTicket() {// this will block until resource is available. it will provide a ticket number.
                             // failure to return ticket will make resource unavailable
        int myTicket = -1;// check for -1 return will indicate error
        while (!windowAvail) {
            // wait
        }
        boolean incomplete = true;
        while (incomplete) {
            if (windowAvail) {
                if (windowAvail) {
                    windowAvail = false;
                    lastTicket = (lastTicket + 1) % 4000;
                    myTicket = lastTicket;

                    windowAvail = true;
                    incomplete = false;
                }
            }
        }
        while (myTicket != nowServing) {
            // wait for resource
        }
        return myTicket;
    }

    public void returnTicket(int ticket) {// this will free up the resource
        if (ticket != nowServing) {
            System.out.println("Something fucked up is happening at the ticket window");
        }
        nowServing = (nowServing + 1) % 4000;// i should probably make this threadsafe too but we shouldnt need it if
                                             // everyone follows the rules
    }

    public E popOld() {
        if (this.checkAvailable()) {

            E element = null; // String next = null;
            int ticket = takeTicket(); // while (busy) {
                                       // // wait
                                       // }
            if (checkAvailable()) { // if (checkAvailable()) {
                                    // busy = true;
                element = buffer.get(tail); // next = buffer[tail];
                buffer.set(tail, null); // buffer[tail] = null;
                tail = (tail + 1) % buffer.size(); // tail = (tail + 1) % 10;
                                                   // busy = false;
                                                   //
            } else {
                System.out.println(
                        "last entryyoinked while waiting for ticket!");
            } // }
            returnTicket(ticket);
            return element; // return next;

        } else {
            return null;
        }
    }

    public E popNew() {
        if (this.checkAvailable()) {

            E element = null; // String next = null;
            int ticket = takeTicket(); // while (busy) {
                                       // // wait
                                       // }
            if (checkAvailable()) { // if (checkAvailable()) {
                                    // busy = true;
                element = buffer.get(head); // next = buffer[tail];
                buffer.set(head, null); // buffer[tail] = null;
                head = (head + buffer.size() - 1) % buffer.size(); // tail = (tail + 1) % 10;
                // busy = false;
                //
            } else {
                System.out.println(
                        "last entryyoinked while waiting for ticket!");
            } // }
            returnTicket(ticket);
            return element; // return next;

        } else {
            return null;
        }
    }

    public void push(E input) {
        int ticket = takeTicket(); // busy = true;
        System.out.println("buf siz: "+buffer.size());
        if(buffer.size()>0){
            buffer.set(head, input); // buffer[head] = next;
            head = (head + 1) % buffer.size(); // head = (head + 1) % 10;
        }
        else{
            System.out.println("Ring buffer zero length?? cant push "+head+" "+ticket);
            
        }
        returnTicket(ticket); // busy = false;

        if (head == tail) {
            System.out.println("oops, i think the ringbuffer overflowed");
        }

    }    
    public void wipe() {
        int ticket = takeTicket(); 
        head = tail;
        returnTicket(ticket); 

    }

    // public void run() {// loop in thread
    // while (true) {
    // String next = sc.nextLine();
    // while (busy) {
    // // wait
    // }
    // busy = true;
    // buffer[head] = next;
    // head = (head + 1) % 10;
    // busy = false;
    // if (head == tail) {
    // System.out.println("oops, the buffer stuffer overflowed");
    // }
    // }
    // }

    public boolean checkAvailable() {
        return head != tail;
    }

    // public String readLine() {// does not block? I lied

    // String next = null;
    // while (busy) {
    // // wait
    // }
    // if (checkAvailable()) {
    // busy = true;
    // next = buffer[tail];
    // buffer[tail] = null;
    // tail = (tail + 1) % 10;
    // busy = false;

    // }
    // return next;
    // }

    // public Object peek() {
    // return null;
    // }

}
