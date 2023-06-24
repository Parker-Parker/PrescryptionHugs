

public class InputOutputHandler {
    //User input commands
    UserInputHandler userInputHandler = new UserInputHandler();
    
    public void setUserInputHandler(UserInputHandler handler){
        userInputHandler = handler;
    }

    public UserInputHandler getUserInputHandler(){
        return userInputHandler;
    }



    //Observer output commands //for clients that want field/card data
    ObserverOutputHandler observerOutputHandler = new ObserverOutputHandler();
    
    public void setObserverOutputHandler(ObserverOutputHandler handler){
        observerOutputHandler = handler;
    }

    public ObserverOutputHandler getObserverOutputHandler(){
        return observerOutputHandler;
    }



}