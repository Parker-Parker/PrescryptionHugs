import java.util.EnumMap;



public class GenericNetworkObserverOutput implements iObserverOutput{
    EnumMap<ObserverTopics, String> rawMsg;
    EnumMap<ObserverTopics, Boolean> subscriptions = new EnumMap<>(ObserverTopics.class);
    ObserverOutputHandler handler;

    SafeRingBuffer<EnumMap<ObserverTopics, String>> obsOutputBuffer = new SafeRingBuffer<>(50);
    

    public GenericNetworkObserverOutput() {
        for(ObserverTopics key : ObserverTopics.values()) {
            subscriptions.put(key, true);
        }
    }

    @Override
    public void push(EnumMap<ObserverTopics, String> latest) {
        obsOutputBuffer.push(latest);
    }

    @Override
    public boolean checkAvailable() {
        return obsOutputBuffer.checkAvailable();
    }

    @Override
    public String pop() {
        rawMsg = obsOutputBuffer.popOld();
        boolean first = true;
        String outMsg = "";
        for (ObserverTopics topic:ObserverTopics.values()){
            if(rawMsg.containsKey(topic)){
                if(!first){
                    outMsg = outMsg+";";
                }
                first = false;
                outMsg = outMsg+ rawMsg.get(topic);
            }
            else{
            }
        }
        return outMsg;
    }

    @Override
    public void setSub(int[] x) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'setSub'");
        System.out.println("setSub not implemented");
    }

    @Override
    public boolean checkSub(ObserverTopics topic) {
        return subscriptions.containsKey(topic)&&(subscriptions.get(topic)==true);
    }

    @Override
    public void setHandler(ObserverOutputHandler observerOutputHandler) {
        this.handler = observerOutputHandler;
    }

    @Override
    public ObserverOutputHandler getHandler() {
        return handler;
    }

}
