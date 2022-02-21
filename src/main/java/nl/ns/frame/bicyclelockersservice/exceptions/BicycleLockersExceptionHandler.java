package nl.ns.frame.bicyclelockersservice.exceptions;

import nl.ns.frame.bicyclelockersservice.bicyclelockers.exceptions.BicycleLockersBadRequestException;
import nl.ns.frame.bicyclelockersservice.customers.exceptions.CustomersBadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BicycleLockersExceptionHandler {
    @ExceptionHandler({CustomersBadRequestException.class, BicycleLockersBadRequestException.class})
    public ResponseEntity<Object> handleConflict(
            final RuntimeException runtimeException) {
        return new ResponseEntity<>(
                runtimeException.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
