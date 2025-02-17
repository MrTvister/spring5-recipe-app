package guru.springframework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException(String recipe_not_found) {
        super(recipe_not_found);
    }

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
