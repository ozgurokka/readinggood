package com.getir.readingGood.exception;

import com.getir.readingGood.model.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

 /* @ExceptionHandler
  public ResponseEntity<AbstractMap.SimpleEntry<String, String>> handle(Exception exception) {
    LOG.error("Request could not be processed: ", exception);
    AbstractMap.SimpleEntry<String, String> response =
        new AbstractMap.SimpleEntry<>("message", "Request could not be processed");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class) // 1.
  public ResponseEntity<List<String>> handleException(
          HttpServletRequest request, MethodArgumentNotValidException e) {

    List<String> collect = e.getBindingResult()
            .getAllErrors().stream()
            .map(ObjectError::getDefaultMessage)
            .collect(Collectors.toList());

    return ResponseEntity.badRequest().body(collect);
  }*/

  @ExceptionHandler(CustomerAlreadyExistException.class)
  protected ResponseEntity<Object> alreadyRegistered(CustomerAlreadyExistException ex) {
    ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  protected ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(BookNotFoundException.class)
  protected ResponseEntity<Object> handleBookNotFound(BookNotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(NotEnoughStockException.class)
  protected ResponseEntity<Object> handlekNotEnoughStock(NotEnoughStockException ex) {
    ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<Object> handlekNotEnoughStock(NotFoundException ex) {
    ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
    apiError.setMessage(ex.getMessage());
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {

    return new ResponseEntity<>(apiError, apiError.getStatus());
  }


}
