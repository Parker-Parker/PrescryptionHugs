public enum TurnState {
    playerDeal,  //only once
    playerBegin,
    
    playerDraw,
    playerReady,  //thinking
    playerSacrifice,
    playerSummon,

    playerEnd,
    playerAttack,
    interruptEvent,
    leshyBegin,
    leshySummon,
    leshyAttack,
    leshyWin,
    leshyEnd,
    // non sure where to put this
    // but for ux animations, Turn controller should dump a listto some sort of log that gets published to subscribers(global animation queue?like on a bus???) ex:
    //      ex:
    //      playerCard1 ATK left
    //      LeshyCard0 DIE
    //      playerCard1 ATK up
    //      LeshyCard1 HP 3>1
    //      playerCard1 DIE
    //      playerCard3 ATK up
    //      LeshyCard3 DIE
    //      LeshyBackCard3 HP 4>2
    //      LeshyBackCard3 HP 4>2
    //      LeshybackCard3 MOV down
    //      LeshyCard1 ATK down
    //      LeshyCard3 ATK down fly
    
    
}
