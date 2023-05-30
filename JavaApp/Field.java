public class Field {
    Card[] nullCards = {null, null, null, null};
    Card[] enemyCardsBack = {null, null, null, null};
    Card[] enemyCards = {null, null, null, null};
    Card[] playerCards = {null, null, null, null};
    Card[][] rows = {playerCards,enemyCards,enemyCardsBack};
    int scale = 0;
    private NullCard nullCard = new NullCard(this);
    

    public Card[] getPlayerCards(){
        return playerCards;
    }

    public Boolean playCard(Card card, int slot) { //return true if successful, expect prev container to remove from its list
        if(playerCards[slot] == null){
            playerCards[slot] = card;
            //playerCards[slot].setField(this);
            playerCards[slot].onSummon(this);
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
                if (c != null) {c.setField(this);}
            }
        }
        
    }

    public void enemyPreSummon(Card card, int i) {
    }

    public void enemyAttack(int i) {     
        if(enemyCards[i]!=null){

            
            int[] targets = enemyCards[i].getAttacks(i);//(get pos based debuff/buff too)asks each card(slot) who it wants to attack, then does the attack(flight/jump/nope sigils)//must call attackee's takedamage function(handle death,overflow dmg, reflect)
            for(int t : targets){
                if (t>=0 && t<=3) {//only attack if the attacker is looking for a valid target slot
                    // //get buffs
                    // int left  = playerCards[i-1]== null ? 0 : (i>1  ? playerCards[i-1].getBuff(-1)  : 0);     // come back and add null handling
                    // int up    = enemyCards[i]   == null ? 0 : (true ? enemyCards[i].getBuff(0)    : 0);     // come back and add null handling
                    // int right = playerCards[i+1]== null ? 0 : (i<3  ? playerCards[i+1].getBuff(1) : 0);     // come back and add null handlinge
                    
                    //get buffs
                    int left  = i>0  ? (enemyCards[i-1]== null ? 0 :  enemyCards[i-1].getBuff(-1))  : 0;     // come back and add null handling
                    int up    = true ? (playerCards[i]   == null ? 0 :  playerCards[i].getBuff(0)   ) : 0;     // come back and add null handling
                    int right = i<3  ? (enemyCards[i+1]== null ? 0 :  enemyCards[i+1].getBuff(1)) : 0;     // come back and add null handlinge
                    
                    int buff  = left + up + right;
                    //this.cardAttack(dmg,source, target)
                    int damage = enemyCards[i].getBaseAttack()+buff;
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
        }
    }

    public void playerAttack(int i) {
        if(playerCards[i]!=null){

        
            int[] targets = playerCards[i].getAttacks(i);//(get pos based debuff/buff too)asks each card(slot) who it wants to attack, then does the attack(flight/jump/nope sigils)//must call attackee's takedamage function(handle death,overflow dmg, reflect)
            for(int t : targets){
                if (t>=0 && t<=3) {//only attack if the attacker is looking for a valid target slot
                    // //get buffs
                    // int left  = playerCards[i-1]== null ? 0 : (i>1  ? playerCards[i-1].getBuff(-1)  : 0);     // come back and add null handling
                    // int up    = enemyCards[i]   == null ? 0 : (true ? enemyCards[i].getBuff(0)    : 0);     // come back and add null handling
                    // int right = playerCards[i+1]== null ? 0 : (i<3  ? playerCards[i+1].getBuff(1) : 0);     // come back and add null handlinge
                    
                    //get buffs
                    int left  = i>0  ? (playerCards[i-1]== null ? 0 :  playerCards[i-1].getBuff(-1))  : 0;     // come back and add null handling
                    int up    = true ? (enemyCards[i]   == null ? 0 :  enemyCards[i].getBuff(0)   ) : 0;     // come back and add null handling
                    int right = i<3  ? (playerCards[i+1]== null ? 0 :  playerCards[i+1].getBuff(1)) : 0;     // come back and add null handlinge
                    
                    int buff  = left + up + right;
                    //this.cardAttack(dmg,source, target)
                    int damage = playerCards[i].getBaseAttack()+buff;
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
                    //this is only for the main case, on hit
                    int overflow = target.takeDamage(damage, playerCards[i]);//the damage source is passed in so the spike sigil can be tested  in takeDamage and directly call the revenge target's .takeDamage(1,null); 
                
                    if((overflow>0) && (enemyCardsBack[i+t]!=null)){
                        enemyCardsBack[i+t].takeDamage(overflow, playerCards[i]);
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
}
