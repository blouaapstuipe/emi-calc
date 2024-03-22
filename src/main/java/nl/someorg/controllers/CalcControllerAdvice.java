package nl.someorg.controllers;

import nl.someorg.calculators.CalculatorException;
import nl.someorg.model.CalcResult;
import nl.someorg.validation.CalcValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class CalcControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { CalcValidationException.class})
    protected ResponseEntity<Object> handleValidationException(
            RuntimeException ex, WebRequest request) {
        CalcResult result = new CalcResult();
        result.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, result,
                new HttpHeaders(), HttpStatus.OK, request);
    }

    @ExceptionHandler(value
            = { CalculatorException.class})
    protected ResponseEntity<Object> handleCalculatorException(
            RuntimeException ex, WebRequest request) {
        CalcResult result = new CalcResult();
        result.setMessage(ex.getMessage());
        return handleExceptionInternal(ex, result,
                new HttpHeaders(), HttpStatus.OK, request);
    }
}
