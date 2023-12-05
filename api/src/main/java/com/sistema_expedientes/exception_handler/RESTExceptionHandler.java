package com.sistema_expedientes.exception_handler;

import com.sistema_expedientes.exception_handler.message.CustomErrorMessage;
import com.sistema_expedientes.exception_handler.message.FieldErrorRequestFormat;
import com.sistema_expedientes.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RESTExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<CustomErrorMessage> ResourceNotFoundExceptionHandler(ResourceNotFoundException exception){
        CustomErrorMessage errorMessage = new CustomErrorMessage(exception.getMessage(), exception.getCause().getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<FieldErrorRequestFormat> MethodArgumentNotValidExceptionHandler(
            MethodArgumentNotValidException exception
    ){
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(
                (error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FieldErrorRequestFormat(
                "Existen problemas con el formato de la petición",
                errors
        ));

    }

}
