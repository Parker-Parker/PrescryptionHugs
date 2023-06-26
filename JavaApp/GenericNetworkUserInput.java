import java.util.LinkedList;
// import java.util.ArrayList;
import java.util.Arrays;


public class GenericNetworkUserInput implements iUserInput{

    SafeRingBuffer<int[]> userInputBuffer = new SafeRingBuffer<int[]>();//might need to throw this in an iUserInput
    


    //////////////////////////////////////////////////////////////////////////////// TODO: Do
    ////////////////////    v  v  iUserInput Stuff   v  v   ///////////////////////// TODO:These
    //////////////////////////////////////////////////////////////////////////////// TODO: funcs

   
    @Override
    public boolean checkAvailable() {
        return userInputBuffer.checkAvailable();
    }

    @Override
    public void cancelRequest() {
        userInputBuffer.wipe();
    }

    @Override
    public void request(TurnState type, LinkedList<String> choices) {
        //currently dows nothing
        // maybe I can make and optional debug message ringbuffer that this populates and TCPServer checks then writes to client//TCPServerSlave will hold the ring buffer(Mayber?)and it should be set to null if unused(if client cant handle random unformatted text lines (ie it doesn't need any important info lol)) 
    }

    @Override
    public int[] popParsed(TurnState type) {//pops commands(oldest first) off input buffer until it finds a command of correct type, returns oldest user command of type or null if none found.
        int[] out = null;
        int[] nextCmd = null;
        while((out==null)&&userInputBuffer.checkAvailable()){
            nextCmd = userInputBuffer.popOld();
            if((nextCmd!=null)&&(nextCmd.length==6)&&(nextCmd[1]==type.ordinal())){
                out = Arrays.copyOfRange(nextCmd, 2, 6);
            }
        }
        return out;
    }

    @Override
    public void push(int[] x) {
        this.userInputBuffer.push(x);
    }
    
}
