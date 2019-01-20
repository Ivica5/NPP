package io.ivan.NPP.exceptionController;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController{

	@ExceptionHandler()
	public String handleErrors(Exception exception) {
		if(exception.getMessage().equals("wrongInputModal")) {
			return "wrongInputModal";
		}
		else {
			return "baseModal";
		}	
	}
}
