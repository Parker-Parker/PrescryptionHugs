import java.util.LinkedList;
// import java.util.ArrayList;

public class GenericNetworkInput implements iNetworkInput,iUserInput {

    SafeRingBuffer<int[]> userInputBuffer = new SafeRingBuffer<>();//might need to throw this in an iUserInput
    InputProtocolString protocol = new InputProtocolString();
    InputOutputType[] ioTypesByOrdinal = new InputOutputType[30];









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

    @Override
    public String peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    @Override
    public int[] pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public boolean checkAvailable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkAvailable'");
    }

    @Override
    public void cancelRequest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelRequest'");
    }

    @Override
    public void request(TurnState type, LinkedList<String> choices) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'request'");
    }

    @Override
    public int[] popParsed(TurnState type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'popParsed'");
    }
    //////////////////////////////////////////////////////////////////////////////// TODO: Do
    ////////////////////    v  v  Important Stuff   v  v   ///////////////////////// TODO:These
    //////////////////////////////////////////////////////////////////////////////// TODO: funcs

    @Override
    public iUserInput getUserInput() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserInput'");
    }

    @Override
    public boolean hasInputType(InputOutputType type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hasInputType'");
    }

    @Override
    public void register(TCPServerSlave newClient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public void process(String clientMessage) {
        int[] x = protocol.parse(clientMessage);
        if ((x!=null)&&(x.length>=1)){
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
