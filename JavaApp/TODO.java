public class TODO{
int stuff;

    //TODO:animation queue
    //TODO:Projector Field Output Render client
    //TODO:Projector Field Output Render iNetworkInput that extends GenericNetworkInput and has config cmd in TCPServerMaster

    //TODO: Polished network player input client(that presents field info and options)

    //TODO: UserInputHandler --> InputHandler
        //TODO:manage all input output types
        //TODO:TCPServerMaster.UserInputHandler -->InputHandler
        //TODO:TCPServerMaster registers all handlers correctly base on config command
    //TODO: implement <>InputHandler for all ioTypes
    //TODO: implement i<>Input for all ioTypes
    //TODO: implement InputProtocolString.parse() for all ioTypes
    //TODO: there was something else like this, cant remember

    
    //TODO:Card Input Hardware client //TODO: literally the arduiino
    //TODO:Card Input Hardware iNetworkInput that extends GenericNetworkInput and has config cmd in TCPServerMaster

    //TODO:for pretty much anything that wants to talk to server: iNetworkInput that extends GenericNetworkInput and has config cmd in TCPServerMaster
        //leshyfield
        //carkinfield/hand/deck
        

    //TODO:Card Input Hardware iNetworkInput that extends GenericNetworkInput and has config cmd in TCPServerMaster

    //TODO:iObserverInput has ability to sub/register multiple topics
        //TODO:iObserverInput topics: //Hand, deck, field, leshyplan,// state, lives //map //inputs network debug ??//animation
    //TODO: ObserverInputHandler must publish to all subbed Iobserverinput //maybe like oih.pub(topic, string)
    //TODO: ObserverInputHandler must have encode/decode method for all things(i.e. field,card, list<card>...) 
    //TODO: ObserverInputHandler must add timestamp/sequence id to messages 
    ////////////////////////////////////////////////////////////////////////////////////////////

    //TODO: field.refresh

    //TODO: implement game/map/something to track state outside of round
        //TODO:Lives
        //TODO:Decks
        //TODO:Map between rounds
        //TODO:loading field from file/script/itinerary
        //TODO:holds turncontroller
        //TODO:clones decks to give to field for destructive use during round 

    //TODO: encounter/round that stores list of leshy's moves for a battle (and maybe interrupts to register)
    //TODO: Leshy behavior
        //leshy load moveslist from file
        //leshy change movelist admin

    //TODO: implement event/admin interrupt turncontroller

    //TODO: card lib manager
    //TODO: implement sigil behaviors

    //TODO: Turncontroller.executeState() fix playerReady
    //TODO: Turncontroller.executeState() fix playerSacrifice
    //TODO: Turncontroller.executeState() fix playerSummon

    //TODO: Turncontroller.executeState() add LeshyPlayBack //TODO: to put cards into back row from leshy's mind

///////////////////////////////////////////////////////////////////////////////////////////////////
    //TODO: Damage class with damage tags: overflow poison fly quillrevenge etc// this will allow working combo
    //TODO: Fix get buff
    //TODO: add field/card refresh so that card data always up to dates
    //TODO: add publish anim messages to every fieldstate change(in attack/defend functions themselves or maybe field can do somethging wierd)
        //TODO: try to pub animations in correct order



///////////////////////////////////////////////////////////////////////////////////////////////////
    //may need to make HANDLESS/Deckless implementation, where the software does not track carkin's hand
    //instead trusts what RFID reader says and make cards on the fly the moment they are played 
///////////////////////////////////////////////////////////////////////////////////////////////////
    //cardASSETS
}

