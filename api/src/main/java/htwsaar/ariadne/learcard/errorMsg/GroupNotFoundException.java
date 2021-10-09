package htwsaar.ariadne.learcard.errorMsg;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Group not found exception
 * @author Pamela Filipinski
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Group")  // 404
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(Long id) {
        super("Could not find group with id: " + id);
    }

}
