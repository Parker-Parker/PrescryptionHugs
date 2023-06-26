import java.util.EnumMap;

public interface iObserverOutput {

    void push(EnumMap<ObserverTopics, String> latest);

    boolean checkAvailable();

    String pop();

    void setSub(int[] x);

}
