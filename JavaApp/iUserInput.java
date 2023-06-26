import java.util.LinkedList;

public interface iUserInput {

    boolean checkAvailable();

    void cancelRequest();

    void request(TurnState type, LinkedList<String> choices);

    int[] popParsed(TurnState type);

    void push(int[] x);

}
