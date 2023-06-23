
public class NullCard extends Card{// this is a special "virtual" card, it is meant to represent striking an unoccupied card slot
 
    public boolean turn = true;//true if player is attacking

    public NullCard(Field field) {
        super();
        this.field = field;
        this.title = "Null";
    }
    public int takeDamage(int damage, Card card) {
        
        if(card==null){
            System.out.println(title + " took " + damage + " point of revenge damage //um... WHAT!?");
        } 
        else {
            System.out.println(card.title +  " dealt " + damage + " to " + title);
            
            field.directDamage( this.turn? damage:-damage );//positive tips scale in player's favor
        }
        return 0;
    }
    public void setTurn(boolean playerTurn) {
        this.turn = playerTurn;
    }   

}
