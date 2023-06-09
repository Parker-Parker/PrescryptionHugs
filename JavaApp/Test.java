import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Test
{
    public static void main(String []args)
    {

        // System.out.println("beginning input test");
        // Scanner test1 = new Scanner(System.in);
        // for(int i=0; i<500;i++){
        //     try{TimeUnit.SECONDS.sleep(1); }
        //         catch (Exception e){System.out.println(e);}
        //     System.out.println("Time = +" +i);    
        //     if(test1.hasNextLine()){
        //         System.out.println("Received: <"+test1.nextLine()+">");
        //     }
        // }


        // System.out.println("beginning input test");
        // InputBufferStuffer buff = new InputBufferStuffer(new Scanner(System.in));
        // buff.start();
        // for(int i=0; i<8000;i++){
        //     try{TimeUnit.MILLISECONDS.sleep(1); }
        //         catch (Exception e){System.out.println(e);}
        //     if(i%200==0){    
        //         System.out.println("Time = +" +i);}    
        //     if(buff.checkAvailable()){
        //         System.out.println("Received: <"+buff.readLine()+">");
        //     }
        // }





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
        // System.out.println(card.getTitle());F
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


        
        
        // String kek = "poop";
        // System.out.println("Test 2 begins...");
        // TurnController game = new TurnController(testField);
        // // Scanner cmdIn = new Scanner(System.in);
        // for(int i = 0;i<45;i++){
        //     //update other controllers
        //     game.executeState();
        //     game.getField().printField();
        //     if(true){
        //         System.out.println("Hit enter to continue...  "+ i);
        //         kek = cmdIn.nextLine();
        //     }
        // }
        
        
        String kek = "poop";
        System.out.println("Test 2 begins...");
        TurnController game = new TurnController(testField);
        game.user.register(new UserInputLocal());
        ServerHandler server = new ServerHandler(game.user);
        // Scanner cmdIn = new Scanner(System.in);
        for(int i = 0;i<45;i++){
            //update other controllers
            game.executeState();
            game.getField().printField();
            if(true){
                // System.out.println("Hit enter to continue...  "+ i);
                // kek = cmdIn.nextLine();
            }
        }
        

    }
};