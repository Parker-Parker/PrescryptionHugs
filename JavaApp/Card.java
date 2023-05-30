import java.util.EnumMap;



public class Card implements iCard {
    static int id = 0;
    String title = "Card ";
    EnumMap<Sigils, Boolean> sigils = new EnumMap<>(Sigils.class);
    int baseAttack = 0;
    int baseHealth = 1;
    int attack = 0;
    int health = 1;
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
        return 0;
    } 
    public boolean checkDead(){
        return dead;
    }
    public void die(){
        //do ouroboros
        this.dead = true;
        //field.purgeDead();//we can try a field.purgeThis(Card) too
        System.out.println(title+" has perished");
        field.purge(this);
    }
    public void setField(Field field){
        this.field = field;
    }

    public void giveSigil(Sigils s) {
        this.sigils.put(s,true);
    }

    public Card makeCopy() {
        return new Card();//TODO
    }
}

