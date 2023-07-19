
public class FieldStartupAnimator {

    public static void animatePickup(Field finalField, ObserverOutputHandler observerOutputHandler) {
        if(finalField!=null&&observerOutputHandler!=null){
            Field tempField = new Field();

            for(int col = 0;col >4;col++){
                tempField.getEnemyCardsBack()[col] = finalField.getEnemyCardsBack()[col] ;
                tempField.getEnemyCards()[col] = finalField.getEnemyCards()[col] ;
                tempField.getPlayerCards()[col] = finalField.getPlayerCards()[col] ;

            }




            for(int col = 0;col >4;col++){
                observerOutputHandler.publishAnim(tempField, 0, col, Animations.YeetOut);
                tempField.getEnemyCardsBack()[col] = null;
            }
            for(int col = 0;col >4;col++){
                observerOutputHandler.publishAnim(tempField, 1, col, Animations.YeetOut);
                tempField.getEnemyCards()[col] = null ;
            }
            for(int col = 0;col >4;col++){
                observerOutputHandler.publishAnim(tempField, 2, col, Animations.YeetOut);
                tempField.getPlayerCards()[col] = null ;
            }



        }

    }

    public static void animatePutdown(Field finalField, ObserverOutputHandler observerOutputHandler) {
        if(finalField!=null&&observerOutputHandler!=null){
            Field tempField = new Field();
            tempField.clear();
            
            for(int col = 0;col >4;col++){
                tempField.getPlayerCards()[col] = finalField.getPlayerCards()[col] ;
                observerOutputHandler.publishAnim(tempField, 2, col, Animations.YeetIn);
            }
            for(int col = 0;col >4;col++){
                tempField.getEnemyCards()[col] = finalField.getEnemyCards()[col] ;
                observerOutputHandler.publishAnim(tempField, 1, col, Animations.YeetIn);
            }
            for(int col = 0;col >4;col++){
                tempField.getEnemyCardsBack()[col] = finalField.getEnemyCardsBack()[col] ;
                observerOutputHandler.publishAnim(tempField, 0, col, Animations.YeetIn);
            }
            
        }
    }

}
