package at.ac.tuwien.ieg.asbruedit.exception;

public class InvalidModelException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 354258590830069644L;
	private Object encounteredElement;
	private String expected;
	
	public InvalidModelException(Object encounteredElement, Class<?> expected) {
		this(encounteredElement, expected.toString());
	}
	
	public InvalidModelException(Object encounteredElement, String expected) {
		this.encounteredElement = encounteredElement;
		this.expected = expected;
	}

	@Override
	public String getMessage() {
		return "Invalid model element: " + encounteredElement + ", expected: " + expected;
	}
}
