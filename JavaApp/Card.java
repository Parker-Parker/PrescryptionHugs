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
    
}

