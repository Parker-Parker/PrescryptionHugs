import java.util.Scanner;


public class Test
{
    public static void main(String []args)
    {

        System.out.println("Welcome to Inscryption. Server running");
        System.out.println("generating test game...");
        Field testField = new Field();
        //testField.setDeck();
        //testField.setDeck();
        testField.bulkInit( );
        testField.printField();
        System.out.println("This should be true:  "+ testField.playCard(new Card(), 0) );
        System.out.println("This should be true:  "+ testField.playCard(new Card(), 1) );
        System.out.println("This should be false: "+ testField.playCard(new Card(), 2) );
        System.out.println("This should be true:  "+ testField.playCard(new Card(), 3) );
        testField.printField();
        // System.out.println("generating test card");
        // Card card = new Card();
        // System.out.println(card.getTitle());
        String kek = "poop";
        System.out.println("Test 2 begins...");
        TurnController game = new TurnController(testField);
        Scanner cmdIn = new Scanner(System.in);
        for(int i = 0;i<350;i++){
            //update other controllers
            game.executeState();
            game.getField().printField();
            if(true){
                System.out.println("Hit enter to continue...");
                kek = cmdIn.nextLine();
            }
        }
        

    }
};