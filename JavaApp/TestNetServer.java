import java.util.Scanner;

public class TestNetServer
{
    public static void main(String []args)
    {
        System.out.println("Welcome to Inscryption. Server running");
        System.out.println("generating test game...");
        Field testField = new Field();
        testField.bulkInit( );
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

        Card testCard1 = new Card(4, 4, "Chungy");
        testCard1.cost = 2;
        Card testCard2 = new Card(4, 1, "Sploinks");
        testCard2.cost = 1;
        Card testCard3 = new Card(0, 12, "big rock");
        testCard3.cost = 0;
        testCard3.value = 0;
        
        testField.getHand().add(testCard1);
        testField.getHand().add(testCard2);
        testField.getHand().add(testCard3);


        TurnController game = new TurnController(testField);
        TCPServerMaster serverMaster = new TCPServerMaster(game.ioHandler);
        serverMaster.start();
        
        Scanner cmdIn = new Scanner(System.in);
        System.out.println("Hit enter when all connections established...");
        System.out.println(cmdIn.nextLine());
        
        String handsString = "";
        int loopctr = 0;
        for(int i = 0;i<450;i++){
            //update other controllers
            game.executeState();
            game.getField().printField();
            loopctr = 0;
            handsString = "hand: ";
            for (Card c : game.getField().getHand()){
                handsString = handsString +" "+loopctr+") "+c.title+" "+c.cost+" "+c.attack+" "+c.health;
                loopctr++;
            }
            System.out.println(handsString);
            // game.ioHandler.observerOutputHandler.publishField(testField);
            game.ioHandler.getObserverOutputHandler().publishAnim(testField, 0, 0, Animations.Attack);
            
        }
        

    }
};