package com.example.fundamentos_4.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class InterceptadorErrores extends ResponseEntityExceptionHandler {
    public final ResponseEntity<ExcepcionRespuesta> manejadorTodasLasExcepciones(Exception ex, WebRequest request) {
        ExcepcionRespuesta excepcion = new ExcepcionRespuesta(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(excepcion, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExcepcionNoEncontradoModelo.class)
    public ResponseEntity<ExcepcionRespuesta> manejadorExcepcionNoEncontradoModelo(ExcepcionNoEncontradoModelo ex, WebRequest request) {
        ExcepcionRespuesta excepcion = new ExcepcionRespuesta(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(excepcion, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String mensaje = ex.getBindingResult().getAllErrors().stream().map(e -> {return e.getDefaultMessage();}).collect(Collectors.joining());

        ExcepcionRespuesta excepcion = new ExcepcionRespuesta(LocalDateTime.now(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(excepcion, HttpStatus.BAD_REQUEST);
    }
}
