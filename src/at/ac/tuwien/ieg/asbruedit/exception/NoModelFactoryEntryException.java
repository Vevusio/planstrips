package at.ac.tuwien.ieg.asbruedit.exception;

public class NoModelFactoryEntryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 354258590830069644L;
	private String expected;
	
	public NoModelFactoryEntryException(Class<?> expected) {
		this(expected.toString());
	}
	
	public NoModelFactoryEntryException(String expected) {
		this.expected = expected;
	}

	@Override
	public String getMessage() {
		return "No factory entry for element: " + expected;
	}
}
