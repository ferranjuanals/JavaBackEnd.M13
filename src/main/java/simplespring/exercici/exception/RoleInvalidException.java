package simplespring.exercici.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class RoleInvalidException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public RoleInvalidException(String role) {
		super("Aquest Role no existeix: " + role);
	}
}
