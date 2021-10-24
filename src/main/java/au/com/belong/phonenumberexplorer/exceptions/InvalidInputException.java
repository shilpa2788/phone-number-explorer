package au.com.belong.phonenumberexplorer.exceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String Id) {
        super(Id + " Not found in Database. Please try with proper data ");
    }

}
