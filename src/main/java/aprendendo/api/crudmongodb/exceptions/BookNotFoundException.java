package aprendendo.api.crudmongodb.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND)
public class BookNotFoundException extends RuntimeException{
    
    public BookNotFoundException() {
        super("Book with this id not found",null,false,false);
    }
}
