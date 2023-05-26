
public class NullCard extends Card{// this is a special "virtual" card, it is meant to represent striking an unoccupied card slot

    private Field field;

    public NullCard(Field field) {
        super();
        this.field = field;
    }

}
