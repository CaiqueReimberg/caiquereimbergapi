package br.edu.infnet.caiquereimbergapi.exceptions;

public class NotFoundStoreException extends RuntimeException {
    public NotFoundStoreException(String message) {
        super(message);
    }
}
