import java.util.EnumMap;

public class AnimationNetworkObserverOutput extends GenericNetworkObserverOutput{
    
    @Override
    public void push(EnumMap<ObserverTopics, String> latest) {
        if((latest!=null)&& latest.containsKey(ObserverTopics.Animations)&&(latest.get(ObserverTopics.Animations)!=null)){
            this.obsOutputBuffer.push(latest);
            // super.push(latest);
        }
    }
    
}
