package br.edu.infnet.caiquereimbergapi.exceptions.handler;

import br.edu.infnet.caiquereimbergapi.exceptions.InvalidBookException;
import br.edu.infnet.caiquereimbergapi.exceptions.NotFoundBookException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>>  handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
    {
        Map<String,String> map = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors().forEach((error)->{
            String message = error.getDefaultMessage();
            String fieldName = ((FieldError) error).getField();

            map.put(fieldName, message);
        });


        return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidBookException.class})
    public ResponseEntity<Map<String, String>>  handleInvalidBookException(InvalidBookException ex)
    {
        Map<String,String> map = new HashMap<String, String>();

        map.put("timestamp", LocalDateTime.now().toString());
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.BAD_REQUEST.toString());
        map.put("detail", "Verifique os dados fornecidos para o livro!");

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundBookException.class})
    public ResponseEntity<Map<String, String>>  handleNotFoundBookException(NotFoundBookException ex)
    {
        Map<String,String> map = new HashMap<String, String>();

        map.put("timestamp", LocalDateTime.now().toString());
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.toString());
        map.put("detail", "Livro não encontrado!");

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>>  handleIllegalArgumentException(IllegalArgumentException ex)
    {
        Map<String,String> map = new HashMap<String, String>();

        map.put("timestamp", LocalDateTime.now().toString());
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.BAD_REQUEST.toString());
        map.put("detail", "Algum argumento inválido foi fornecido!");

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Map<String, String>>  handleDataIntegrityViolationException(DataIntegrityViolationException ex)
    {
        Map<String,String> map = new HashMap<String, String>();

        map.put("timestamp", LocalDateTime.now().toString());
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.CONFLICT.toString());
        map.put("detail", "Problema no momento de criar!");

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Map<String, String>>  handleRuntimeException(RuntimeException ex)
    {
        Map<String,String> map = new HashMap<String, String>();

        map.put("timestamp", LocalDateTime.now().toString());
        map.put("error", ex.getMessage());
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.toString());
        map.put("detail", "Ocorreu um erro inesperado!");

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
