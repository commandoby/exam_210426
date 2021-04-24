package by.techmeskills.figuresfx.exceptions;

public class WrongShapeException extends Exception {
    public WrongShapeException(String message) {
        super("Wrong shape. " + message);
    }
}
