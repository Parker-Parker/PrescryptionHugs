public class AnimationNetworkOutput extends GenericNetworkInput {

    iUserInput userInput = null;
    iObserverOutput obsOutput = new AnimationNetworkObserverOutput();

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
