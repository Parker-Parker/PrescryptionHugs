import java.util.Scanner;


public class Test
{
    public static void main(String []args)
    {
        System.out.println("Welcome to Inscryption. Server running");
        System.out.println("generating test game...");
        Field testField = new Field();
        testField.bulkInit( );
        testField.printField();
        System.out.println("This should be true:  "+ testField.playCard(new Card(), 0) );
        System.out.println("This should be true:  "+ testField.playCard(new Card(), 1) );
        System.out.println("This should be false: "+ testField.playCard(new Card(), 2) );
        System.out.println("This should be true:  "+ testField.playCard(new Card(), 3) );
        testField.printField();
        testField.enemyCards[0].giveSigil(Sigils.Airborne);
        testField.playerCards[1].giveSigil(Sigils.SharpQuills);
        testField.enemyCardsBack[3] = new Card(4, 4, "MONGO");
        testField.enemyCardsBack[3].setField(testField);
        testField.enemyCardsBack[3].giveSigil(Sigils.SharpQuills);
        testField.enemyCardsBack[3].giveSigil(Sigils.Airborne);
        testField.enemyCardsBack[3].giveSigil(Sigils.MightyLeap);
        testField.enemyCardsBack[3].giveSigil(Sigils.TrifurcatedStrike); 
        testField.enemyCardsBack[3].giveSigil(Sigils.WorthySacrifice);
        testField.enemyCardsBack[3].giveSigil(Sigils.DeathTouch);// quill combo does not yet work

        
        
        String kek = "poop";
        System.out.println("Test 2 begins...");
        TurnController game = new TurnController(testField);
        game.user.register(new UserInputLocal());
        Scanner cmdIn = new Scanner(System.in);
        kek = cmdIn.nextLine();
        for(int i = 0;i<65;i++){
            //update other controllers
            game.executeState();
            game.getField().printField();
            
        }
        

    }
};