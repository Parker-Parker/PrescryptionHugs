import java.util.LinkedList;
// import java.util.ArrayList;
import java.util.Arrays;

public class GenericNetworkInput implements iNetworkInput {

    // SafeRingBuffer<int[]> userInputBuffer = new SafeRingBuffer<int[]>();//might need to throw this in an iUserInput
    InputProtocolString protocol = new InputProtocolString();
    InputOutputType[] ioTypesByOrdinal = new InputOutputType[30];
    TCPServerSlave clientConnection;

    iUserInput userInput = new GenericNetworkUserInput();
    iObserverOutput obsOutput = new GenericNetworkObserverOutput();

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
    ////////////////////    v  v  Important Stuff   v  v   ///////////////////////// TODO:These
    //////////////////////////////////////////////////////////////////////////////// TODO: funcs

    @Override
    public iUserInput getUserInput() {
        return this.userInput; //returning <this> is temporary// should make an EnumMap<InputOutputType,iInput>// shouldnt do that, just have a field for iUserInput, iAdminInput...//either way stop having this function implement i<>Input and have a field do it instead
        }

    @Override
    public boolean hasInputType(InputOutputType type) {
        switch(type){
            case Observer:
                return !(this.getObserverOutput()==null);
            case User:
                return !(this.getUserInput()==null);//TODO: this REALLY needs to be changed to the genNetInp's iUserInput object derectly (not this.getUserInput) once its has bee implemented
            default:
                return false;
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
                    if(x[0]==2){
                        this.obsOutput.setSub(x);
                    }
                    if(x[0]==1){
                        this.obsOutput.getHandler().requestLatest();;//hackyyyyyyyyyyyyyyyy
                    }
                    
                    break;
                case Render:
                    break;
                case System:
                    break;
                case User:
                    this.userInput.push(x);
                    // userInputBuffer.push(x);
                    break;
                default:
                    break;
            }
        }
        else{
            System.out.println("clientMessage could not be parsed :"+clientMessage);
        }
    }

    //outputs in queue
    @Override
    public boolean hasNextLine() {
        return this.obsOutput.checkAvailable();
    }

    @Override
    public String nextLine() {

        return this.obsOutput.pop();

    }

    @Override
    public iObserverOutput getObserverOutput() {
        return this.obsOutput;
    }   
}
