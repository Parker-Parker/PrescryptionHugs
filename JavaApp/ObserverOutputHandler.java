import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;

public class ObserverOutputHandler {

    LinkedList<iObserverOutput> observerOutputs = new LinkedList<iObserverOutput>();
    TurnController game;

    // EnumMap<ObserverTopics, String> fieldSerializedByTopic = new EnumMap<>(ObserverTopics.class);

    EnumMap<ObserverTopics, String> latest = new EnumMap<>(ObserverTopics.class);

    public void register(iObserverOutput output) { //TODO:need a deregister option
        this.observerOutputs.add(output);
    }

    //maybe pubsub is handled byu each output itself. OOH gives every iOO all data and each iOO transmits the tags it needs 
    /////////////////////////////////////////////////////////////////////////////////////
    // public void subscribe(iObserverOutput output, ObserverTopics topic) { //TODO:need a deregister option
    //     this.observerOutputs.add(output);
    // }
    // public void unsubscribe(iObserverOutput output, ObserverTopics topic) { //TODO:need a deregister option
    //     this.observerOutputs.add(output);
    // }
    // public void setTopics(iObserverOutput output,EnumMap<ObserverTopics,boolean> topics) { //TODO:need a deregister option
    //     // this.observerOutputs.add(output);
    // }
    
    public void publishField(Field field){
        EnumMap<ObserverTopics, String> fieldSerializedByTopic = new EnumMap<>(ObserverTopics.class);
        this.serializeField(field,fieldSerializedByTopic);
        // other serialize methods
        latest = fieldSerializedByTopic;
        for(iObserverOutput output : observerOutputs){
            output.push(latest);
            // this.transmitLatest(output);
        }

    }    
    public void requestLatest(iObserverOutput output){
        output.push(latest);

    }
    public EnumMap<ObserverTopics, String> getLatest(iObserverOutput output){
        return latest;

    }

    // private void transmitLatest(iObserverOutput output) {
        
    // }

    // ";" separates topics
    // ":" separates topic label from content
    private void serializeField(Field field, EnumMap<ObserverTopics, String> fieldSerializedByTopic) {
        //Field/state
        fieldSerializedByTopic.put(ObserverTopics.EnemyCardsBack,      this.serializeEnemyCardsBack(field)       );                     
        fieldSerializedByTopic.put(ObserverTopics.EnemyCards,          this.serializeEnemyCards(field)           );             
        fieldSerializedByTopic.put(ObserverTopics.PlayerCards,         this.serializePlayerCards(field)          );           
        fieldSerializedByTopic.put(ObserverTopics.Hand,                this.serializeHand(field)                 );                          
        fieldSerializedByTopic.put(ObserverTopics.Sacrifices,          this.serializeSacrifices(field)           );            
        fieldSerializedByTopic.put(ObserverTopics.MainDeck,            this.serializeMainDeck(field)             );           
        fieldSerializedByTopic.put(ObserverTopics.SideDeck,            this.serializeSideDeck(field)             );   
        fieldSerializedByTopic.put(ObserverTopics.EnemyPlannedMoves,   this.serializeEnemyPlannedMoves(field)    );             
        // fieldSerializedByTopic.put(ObserverTopics.scale,               this.serializescale(field)                );  //make all this state
        // fieldSerializedByTopic.put(ObserverTopics.Candles,             this.serializeCandles(field)              );    
        // fieldSerializedByTopic.put(ObserverTopics.TurnControllerState, this.serializeTurnControllerState(field)  );        

        
                
    }

    //HELPERS
    private String serializeSigils(EnumMap<Sigils, Boolean> cardSigils) {
        String serSigils = "sig-";
        for (Sigils testSigil : Sigils.values()) {         //this is sloppy as heck, relies on assumption of consistent ordering
            serSigils = serSigils + (cardSigils.get(testSigil) ? "1" : "0");
        }
        return serSigils;
    }
    private String serializeCard(Card card) {
        
        if(card!= null){
            String serCard = card.getTitle() +"/"                              
            +  "" + card.getCardId()
            + " " + card.getBaseAttack()                                      
            + " " + card.getBaseHealth()                                      
            + " " + card.getAttack()                                      
            + " " + card.getHealth()                                      
            + " " + card.getCost() 
            + " " + card.getValue()
            + " " + this.serializeSigils(card.getSigils())                                       
            // + " " + card.image
        ;
        return serCard;
    
        }
        else{
            return "null/null";
        }
    }
    private String serializeArrayOfCards(Card[] row) {
        boolean first = true;
        String serArray = "ar-" +row.length+"[";
        for (Card card:row){
            if(!first){
                serArray = serArray +",";
            }
            first = false;
            serArray = serArray+ this.serializeCard(card);
        }
        
        return serArray;
    }
    private String serializeListOfCards(LinkedList<Card> cards) {
        boolean first = true;
        String serList = "li-" +cards.size()+"[";
        for (Card card:cards){
            if(!first){
                serList = serList +",";
            }
            first = false;
            serList = serList+ this.serializeCard(card);
        }
        
        return serList;
    }
    



    //TOPICS
    private String serializeEnemyCardsBack(Field field){
        return "EnemyCardsBack:"+this.serializeArrayOfCards(field.getEnemyCardsBack());
    }
    private String serializeEnemyCards(Field field){
        return "EnemyCards:"+this.serializeArrayOfCards(field.getEnemyCards());
    }
    private String serializePlayerCards(Field field){
        return "PlayerCards:"+this.serializeArrayOfCards(field.getPlayerCards());
    }
    private String serializeHand(Field field){
        return "Hand:"+this.serializeListOfCards(field.getHand());
    }
    private String serializeSacrifices(Field field){
        return "Sacrifices:"+this.serializeCard(field.getCurrent()) + "]"
                            +this.serializeListOfCards(field.getSacrifices());
    }
    private String serializeMainDeck(Field field){
        return "MainDeck:"+this.serializeListOfCards(field.getMainDeck());
    }
    private String serializeSideDeck(Field field){
        return "SideDeck:"+this.serializeListOfCards(field.getSideDeck());
    }
    private String serializeEnemyPlannedMoves(Field field){
        ArrayList<LinkedList<Card>> moves = field.getEnemyPlannedMoves();
        String serMoves = "EnemyPlannedMoves:"+
                        this.serializeListOfCards(moves.get(0)) + "]" +
                        this.serializeListOfCards(moves.get(1)) + "]" +
                        this.serializeListOfCards(moves.get(2)) + "]" +
                        this.serializeListOfCards(moves.get(3)) ;

        return serMoves;
    }

    // private String serializescale(Field field){
    //     return null;
    // }
    // private String serializeCandles(Field field){
    //     return null;
    // }
    // private String serializeTurnControllerState(Field field){
    //     return null;
    // }












}


