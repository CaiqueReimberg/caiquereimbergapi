package br.edu.infnet.caiquereimbergapi.exceptions;

public class NotFoundComicException extends RuntimeException {
    public NotFoundComicException(String message) {
        super(message);
    }
}
