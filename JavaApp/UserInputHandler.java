import java.util.LinkedList;


public class UserInputHandler {
    LinkedList<iUserInput> userInputs = new LinkedList<iUserInput>();
    TurnController game;

    public void register(iUserInput input) {
        userInputs.add(input);
        userInputs.sort(null);
    }

    public void requestInputBroadcast(){//TurnState,hand/cardlist) {

    }
    public void requestInputSatisfied(){//TurnState,hand/cardlist) {

    }

    public void getUserInput(){
        if(userInputs.peek()==null){
            //no interfaces exist, skip turn 
        }
        else {//block until input
            String commandString = null;//should really make a command class, that holds cmd, arg1, arg2...
            while (commandString==null){
                for (iUserInput in : userInputs){
                    
                    //should check in reverse priority
                    commandString = in.checkAvailable() ? commandString : in.pop();
                    // commandString = (in.peek() == null) ? commandString : in.pop();
                    //maybe also peek-compare command type

                }
            }
        }
    }



}
