package com.senior.project.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class StudentExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
		Error erros = new Error("server error", ex.getLocalizedMessage());
		return new ResponseEntity<Object>(erros, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(StudentNotFound.class)
	public final ResponseEntity<Object> handleStudentNotException(StudentNotFound ex, WebRequest request){
		Error erros = new Error("Student not found", ex.getLocalizedMessage());

		return new ResponseEntity<Object>(erros, HttpStatus.NOT_FOUND);
	}



	
	
     
}
