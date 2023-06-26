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
        String serArray = "ar-" +row.length+"(";
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
        String serList = "li-" +cards.size()+"(";
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
        return "Sacrifices:"+this.serializeCard(field.getCurrent()) + ")"
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
                        this.serializeListOfCards(moves.get(0)) + ")" +
                        this.serializeListOfCards(moves.get(1)) + ")" +
                        this.serializeListOfCards(moves.get(2)) + ")" +
                        this.serializeListOfCards(moves.get(3)) ;

        return serMoves;
    }

    


    // public Field deserializeField(String field) {
    //     EnumMap<ObserverTopics, String> fieldSerializedByTopic
        
                
    // }
    public void deserializeField(Field field, EnumMap<ObserverTopics, String> fieldSerializedByTopic) {
        //Field/state
        field.setEnemyCardsBack(      this.deserializeEnemyCardsBack(fieldSerializedByTopic.get(ObserverTopics.EnemyCardsBack))       );                     
        field.setEnemyCards(          this.deserializeEnemyCards(fieldSerializedByTopic.get(ObserverTopics.EnemyCards))           );             
        field.setPlayerCards(         this.deserializePlayerCards(fieldSerializedByTopic.get(ObserverTopics.PlayerCards))          );           
        field.setHand(                this.deserializeHand(fieldSerializedByTopic.get(ObserverTopics.Hand))                 );                          
        field.setSacrifices(          this.deserializeSacrifices(fieldSerializedByTopic.get(ObserverTopics.Sacrifices))           );            
        field.setMainDeck(            this.deserializeMainDeck(fieldSerializedByTopic.get(ObserverTopics.MainDeck))             );           
        field.setSideDeck(            this.deserializeSideDeck(fieldSerializedByTopic.get(ObserverTopics.SideDeck))             );   
        field.setEnemyPlannedMoves(   this.deserializeEnemyPlannedMoves(fieldSerializedByTopic.get(ObserverTopics.EnemyPlannedMoves))    );
        
                
    }



    public Card[] deserializeEnemyCardsBack(String topicString)   {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.EnemyCardsBack.name()))        
        ){
            return this.deserializeArrayOfCards(topic[1]);

        }
        return null;
    }        
    public Card[] deserializeEnemyCards(String topicString)   {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.EnemyCards.name()))        
        ){
            return this.deserializeArrayOfCards(topic[1]);

        }
        return null;
    }            
    public Card[] deserializePlayerCards(String topicString)  {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.PlayerCards.name()))        
        ){
            return this.deserializeArrayOfCards(topic[1]);

        }
        return null;
    }              
    public LinkedList<Card> deserializeHand(String topicString)   {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.Hand.name()))        
        ){
            return this.deserializeListOfCards(topic[1]);

        }
        return null;
    }                    
    public LinkedList<Card> deserializeSacrifices(String topicString) {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.Sacrifices.name()))        
        ){
            return this.deserializeListOfCards(topic[1]);

        }
        return null;
    }            
    public LinkedList<Card> deserializeMainDeck(String topicString)   {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.MainDeck.name()))        
        ){
            return this.deserializeListOfCards(topic[1]);

        }
        return null;
    }                
    public LinkedList<Card> deserializeSideDeck(String topicString)   {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.SideDeck.name()))        
        ){
            return this.deserializeListOfCards(topic[1]);

        }        
        return null;

    }                
    public ArrayList<LinkedList<Card>> deserializeEnemyPlannedMoves(String topicString)    {
        String[] topic = topicString.split(":");
        if((topic.length==2)    &&
            (topic[0]!=null)    &&
            (topic[1]!=null)    &&
            (!topic[0].isEmpty())    &&
            (!topic[1].isEmpty())    &&
            (!topic[0].equalsIgnoreCase(ObserverTopics.EnemyPlannedMoves.name()))        
        ){
            ArrayList <LinkedList<Card>> deserArrayList = new ArrayList<LinkedList<Card>>(4);
            for(int i=0;i<4;i++){
                deserArrayList.add(new LinkedList<Card>());
            }
            String[] lists = topic[1].split(")");
            if(lists.length==4){
                for(int i=0;i<4;i++){
                    deserArrayList.set(i, this.deserializeListOfCards(lists[i]));
                } 
            }
            



            return deserArrayList;

        }
        return null;
    }      

















    //HELPERS
    private EnumMap<Sigils, Boolean> deserializeSigils(String cardSigils) {
        String[] sig = cardSigils.split("-");
        if( (sig.length==2) &&
            (sig[0]!=null)  &&
            (sig[1]!=null)  &&
            sig[0].equalsIgnoreCase("sig")  &&
            sig[1].length()==Sigils.values().length
            ){//String is valid sigil flagset
                int i = 0;
                EnumMap<Sigils, Boolean> deserSigils = new EnumMap<Sigils, Boolean>(Sigils.class);
                for(Sigils s : Sigils.values()){
                    if(sig[1].charAt(i)=='1'){
                        deserSigils.put(s, true);
                    }
                    else{
                        deserSigils.put(s, false);    
                    }
                i++;
                }
                return deserSigils;
            }
            return null;
    }
    private Card deserializeCard(String cardString) {
        String[] cs = cardString.split("/");
        if( (cs.length==2) &&
            (cs[0]!=null)  &&
            (cs[1]!=null)  &&
            (!cs[0].isEmpty()) &&
            (!cs[1].isEmpty()) &&
            (!cs[0].equalsIgnoreCase("null")) && //name valid
            (!cs[1].equalsIgnoreCase("null"))   //&&
            // cs[1].length()==Sigils.values().length
            ){//name valid, string seems splittable
                Card deserCard = new Card();
                deserCard.setTitle(cs[0]);
                cs = cs[1].split(" ");
                if( (cs.length==8) &&
                    (cs[0]!=null)  &&  (cs[1]!=null)  &&  (cs[2]!=null)  &&  (cs[3]!=null)  &&  (cs[4]!=null)  &&  (cs[5]!=null)  &&  (cs[6]!=null)  &&  (cs[7]!=null)  &&  
                    (!cs[0].isEmpty())  &&  (!cs[1].isEmpty())  &&  (!cs[2].isEmpty())  &&  (!cs[3].isEmpty())  &&  (!cs[4].isEmpty())  &&  (!cs[5].isEmpty())  &&  (!cs[6].isEmpty())  &&  (!cs[7].isEmpty()) 
                ){
                                        
                    int i = -1;
                    try{i = Integer.parseInt(cs[0]);}catch(Exception e){e.printStackTrace();i= -2;}
                    deserCard.setCardId(i);
                    try{i = Integer.parseInt(cs[1]);}catch(Exception e){e.printStackTrace();i= -2;}
                    deserCard.setBaseAttack(i);
                    try{i = Integer.parseInt(cs[2]);}catch(Exception e){e.printStackTrace();i= -2;}
                    deserCard.setBaseHealth(i); 
                    try{i = Integer.parseInt(cs[3]);}catch(Exception e){e.printStackTrace();i= -2;}                            
                    deserCard.setAttack(i);    
                    try{i = Integer.parseInt(cs[4]);}catch(Exception e){e.printStackTrace();i= -2;}                                     
                    deserCard.setHealth(i);  
                    try{i = Integer.parseInt(cs[5]);}catch(Exception e){e.printStackTrace();i= -2;}                                     
                    deserCard.setCost(i); 
                    try{i = Integer.parseInt(cs[6]);}catch(Exception e){e.printStackTrace();i= -2;}
                    deserCard.setValue(i);                  
                    deserCard.setSigils(this.deserializeSigils(cs[7]));
                    if (deserCard.getSigils()==null){
                        EnumMap<Sigils, Boolean> fixed = new EnumMap<Sigils, Boolean>(Sigils.class);
                        for(Sigils s : Sigils.values()){
                            fixed.put(s, false);
                        }
                    } 
                    return deserCard;

                }

            }
            return null;

    }
    private Card[] deserializeArrayOfCards(String rowString) {
        Card[] deserRow = new Card[4];
         
        ////////////////////////////////////////////////
        String[] row = rowString.split("(");
        if( (row.length==2) &&
            (row[0]!=null)  &&
            (row[1]!=null)  &&
            (!row[0].isEmpty())  &&
            (!row[1].isEmpty())  
            ){//header appears parsable
                String[] header = row[0].split("-");
                if( (header.length==2) &&
                    (header[0]!=null)  &&
                    (header[1]!=null)  &&
                    (!header[0].isEmpty())  &&
                    (!header[1].isEmpty())  &&
                    (header[0].equalsIgnoreCase("ar"))
                    ){
                        int i = -1;
                        try{i = Integer.parseInt(header[1]);}catch(Exception e){e.printStackTrace();i= 0;}
                        if(i==4){
                            String[] cardStrings = row[1].split(",");
                            boolean test = true;
                            for(String s : cardStrings){
                                if(s==null||s.isEmpty()){
                                    test = false;
                                }
                            }
                            if(test && i==cardStrings.length){
                                // for(; i>0; i--){
                                for(int t=0; t<i; t++){
                                    deserRow[i] = this.deserializeCard(cardStrings[i]);
                                }
                                return deserRow;
                            }
                        }
                    }
                }

        return null;
    }
    private LinkedList<Card> deserializeListOfCards(String cardsString) {
        LinkedList<Card> deserList = new LinkedList<Card>();
         
        ////////////////////////////////////////////////
        String[] row = cardsString.split("(");
        if( (row.length==2) &&
            (row[0]!=null)  &&
            (row[1]!=null)  &&
            (!row[0].isEmpty())  &&
            (!row[1].isEmpty())  
            ){//header appears parsable
                String[] header = row[0].split("-");
                if( (header.length==2) &&
                    (header[0]!=null)  &&
                    (header[1]!=null)  &&
                    (!header[0].isEmpty())  &&
                    (!header[1].isEmpty())  &&
                    (header[0].equalsIgnoreCase("li"))
                    ){//looks like can determine length
                        int i = -1;
                        try{i = Integer.parseInt(header[1]);}catch(Exception e){e.printStackTrace();i= 0;}
                        
                        String[] cardStrings = row[1].split(",");
                        boolean test = true;
                        for(String s : cardStrings){
                            if(s==null||s.isEmpty()){
                                test = false;
                            }
                        }
                        if(test && i==cardStrings.length){
                            for(int t=0; t<i; t++){
                                deserList.add(this.deserializeCard(cardStrings[i]));
                            }
                            return deserList;
                        }
                    
                    }
                }

        return null;
    }
    






}


