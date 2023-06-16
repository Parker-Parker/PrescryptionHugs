import java.util.LinkedList;
// import java.util.ArrayList;
import java.util.Arrays;

public class GenericNetworkInput implements iNetworkInput,iUserInput {

    SafeRingBuffer<int[]> userInputBuffer = new SafeRingBuffer<int[]>();//might need to throw this in an iUserInput
    InputProtocolString protocol = new InputProtocolString();
    InputOutputType[] ioTypesByOrdinal = new InputOutputType[30];
    TCPServerSlave clientConnection;









    public GenericNetworkInput(LinkedList<InputOutputType> ioTypes) {
        for(InputOutputType t:InputOutputType.values()){
            ioTypesByOrdinal[t.ordinal()] = t;
        }
    }

    public GenericNetworkInput() {

        for(InputOutputType t:InputOutputType.values()){
            ioTypesByOrdinal[t.ordinal()] = t;
        }
    }
    //////////////////////////////////////////////////////////////////////////////// TODO: Do
    ////////////////////    v  v  iUserInput Stuff   v  v   ///////////////////////// TODO:These
    //////////////////////////////////////////////////////////////////////////////// TODO: funcs

    @Override
    public String peek() {
        return null;
    }

    @Override
    public int[] pop() {        return null;
    }

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
    //////////////////////////////////////////////////////////////////////////////// TODO: Do
    ////////////////////    v  v  Important Stuff   v  v   ///////////////////////// TODO:These
    //////////////////////////////////////////////////////////////////////////////// TODO: funcs

    @Override
    public iUserInput getUserInput() {
        return this; //this is temporary// should make an EnumMap<InputOutputType,iInput>// shouldnt do that, just have a field for iUserInput, iAdminInput...//either way stop having this function implement i<>Input and have a field do it instead
        }

    @Override
    public boolean hasInputType(InputOutputType type) {
        switch(type){
            case User:
                return !(this.getUserInput()==null);//TODO: this REALLY needs to be changed to the genNetInp's iUserInput object derectly (not this.getUserInput) once its has bee implemented
                // break;
            default:
                return false;
                // break;

        }
    }

    @Override
    public void register(TCPServerSlave newClient) {//maybe set this to setClient, since there can only be one client
        this.clientConnection = newClient;
        if(this.clientConnection.getParent()==null || this.clientConnection.getParent() != this){
            this.clientConnection.setParent(this);
        }
    }

    @Override
    public void process(String clientMessage) {
        int[] x = protocol.parse(clientMessage);
        if ((x!=null)&&(x.length>=1)){
            System.out.println("Processed input of type: "+ioTypesByOrdinal[x[0]].name()+" from client: " + this.clientConnection.getClientName());

            switch(ioTypesByOrdinal[x[0]]){
                case Admin:
                    break;
                case Enemy:
                    break;
                case Observer:
                    break;
                case Render:
                    break;
                case System:
                    break;
                case User:
                    userInputBuffer.push(x);
                    break;
                default:
                    break;
            }
        }
        else{
            System.out.println("clientMessage could not be parsed :"+clientMessage);
        }
    }

}
