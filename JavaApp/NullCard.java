
public class NullCard extends Card{// this is a special "virtual" card, it is meant to represent striking an unoccupied card slot


    public NullCard(Field field) {
        super();
        this.field = field;
        this.title = "Null";
    }
    public int takeDamage(int damage, Card card) {
        // int overflow = damage>health ? damage-health : 0;
        // if(damage>0){

        //     this.health = this.health - damage;
        //     if(this.health <= 0){this.die();}
        //     if(checkSigil(Sigils.SharpQuills)){
        //         if(!(card == null)){
        //             card.takeDamage(1, null);//realistically, if i wanted this to be robust, I would always pass the dmg src, and add a list of damage tags(poison, quill, etc). oh well
        //         } 
        //     }
        // }
        if(card==null){
            System.out.println(title + " took " + damage + " point of revenge damage //um... WHAT!?");
        } 
        else {
            System.out.println(card.title +  " dealt " + damage + " to " + title +"  // TODO:fix this");
        }
        return 0;
    }   

}
