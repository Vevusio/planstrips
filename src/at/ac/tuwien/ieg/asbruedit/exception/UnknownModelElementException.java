package at.ac.tuwien.ieg.asbruedit.exception;

public class UnknownModelElementException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 354258590830069644L;
	private Object modelElement;
	
	public UnknownModelElementException(Object modelElement) {
		this.modelElement = modelElement;
	}

	@Override
	public String getMessage() {
		return "Unknown model element " + modelElement;
	}
}
