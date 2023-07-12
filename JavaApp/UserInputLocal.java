import java.util.LinkedList;
import java.util.Scanner;
import java.util.EnumMap;

public class UserInputLocal implements iUserInput {
    int[] command = {0,0,0,0};
    InputBufferStuffer buffer;
    TurnState mode = null;
    EnumMap<TurnState, String> typeCodes = new EnumMap<>(TurnState.class);
    public UserInputLocal(){
        
             
        typeCodes.put(TurnState.playerDraw,"DRA");
        typeCodes.put(TurnState.playerReady,"RDY");  //thinking
        typeCodes.put(TurnState.playerSacrifice,"SAC");
        typeCodes.put(TurnState.playerSummon,"SUM");

        buffer =  new InputBufferStuffer(new Scanner(System.in));
        buffer.start();

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
            System.out.println("User Input Options: "+optInfo);
        }



    }    
    
 

    @Override
    public boolean checkAvailable() {//can i pop?? .D~8)

        while(buffer.checkAvailable()){
            if(buffer.peek()==null){

            }
            else{

            }
        }
        return false;
    }

    @Override
    public void cancelRequest() {
        System.out.println(this.mode.name()+" request closed");
        this.mode = null;
    }

    public int[] parse(String text){
        
        return null;
    }
    public int[] parse(TurnState type, String text){
        int[] cmd ={0,0,0,0};
        String[] args = text.split(" ");
        System.out.println("Parsing...");
        
        for (String a : args){
            System.out.println(a);    
        }
        System.out.println(args);
        if (args.length<3){
            System.out.println("UserInputLocal: invalid user input: "+text);
        }
        else if(!args[0].equalsIgnoreCase("USER")){
            System.out.println("UserInputLocal: Ignoring non-USER command: "+text);
            
        }
        else if(!args[1].equalsIgnoreCase(typeCodes.get(type))){
            System.out.println("UserInputLocal: Current request is: "+type.name()+"... Ignoring off topic USER command: "+text);
            
        }
        else //if(args[1].toUpperCase()!=typeCodes.get(type)){
        {

            switch(type)       {
                case playerDraw:
                    switch(args[2])       {
                        //return int[1]// return (deck)
                        // 1: Main  
                        // 0: Squirrel
                        case "0":
                            cmd[0] =0;
                            return cmd;               
                        case "1":
                            cmd[0] =1;
                            return cmd;
                        default:
                        System.out.println("UserInputLocal: invalid command argument: "+text);
                            break;
                    }   break;
                case playerReady:
                    switch(args[2])       {
                        // return (action, card, slot)  
                        //action:                   //card:                 //slot:           
                        // 0: Bell                  // x                    //x                                                            
                        // 1: SummonDirect          // 0-n: index in hand   //0-3 index on field                                                                              
                        // 2: SummonSacrifice       // 0-n: index in hand   //x
                        case "0":
                            cmd[0] = 0;
                            cmd[1] = 0;
                            cmd[2] = 0;
                            return cmd;
                        case "1":
                            if(args.length<5){
                                System.out.println("UserInputLocal: Not enough arguments: "+text);
                            }
                            else{
                                try{
                                    cmd[0] = 1;
                                    cmd[1] = Integer.parseInt( args[3]);
                                    cmd[2] = Integer.parseInt( args[4]);;
                                    return cmd;
                                } catch (Exception E){
                                    System.out.println("UserInputLocal: Non integer arguments: "+text);
                                }
                            }
                            break;
                        case "2"://sac
                            if(args.length<4){
                                System.out.println("UserInputLocal: Not enough arguments: "+text);
                            }
                            else{
                                try{
                                    cmd[0] = 2;
                                    cmd[1] = Integer.parseInt( args[3]);
                                    cmd[2] = 0;
                                    return cmd;
                                } catch (Exception E){
                                    System.out.println("UserInputLocal: Non integer arguments: "+text);
                                }
                            }
        
                            break;
                        default:
                        System.out.println("UserInputLocal: invalid command argument: "+text);
                
                            break;
                    }   break;
                case playerSacrifice:
                    switch(args[2])       {// return (action, card, slot)  
                        //action:                   //card:                          
                        // 0: cancel                // x                                                                          
                        // 1: Sacrifice             //0-3 index on field                                                                              
                        
                        case "0":
                            cmd[0] = 0;
                            cmd[1] = 0;
                            break;
                        case "1":
                            if(args.length<4){
                                System.out.println("UserInputLocal: Not enough arguments: "+text);
                            }
                            else{
                                try{
                                    cmd[0] = 1;
                                    cmd[1] = Integer.parseInt( args[3]);
                                    return cmd;
                                } catch (Exception E){
                                    System.out.println("UserInputLocal: Non integer arguments: "+text);
                                }
                            }
                            break;
                        default:
                        System.out.println("UserInputLocal: invalid command argument: "+text);
                
                            break;
                    }   break;
                case playerSummon:
                    switch(args[2])       {// return (slot)  
                        //slot:                                             
                        // 0: Slot                                                                                         
                        // 1: Slot             
                        // 2: slot                                                                                    
                        // 3: Slot               
                        case "0":
                            cmd[0] = 0;
                            cmd[1] = 0;
                            cmd[2] = 0;
                            cmd[3] = 0;
                            break;
                        case "1":
                            cmd[0] = 1;
                            cmd[1] = 0;
                            cmd[2] = 0;
                            cmd[3] = 0;
                            break;
                        case "2":
                            cmd[0] = 2;
                            cmd[1] = 0;
                            cmd[2] = 0;
                            cmd[3] = 0;
                            break;
                        case "3":
                            cmd[0] = 3;
                            cmd[1] = 0;//coudvle just done a hashmap with strings as keys... D'OH!
                            cmd[2] = 0;
                            cmd[3] = 0;
                            break;
                        default:
                        System.out.println("UserInputLocal: invalid command argument: "+text);
                
                            break;
                    }   break;
                default:
                    System.out.println("UserInputLocal: "+type.name()+" is unknown request type. command was: "+text);// should probably add some null handling for type above.
                    break;
            }
        }
        return null;
    }
    public void read(){
        
    }


    @Override
    public int[] popParsed(TurnState type) {
        if(buffer.checkAvailable()){
            return parse(type, buffer.readLine());
    
    
        }
        else{
            return null;
        }
    }


    @Override
    public void push(int[] x) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'push'");
    }
}
