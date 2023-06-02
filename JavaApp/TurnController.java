import java.util.LinkedList;

public class TurnController {
    int turnNo = 0;
    TurnState state;
    Field field;
    Card[] nextMove = {null, null, null, null};
    UserInputHandler user;
    //make a "move" object called nextMove that holds what leshy will play to the back next
    //make a "script/itinerary" object that holds or fetches multiple "moves", and coordinates nextMove

    public TurnController(Field inField){
        this.field = inField;
        this.state = TurnState.playerDeal;
        this.user = new UserInputHandler();
    }

    public Field getField(){
        return this.field;
    }

    public void setState(TurnState newState){
        // check event list for interrupts
        //if no interrup
        System.out.println("next state shall be: "+newState.name());
        this.state = newState;
    }

    

    public void executeState() {
        System.out.println("Exectuting state: "+ this.state.name());

        LinkedList<String> choices =  new LinkedList<String>();

        switch(this.state) {
            case playerDeal:
                //set field
                for(int i=0; i<5; i++){
                    field.popMainDeck(); //add 5 cards to hand
                }// leshy should prompt player to draw
                field.popSideDeck();//add a squirrel to hand
                this.setState(TurnState.playerDraw);
                break;
            case playerBegin:
                this.setState(TurnState.playerDraw);
                break;
            case playerDraw:
                choices.add("Main");
                choices.add("Side");
                //collect respond to player input
                // int[] cmdDraw = user.getDrawInput();//this should never be null, block if you cant return int[1]// return (deck)
                int[] cmdDraw = user.getUserInput(this.state, choices);//this should never be null, block if you cant return int[1]// return (deck)
                                                                                                            // 1: Main  
                                                                                                            // 0: Squirrel
                //boolean draw = true;
                if(cmdDraw[0]==1){
                    field.popMainDeck();
                }
                else {
                    field.popSideDeck();
                }
                this.setState(TurnState.playerReady);
                break;
            case playerReady:
                for(Card c : field.getHand()){
                    choices.add(c.getTitle());
                }

                //collect respond to player input
                // int[] cmdReady = user.getPlayerReadyInput();//never null, block if you cant return int[3]
                int[] cmdReady = user.getUserInput(this.state, choices);//never null, block if you cant return int[3]
                // return (action, card, slot)  
                //action:                   //card:                 //slot:           
                // 0: Bell                  // x                    //x                                                            
                // 1: SummonDirect          // 0-n: index in hand   //0-3 index on field                                                                              
                // 2: SummonSacrifice       // 0-n: index in hand   //x
                if(cmdReady[0]==0){
                    this.setState(TurnState.playerEnd); //if player ends turn    
                } 
                else if(cmdReady[0]==1){ //if player attempts summon of 0 cost
                    
                    field.playCard(cmdReady[1], cmdReady[2]);  // check if sacrifices are sufficient, 
                                                                //if no, just report angry leshy to anim queue
                                                                //if yes, add card to field at slot, run card.whenPlayed() then purge from hand
                    this.setState(TurnState.playerReady);       //no more work needed, ready to play new card
                    //may want to add something that waits for bad card cleared. may want field.playCard() to return true/false to facilitate this 
                }
                else if(cmdReady[0]==2){
                    field.clearSacrifices();
                    field.prepPlayCard(cmdReady[1]); // confirm there is enough blood on the field nvm //maybe do pop>put_first? nvm 
                                                //if no bounce back wait for card clear then bounce back to ready 
                                                //confirm not 0 cost
                    field.clearSacrifices();
                    this.setState(TurnState.playerSacrifice);
                    

                }

                
                
                //this.setState(TurnState.playerSummon); //if player attempts summon of 0 cost
                //this.setState(TurnState.playerSacrifice); //if player attempts summon of 1+ cost
                //this.setState(TurnState.playerEnd); //if player ends turn
                
                break;
            case playerSacrifice:
                
                if(field.checkSacrifices()){//sacrifices are satisfactory
                    this.setState(TurnState.playerSummon); //if summon cost satisfied //call sacrifice method on all cards in list
                } 
                else    
                {
                    for(Card c : field.getPlayerCards()){
                        choices.add(c.getTitle());
                    }
                    //collect respond to player input
                    // int[] cmdSac = user.getSacrificeInput();//never null, block if you cant return int[2]
                    int[] cmdSac = user.getUserInput(this.state, choices);//never null, block if you cant return int[2]
                    // return (action, card, slot)  
                    //action:                   //card:                          
                    // 0: cancel                // x                                                                          
                    // 1: Sacrifice             //0-3 index on field                                                                              
                    if(cmdSac[0]==0){
                        field.clearSacrifices();
                        this.setState(TurnState.playerReady); //if player cancels sacrifices before cost is reached    
                    } 
                    else if(cmdSac[0]==1){ //if player adds a sacrifice
                        field.addSacrifice(cmdSac[1]);//should check if card at index is worth>0
                        this.setState(TurnState.playerSacrifice);     
                    }
                }
                
                // this.setState(TurnState.playerSummon); //if summon cost satisfied //call sacrifice method on all cards in list
                //this.setState(TurnState.playerSacrifice); //if summon cost not satisfied
                //this.setState(TurnState.playerReady); //if summon canceled
                
                break;
            case playerSummon:
                int cost = field.executeSacrifices();//should return total cost. if cost > 0 cannot cancel
                if(field.checkRoom()){
                    boolean summoned = false;
                    while(summoned==false) {
                        // int[] cmdSummon = user.getSummonInput();//never null, block if you cant return int[1]
                        choices.add("0");
                        choices.add("1");
                        choices.add("2");
                        choices.add("3");
                        int[] cmdSummon = user.getUserInput(this.state, choices);//never null, block if you cant return int[1]
                        // return (slot)  
                        //card:                          
                        // x                                                                          
                        //0-3 index on field                                                                              
                        summoned = field.playCard(0, cmdSummon[0]);
                        this.setState(TurnState.playerReady); //if summon successful    
                
                        if(summoned){
                            this.setState(TurnState.playerReady); //if summon successful    
                        }
                        else{
                            this.setState(TurnState.playerSummon); //if summon successful    
                
                            //leshy yell at player
                        }
                    }
                }
                else{//there is no room(all sacrifices likely unkillable)
                    this.setState(TurnState.playerReady); //if summon successful    
                }
                
                break;
            case playerEnd:
            this.setState(TurnState.playerAttack);
                break;
            case playerAttack:
                for(int i = 0; i<4; i++){
                    field.playerAttack(i);//(get pos based debuff/buff too)asks each card(slot) who it wants to attack, then does the attack(flight/jump/nope sigils)//must call attackee's takedamage function(handle death,overflow dmg, reflect)
                }
                this.setState(TurnState.leshyBegin);
                break;
            case interruptEvent:
                break;
            case leshyBegin:
                this.setState(TurnState.leshySummon);
                break;
            case leshySummon:
                for(int i = 0; i<4; i++){
                    field.enemySummon(i); //moves card from leshys back to active(if it can)
                }
                this.setState(TurnState.leshyAttack);
                break;
            case leshyAttack:
                for(int i = 0; i<4; i++){
                    field.enemyAttack(i); //(get pos based debuff/buff too)asks each card(slot) who it wants to attack, then does the attack(flight/jump/nope sigils)//must call attackee's takedamage function(handle death,overflow dmg, reflect)
                }
                this.setState(TurnState.leshyEnd);
                break;
            case leshyWin:
                break;
            case leshyEnd:
                //leshyPlanNextMove()
                //leshyfetchnextmove() //fetches next move from a move itinerary that can be edited asynchronously
                for(int i = 0; i<4; i++){
                    field.enemyPreSummon(nextMove[i],i); //moves card from leshys mind to on deck(if it can)
                }
                this.setState(TurnState.playerBegin);
                break;
        }
    }    
}
