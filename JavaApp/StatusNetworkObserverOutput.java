import java.util.EnumMap;

public class StatusNetworkObserverOutput extends GenericNetworkObserverOutput{
    
    @Override
    public void push(EnumMap<ObserverTopics, String> latest) {
        if((latest!=null)&& latest.containsKey(ObserverTopics.MatchState)&&(latest.get(ObserverTopics.MatchState)!=null)){
            EnumMap<ObserverTopics, String> output = new EnumMap<ObserverTopics, String>(ObserverTopics.class);
            output.put(ObserverTopics.MatchState, latest.get(ObserverTopics.MatchState));
            this.obsOutputBuffer.push(output);
            // super.push(latest);
        }
    }
    
}
