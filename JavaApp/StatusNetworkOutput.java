public class StatusNetworkOutput extends GenericNetworkInput {

    iUserInput userInput = null;
    iObserverOutput obsOutput = new StatusNetworkObserverOutput();

    @Override
    public boolean hasInputType(InputOutputType type) {
        switch(type){
            case Observer:
                return (true);
            default:
                return false;
        }
    }
    
}
