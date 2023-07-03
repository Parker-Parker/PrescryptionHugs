import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class FieldStarter {
    
}




//easy game intro


class Game1 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, new Coyote(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))));

    public Game1() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game11 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, null, null, null, new Bat(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, new Coyote(), null, null, new Rattler(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, new Coyote(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, null, new WolfCub(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))));

    public Game11() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game12 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, null, null, null, new Bat(), null, null, new Sparrow(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, new Coyote(), null, null, new Rattler(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, new Coyote(), null, new Elk(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, new RiverSnapper(), null, new WolfCub(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))));

    public Game12() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game13 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, null, null, null, new Bat(), null, new Sparrow(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, new Coyote(), new WolfCub(), null, new Mole(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, new Coyote(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, new RiverSnapper(), null, new Elk(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))));

    public Game13() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game14 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, null, null, null, new Adder(), null, new Sparrow(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, new Coyote(), null, null, new Mole(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, null, new Bullfrog(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null)),
        new LinkedList<Card>(List.of(null, null, new RiverSnapper(), null, new Elk(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null))));


    public Game14() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 





//slow start and long game carkin loses




class Game2 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, new Boulder(), null, null};
    private static final Card[] playerCardsArray = {null, null, null, new Stump()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(       
        new LinkedList<Card>(List.of(new Wolf(), new Coyote(), new Mantis(), null, new Bullfrog(), null, null, null, new Wolf(), null, null, new Grizzly(), null, null, null, null, null, null, new Adder(), null, null, new Grizzly(), new Sparrow(), null, new Grizzly())),
        new LinkedList<Card>(List.of(new Bullfrog(), null, null, null, new Grizzly(), null, new Coyote(), null, new Mantis(), null, new RiverSnapper(), null, null, new Alpha(), null, new WolfCub(), new Grizzly(), null, null, null, new Grizzly(), null, new Bat(), null, null)),
        new LinkedList<Card>(List.of(null, new Wolf(), null, new Wolf(), null, new Mantis(), new Coyote(), null, null, new RiverSnapper(), new Grizzly(), null, null, new Bullfrog(), null, null, new Adder(), null, null, new WolfCub(), null, null, null, null, new Sparrow())),
        new LinkedList<Card>(List.of(null, null, new Coyote(), null, null, new Wolf(), null, null, null, new Grizzly(), new Adder(), null, new Grizzly(), new Wolf(), null, new RiverSnapper(), new Grizzly(), new Mantis(), null, null, null, null, null, null, new WolfCub()))));

    public Game2() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game21 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new WolfCub(), new Coyote(), new Mantis(), null, new Bullfrog(), null, new RiverSnapper(), null, new Alpha(), null, null, new Grizzly(), null, null, null, new WolfCub(), null, null, new Adder(), null, null, new Grizzly(), new Sparrow(), new Bat(), new Grizzly())),
        new LinkedList<Card>(List.of(new Bullfrog(), null, null, null, new Grizzly(), null, new Coyote(), null, new Mantis(), null, new Grizzly(), null, null, new RiverSnapper(), null, new WolfCub(), new Grizzly(), new Adder(), null, null, new Grizzly(), null, new Bat(), null, null)),
        new LinkedList<Card>(List.of(null, new Wolf(), null, new Alpha(), null, new Mantis(), new Coyote(), null, null, new RiverSnapper(), new Grizzly(), null, new Grizzly(), new Bullfrog(), null, null, new Adder(), null, null, new WolfCub(), null, new Grizzly(), null, null, new Sparrow())),
        new LinkedList<Card>(List.of(null, new Bullfrog(), new Grizzly(), null, new Coyote(), new Grizzly(), null, null, null, new Grizzly(), new Adder(), null, new Grizzly(), null, null, new RiverSnapper(), new Grizzly(), new Mantis(), null, new Bat(), null, null, new Grizzly(), null, new WolfCub()))));

    public Game21() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game22 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Elk(), new Coyote(), new Mantis(), null, new Bullfrog(), null, new Elk(), null, new Alpha(), null, null, new Grizzly(), null, null, null, new Mantis(), null, null, new Wolf(), null, null, new Elk(), new Wolf(), new Sparrow())),
        new LinkedList<Card>(List.of(new Grizzly(), null, null, null, new Bullfrog(), null, new Wolf(), null, new Grizzly(), null, new Grizzly(), null, null, new Elk(), null, new Coyote(), new Wolf(), new Mantis(), null, null, new Adder(), null, null, new Wolf(), new Bat())),
        new LinkedList<Card>(List.of(null, new Mantis(), null, new Grizzly(), null, new Bullfrog(), new Coyote(), null, null, new Adder(), new Grizzly(), null, new Grizzly(), new Sparrow(), null, null, new Wolf(), null, null, new RiverSnapper(), null, new Grizzly(), null, null, new Alpha())),
        new LinkedList<Card>(List.of(null, new Bullfrog(), new Wolf(), null, new RiverSnapper(), new Wolf(), null, null, null, new Wolf(), new Coyote(), null, new Wolf(), null, null, new Mantis(), new Wolf(), new Wolf(), null, new Bat(), null, null, new Wolf(), null))));


    public Game22() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 



class Game23 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Elk(), null, null, new Alpha(), null, new Coyote(), null, new Bullfrog(), new Elk(), null, new Grizzly(), null, new Mantis(), new Sparrow(), null, new Elk(), new Mantis(), null, null, null, new Wolf(), new Grizzly(), null, null, new Bat())),
        new LinkedList<Card>(List.of(null, new Grizzly(), null, null, null, new Adder(), null, new Wolf(), new Bat(), null, new Mantis(), null, new Grizzly(), new Coyote(), new Bullfrog(), null, null, new Grizzly(), null, null, null, new Wolf(), null, new Elk(), new Wolf())),
        new LinkedList<Card>(List.of(new Coyote(), null, null, new Grizzly(), new Mantis(), null, new Adder(), null, new Grizzly(), null, new Elk(), new Alpha(), new Sparrow(), null, null, new RiverSnapper(), null, new Grizzly(), new Bullfrog(), null, null, new Mantis(), null, null, new Wolf())),
        new LinkedList<Card>(List.of(new Grizzly(), null, new Bullfrog(), new RiverSnapper(), null, null, new Wolf(), null, null, new Grizzly(), new Wolf(), null, null, new Grizzly(), null, null, null, new Wolf(), null, null, new Mantis(), new Wolf(), new Coyote(), null, new Elk()))));

    public Game23() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game24 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Bullfrog(), null, new Elk(), new Mantis(), null, null, new Elk(), new Alpha(), null, new Grizzly(), null, null, new Coyote(), new Bat(), null, null, null, new Sparrow(), null, new Wolf(), new Mantis(), null, null, new Grizzly(), new Sparrow(), null, null, new Grizzly(), new Wolf(), new Elk(), null, new Coyote())),
        new LinkedList<Card>(List.of(null, new Grizzly(), null, new Wolf(), null, new Bat(), new Grizzly(), null, null, new Grizzly(), null, null, new Adder(), null, new Mantis(), new Coyote(), new Bullfrog(), new Wolf(), null, null, null, new Wolf(), null, new Elk(), new Elk(), null, new Grizzly(), new Wolf(), new Elk(), new Grizzly(), new Elk(), null, new Elk())),
        new LinkedList<Card>(List.of(new Wolf(), null, new Grizzly(), null, null, null, new Mantis(), null, new Grizzly(), new Coyote(), null, null, new RiverSnapper(), null, null, new Elk(), new Adder(), null, new Grizzly(), new Sparrow(), null, new Grizzly(), new Alpha(), new Elk(), new Bat(), new Grizzly(), new Elk(), null, new Mantis(), null, new Elk(), new Coyote(), null, new Elk())),
        new LinkedList<Card>(List.of(null, new Bullfrog(), new Elk(), null, new Wolf(), null, new Grizzly(), null, null, null, new Grizzly(), new Wolf(), null, null, new RiverSnapper(), null, null, new Wolf(), new Mantis(), null, new Coyote(), null, new Elk(), null, new Grizzly(), new Coyote(), new Elk(), new Elk(), null, new Grizzly(), new Coyote(), new Elk(), new Elk(), null, new Grizzly()))));

    public Game24() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 






//long game aggressive start carkin wins



class Game3 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {new Coyote(), null, new Wolf(), new Wolf()};
    private static final Card[] enemyCardsFrontArray = {null, new Stump(), null, null};
    private static final Card[] playerCardsArray = {new GrandFir(), null, new GrandFir(), new Boulder()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Elk(), new Elk(), new Elk(), new Wolf(), null, new Elk(), new Grizzly(), null, null, null, new Bullfrog(), null, null, null, null, null, null, new Sparrow(), null, null, new Mantis(), new Wolf(), new Grizzly(), null, null, new Coyote(), null, new Bat(), new Elk(), null, new Alpha(), null, null, new Mantis(), null, null)),
        new LinkedList<Card>(List.of(new Elk(), null, new Adder(), new Wolf(), new Wolf(), null, null, new Elk(), null, null, null, new Adder(), null, null, new Wolf(), new Elk(), null, new Coyote(), new Mantis(), null, null, new Wolf(), null, new Elk(), new Elk(), null, null, null, null, null, new Bat(), new Grizzly(), null, new Grizzly())),
        new LinkedList<Card>(List.of(new Elk(), null, new Mantis(), null, new Elk(), new MoleMan(), new Bat(), null, null, null, new Coyote(), null, null, new RiverSnapper(), null, null, new Elk(), null, null, null, null, null, null, null, null, new Bat(), new Grizzly(), new Mantis(), null, new Grizzly(), null, new Elk(), new Coyote(), null, new Wolf())),
        new LinkedList<Card>(List.of(new Elk(), null, new Rattler(), new Elk(), new Elk(), null, new Grizzly(), new Coyote(), null, new Elk(), null, new Elk(), new Coyote(), new Grizzly(), null, null, null, new Coyote(), new Grizzly(), new Elk(), null, new Wolf(), null, null, null, null, new Elk(), null, null, null, new Bullfrog(), new Elk(), new Wolf(), null, new Grizzly()))));

    public Game3() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 


class Game31 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Bullfrog(), null, new Grizzly(), new Elk(), new Wolf(), new Elk(), null, null, new Elk(), new Mantis(), null, null, new Elk(), new Bat(), new Elk(), null, null, new Coyote(), new Grizzly(), new Elk(), null, null, null, new Mantis(), null, new Sparrow(), null, new Grizzly(), null, null, new Elk(), new Grizzly(), new Wolf(), new Elk(), null, null, new Grizzly())),
        new LinkedList<Card>(List.of(new Grizzly(), null, new Grizzly(), new Bat(), new Grizzly(), null, new Grizzly(), new Elk(), new Wolf(), null, null, new Elk(), new Elk(), null, null, new Coyote(), new Grizzly(), null, null, new Wolf(), new Elk(), new Grizzly(), new Elk(), null, new Grizzly(), new Elk(), new Grizzly(), new Elk(), null, new Wolf(), new Elk(), null, new Adder(), null, null)),
        new LinkedList<Card>(List.of(new Elk(), new Coyote(), null, new Elk(), new Alpha(), null, null, new Elk(), new Bat(), new Grizzly(), null, new Grizzly(), new Sparrow(), null, null, new Elk(), new Mantis(), new Grizzly(), new Elk(), null, new Grizzly(), null, new Elk(), null, new Elk(), new Grizzly(), null, new Coyote(), null, new Mantis(), null, new Elk(), new Grizzly(), new Elk(), new Mantis())),
        new LinkedList<Card>(List.of(new Grizzly(), null, null, new Elk(), new Bullfrog(), null, new Grizzly(), new Elk(), null, null, new Elk(), new Coyote(), null, new Grizzly(), null, new Grizzly(), new Elk(), null, new Elk(), new Elk(), new Elk(), null, null, new Mantis(), new Grizzly(), null, new Grizzly(), null, new Coyote(), null, new Grizzly(), new Elk(), new Elk(), null, null, null))));

    public Game31() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game32 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Grizzly(), null, null, new Elk(), new Elk(), new Grizzly(), null, new Elk(), new Coyote(), null, new Mantis(), null, null, new Grizzly(), new Wolf(), new Elk(), null, new Bat(), null, new Elk(), new Grizzly(), new Wolf(), null, null, null, new Sparrow(), null, null, new Elk(), null, new Grizzly(), new Elk(), new Mantis(), null, new Grizzly(), new Elk())),
        new LinkedList<Card>(List.of(null, null, new Coyote(), new Grizzly(), null, new Grizzly(), new Elk(), new Elk(), null, null, new Elk(), new Grizzly(), new Elk(), null, null, new Elk(), new Bat(), null, new Grizzly(), null, new Elk(), new Adder(), new Grizzly(), null, null, null, new Wolf(), new Grizzly(), new Elk(), null, new Elk(), null, new Wolf(), new Elk(), null, new Grizzly(), null, new Elk())),
        new LinkedList<Card>(List.of(new Elk(), new Mantis(), new Grizzly(), null, null, new Elk(), new Grizzly(), null, null, new Elk(), new Grizzly(), null, new Coyote(), null, new Elk(), new Wolf(), new Elk(), null, new Grizzly(), new Elk(), null, new Mantis(), new Grizzly(), null, new Elk(), new Elk(), null, null, new Elk(), new Elk(), new Sparrow(), null, new Grizzly(), new Elk(), null, null)),
        new LinkedList<Card>(List.of(null, new Elk(), new Elk(), null, null, new Elk(), new Coyote(), new Elk(), null, null, new Grizzly(), new Elk(), new Mantis(), null, null, new Elk(), new Bullfrog(), null, new Grizzly(), new Grizzly(), null, new Elk(), null, new Coyote(), null, null, null, new Elk(), new Mantis(), new Grizzly(), null, new Grizzly(), null, new Elk(), null, new Grizzly(), new Elk()))));

    public Game32() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game33 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, new Elk(), new Grizzly(), null, new Elk(), null, null, null, new Elk(), null, new Elk(), new Elk(), null, new Elk(), new Elk(), new Grizzly(), new Grizzly(), null, new Mantis(), null, new Elk(), new Coyote(), null, new Wolf(), new Grizzly(), null, new Sparrow(), null, new Elk(), null, new Grizzly(), new Elk(), new Coyote(), new Grizzly(), new Mantis(), null, null)),
        new LinkedList<Card>(List.of(new Elk(), null, new Elk(), null, null, null, new Grizzly(), new Elk(), new Coyote(), new Elk(), new Grizzly(), new Elk(), null, null, new Grizzly(), new Elk(), new Elk(), null, new Elk(), null, null, new Bat(), new Elk(), new Grizzly(), null, null, new Elk(), new Coyote(), null, new Elk(), null, new Elk(), null, new Mantis(), new Grizzly(), new Wolf())),
        new LinkedList<Card>(List.of(null, null, new Mantis(), new Elk(), new Elk(), null, new Grizzly(), new Grizzly(), null, new Elk(), null, new Grizzly(), new Elk(), new Grizzly(), new Wolf(), null, new Elk(), null, new Elk(), null, null, null, new Elk(), null, new Sparrow(), new Grizzly(), new Elk(), new Mantis(), new Grizzly(), null, null, new Coyote(), new Elk(), new Elk(), null, new Grizzly())),
        new LinkedList<Card>(List.of(new Elk(), null, new Elk(), new Coyote(), new Elk(), null, new Elk(), new Elk(), new Elk(), new Grizzly(), null, new Bullfrog(), new Elk(), new Grizzly(), null, new Grizzly(), new Mantis(), null, new Elk(), null, null, new Wolf(), new Elk(), new Grizzly(), null, null, new Grizzly(), new Elk(), null, new Grizzly(), new Grizzly(), new Elk(), null, new Bat(), null, null))));


    public Game33() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game34 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {null, null, null, null};
    private static final Card[] enemyCardsFrontArray = {null, null, null, null};
    private static final Card[] playerCardsArray = {null, null, null, null};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Elk(), new Elk(), null, new Elk(), new Elk(), new Elk(), null, new Elk(), new Elk(), new Grizzly(), null, new Grizzly(), new Mantis(), null, new Grizzly(), new Elk(), null, null, null, new Coyote(), null, new Elk(), null, new Elk(), new Grizzly(), new Sparrow(), null, new Elk(), new Grizzly(), new Coyote(), null, new Mantis(), null, new Elk(), null, null)),
        new LinkedList<Card>(List.of(null, new Elk(), new Grizzly(), new Elk(), null, new Grizzly(), new Elk(), new Elk(), null, null, new Bat(), new Grizzly(), new Elk(), new Elk(), new Elk(), null, new Elk(), new Coyote(), new Elk(), new Elk(), new Elk(), new Mantis(), new Grizzly(), new Wolf(), null, new Elk(), null, new Elk(), new Coyote(), null, null, null, null, new Grizzly(), null, new Elk())),
        new LinkedList<Card>(List.of(new Mantis(), new Elk(), null, null, null, null, new Elk(), null, new Elk(), new Grizzly(), null, new Grizzly(), null, null, new Elk(), new Grizzly(), new Elk(), new Elk(), null, null, null, null, new Elk(), new Elk(), new Grizzly(), null, null, new Grizzly(), null, null, new Sparrow(), null, null, new Elk(), new Coyote(), new Grizzly(), null, null, new Elk())),
        new LinkedList<Card>(List.of(new Elk(), new Coyote(), new Elk(), null, new Grizzly(), null, new Elk(), new Grizzly(), null, new Elk(), new Elk(), null, new Elk(), null, new Elk(), null, new Grizzly(), new Elk(), null, null, new Grizzly(), new Elk(), null, new Elk(), new Mantis(), new Elk(), null, null, new Elk(), new Bullfrog(), new Wolf(), null, new Elk(), new Grizzly(), null, null, new Elk()))));


    public Game34() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 







//very tight match average length carkin loses




class Game4 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {new Grizzly(), null, null, new Mantis()};
    private static final Card[] enemyCardsFrontArray = {new Dwayne(), null, new Coyote(), null};
    private static final Card[] playerCardsArray = {null, new Stump(), new Stump() , new Stump()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, new Coyote(), null, new WolfCub(), null, null, new Bullfrog(), null, new Bat(), null, new Grizzly(), null, new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly())),
        new LinkedList<Card>(List.of(new RiverSnapper(), null, new Mantis(), null, new Alpha(), null, new Grizzly(), null, null, new Adder(), null, null, new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly())),
        new LinkedList<Card>(List.of(null, new Wolf(), null, null, new Wolf(), null, new Coyote(), new Bloodhound(), null, null, new Adder(), null, new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly())),
        new LinkedList<Card>(List.of(null, null, null, new Wolf(), new RiverSnapper(), new Grizzly(), null, null, null, new Sparrow(), null, null, new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly(), new Grizzly()))));

    public Game4() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

//if sigils work we can use mantis gods instead of grizzly bears^
class Game41 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {new Grizzly(), null, null, new Mantis()};
    private static final Card[] enemyCardsFrontArray = {new Dwayne(), null, new Coyote(), null};
    private static final Card[] playerCardsArray = {null, new Stump(), new Stump() , new Stump()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new Grizzly(), new Coyote(), null, null, new Bullfrog(), null, new WolfCub(), null, null, null, new Grizzly(), null, null, new Grizzly(), null, null, new Grizzly(), null, new Grizzly(), null, new Grizzly(), null, new Grizzly(), null, null)),
        new LinkedList<Card>(List.of(new Grizzly(), null, new RiverSnapper(), new Grizzly(), null, new Grizzly(), new Mantis(), null, new Coyote(), null, null, null, new Adder(), null, null, new Grizzly(), null, new Grizzly(), null, new Grizzly(), new Bullfrog(), new Sparrow(), new Grizzly(), new Grizzly())),
        new LinkedList<Card>(List.of(new Grizzly(), null, null, null, new Grizzly(), null, null, new Wolf(), null, new Grizzly(), null, null, new RiverSnapper(), new Grizzly(), null, null, null, new Sparrow(), null, null, new Grizzly(), new Alpha(), new Grizzly(), new Coyote(), new Grizzly())),
        new LinkedList<Card>(List.of(new Grizzly(), new Bullfrog(), new WolfCub(), new Grizzly(), new Adder(), new Grizzly(), null, null, null, new Grizzly(), new RiverSnapper(), new Grizzly(), null, null, new Mantis(), new Grizzly(), null, new Bat(), null, new Alpha(), null, new Grizzly(), new Grizzly(), null, new Grizzly()))));


    public Game41() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game42 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {new Grizzly(), null, null, new Mantis()};
    private static final Card[] enemyCardsFrontArray = {new Dwayne(), null, new Coyote(), null};
    private static final Card[] playerCardsArray = {null, new Stump(), new Stump() , new Stump()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, new Grizzly(), null, new Bullfrog(), null, null, new Grizzly(), null, new WolfCub(), null, null, new Grizzly(), null, new Grizzly(), new RiverSnapper(), null, null, null, new Grizzly(), null, new Grizzly(), new Mantis(), null, new Coyote(), null)),
        new LinkedList<Card>(List.of(null, null, new Grizzly(), null, null, null, new Grizzly(), null, null, new Bullfrog(), new Grizzly(), new Wolf(), null, new Grizzly(), new Mantis(), null, null, null, new Grizzly(), new Adder(), null, new Alpha(), null, new Grizzly(), null)),
        new LinkedList<Card>(List.of(null, new Grizzly(), null, null, null, new Coyote(), null, null, null, new Bullfrog(), null, new Grizzly(), new RiverSnapper(), new Grizzly(), null, null, new Mantis(), null, new Grizzly(), new Wolf(), new Grizzly(), null, null, new Grizzly(), new Grizzly())),
        new LinkedList<Card>(List.of(null, new Grizzly(), new Grizzly(), null, new RiverSnapper(), null, null, null, null, new Grizzly(), new Coyote(), null, null, new Coyote(), null, null, new Bullfrog(), null, new WolfCub(), null, null, new Grizzly(), null, null, new Grizzly()))));


    public Game42() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game43 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {new Grizzly(), null, null, new Mantis()};
    private static final Card[] enemyCardsFrontArray = {new Dwayne(), null, new Coyote(), null};
    private static final Card[] playerCardsArray = {null, new Stump(), new Stump() , new Stump()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(new WolfCub(), null, new Bat(), null, null, new Bullfrog(), null, null, null, new Adder(), null, new RiverSnapper(), new Sparrow(), null, null, new Mantis(), null, null, new Coyote(), null, new Grizzly(), null, new Alpha(), null, null)),
        new LinkedList<Card>(List.of(new Bullfrog(), null, null, new Mantis(), null, null, new WolfCub(), null, null, new Grizzly(), null, null, null, new RiverSnapper(), new Adder(), null, new Coyote(), new Grizzly(), new Grizzly(), new Sparrow(), null, new Grizzly(), new Bat(), null)),
        new LinkedList<Card>(List.of(null, new Coyote(), null, new Bullfrog(), null, new Grizzly(), new Mantis(), null, null, null, new Adder(), new Alpha(), null, null, new Grizzly(), null, null, new Grizzly(), new RiverSnapper(), null, null, null, new Bat(), null)), 
        new LinkedList<Card>(List.of(null, new Bullfrog(), new Grizzly(), new RiverSnapper(), null, null, new WolfCub(), null, new Mantis(), null, null, new Grizzly(), null, new Grizzly(), null, new Coyote(), null, new Grizzly(), null, new Grizzly(), new Adder(), new Sparrow(), null))));

    public Game43() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 

class Game44 extends Field {

    private static final Card[] nullArray = {null, null, null, null};
    private static final Card[] enemyCardsBackArray = {new Grizzly(), null, null, new Mantis()};
    private static final Card[] enemyCardsFrontArray = {new Dwayne(), null, new Coyote(), null};
    private static final Card[] playerCardsArray = {null, new Stump(), new Stump() , new Stump()};
    private static final ArrayList<LinkedList<Card>> enemyPlannedMovesArrayList = new ArrayList<>(List.of(      
        new LinkedList<Card>(List.of(null, new Bullfrog(), new Coyote(), null, new RiverSnapper(), null, new Bat(), null, new Alpha(), null, null, new Grizzly(), null, null, null, new Mantis(), null, null, new WolfCub(), null, null, new Adder(), new Grizzly(), new Sparrow())),
        new LinkedList<Card>(List.of(new Grizzly(), null, null, null, new Bullfrog(), null, new WolfCub(), null, new Grizzly(), null, new Grizzly(), null, null, new RiverSnapper(), null, new Coyote(), new Grizzly(), new Mantis(), null, null, new Adder(), null, null, new Grizzly(), new Bat())),
        new LinkedList<Card>(List.of(null, new Mantis(), null, new Grizzly(), null, new Bullfrog(), new Coyote(), null, null, new Adder(), new Grizzly(), null, new Grizzly(), new Sparrow(), null, null, new WolfCub(), null, null, new RiverSnapper(), null, new Grizzly(), null, null, new Alpha())),
        new LinkedList<Card>(List.of(null, null, new Bullfrog(), null, new RiverSnapper(), new Grizzly(), null, null, null, new Grizzly(), new Coyote(), null, new Grizzly(), null, null, new Mantis(), new Grizzly(), new Grizzly(), null, new Bat(), null))));

    public Game44() {
         super(nullArray, enemyCardsBackArray, enemyCardsFrontArray, playerCardsArray, enemyPlannedMovesArrayList);
    }
} 



