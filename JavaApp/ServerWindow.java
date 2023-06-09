import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ServerWindow
{
    public static void main(String []args)
    {

        System.out.println("Welcome to Inscryption. Server running");
        System.out.println("generating new game...");
        Field mainField = new Field();
        mainField.printField();
        TurnController game = new TurnController(mainField);
        ServerHandler server = new ServerHandler(game.user);
        

        //test only
        Scanner sysIn = new Scanner(System.in);
        System.out.println("Is this a test game(Y/n)");
        String input = sysIn.nextLine();
        sysIn = null;
        if(input.toLowerCase().charAt(0) == 'y'){
            System.out.println("Generating test field");
            mainField.bulkInit();
            mainField.printField();
        }
        else{
            //add this
        }


        for(int i = 0;i<45;i++){
            //update other controllers
            game.executeState();
            if((game.state == TurnState.playerBegin)||(game.state == TurnState.leshyBegin))
            { 
                game.getField().printField();
            }    
        }

    }
};