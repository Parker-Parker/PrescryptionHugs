import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Field {
    Card[] nullCards = {null, null, null, null};
    Card[] enemyCardsBack = {null, null, null, null};
    Card[] enemyCards = {null, null, null, null};
    Card[] playerCards = {null, null, null, null};
    Card[][] rows = {playerCards,enemyCards,enemyCardsBack};
    LinkedList<Card> mainDeck = new LinkedList<Card>();
    LinkedList<Card> sideDeck = new LinkedList<Card>();
    LinkedList<Card> hand = new LinkedList<Card>();
    ArrayList<LinkedList<Card>> enemyPlannedMoves = new ArrayList<>(List.of(new LinkedList<Card>(),new LinkedList<Card>(),new LinkedList<Card>(),new LinkedList<Card>()));
    //candle 
    //scale

    //for sacrifices
    LinkedList<Card> sacrifices = new LinkedList<Card>(); 
    Card current = null;
    int summonCandidate;



    int scale = 0;
    int candles = 0;
    private NullCard nullCard = new NullCard(this);
    



    public Field(Card[] nC, Card[] eCB, Card[] eC, Card[] pC, ArrayList<LinkedList<Card>> epm) {
        nullCards = nC;
        enemyCardsBack = eCB;
        enemyCards = eC;
        playerCards = pC;
        enemyPlannedMoves = epm;
        for(int i = 0; i <4; i++){
            if(enemyCardsBack[i] != null){
                enemyCardsBack[i].setField(this);
            }
            if(enemyCards[i] != null){
                enemyCards[i].setField(this);
            }
            if(playerCards[i] != null){
                playerCards[i].setField(this);
            }
        }

    }
    public void dilldillSetup(Card[] nC, Card[] eCB, Card[] eC, Card[] pC, ArrayList<LinkedList<Card>> epm) {
        nullCards = nC;
        enemyCardsBack = eCB;
        enemyCards = eC;
        playerCards = pC;
        enemyPlannedMoves = epm;
        for(int i = 0; i <4; i++){
            if(enemyCardsBack[i] != null){
                enemyCardsBack[i].setField(this);
            }
            if(enemyCards[i] != null){
                enemyCards[i].setField(this);
            }
            if(playerCards[i] != null){
                playerCards[i].setField(this);
            }
        }

    }

    public Field() {
    }

    public void initMainDeck() {
        for(int i = 0; i<25;i++){
            mainDeck.add(new Card(i%3,i%7,"Card D"+i));
        }
        // for (Card c : mainDeck){
        //     c.setField(this);
        // }
    }
    public void initMainDeck(LinkedList<Card> deck) {
        for(Card c : deck){
            mainDeck.add(c.makeCopy());
        }
        
    }
    public void initSideDeck() {
        for(int i = 0; i<25; i++){
            sideDeck.add(new Squirrel());
        }
    }

    public Card[] getPlayerCards(){
        return playerCards;
    }

    

    public Boolean playCard(Card card, int slot) { //return true if successful, expect prev container to remove from its list
        if(playerCards[slot] == null){
            playerCards[slot] = card;
            playerCards[slot].setField(this);//not sure why this was commented out?
            playerCards[slot].onSummon(this);
            playerCards[slot].setFromHand();

            if(playerCards[slot]!=null){
                playerCards[slot].setPos(2, slot);
            }
            // for i 0-3 enemyCards[i].reactSummon()
            return true;
        }
        else {
            return false;
        }
    }

    /////////////////////////////////////////////////////
    //////////////   PRINT FUNCTIONS   //////////////////
    /////////////////////////////////////////////////////
    
    String blank = "          ";
    String horiz = "+" + blank.replace(' ', '-') + "+" + blank.replace(' ', '-') + "+" + blank.replace(' ', '-') + "+" + blank.replace(' ', '-') + "+";
    private TurnController turnController;
    public void setTurnController(TurnController tc){
        this.turnController=tc;
    }
    private String makeLineTitle(Card[] rowCards) {
        String outputString = "|";
        int i;
        for(i = 0; i<4; i++) {
            Card card = rowCards[i];
            if(card == null){
                outputString = outputString + blank + "|";
            }

            else {
                String title = card.getTitle();
                while (title.length()<blank.length()) {
                    title =title +" ";
                    if (title.length()<blank.length()) {
                        title =" "+title;
                    }
                }
                outputString = outputString + title + "|";
        
            }
        }
        return outputString;
    }
    private String makeLineStats(Card[] rowCards) {
        String outputString = "|";
        int i;
        for(i = 0; i<4; i++) {
            Card card = rowCards[i];
            if(card == null){
                outputString = outputString + blank + "|";
            }

            else {
                String title = card.attack +"    "+ card.health;
                while (title.length()<blank.length()) {
                    title =title +" ";
                    if (title.length()<blank.length()) {
                        title =" "+title;
                    }
                }
                outputString = outputString + title + "|";
        
            }
        }
        return outputString;
    }
    private String makeLineSigils(Card[] rowCards) {
        String outputString = "|";
        int i;
        for(i = 0; i<4; i++) {
            Card card = rowCards[i];
            if(card == null){
                outputString = outputString + blank + "|";
            }

            else {
                String title = "";
                for (Sigils s : Sigils.values()){
                    if(card.checkSigil(s)){
                        if(title.length()<blank.length()){
                            switch(s) {
                                case Rabbit:
                                    title = title +"";
                                    break;
                                case Bees:
                                    title = title +"B";
                                    break;
                                case Sprinter:
                                    title = title +">";
                                    break;
                                case DeathTouch:
                                    title = title +"&";
                                    break;
                                case Fledgling:
                                    title = title +"";
                                    break;
                                case Dam:
                                    title = title +"";
                                    break;
                                case Hoarder:
                                    title = title +"";
                                    break;
                                case Burrower:
                                    title = title +"v";
                                    break;
                                case Fecundity:
                                    title = title +"";
                                    break;
                                case LooseTail:
                                    title = title +"T";
                                    break;
                                case CorpseEater:
                                    title = title +"";
                                    break;
                                case BoneKing:
                                    title = title +"=";
                                    break;
                                case Waterborne:
                                    title = title +"S";
                                    break;
                                case Unkillable:
                                    title = title +"R";
                                    break;
                                case SharpQuills:
                                    title = title +"#";
                                    break;
                                case Hefty:
                                    title = title +"H";
                                    break;
                                case AntSpawner:
                                    title = title +"A";
                                    break;
                                case Guardian:
                                    title = title +"G";
                                    break;
                                case Airborne:
                                    title = title +"F";
                                    break;
                                case ManyLives:
                                    title = title +"C";
                                    break;
                                case Repulsive:
                                    title = title +"!";
                                    break;
                                case WorthySacrifice:
                                    title = title +"$";
                                    break;
                                case MightyLeap:
                                    title = title +"@";
                                    break;
                                case BifurcatedStrike:
                                    title = title +"2";
                                    break;
                                case TrifurcatedStrike:
                                    title = title +"3";
                                    break;
                                case FrozenAway:
                                    title = title +"";
                                    break;
                                case TrinketBearer:
                                    title = title +"";
                                    break;
                                case SteelTrap:
                                    title = title +"";
                                    break;
                                case Amorphous:
                                    title = title +"";
                                    break;
                                case TidalLock:
                                    title = title +"";
                                    break;
                                case OmniStrike:
                                    title = title +"";
                                    break;
                                case Leader:
                                    title = title +"~";
                                    break;
                                case Bellist:
                                    title = title +"";
                                    break;
                                case Stinky:
                                    title = title +"-";
                                    break;                
                            }            
                            
                        }
                        else if(title.length()==blank.length()){
                            title = title.substring(0,blank.length()-2) +"+";
                        }
                    }
                }
                while (title.length()<blank.length()) {
                    title =title +" ";
                    if (title.length()<blank.length()) {
                        title =" "+title;
                    }
                }
                outputString = outputString + title + "|";
        
            }
        }
        return outputString;
    }
    public void printField() {
        System.out.println(horiz);
        System.out.println(makeLineTitle(enemyCardsBack));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineSigils(enemyCardsBack));
        System.out.println(makeLineStats(enemyCardsBack));
        System.out.println(horiz);
        System.out.println(makeLineTitle(enemyCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineSigils(enemyCards));
        System.out.println(makeLineStats(enemyCards));
        System.out.println(horiz);
        System.out.println("+   "+blank+blank+blank+blank+"+");
        System.out.println(horiz);
        System.out.println(makeLineTitle(playerCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineSigils(playerCards));
        System.out.println(makeLineStats(playerCards));
        System.out.println(horiz);
    }

    /////////////////////////////////////////////////////
    //////////////   TEST FUNCTIONS   ///////////////////
    /////////////////////////////////////////////////////

    public void bulkInit(Card[] back, Card[] mid, Card[] front) {
        enemyCardsBack  = back;
        enemyCards      = mid;
        playerCards     = front;
    }

    public void bulkInit() {
        initMainDeck();
        initSideDeck();
        enemyCardsBack[0]   = null;
        enemyCardsBack[1]   = new Card();
        enemyCardsBack[2]   = new Card(1,3,"fart"); 
        enemyCardsBack[3]   = null;
        enemyCards[0]       = new Card();
        enemyCards[1]       = new Card(3,2,"wolf");
        enemyCards[2]       = new Card();
        enemyCards[3]       = null; 
        playerCards[0]      = null;
        playerCards[1]      = new Card(1,1,"stoat");
        playerCards[2]      = new Card(2,8,"moose"); 
        playerCards[3]      = new Card(1,1,"bort");
        for (Card[] r :rows){
            for(Card c: r) {
                if (c != null) {c.setField(this);}//should make this happen every all the time update
            }
        }

        for(int c = 0 ; c< 4;c++){
            if(enemyCardsBack[c]!=null){enemyCardsBack[c].setPos(0, c);}
            if(enemyCards[c]!=null){enemyCards[c].setPos(1, c);}
            if(playerCards[c]!=null){playerCards[c].setPos(2, c);}
        }
        for(int i = 0; i<25;i++){
            for(int k = 0; k<4;k++){
                enemyPlannedMoves.get(k).add(new Card("LDeck "+k+" "+i));
                
            }   
        }
        
    }



    public void initPresetCards(){
        for (Card[] r :Arrays.asList(playerCards,enemyCards,enemyCardsBack)){
            for(Card c: r) {
                if (c != null) {c.setField(this);}//should make this happen every all the time update
            }
        }

        for(int c = 0 ; c< 4;c++){
            if(enemyCardsBack[c]!=null){enemyCardsBack[c].setPos(0, c);}
            if(enemyCards[c]!=null){enemyCards[c].setPos(1, c);}
            if(playerCards[c]!=null){playerCards[c].setPos(2, c);}
        }
    }
    public void enemyPreSummon(Card card, int i) {
    }
    public void updateCardStats(){
        for(int i = 0;i<4;i++){
            if(enemyCards[i]!=null){
                //buff enemy
                //get buffs
                int left  = i>0  ? (enemyCards[i-1]== null ? 0 :  enemyCards[i-1].getBuff(-1))  : 0;     // come back and add null handling
                int up    = true ? (playerCards[i] == null ? 0 :  playerCards[i].getBuff(0))  : 0;     // come back and add null handling
                int right = i<3  ? (enemyCards[i+1]== null ? 0 :  enemyCards[i+1].getBuff(1)) : 0;     // come back and add null handlinge
                
                int buff  = left + up + right;
                enemyCards[i].setAttack(enemyCards[i].getBaseAttack()+buff);
            }
            if(playerCards[i]!=null){
               
                //get player buffs
                int left  = i>0  ? (playerCards[i-1]== null ? 0 :  playerCards[i-1].getBuff(-1))  : 0;     // come back and add null handling
                int up    = true ? (enemyCards[i]   == null ? 0 :  enemyCards[i].getBuff(0)   ) : 0;     // come back and add null handling
                int right = i<3  ? (playerCards[i+1]== null ? 0 :  playerCards[i+1].getBuff(1)) : 0;     // come back and add null handlinge
                
                int buff  = left + up + right;
                //this.cardAttack(dmg,source, target)
                playerCards[i].setAttack(playerCards[i].getBaseAttack()+buff);

            }
    
        }

    }
    public void enemyAttack(int i) {
        this.nullCard.setTurn(false);     
        if(enemyCards[i]!=null){

            
            int[] targets = enemyCards[i].getAttacks(i);//(get pos based debuff/buff too)asks each card(slot) who it wants to attack, then does the attack(flight/jump/nope sigils)//must call attackee's takedamage function(handle death,overflow dmg, reflect)
            for(int t : targets){
                if (((i+t)>=0) && ((i+t)<=3)) {//only attack if the attacker is looking for a valid target slot
                    // //get buffs
                    // int left  = playerCards[i-1]== null ? 0 : (i>1  ? playerCards[i-1].getBuff(-1)  : 0);     // come back and add null handling
                    // int up    = enemyCards[i]   == null ? 0 : (true ? enemyCards[i].getBuff(0)    : 0);     // come back and add null handling
                    // int right = playerCards[i+1]== null ? 0 : (i<3  ? playerCards[i+1].getBuff(1) : 0);     // come back and add null handlinge
                    
                            // //get buffs
                            // int left  = i>0  ? (enemyCards[i-1]== null ? 0 :  enemyCards[i-1].getBuff(-1))  : 0;     // come back and add null handling
                            // int up    = true ? (playerCards[i]   == null ? 0 :  playerCards[i].getBuff(0)   ) : 0;     // come back and add null handling
                            // int right = i<3  ? (enemyCards[i+1]== null ? 0 :  enemyCards[i+1].getBuff(1)) : 0;     // come back and add null handlinge
                            
                            // int buff  = left + up + right;
                            // //this.cardAttack(dmg,source, target)
                            // int damage = enemyCards[i].getBaseAttack()+buff;
                        
                    this.updateCardStats();
                    this.updateCardStats();
                    //this.cardAttack(dmg,source, target)
                    int damage = enemyCards[i].getAttack();
                   
                   
                   
                   
                    //can actually move i+t(valid target slot 0-3) up here or earlier
                    Card target;
                    if (playerCards[i+t]==null) {target = this.nullCard;} else{target = playerCards[i+t];}//handles null target, code below handles valid case...need to add handling for i+t oob
                    //check fly/block sigil if they match attack target, if they miss attack nullcard 
                    //this.checkHit(atkCark,tgtCard);
                    
                    //sigils: atk~ airborne     def~      leap waterborne             other def~ tail burrower    //Tail can be done on tailCard.takeDamage(), burrower should be done every time an attack is "announced",(ask everycard on the field left to right if they want to burrow, if so attempt move to target slot then execute attack), both will require a "bool move(int slot)" method that tries to move the card to the given slot and returns true if successful. tail will need this bool verify if it takes damage or child card takes damage.
                    //              x               x       x       1       miss
                    //              0               x       x       0       hit
                    //              1               x       0       x       miss
                    //              1               x       1       0       hit
                    //              0               x       0       0       
                    //sigils: atk~ airborne def~repulsive leap waterborne // repulsive(prevent attack) should be checked on takeDamage(int,card), i think; only actually takes damage if sourcecard is null, since that would be revenge damagefrom spikes 
                    
                  

                    if(target.checkSigil(Sigils.Waterborne)){// if waterborne, automatic miss(direct HP damage)
                        target = this.nullCard;
                    }
                    else {//no waterborne
                        if(enemyCards[i].checkSigil(Sigils.Airborne)){
                            if(target.checkSigil(Sigils.MightyLeap)){// airborne and leap = hit
                                //target = target;
                            }
                            else{//airborne no mighty leap =miss
                                target = this.nullCard;
                            }
                        }
                        else{// if no waterborne, and no airborne automatic hit
                            //target = target;
                        }
                    }
                    if(target.checkSigil(Sigils.Repulsive)){
                        if (damage>0){
                            // turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i, Animations.Hurt);//Do a sad wiggle
                            turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i, Animations.Wiggle);//Do a sad wiggle
                        }
                        damage = 0;
                    }
                    if(turnController!=null&&damage>0){
                        Animations atkAnim = (t<0)?(Animations.AttackLeft): ((t>0)?(Animations.AttackRight):(Animations.Attack));
                        turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i, atkAnim);
                    }
                    
                    //this is only for the main case, on hit
                    int overflow = target.takeDamage(damage, enemyCards[i]);//the damage source is passed in so the spike sigil can be tested  in takeDamage and directly call the revenge target's .takeDamage(1,null); 
                
                    // if((overflow>0) && (enemyCardsBack[i+t]!=null)){
                    //     enemyCardsBack[i+t].takeDamage(overflow, playerCards[i]);
                    // }
                }
            }
        }

    }

    public void enemySummon(int i) { 
        if(enemyCards[i]==null){
            enemyCards[i]=enemyCardsBack[i];
            enemyCardsBack[i]=null;
            if(enemyCards[i]!=null){
                enemyCards[i].setPos(1, i);
                this.updateCardStats();
                if(turnController!=null){
                    turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i, Animations.MoveDown);
                }
            }
        }
    }

    public void playerAttack(int i) {
        this.nullCard.setTurn(true);
        if(playerCards[i]!=null){

        
            int[] targets = playerCards[i].getAttacks(i);//(get pos based debuff/buff too)asks each card(slot) who it wants to attack, then does the attack(flight/jump/nope sigils)//must call attackee's takedamage function(handle death,overflow dmg, reflect)
            for(int t : targets){
                if (((i+t)>=0) && ((i+t)<=3)) {//only attack if the attacker is looking for a valid target slot
                    // //get buffs
                    // int left  = playerCards[i-1]== null ? 0 : (i>1  ? playerCards[i-1].getBuff(-1)  : 0);     // come back and add null handling
                    // int up    = enemyCards[i]   == null ? 0 : (true ? enemyCards[i].getBuff(0)    : 0);     // come back and add null handling
                    // int right = playerCards[i+1]== null ? 0 : (i<3  ? playerCards[i+1].getBuff(1) : 0);     // come back and add null handlinge
                    
                            // //get buffs
                            // int left  = i>0  ? (playerCards[i-1]== null ? 0 :  playerCards[i-1].getBuff(-1))  : 0;     // come back and add null handling
                            // int up    = true ? (enemyCards[i]   == null ? 0 :  enemyCards[i].getBuff(0)   ) : 0;     // come back and add null handling
                            // int right = i<3  ? (playerCards[i+1]== null ? 0 :  playerCards[i+1].getBuff(1)) : 0;     // come back and add null handlinge
                            
                            // int buff  = left + up + right;
                            // //this.cardAttack(dmg,source, target)
                            // int damage = playerCards[i].getBaseAttack()+buff;


                    this.updateCardStats();
                    this.updateCardStats();
                    int damage = playerCards[i].getAttack();


                    //can actually move i+t(valid target slot 0-3) up here or earlier
                    Card target;
                    if (enemyCards[i+t]==null) {target = this.nullCard;} else{target = enemyCards[i+t];}//handles null target, code below handles valid case...need to add handling for i+t oob
                    //check fly/block sigil if they match attack target, if they miss attack nullcard 
                    //this.checkHit(atkCark,tgtCard);
                    
                    //sigils: atk~ airborne     def~      leap waterborne             other def~ tail burrower    //Tail can be done on tailCard.takeDamage(), burrower should be done every time an attack is "announced",(ask everycard on the field left to right if they want to burrow, if so attempt move to target slot then execute attack), both will require a "bool move(int slot)" method that tries to move the card to the given slot and returns true if successful. tail will need this bool verify if it takes damage or child card takes damage.
                    //              x               x       x       1       miss
                    //              0               x       x       0       hit
                    //              1               x       0       x       miss
                    //              1               x       1       0       hit
                    //              0               x       0       0       
                    //sigils: atk~ airborne def~repulsive leap waterborne // repulsive(prevent attack) should be checked on takeDamage(int,card), i think; only actually takes damage if sourcecard is null, since that would be revenge damagefrom spikes 
            
                    
                    if(target.checkSigil(Sigils.Waterborne)){// if waterborne, automatic miss(direct HP damage)
                        target = this.nullCard;
                    }
                    else {//no waterborne
                        if(playerCards[i].checkSigil(Sigils.Airborne)){
                            if(target.checkSigil(Sigils.MightyLeap)){// airborne and leap = hit
                                //target = target;
                            }
                            else{//airborne no mighty leap =miss
                                target = this.nullCard;
                            }
                        }
                        else{// if no waterborne, and no airborne automatic hit
                            //target = target;
                        }
                    }
                    if(target.checkSigil(Sigils.Repulsive)){
                        if (damage>0){
                            // turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 2, i, Animations.Hurt);//Do a sad wiggle
                            turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 2, i, Animations.Wiggle);//Do a sad wiggle
                        }
                        damage = 0;
                    }

                    if(turnController!=null&&damage>0){
                        turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 2, i, Animations.PlayerAttack);
                    }

                    //this is only for the main case, on hit
                    int overflow = target.takeDamage(damage, playerCards[i]);//the damage source is passed in so the spike sigil can be tested  in takeDamage and directly call the revenge target's .takeDamage(1,null); 
                
                    if((overflow>0) && (enemyCardsBack[i+t]!=null)){
                        if((playerCards[i]!=null)){
                            enemyCardsBack[i+t].takeDamage(overflow, playerCards[i]);
                    
                        }
                        else{//hacky null handling
                            enemyCardsBack[i+t].takeDamage(overflow, null);
                    
                        }
                    }
                }
            }
        }
    }

    public void purgeDead() {
        for(int i = 0; i<4; i++){
            this.enemyCardsBack[i]  = (this.enemyCardsBack[i]==null)?   null:(this.enemyCardsBack[i].checkDead() ? null : this.enemyCardsBack[i]);
            this.enemyCards[i]      = (this.enemyCards[i]==null)    ?   null:(this.enemyCards[i].checkDead()     ? null : this.enemyCards[i]);
            this.playerCards[i]     = (this.playerCards[i]==null)   ?   null:(this.playerCards[i].checkDead()    ? null : this.playerCards[i]);
        }
    }
    public void purge(Card card) {
        for(int i = 0; i<4; i++){
            if(this.enemyCardsBack[i]==card) {this.enemyCardsBack[i]=null;} 
            if(this.enemyCards[i]    ==card) {this.enemyCards[i]    =null;}
            if(this.playerCards[i]   ==card) {this.playerCards[i]   =null;}
        }
    }



    public void popMainDeck() {
        if (mainDeck.peek()!=null) {
            hand.add(mainDeck.pop());
            System.out.println("drew card");
        }
        else {
            System.out.println("tried to draw card, but none left");
            //do end of deck thing
        }
        
    }



    public void popSideDeck() {
        if (sideDeck.peek()!=null) {
            hand.add(sideDeck.pop());
            System.out.println("drew Squirrel");
        }
        else {
            System.out.println("tried to draw Squirrel, but none left");
            //do end of deck thing
        }
        
    }
    public boolean playCard(int i, int slot) {
        if(hand.size()>i && hand.get(i)!=null){
            hand.get(i).setPos(2, slot);
        }
        return hand.size()>i ?playCard(hand.get(i), slot):false;
    }
    
    public boolean playCardSac(int slot) {
        return playCard(current, slot);
    }
    
    public boolean checkRoom() {
        return ((this.playerCards[0]==null)||(this.playerCards[1]==null)||(this.playerCards[2]==null)||(this.playerCards[3]==null));
    }
    public int executeSacrifices() {
        int value = 0;
        for(Card c : sacrifices){
            value+=c.value;
            c.sacrifice();
        }
        return value;
    }
    public void addSacrifice(int i) {
        if(!(i<0||i>3)){
            if(!(this.playerCards[i]==null)){
                if(this.playerCards[i].value >0){
                    this.sacrifices.add(playerCards[i]);
                }
                else{
                    System.out.println("terrain sac requested");
                }
            }
            else{
                System.out.println("null sac requested");
            }    
        }
        else{
            System.out.println("oob sac requested");
        }
    }
    public void clearSacrifices() {
        while(!this.sacrifices.isEmpty()){
            this.sacrifices.pop();
        }
    }
    public boolean checkSacrifices() {
        
        int totValue = 0;
        for(Card c : sacrifices){
            if(!(c==null)){
                if(c.value >0){
                    totValue += c.value;
                }
            }
        }
        return (totValue >= this.current.cost);


        // return false;
    }
    public boolean prepPlayCard(int i) {
        // confirm there is enough blood on the field  
        //if no bounce back wait for card clear then bounce back to ready 
        //confirm not 0 cost
        if((hand.size()>i)&&(hand.get(i)!=null)){
            if(hand.get(i).cost>0){
                int availableBlood = 0;
                for(int c = 0; c<4; c++){
                    if(!(this.playerCards[c]==null)){
                        if(this.playerCards[c].value >0){
                            availableBlood += this.playerCards[c].value;
                        }
                    }
                }
                if(availableBlood >= hand.get(i).cost){//valid summonable
                    this.current=hand.get(i);
                    this.summonCandidate = i;
                    return true;
                }
                
            }

        }
        else {//card is null??
            System.out.println("card is null?");
        }
        return false;

    }
    public LinkedList<Card> getHand() {
        return hand;
    }
    public void enemySummonRear() { //
        for(int i = 0; i<4; i++){ //mode: independent patient queues no pileup //We can add different modes to make this more configurable, per battle
                                    //desc: each column has a card feeder that will automatically summon the next card if the back row of field is vacant
                                    //      it is valid to put nulls in list to postpone summoning for a turn
                                    //      independant: it will not wait until an entire row can be summoned at once, separate columns can become out of sync  //alt opts could be Sync/blocking
                                    //      patient:     if the field slot is already occupied the feeder will not advance until it is available                //alts can be impatient-wasteful(tosses card that cannot be summoned and advances queue), impatient-opportunist(plays to any open space), impatient-recycler(puts card back on end of queue if blocked),patient-pileup(Cards with an empty space in front of them in the feeder will advance, but blocked cards will be patient. i.e. if front card is blocked advance all cards behind the frontmost null (by removing that null) ex: > Bear null Moose null Elk null Elk null | Field:Squirrel >>> Bear null Moose null Elk null Elk | Field:Squirrel >>> Bear null Moose null Elk Elk | Field:Squirrel >>> Bear null Moose Elk Elk | Field:Squirrel >>> Field Sq dies >>> Bear null Moose Elk Elk | Field:null >>> Bear null Moose Elk | Field:Elk >>> Bear Moose Elk | Field:Elk )                 
            if(enemyCardsBack[i] == null){
                if(!enemyPlannedMoves.get(i).isEmpty()){
                    enemyCardsBack[i] = enemyPlannedMoves.get(i).pop();
                    if(enemyCardsBack[i] != null){
                        enemyCardsBack[i].setField(this);
                        enemyCardsBack[i].setPos(0,i);


                        if(turnController!=null){
                            turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 0, i, Animations.EnterTop);
                        }
                    }
                    
                }
                else{
                    System.out.println("Leshy is out of moves for column: "+i);
                }
                
            } else if((!enemyPlannedMoves.isEmpty())&&(enemyPlannedMoves.get(i).peek()==null) ){
                enemyPlannedMoves.get(i).pop();
            }
        }
    }
    public void directDamage(int damage) {
        scale = scale + damage;
        System.out.println( damage>0? ("Leshy took " + damage+" damage! score is "+this.scale):("Player took " + (-damage)+" damage! score is "+this.scale)    );
        
    }
    public Card[] getEnemyCards() {
        return this.enemyCards;
    }
    public Card[] getEnemyCardsBack() {
        return this.enemyCardsBack;
    }
    public LinkedList<Card> getSideDeck() {
        return this.sideDeck;
    }
    public LinkedList<Card> getMainDeck() {
        return this.mainDeck;
    }
    public LinkedList<Card> getSacrifices() {
        return this.sacrifices;
    }
    public Card getCurrent() {
        return this.current;
    }
    public ArrayList<LinkedList<Card>> getEnemyPlannedMoves() {
        return this.enemyPlannedMoves;
    }
    public void setNullCards(Card[] nullCards) {
        this.nullCards = nullCards;
    }
    public void setEnemyCardsBack(Card[] enemyCardsBack) {
        this.enemyCardsBack = enemyCardsBack;
    }
    public void setEnemyCards(Card[] enemyCards) {
        this.enemyCards = enemyCards;
    }
    public void setPlayerCards(Card[] playerCards) {
        this.playerCards = playerCards;
    }
    public void setRows(Card[][] rows) {
        this.rows = rows;
    }
    public void setMainDeck(LinkedList<Card> mainDeck) {
        this.mainDeck = mainDeck;
    }
    public void setSideDeck(LinkedList<Card> sideDeck) {
        this.sideDeck = sideDeck;
    }
    public void setHand(LinkedList<Card> hand) {
        this.hand = hand;
    }
    public void setEnemyPlannedMoves(ArrayList<LinkedList<Card>> enemyPlannedMoves) {
        this.enemyPlannedMoves = enemyPlannedMoves;
    }
    public void setSacrifices(LinkedList<Card> sacrifices) {
        this.sacrifices = sacrifices;
    }
    public void setCurrent(Card current) {
        this.current = current;
    }
    public void setSummonCandidate(int summonCandidate) {
        this.summonCandidate = summonCandidate;
    }
    public void setScale(int scale) {
        this.scale = scale;
    }
    public void setCandles(int candles) {
        this.candles = candles;
    }
    public void setNullCard(NullCard nullCard) {
        this.nullCard = nullCard;
    }
    public void setBlank(String blank) {
        this.blank = blank;
    }
    public void setHoriz(String horiz) {
        this.horiz = horiz;
    }
    public TurnController getTurnController() {
        return turnController;
    }    
    public LinkedList<Card> deepCopyDeck(LinkedList<Card> deck){
        LinkedList<Card> newDeck = new LinkedList<Card>();
        for(Card c : deck){
            // newDeck.add(new Card(c));//TODO:FIX <<this accidentally "casts" all objects to cards
            newDeck.add(c.makeCopy());//TODO:FIX <<this accidentally "casts" all objects to cards
            
            // newDeck.add(c.getClass().cast(new Card(c)));//<<<THIS LINE IS FUCKING TERRIFYING PLEASE DO SOMETHING ELSE
            // //TODO: FIX IT 
            
        }
        return newDeck;
    }

    public void clear() {
        for(int i = 0; i<4;i++){
            enemyCards[i] = null;
            enemyCardsBack[i] = null;
            playerCards[i] = null;
        }
    }

    public void enemyCardMove(int i) {
        if(playerCards[i]!=null){//TODO: move this to its own function, kinda hacky
            playerCards[i].setMoved(false);
        }
        if(enemyCards[i]!=null&&enemyCards[i].checkSigil(Sigils.Sprinter)&&!enemyCards[i].checkMoved()){
            boolean rightBlocked = i>=3||enemyCards[i+1]!=null;
            boolean  leftBlocked = i<=0||enemyCards[i-1]!=null;
            if(rightBlocked&&leftBlocked){
                // wiggle, panic
                //publish anim
                if(turnController!=null){
                    turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i, Animations.Wiggle);
                }
            }
            else{
                boolean dir = (!(rightBlocked||leftBlocked))?enemyCards[i].getMoveDirection():leftBlocked;
                enemyCards[i].setMoved(true);
                enemyCards[i].setMoveDirection(dir);
                enemyCards[i].setPos(1, i+(dir?1:-1));;
                enemyCards[i+(dir?1:-1)] = enemyCards[i];
                enemyCards[i] = null;
                //publish anim
                if(turnController!=null){
                    turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i+(dir?1:-1), (dir?Animations.MoveRight:Animations.MoveLeft));
                }
            }

        }
    }

    public void enemyCardEvolve(int i) {
        if (enemyCards[i]!=null&&enemyCards[i].checkSigil(Sigils.Fledgling)){
            enemyCards[i] = enemyCards[i].getEvolution();
            //anim

            if(turnController!=null){
                turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 1, i, Animations.Wiggle);
            }
        }
    }

    public void playerCardEvolve(int i) {
        if (playerCards[i]!=null&&playerCards[i].checkSigil(Sigils.Fledgling)){
            playerCards[i] = playerCards[i].getEvolution();
            //anim

            if(turnController!=null){
                turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 2, i, Animations.Wiggle);
            }
        }

    }


    public void playerCardMove(int i) {
        if(enemyCards[i]!=null){//TODO: move this to its own function, kinda hacky
            enemyCards[i].setMoved(false);
        }
        if(playerCards[i]!=null&&playerCards[i].checkSigil(Sigils.Sprinter)&&!playerCards[i].checkMoved()){
            boolean rightBlocked = i>=3||playerCards[i+1]!=null;
            boolean  leftBlocked = i<=0||playerCards[i-1]!=null;
            if(rightBlocked&&leftBlocked){
                // wiggle, panic
                //publish anim
                if(turnController!=null){
                    turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 2, i, Animations.Wiggle);
                }
            }
            else{
                boolean dir = (!(rightBlocked||leftBlocked))?playerCards[i].getMoveDirection():leftBlocked;
                playerCards[i].setMoved(true);;
                playerCards[i].setMoveDirection(dir);
                playerCards[i].setPos(2, i+(dir?1:-1));;
                playerCards[i+(dir?1:-1)] = playerCards[i];
                playerCards[i] = null;
                //publish anim
                if(turnController!=null){
                    turnController.ioHandler.getObserverOutputHandler().publishAnim(this, 2, i+(dir?1:-1), (dir?Animations.MoveRight:Animations.MoveLeft));
                }
            }

        }
    }
}
