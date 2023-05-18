public class Field {
    Card[] nullCards = {null, null, null, null};
    Card[] enemyCardsBack = {null, null, null, null};
    Card[] enemyCards = {null, null, null, null};
    Card[] playerCards = {null, null, null, null};
    int health = 0;
    

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
        System.out.println(makeLineTitle(nullCards));
        System.out.println(horiz);
        System.out.println(makeLineTitle(enemyCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
        System.out.println(makeLineTitle(nullCards));
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
        System.out.println(makeLineTitle(nullCards));
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
        enemyCardsBack[2]   = new Card(); 
        enemyCardsBack[3]   = null;
        enemyCards[0]       = new Card();
        enemyCards[1]       = new Card("test 2");
        enemyCards[2]       = null;
        enemyCards[3]       = null; 
        playerCards[0]      = null;
        playerCards[1]      = null; 
        playerCards[2]      = new Card(); 
        playerCards[3]      = null;
    }
}
