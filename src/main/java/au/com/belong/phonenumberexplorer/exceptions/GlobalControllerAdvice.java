package au.com.belong.phonenumberexplorer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice

{
    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {

        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);

    }
    //Can add further exceptions if any eg : for bad request  etc

}
