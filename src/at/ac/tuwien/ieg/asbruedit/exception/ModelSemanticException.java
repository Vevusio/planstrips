package at.ac.tuwien.ieg.asbruedit.exception;

public class ModelSemanticException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4701809154829236807L;

	public ModelSemanticException(Throwable e) {
		super(e);
	}

	public ModelSemanticException() {
		super();
	}

	public ModelSemanticException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ModelSemanticException(String arg0) {
		super(arg0);
	}
}
