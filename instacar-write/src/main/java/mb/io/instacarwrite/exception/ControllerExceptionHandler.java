package mb.io.instacarwrite.exception;

import javax.servlet.http.HttpServletRequest;

import mb.io.instacarwrite.service.exception.ImageAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import mb.io.instacarwrite.service.exception.NoElementException;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(NoElementException.class)
    public ResponseEntity<StandardError> NoElementException(NoElementException e, HttpServletRequest req){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ImageAmountException.class)
    public ResponseEntity<StandardError> ImageAmountException(ImageAmountException e, HttpServletRequest req){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest req){
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(),"data validation error",System.currentTimeMillis());
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.addFields(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }





}