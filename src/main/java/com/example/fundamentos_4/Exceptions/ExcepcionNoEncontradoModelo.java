package com.example.fundamentos_4.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcepcionNoEncontradoModelo extends RuntimeException {

    public ExcepcionNoEncontradoModelo(String mensaje) {
        super(mensaje);
    }
}
