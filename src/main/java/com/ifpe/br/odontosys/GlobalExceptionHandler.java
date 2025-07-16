package com.ifpe.br.odontosys;

import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.AllArgsConstructor;
import lombok.Getter;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException ex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ErrorMessage errorMessage = new ErrorMessage(java.time.LocalDateTime.now().format(formatter),ex.getMessage());

        return ResponseEntity.badRequest().body(errorMessage);
    }

    @Getter
    @AllArgsConstructor
    public class ErrorMessage {
        private String time;
        private String message;
    }

}
