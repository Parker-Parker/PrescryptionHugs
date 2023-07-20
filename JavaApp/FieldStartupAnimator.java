
public class FieldStartupAnimator {

    private final static boolean takeHandCards = false;

    public static void animatePickup(Field finalField, ObserverOutputHandler observerOutputHandler) {
        
        int sent = 0;
        if(finalField!=null&&observerOutputHandler!=null){
            Field tempField = new Field();

            for(int col = 0;col <4;col++){
                tempField.getEnemyCardsBack()[col] = finalField.getEnemyCardsBack()[col] ;
                tempField.getEnemyCards()[col] = finalField.getEnemyCards()[col] ;
                tempField.getPlayerCards()[col] = finalField.getPlayerCards()[col] ;

            }


            for(int col = 0;col <4;col++){
                if(tempField.getEnemyCardsBack()[col]!=null){
                    observerOutputHandler.publishAnim(tempField, 0, col, Animations.YeetOut);
                    tempField.getEnemyCardsBack()[col] = null;
                    // sent++;
                }
                
                
            }
            for(int col = 0;col <4;col++){
                if(tempField.getEnemyCards()[col]!=null){
                
                    observerOutputHandler.publishAnim(tempField, 1, col, Animations.YeetOut);
                    tempField.getEnemyCards()[col] = null ;
                    // sent++;
                }
            }
            for(int col = 0;col <4;col++){
                if(tempField.getPlayerCards()[col]!=null){
                    if(takeHandCards||!tempField.getPlayerCards()[col].checkSigil(Sigils.PlayedFromHand)){
                        observerOutputHandler.publishAnim(tempField, 2, col, Animations.YeetOut);
                    }
                    tempField.getPlayerCards()[col] = null ;
                    // sent++;
                }
            }



        }
        System.out.println("Sent "+sent +" field"+(finalField!=null)+" obsout:"+(observerOutputHandler!=null));

    }

    public static void animatePutdown(Field finalField, ObserverOutputHandler observerOutputHandler) {
        int sent = 0;
        if(finalField!=null&&observerOutputHandler!=null){
            Field tempField = new Field();
            tempField.clear();
            
            for(int col = 0;col <4;col++){
                if(finalField.getPlayerCards()[col]!=null){
                
                    tempField.getPlayerCards()[col] = finalField.getPlayerCards()[col] ;
                    observerOutputHandler.publishAnim(tempField, 2, col, Animations.YeetIn);
                    // sent++;
                }
            }
            for(int col = 0;col <4;col++){
                if(finalField.getEnemyCards()[col]!=null){
                    tempField.getEnemyCards()[col] = finalField.getEnemyCards()[col] ;
                    observerOutputHandler.publishAnim(tempField, 1, col, Animations.YeetIn);
                    // sent++;
                }
            }
            for(int col = 0;col <4;col++){
                if(finalField.getEnemyCardsBack()[col]!=null){
                    tempField.getEnemyCardsBack()[col] = finalField.getEnemyCardsBack()[col] ;
                    observerOutputHandler.publishAnim(tempField, 0, col, Animations.YeetIn);
                    // sent++;
                }
            }
            
        }
        // System.out.println("Sent "+sent +" field"+(finalField!=null)+" obsout:"+(observerOutputHandler!=null));
    }

}
