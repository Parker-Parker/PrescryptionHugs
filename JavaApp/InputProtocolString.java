import java.util.EnumMap;
import java.util.HashMap;

public class InputProtocolString {
    static final int[] command = { 0, 0, 0, 0, 0, 0 };

    EnumMap<InputOutputType, String> typeCodesEncode = new EnumMap<>(InputOutputType.class);
    HashMap<String, InputOutputType> typeCodesDecode = new HashMap<String, InputOutputType>();

    EnumMap<TurnState, String> typeUserCodesEncode = new EnumMap<>(TurnState.class);
    HashMap<String, TurnState> typeUserCodesDecode = new HashMap<String, TurnState>();

    public InputProtocolString() {

        typeCodesEncode.put(InputOutputType.User, "User".toUpperCase());
        typeCodesEncode.put(InputOutputType.Enemy, "Enemy".toUpperCase());
        typeCodesEncode.put(InputOutputType.Admin, "Admin".toUpperCase());
        typeCodesEncode.put(InputOutputType.System, "System".toUpperCase());
        typeCodesEncode.put(InputOutputType.Observer, "Observer".toUpperCase());
        typeCodesEncode.put(InputOutputType.Render, "Render".toUpperCase());
        for (InputOutputType key : typeCodesEncode.keySet()) {
            typeCodesDecode.put(typeCodesEncode.get(key), key);
        }

        typeUserCodesEncode.put(TurnState.playerDraw, "DRA");
        typeUserCodesEncode.put(TurnState.playerReady, "RDY");
        typeUserCodesEncode.put(TurnState.playerSacrifice, "SAC");
        typeUserCodesEncode.put(TurnState.playerSummon, "SUM");
        for (TurnState key : typeUserCodesEncode.keySet()) {
            typeUserCodesDecode.put(typeUserCodesEncode.get(key), key);
        }

    }


    public int[] parse(String text){

        int[] cmd = null;
        String[] args = text.split(" ");
        // System.out.println("Parsing..."); //debug
        
        if (args.length<3){
            System.out.println("Input rejected, too few parameters: "+text);
            return null;
        }
        else if(!(typeCodesDecode.get(args[0].toUpperCase())==null)){

            switch(typeCodesDecode.get(args[0].toUpperCase())){
                case Admin:
                    System.out.println("TODO: IO type valid but not yet implemented: "+text);//TODO Implement String command type
                    return null;
                    // break;
                case Enemy:
                    System.out.println("TODO: IO type valid but not yet implemented: "+text);//TODO Implement String command type
                    return null;
                    // break;
                case Observer:
                    System.out.println("TODO: IO type valid but not yet implemented: "+text);//TODO Implement String command type
                    return null;
                    // break;
                case Render:
                    System.out.println("TODO: IO type valid but not yet implemented: "+text);//TODO Implement String command type
                    return null;
                    // break;
                case System:
                    System.out.println("TODO: IO type valid but not yet implemented: "+text);//TODO Implement String command type
                    return null;
                    // break;
                case User:
                    return parseUser(text);
                    // break;
                default:
                    System.out.println("Ignoring command of unrecognized IO type: "+text);
                    return null;
                    // break;

            }
        }else{

            System.out.println("Ignoring command of unrecognized IO type: "+text);
            return null;
        }


    }

    public int[] parseUser(String text){

        String[] args = text.split(" ");
        // System.out.println("Parsing User Input command..."); //debug
        return  typeUserCodesDecode.get(args[1].toUpperCase()) == null? null :parseUserByState(typeUserCodesDecode.get(args[1].toUpperCase()), text);//sanity checking

    }

    public int[] parseUserByState(TurnState type, String text) { //this is legacy should work fine but could definitely be improved
        int[] cmd = command.clone();
        String[] args = text.split(" ");
        // System.out.println("Parsing User input command by type: " + type.name());

        if (args.length < 3) {
            System.out.println("UserInput: invalid user input: " + text);
        } else if (!args[0].equalsIgnoreCase("USER")) {
            System.out.println("UserInput: Ignoring non-USER command: " + text);

        } else if (!args[1].equalsIgnoreCase(typeUserCodesEncode.get(type))) {
            System.out.println(
                    "UserInput: Current request is: " + type.name() + "... Ignoring off topic USER command: " + text);

        } else // if(args[1].toUpperCase()!=typeCodes.get(type)){
        {
            cmd[0] = InputOutputType.User.ordinal();
            cmd[1] = type.ordinal();
            switch (type) {
                case playerDraw:
                    switch (args[2]) {
                        // return int[1]// return (deck)
                        // 1: Main
                        // 0: Squirrel
                        case "0":
                            cmd[2] = 0;
                            return cmd;
                        case "1":
                            cmd[2] = 1;
                            return cmd;
                        default:
                            System.out.println("UserInput: invalid command argument: " + text);
                            break;
                    }
                    break;
                case playerReady:
                    switch (args[2]) {
                        // return (action, card, slot)
                        // action: //card: //slot:
                        // 0: Bell // x //x
                        // 1: SummonDirect // 0-n: index in hand //0-3 index on field
                        // 2: SummonSacrifice // 0-n: index in hand //x
                        case "0":
                            cmd[2] = 0;
                            cmd[3] = 0;
                            cmd[4] = 0;
                            return cmd;
                        case "1":
                            if (args.length < 5) {
                                System.out.println("UserInput: Not enough arguments: " + text);
                            } else {
                                try {
                                    cmd[2] = 1;
                                    cmd[3] = Integer.parseInt(args[3]);
                                    cmd[4] = Integer.parseInt(args[4]);
                                    ;
                                    return cmd;
                                } catch (Exception E) {
                                    System.out.println("UserInput: Non integer arguments: " + text);
                                }
                            }
                            break;
                        case "2":// sac
                            if (args.length < 4) {
                                System.out.println("UserInput: Not enough arguments: " + text);
                            } else {
                                try {
                                    cmd[2] = 2;
                                    cmd[3] = Integer.parseInt(args[3]);
                                    cmd[4] = 0;
                                    return cmd;
                                } catch (Exception E) {
                                    System.out.println("UserInput: Non integer arguments: " + text);
                                }
                            }

                            break;
                        default:
                            System.out.println("UserInput: invalid command argument: " + text);

                            break;
                    }
                    break;
                case playerSacrifice:
                    switch (args[2]) {// return (action, card, slot)
                        // action: //card:
                        // 0: cancel // x
                        // 1: Sacrifice //0-3 index on field

                        case "0":
                            cmd[2] = 0;
                            cmd[3] = 0;
                            return cmd;
                        case "1":
                            if (args.length < 4) {
                                System.out.println("UserInput: Not enough arguments: " + text);
                            } else {
                                try {
                                    cmd[2] = 1;
                                    cmd[3] = Integer.parseInt(args[3]);
                                    return cmd;
                                } catch (Exception E) {
                                    System.out.println("UserInput: Non integer arguments: " + text);
                                }
                            }
                            break;
                        default:
                            System.out.println("UserInput: invalid command argument: " + text);

                            break;
                    }
                    break;
                case playerSummon:
                    switch (args[2]) {
                        case "0":
                            cmd[2] = 0;
                            cmd[3] = 0;
                            cmd[4] = 0;
                            cmd[5] = 0;
                            return cmd;
                        case "1":
                            cmd[2] = 1;
                            cmd[3] = 0;
                            cmd[4] = 0;
                            cmd[5] = 0;
                            return cmd;
                        case "2":
                            cmd[2] = 2;
                            cmd[3] = 0;
                            cmd[4] = 0;
                            cmd[5] = 0;
                            return cmd;
                        case "3":
                            cmd[2] = 3;
                            cmd[3] = 0;// coudvle just done a hashmap with strings as keys... D'OH!
                            cmd[4] = 0;
                            cmd[5] = 0;
                            return cmd;
                        default:
                            System.out.println("UserInput: invalid command argument: " + text);

                            break;
                    }
                    break;
                default:
                    System.out.println("UserInput: " + type.name() + " is unknown request type. command was: " + text);
                    break;
            }
        }
        return null;
    }

}
