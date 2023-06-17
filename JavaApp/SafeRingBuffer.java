import java.util.ArrayList;

public class SafeRingBuffer<E> { // Class to allow threadsafe asynchronous access to a ringbuffer

    ArrayList<E> buffer;    // Array in which everything is stored 
    int head = 0;           //most recent entry
    int tail = 0;           //oldest entry

    // LinkedList<int> x; //just for demo purposes
    // LinkedList<int[]> y;


    boolean windowAvail = false; 
    int lastTicket = 0; 
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
    }

    //think deli-counter ticketing
    public int takeTicket() {// this will block until resource is available. it will provide a ticket number.
                             // failure to return ticket will make resource unavailable
                             
        int myTicket = -1;// TODO:check for -1 return will indicate error
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

            E element = null; 
            int ticket = takeTicket(); 
            if (checkAvailable()) { 
                // element = buffer.get(tail); 
                element = buffer.get(tail); 
                buffer.set(tail, null); 
                tail = (tail + 1) % buffer.size(); 
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

            E element = null; 
            int ticket = takeTicket(); 
            if (checkAvailable()) { 
                // element = buffer.get(head); 
                int newest = (head + buffer.size() - 1) % buffer.size();//head points to next empty spot, head -1 is last added
                element = buffer.get(newest); 
                buffer.set(newest, null); 
                head = newest; 
            } else {
                System.out.println(
                        "last entry yoinked while waiting for ticket!");
            } // }
            returnTicket(ticket);
            return element; // return next;

        } else {
            return null;
        }
    }

    public void push(E input) {
        int ticket = takeTicket();
        System.out.println("buf siz: "+buffer.size());
        if(buffer.size()>0){
            buffer.set(head, input); 
            head = (head + 1) % buffer.size(); 
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

    public boolean checkAvailable() {
        return head != tail;
    }


}
