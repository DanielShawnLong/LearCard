package htwsaar.ariadne.learcard.errorMsg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Card")  // 404
public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(Long id){
        super("Could not find card with id:  " + id);
    }
}
