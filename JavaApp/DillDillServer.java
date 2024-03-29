import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class DillDillServer
{
    static LinkedList<Card> mDeck = new LinkedList<Card>(Arrays.asList(
    new Squirrel(),    
    new Stoat()       ,               
    new Wolf()       ,              
    new WolfCub()    ,                 
    new Adder()      ,                     
    new Bullfrog()   ,                   
    new Cat()        ,                  
    new BlackGoat()  ,                  
    new Grizzly()    ,               
    new Mantis()     ,               
    new MantisGod()  ,                  
    new StrangeLarva(),                 
    new Porcupine()    ,                      
    new Skunk()           ,          
    new RiverSnapper()     ,           
    new Sparrow()           ,     
    new Alpha()  
    
    
    
    //new Squirrel(), new Stoat(), new WolfCub(), new Coyote(), new Mantis(), new Bullfrog(), new RiverSnapper(), new Alpha(), new Grizzly(), new WolfCub(), new Adder(), new Grizzly(), new Sparrow(), new Bat(), new Grizzly()
    
    ));
        
    public static void main(String []args)
    {
        System.out.println("Welcome to Inscryption. Server running");
        System.out.println("generating test game...");

        Field testField = new Game42();
        TurnController turnCon = new TurnController(testField);
        TCPServerMaster serverMaster = new TCPServerMaster(turnCon.ioHandler);
        serverMaster.start();
    
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

        while(true){
            testField.initPresetCards();
            fieldPicker = null;
            turnCon.ioHandler.getUserInputHandler().clear();
            turnCon.setState(TurnState.playerDeal);
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