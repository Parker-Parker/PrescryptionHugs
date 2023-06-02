import java.util.LinkedList;

public interface iUserInput {

    String peek();

    int[] pop();

    boolean checkAvailable();

    void cancelRequest();

    void request(TurnState type, LinkedList<String> choices);

}
