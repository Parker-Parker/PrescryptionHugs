import java.util.EnumMap;

public interface iObserverOutput {

    void push(EnumMap<ObserverTopics, String> latest);

    boolean checkAvailable();

    String pop();

    void setSub(int[] x);

    boolean checkSub(ObserverTopics animations);

    void setHandler(ObserverOutputHandler observerOutputHandler);

    ObserverOutputHandler getHandler();

}
