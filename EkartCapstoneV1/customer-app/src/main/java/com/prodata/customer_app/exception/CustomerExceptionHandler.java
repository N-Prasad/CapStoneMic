package com.prodata.customer_app.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				"an unexpected error occurred. " + ex.getMessage(), req.getContextPath()); // req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CustomerNotFoundException.class) // 404 Not Found
	public final ResponseEntity<ErrorDetails> handleCustomerNotFoundException(CustomerNotFoundException ex,
			WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getContextPath());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// handle both billing and shipping address not found
	@ExceptionHandler(AddressNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleAddressNotFoundException(AddressNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), request.getContextPath());
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// Handle IllegalArgumentException for both Customer and Address validation
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400 Bad Request //invalid arguments for controller method
	public final ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex,
			WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Invalid input: " + ex.getMessage(),
				req.getContextPath());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// Handle validation errors for invalid @RequestBody or @RequestParam
	@Override
//	@ExceptionHandler(MethodArgumentNotValidException.class) // 400 Bad Request handle validation error for invalid
																// @RequestBody from @Valid
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " : " + error.getDefaultMessage()).collect(Collectors.joining(", "));
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), "Validation error(s): " + validationErrors,
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// Handle missing parameters like addressId or customerId
	@Override
	//@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
				"Required parameter missing: " + ex.getParameterName(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	// Handle Http Request method not support
	@Override
	//@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = "HTTP method " + ex.getMethod() + " is not supported. Supported methods are: "
				+ ex.getSupportedHttpMethods();
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
//	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<String> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                .body("Invalid Content-Type: " + ex.getContentType());
//    }

}
