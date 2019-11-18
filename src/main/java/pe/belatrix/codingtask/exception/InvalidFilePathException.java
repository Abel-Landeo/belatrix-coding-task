package pe.belatrix.codingtask.exception;

public class InvalidFilePathException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidFilePathException(String errorMessage) {
		super(errorMessage);
	}

}
