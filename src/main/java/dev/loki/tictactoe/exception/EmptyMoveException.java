package dev.loki.tictactoe.exception;

public class EmptyMoveException extends Throwable {
    public EmptyMoveException(String message) {
        super(message);
    }

}
