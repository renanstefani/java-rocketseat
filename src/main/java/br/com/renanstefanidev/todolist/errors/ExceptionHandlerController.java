package br.com.renanstefanidev.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// ControllerAdvice é uma annotation do spring usada pra definir classes globais no momento de tratamento de exceções
@ControllerAdvice
public class ExceptionHandlerController {
    
    // Toda exceção do tipo descrito vai passar por esse método antes de retornar ao usuário 
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        // o getMessage abaixo vai retornar a mensagem criada na exception
        // utilizamos o getMostSpecificCause() para evitar a conversão JSON e alertar usuário
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMostSpecificCause().getMessage());


    }
}
