package nl.cge.tran.domein;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = -6051451803764055279L;

	public ApplicationException() {
		super();
	}

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(Throwable cause) {
		super(cause);
	}

}
