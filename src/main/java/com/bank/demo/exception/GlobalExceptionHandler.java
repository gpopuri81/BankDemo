package com.bank.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	  @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
		  ErrorResponse errorResponse = new ErrorResponse();
		  errorResponse.setErrorCode("400");
		  errorResponse.setMessage(ex.getLocalizedMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
	    	ErrorResponse errorResponse = new ErrorResponse();
			  errorResponse.setErrorCode("500");
			  errorResponse.setMessage(ex.getLocalizedMessage());
	        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
	    	ErrorResponse errorResponse = new ErrorResponse();
	        String errorMessage = ex.getBindingResult().getAllErrors().stream()
	                .map(error -> error.getDefaultMessage())
	                .reduce((msg1, msg2) -> msg1 + "; " + msg2)
	                .orElse("Validation error");
	        
	        errorResponse.setErrorCode("400");
			  errorResponse.setMessage(errorMessage);
	        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	    }

		public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
			log.warn("Customer not found", ex);// TODO: need to log proper tracking infomration for the exception like use micrometer or any other tracing technique
			ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setErrorCode("customer.not.found");
	        errorResponse.setMessage("Customer not found");//Need to use the messages using message source
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);// We may use 422 as well for business exceptions
		}

}
