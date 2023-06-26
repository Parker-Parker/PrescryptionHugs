
public interface iNetworkInput {

    iUserInput getUserInput();

    boolean hasInputType(InputOutputType type);

    void register(TCPServerSlave newClient);

    void process(String clientSentence);

    boolean hasNextLine();

    String nextLine();

    iObserverOutput getObserverOutput();

}
