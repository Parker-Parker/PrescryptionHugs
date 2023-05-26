import java.util.EnumMap;



public class Card implements iCard {
    static int id = 0;
    String title = "Card ";
    EnumMap<Sigils, Boolean> sigils = new EnumMap<>(Sigils.class);
    int baseAttack = 0;
    int baseHealth = 1;
    int attack = 0;
    int health = 1;
    

    public Card() {
        title = title + id;
        id++;
    }
    
    public Card(String input) {
        title = input;
    }

    public void setTitle(String input) {
        title = input;
    }
    
    public String getTitle() {
        return title;
    }

    public void onSummon(Field field) {
    }

    public void doAttack() {
    }

    public int[] getAttacks(int i) {
        return null;
    }

    public int getBuff(int i) {
        return 0;
    }

    public boolean checkSigil(Sigils waterborne) {
        return false;
    }

    public int takeDamage(int damage, Card card) {
        return 0;
    }

    public int getBaseAttack() {
        return 0;
    } 
    
}

