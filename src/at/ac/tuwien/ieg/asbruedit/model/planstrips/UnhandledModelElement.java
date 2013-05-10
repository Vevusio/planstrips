package at.ac.tuwien.ieg.asbruedit.model.planstrips;

public class UnhandledModelElement {
	private Object model;
	private String information;

	public UnhandledModelElement(Object model) {
		this.model = model;
		setInformation(model.getClass().getSimpleName());
	}
	
	public void setInformation(String information) {
		this.information = information;
	}
	
	public String getInformation() {
		return information;
	}
	
	public String toString() {
		return information;
	}

	public Object getModel() {
		return model;
	}
}
