package com.prodata.product_app.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)// 500
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getContextPath()); // req.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ProductNotFoundException.class) //404 Not Found
	public final ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getContextPath());
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)// 400 Bad Request //invalid arguments for controller method
	public final ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest req) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getContextPath());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	@ResponseStatus(HttpStatus.BAD_REQUEST)// 400 Bad Request handle validation error for invalid @RequestBody from @Valid
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String errorMsg = "Validation Failed: " + ex.getErrorCount() + " errors. First error is " + ex.getMessage();
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), errorMsg, ex.getBindingResult().toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}

	@Override
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED) // 405
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = "HTTP method " + ex.getMethod() + " is not supported. Supported methods are: "
				+ ex.getSupportedHttpMethods();

		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), message, request.getContextPath());
		return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
	}

//	handleHttpRequestMethodNotSupported
// handleMethodNotAllowedException
	
	/*************
	 * Create Product (POST /api/products):

201 Created: Product created successfully.
400 Bad Request: Invalid product data (e.g., missing required fields).
Get Product by ID (GET /api/products/{id}):

200 OK: Product found.
404 Not Found: Product with the specified ID does not exist.
Update Product (PUT /api/products/{id}):

200 OK: Product updated successfully.
404 Not Found: Product with the specified ID does not exist.
400 Bad Request: Invalid product data for update.
Delete Product (DELETE /api/products/{id}):

204 No Content: Product deleted successfully.
404 Not Found: Product with the specified ID does not exist.
*******/

}
