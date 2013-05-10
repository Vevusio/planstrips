package at.ac.tuwien.ieg.asbruedit.editor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import at.ac.tuwien.ieg.asbruedit.exception.MissingPartException;

public abstract class ReflectivePartFactory {
	protected static final String PART_MODEL_SETTER = "setModel";

	public Object createPart(Object context, Object model) {
		Class<?> partClass = null;
		try {
			//get the class of the part corresponding to the model element
			partClass = inferEditPartClass(context, model);
		} catch (ClassNotFoundException e) {
			throw new MissingPartException(model);
		}
		Object part = null;
		try {
			//instantiate the part
			part = partClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(partClass.toString(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		//obtain the setter for the parts model element and use it to set the model
		Method editPartModelSetter;
		try {
			editPartModelSetter = partClass.getMethod(PART_MODEL_SETTER, Object.class);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		//temporarily disable accessibility checking
		editPartModelSetter.setAccessible(true);
		try {
			editPartModelSetter.invoke(part, model);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(editPartModelSetter.toGenericString(), e);
		}
		editPartModelSetter.setAccessible(false);
		//serve up the part with the corresponding model being set
		return part;
	}
	
	/**
	 * @return The suffix that is appended to a model class name to create the part class name
	 */
	protected abstract String getPartSuffix();
	
	/**
	 * @return The package containing the part classes
	 */
	protected abstract String getPartPackage();
	
	/**
	 * @return The name of the method used to set the model, {@link #PART_MODEL_SETTER} by default
	 */
	protected String getModelSetterMethodName() {
		return PART_MODEL_SETTER;
	}
	
	/**
	 * Based on model class and package check if a 
	 * corresponding part for the model can be found.
	 * @param context
	 * @param model
	 * @return Class corresponding to the models part 
	 * @throws ClassNotFoundException 
	 */
	protected Class<?> inferEditPartClass(Object context, Object model) throws ClassNotFoundException {
		String editPartsPackageName = getPartPackage();
		//figure out the qualified class of the edit part of this model 
		String editPartSimpleClassName = model.getClass().getSimpleName() + getPartSuffix();
		String editPartQualifiedClassName = editPartsPackageName + "." + editPartSimpleClassName;
		return Class.forName(editPartQualifiedClassName);
	}
}
