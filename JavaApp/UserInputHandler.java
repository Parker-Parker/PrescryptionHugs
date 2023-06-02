import java.util.LinkedList;


public class UserInputHandler {
    LinkedList<iUserInput> userInputs = new LinkedList<iUserInput>();
    TurnController game;
    int[] play_args;

    public void register(iUserInput input) {
        userInputs.add(input);
        userInputs.sort(null);
    }

    public void requestInputBroadcast(){//TurnState,hand/cardlist) {

    }
    public void requestInputSatisfied(){//TurnState,hand/cardlist) {

    }

    // public void getUserInput(){
    //     if(userInputs.peek()==null){
    //         //no interfaces exist, skip turn 
    //     }
    //     else {//block until input
    //         String commandString = null;//should really make a command class, that holds cmd, arg1, arg2...
    //         while (commandString==null){
    //             for (iUserInput in : userInputs){
                    
    //                 //should check in reverse priority
    //                 commandString = in.checkAvailable() ? commandString : in.pop();
    //                 // commandString = (in.peek() == null) ? commandString : in.pop();
    //                 //maybe also peek-compare command type

    //             }
    //         }
    //     }
    // }


    public int[] getUserInput(TurnState type, LinkedList<String> choices){
        if(userInputs.peek()==null){
            //no interfaces exist, skip turn 
            return defaultCommand(type);
        }
        else {//block until input
            for (iUserInput in : userInputs){       
                // in.request(type);
                in.request(type, choices);
            }
            int[] cmd = null;//should really make a command class, that holds cmd, arg1, arg2...
            while (cmd==null){
                for (iUserInput in : userInputs){

                    // String message = in.checkAvailable() ? "Accepted command from "+in.peek().getName();
                    cmd = in.checkAvailable() ? in.pop() : cmd;// not sure if I should add type to these
                }
            }
            for (iUserInput in : userInputs){       
                // in.request(type);
                // in.cancelRequest(type, message);
                in.cancelRequest();
            }
            return cmd;
            
        }
    }



    int rando = 0;
    private int[] defaultCommand(TurnState type) {
        
        int[] cmd = new int[4];
        switch(type){
            case playerDraw:
                cmd[0] = 1; //main deck
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
            case playerReady:
                cmd[0] = 0; //bell
                cmd[1] = 0; //  x
                cmd[2] = 0; //  x
                cmd[3] = 0; 
            case playerSacrifice:
                cmd[0] = 0; //cancel
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
            case playerSummon:
                rando = (rando+1)%4;
                cmd[0] = rando;//idk
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
            // case default:
            //     cmd[0] = 0;//idk
            //     cmd[1] = 0;
            //     cmd[2] = 0;
            //     cmd[3] = 0;
        }
        return cmd;
    }

    public void parseCMD(String s){

    }





    // public int[] getDrawInput() {
    //     if(userInputs.peek()==null){
    //         //no interfaces exist, skip turn 
    //     }
    //     else {//block until input
    //         for(iUserInput in : this.userInputs ){
    //             in.requestPlayerDraw()
    //         }
    //         int[] cmd = null;//should really make a command class, that holds cmd, arg1, arg2...
    //         while (commandString==null){
    //             for (iUserInput in : userInputs){
                    
    //                 //should check in reverse priority
    //                 commandString = in.checkAvailable() ? commandString : in.pop();
    //                 // commandString = (in.peek() == null) ? commandString : in.pop();
    //                 //maybe also peek-compare command type

    //             }
    //         }
    //     }    
    // }

    // public int[] getSummonInput() {
    //     return null;
    // }

    // public int[] getSacrificeInput() {
    //     return null;
    // }

    // public int[] getPlayerReadyInput() {
    //     return null;
    // }

}