package br.com.jeansemolini.cbfapi.domain.exception;

public class RegistroJaExisteException extends RuntimeException {

    public RegistroJaExisteException(String message) {
        super(message);
    }
}
