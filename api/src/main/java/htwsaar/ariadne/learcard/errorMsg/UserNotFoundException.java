package htwsaar.ariadne.learcard.errorMsg;

/**
 * User not found exception
 * @author Pamela Filipinski
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id){
        super("Could not find user " + id);
    }
}
