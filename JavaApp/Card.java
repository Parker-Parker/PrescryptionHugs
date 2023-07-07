import java.util.EnumMap;

//TODO: make libCard extends card?
//TODO: make libraryHandler() that  can
    //importLibrary("libfile.csv") //parses text file, for each valid entry adds libCard to hashmap keyed by card name //should look for String filename.contains(.CSV/.JSON/.txt)
    //addCard(params) that makes new card() and adds it to librart    
    //make(String name) returns a copy of card in lib hashmap(using libcard's clone())
    //libCard.clone() returns a copy of the card, might want to make it named duplicate() instead    

public class Card implements iCard {
    static int id = 0;
    int cardId = 0;
    String title = "Card ";
    EnumMap<Sigils, Boolean> sigils = new EnumMap<>(Sigils.class);
    int baseAttack = 0;
    int baseHealth = 1;
    int attack = 0;
    int health = 1;
    int cost = 0;
    Object image = null;
    public int value = 1;

    int row = 0;
    int col = 0;
    

    boolean dead = false;
    Field field = null;

    int[][] attackLists= {{0}, {-1,1}, {-1,0,1}, {-2,-1,0,1,2} };


    public Card() {
        for(Sigils key : Sigils.values()) {
            sigils.put(key, false);
        }
        title = title + id;
        id++;
    }
    
    public Card(String input) {
        for(Sigils key : Sigils.values()) {
            sigils.put(key, false);
        }
        title = input;
    }
    public Card(int atk, int def, String name) {
        for(Sigils key : Sigils.values()) {
            sigils.put(key, false);
        }
        title = name;
        attack = baseAttack = atk;
        health = baseHealth = def;
    }

    public void setTitle(String input) {
        title = input;
    }
    
    public String getTitle() {
        return title;
    }

    public void onSummon(Field field) {
        this.field = field;

        this.field.updateCardStats();
    }


    public int[] getAttacks(int i) {
        if(this.checkSigil(Sigils.BifurcatedStrike)){
            return attackLists[1];
        }  else
        if(this.checkSigil(Sigils.TrifurcatedStrike)){
            return attackLists[2];
        }  else
        if(this.checkSigil(Sigils.OmniStrike)){
            return attackLists[3];
        }
        else {
            return attackLists[0];
        }
    }

    public int getBuff(int i) {
        switch(i){
            case 0:
            return this.checkSigil(Sigils.Stinky) ? -1 : 0;
            case -1:
            case 1:
            return this.checkSigil(Sigils.Leader) ? 1 : 0;
            default:
            return 0;            
        }
    }

    public boolean checkSigil(Sigils sigil) {
        return sigils.get(sigil);
    }

    public int takeDamage(int damage, Card card) {
        int overflow = damage>health ? damage-health : 0;
        if(damage>0){
            this.health = this.health - damage;


            if(this.field!=null&&this.field.getTurnController()!=null){
                field.getTurnController().ioHandler.getObserverOutputHandler().publishAnim(this.field, row, col, Animations.Hurt);
            }

            if(this.health <= 0){this.die();}
            if(checkSigil(Sigils.SharpQuills)){
                if(!(card == null)){
                    card.takeDamage(1, null);//realistically, if i wanted this to be robust, I would always pass the dmg src, and add a list of damage tags(poison, quill, etc). oh well
                } 
            }
        }
        if(card==null){
            System.out.println(title + " took " + damage + " point of revenge damage");
        } 
        else {
            System.out.println(card.title +  " dealt " + damage + " to " + title);
        }
        return overflow;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public int getHealth() {
        return this.health;
    } 
    public boolean checkDead(){
        return dead;
    }
    public void die(){

        if(this.field!=null&&this.field.getTurnController()!=null){
            field.getTurnController().ioHandler.getObserverOutputHandler().publishAnim(this.field, row, col, Animations.Die);
        }

        //do ouroboros
        this.dead = true;
        //field.purgeDead();//we can try a field.purgeThis(Card) too
        System.out.println(title+" has perished");
        field.purge(this);
        field.updateCardStats();
    }
    public void setField(Field field){
        this.field = field;
    }

    public void giveSigil(Sigils s) {
        this.sigils.put(s,true);
    }

    public Card makeCopy() {
        Card copy = new Card();
        copy.attack = this.attack;
        copy.baseAttack = this.baseAttack;
        copy.baseHealth = this.baseHealth;
        copy.cost = this.cost;
        copy.health = this.health;
        copy.image = this.image;
        copy.sigils = this.sigils.clone();
        copy.title = this.title;
        copy.value = this.value;
        return copy;
    }

    public void sacrifice() {
        if(this.checkSigil(Sigils.ManyLives)){
            //cat thing
        }
        else{
            this.die();
        }
    }

    public int getCost() {
        return cost;
    }

    public int getCardId() {
        return cardId;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getValue() {
        return value;
    }

    public EnumMap<Sigils, Boolean> getSigils() {
        return sigils;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public void setSigils(EnumMap<Sigils, Boolean> sigils) {
        this.sigils = sigils;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setAttackLists(int[][] attackLists) {
        this.attackLists = attackLists;
    }

    public void setPos(int r,int c){
        row = r;
        col = c;
    }

}

