package br.edu.infnet.caiquereimbergapi.exceptions;

public class NotFoundBookException extends RuntimeException {
    public NotFoundBookException(String message) {
        super(message);
    }
}
