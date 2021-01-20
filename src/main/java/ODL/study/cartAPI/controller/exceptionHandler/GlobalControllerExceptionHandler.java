package ODL.study.cartAPI.controller.exceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(ex.getMessage() == null ? "Entity do not found!" : ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> constraintViolationException(ConstraintViolationException ex) {
        return new ResponseEntity<>(new ErrorMessage("Not valid data!"), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new ErrorMessage(ex.getMessage()), status);
    }

    @Data
    @RequiredArgsConstructor
    private static class ErrorMessage {
        private final String message;
    }
}
