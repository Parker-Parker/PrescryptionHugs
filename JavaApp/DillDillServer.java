import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class DillDillServer
{
    static LinkedList<Card> mDeck = new LinkedList<Card>(Arrays.asList(new Stoat(), new Squirrel(), new WolfCub(), new Coyote(), new Mantis(), new Bullfrog(), new RiverSnapper(), new Alpha(), new Grizzly(), new WolfCub(), new Adder(), new Grizzly(), new Sparrow(), new Bat(), new Grizzly()));
        
    public static void main(String []args)
    {
        System.out.println("Welcome to Inscryption. Server running");
        System.out.println("generating test game...");
        // Field testField = new Field();
        // testField.bulkInit( );
        // testField.printField();
        // testField.enemyCards[0].giveSigil(Sigils.Airborne);
        // testField.playerCards[1].giveSigil(Sigils.SharpQuills);
        // testField.enemyCardsBack[3] = new Card(4, 4, "MONGO");
        // testField.enemyCardsBack[3].setField(testField);
        // testField.enemyCardsBack[3].giveSigil(Sigils.SharpQuills);
        // testField.enemyCardsBack[3].giveSigil(Sigils.Airborne);
        // testField.enemyCardsBack[3].giveSigil(Sigils.MightyLeap);
        // testField.enemyCardsBack[3].giveSigil(Sigils.TrifurcatedStrike); 
        // testField.enemyCardsBack[3].giveSigil(Sigils.WorthySacrifice);
        // testField.enemyCardsBack[3].giveSigil(Sigils.DeathTouch);// quill combo does not yet work

        // Card testCard1 = new Card(4, 4, "Chungy");
        // testCard1.cost = 2;
        // Card testCard2 = new Card(4, 1, "Sploinks");
        // testCard2.cost = 1;
        // Card testCard3 = new Card(0, 12, "big rock");
        // testCard3.cost = 0;
        // testCard3.value = 0;
        
        // testField.getHand().add(testCard1);
        // testField.getHand().add(testCard2);
        // testField.getHand().add(testCard3);

        Field testField = new Game42();
        TurnController turnCon = new TurnController(testField);
        TCPServerMaster serverMaster = new TCPServerMaster(turnCon.ioHandler);
        serverMaster.start();
        
        // Scanner cmdIn = new Scanner(System.in);
        // System.out.println("Hit enter when all connections established...");
        // System.out.println(cmdIn.nextLine());
        
    
        //get first field
        //wait for clients
        GuiFieldPicker fieldPicker = new GuiFieldPicker(0);
        while(fieldPicker.waiting()){
        }
        testField = fieldPicker.getField();
        turnCon.setField(testField);
        fieldPicker.close();

        boolean itsGameTime = true;



        
        String handsString = "";
        int loopctr = 0;
        int score = 0;
        /////TODO while true in the future
        // for(int i = 0;i<450;i++){
        while(true){
            fieldPicker = null;
            FieldStartupAnimator.animatePutdown(testField,turnCon.ioHandler.getObserverOutputHandler());

            while(itsGameTime){
                //update other controllers
                turnCon.executeState();
                turnCon.getField().printField();
    
                turnCon.getField().setHand(turnCon.getField().deepCopyDeck(mDeck) );
    
                loopctr = 0;
                handsString = "hand: ";
                for (Card c : turnCon.getField().getHand()){
                    handsString = handsString +" "+loopctr+") "+c.title+" "+c.cost+" "+c.attack+" "+c.health;
                    loopctr++;
                }
                System.out.println(handsString);
                // turnCon.ioHandler.observerOutputHandler.publishField(testField);
                turnCon.ioHandler.getObserverOutputHandler().publishAnim(testField, 1, 3, Animations.Idle);
                turnCon.getField().updateCardStats();
                score = turnCon.getField().scale;
                itsGameTime = score>=5? false : score>-5; 
            }

            FieldStartupAnimator.animatePickup(testField,turnCon.ioHandler.getObserverOutputHandler());

            //clear field
            turnCon.getField().clear();
            turnCon.ioHandler.getObserverOutputHandler().publishAnim(testField, 1, 3, Animations.Idle);
                
            //get next field
            fieldPicker = new GuiFieldPicker(score);
            while(fieldPicker.waiting()){
            }
            testField = fieldPicker.getField();
            turnCon.setField(testField);
            fieldPicker.close();
            itsGameTime = true;
        }
        
        
    }
};