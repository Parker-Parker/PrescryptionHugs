import java.util.LinkedList;

public class UserInputLocal implements iUserInput {
    int[] command = {0,0,0,0};
    @Override
    public String peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    @Override
    public int[] pop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pop'");
    }

    @Override
    public boolean checkAvailable() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkAvailable'");
    }

    @Override
    public void cancelRequest() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cancelRequest'");
    }

    @Override
    public void request(TurnState type, LinkedList<String> choices) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'request'");
    }
    public void parse(String text){

    }
    public void read(){
        
    }

}
