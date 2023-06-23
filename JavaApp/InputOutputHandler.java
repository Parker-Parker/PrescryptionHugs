

public class InputOutputHandler {
    UserInputHandler userInputHandler = new UserInputHandler();
    
    public void setUserInputHandler(UserInputHandler handler){
        userInputHandler = handler;
    }

    public UserInputHandler getUserInputHandler(){
        return userInputHandler;
    }



}