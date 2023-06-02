import java.util.LinkedList;
import java.util.Scanner;

public class UserInputLocal implements iUserInput {
    int[] command = {0,0,0,0};
    InputBufferStuffer buffer;
    TurnState mode = null;

    public UserInputLocal(){
        buffer =  new InputBufferStuffer(new Scanner(System.in));

    }

    
    @Override
    public void request(TurnState type, LinkedList<String> choices) {
        mode = type;
        System.out.println("User input of type: "+type.name()+" requested, listen mode changed");
        if(choices!=null&&choices.peek()!=null){
            String optInfo = "Options for "+type.name()+": ";
            for (int i = 0;i<choices.size();i++){//for each in list         //String s : choices){
                optInfo = optInfo +i +") "+choices.get(i)+" ";
            }
        }



        // switch (mode) {
        //     case playerDraw:

        //     case playerReady:

        //     case playerSacrifice:

        //     case playerSummon:


        // }
    }    
    
    
    
    
    
    
    
    
    @Override
    public String peek() {
        return null;

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

    public void parse(String text){

    }
    public void read(){
        
    }

}
