package at.ac.tuwien.ieg.asbruedit.editor;

import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class AsbruXMLEditorInput implements IEditorInput {
	private File asbruXmlFile;
	
	public AsbruXMLEditorInput(File asbruXmlFile) {
		this.asbruXmlFile = asbruXmlFile;
	}
	
	public File getAsbruXmlFile() {
		return asbruXmlFile;
	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return asbruXmlFile.getName();
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return asbruXmlFile.getName();
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AsbruXMLEditorInput) {
			return asbruXmlFile.equals(((AsbruXMLEditorInput)obj).asbruXmlFile);
		}
		return false;
	}
}
