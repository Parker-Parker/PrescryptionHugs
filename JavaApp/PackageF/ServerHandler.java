package PackageF;

public class ServerHandler {
    // for each handler interface, javaApp should have a list of registered handlers
    // ex) javaApp has user input FIFO. any iUserInput can async put entries into
    // FIFO. during certain executeState in turncontroller, userinputhandler will
    // pull commands off Fifo and update javaAppState vars. //make command line user
    // input prog and network user input prog connected to card/field hardware
    // ex) iUXAnimationQueueHandler javaApp publishes animations to animquehandler
    // which publishes to all iAnimQueReceivers, iAnimQueReceivers can register
    // //maybe animquerec can claim/mark completed items in queue
    // should recieve client messages then implement iUserInputHandler
    // should recieve client messages then implement iUXAnimationQueueHandler
    // should recieve client messages then implement iAdminInputHandler

}
