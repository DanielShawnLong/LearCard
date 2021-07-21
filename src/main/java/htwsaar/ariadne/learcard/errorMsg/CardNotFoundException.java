package htwsaar.ariadne.learcard.errorMsg;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(Long id){
        super("Could not find card " + id);
    }
}
