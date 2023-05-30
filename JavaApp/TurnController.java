public class TurnController {
    int turnNo = 0;
    TurnState state;
    Field field;
    Card[] nextMove = {null, null, null, null};
    //make a "move" object called nextMove that holds what leshy will play to the back next
    //make a "script/itinerary" object that holds or fetches multiple "moves", and coordinates nextMove

    public TurnController(Field inField){
        this.field = inField;
        this.state = TurnState.playerDeal;
    }

    public Field getField(){
        return this.field;
    }

    public void setState(TurnState newState){
        // check event list for interrupts
        //if no interrupt
        this.state = newState;
    }

    

    public void executeState() {
        System.out.println("Exectuting state: "+ this.state.name());

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
                //collect respond to player input
                this.setState(TurnState.playerReady); //if player attempts summon of 0 cost
                break;
            case playerReady:
                //collect respond to player input
                //this.setState(TurnState.playerSummon); //if player attempts summon of 0 cost
                //this.setState(TurnState.playerSacrifice); //if player attempts summon of 1+ cost
                this.setState(TurnState.playerEnd); //if player ends turn
                
                break;
            case playerSacrifice:
                //collect respond to player input
                this.setState(TurnState.playerSummon); //if summon cost satisfied //call sacrifice method on all cards in list
                //this.setState(TurnState.playerSacrifice); //if summon cost not satisfied
                //this.setState(TurnState.playerReady); //if summon canceled
                
                break;
            case playerSummon:
                //collect and respond to player input
                this.setState(TurnState.playerReady); //if summon successful
                //this.setState(TurnState.playerReady); //if summon unsuccessful and there was no sacrifice
                //this.setState(TurnState.playerSummon);//if summon unsuccessful but sacrifice succeeded
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
