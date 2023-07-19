import java.util.LinkedList;


public class UserInputHandler {
    LinkedList<iUserInput> userInputs = new LinkedList<iUserInput>();
    TurnController game;
    int[] play_args;

    public void register(iUserInput input) { //TODO:need a deregister option
        userInputs.add(input);
    }

    public void requestInputBroadcast(){//TurnState,hand/cardlist) {

    }
    public void requestInputSatisfied(){//TurnState,hand/cardlist) {

    }



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
                for (iUserInput in : userInputs){//xrashes if new input added midgame// maybe should clone and itereate thru clone?// TODO:

                    cmd = (cmd == null) ? in.popParsed(type) : cmd;// not sure if I should add type to these
                }
            }
            for (iUserInput in : userInputs){       
                in.cancelRequest();
            }
            return cmd;
            
        }
    }



    int rando = 0;
    private int[] defaultCommand(TurnState type) {
        
        int[] cmd = new int[]{0,0,0,0};
        switch(type){
            case playerDraw:
                cmd[0] = 1; //main deck
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
                break;
            case playerReady:
                cmd[0] = 0; //bell
                cmd[1] = 0; //  x
                cmd[2] = 0; //  x
                cmd[3] = 0;
                break; 
            case playerSacrifice:
                cmd[0] = 0; //cancel
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
                break;
            case playerSummon:
                rando = (rando+1)%4;
                cmd[0] = rando;//idk
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
                break;
            default:
                cmd[0] = 0;//idk
                cmd[1] = 0;
                cmd[2] = 0;
                cmd[3] = 0;
        }
        System.out.println("default user input");
        printCMD(type, cmd);
        return cmd;
    }

    public void printCMD(TurnState type, int[] cmd){
        String out = "CMD "+type.name()+": " ;
        for(int i : cmd){
            out = out+" "+i;
        }
        System.out.println(out);
    }

    public void clear() {
        for (iUserInput in : userInputs){//xrashes if new input added midgame// maybe should clone and itereate thru clone?// TODO:
            while(in.checkAvailable()){
                System.out.println(in.popParsed(TurnState.playerReady));
            }
        }
    }





}