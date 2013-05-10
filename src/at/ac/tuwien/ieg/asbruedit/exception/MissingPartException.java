package at.ac.tuwien.ieg.asbruedit.exception;

public class MissingPartException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 354258590830069644L;
	private Object modelElement;
	
	public MissingPartException(Object modelElement) {
		this.modelElement = modelElement;
	}

	@Override
	public String getMessage() {
		return "No edit part found for model element " + modelElement;
	}
}
