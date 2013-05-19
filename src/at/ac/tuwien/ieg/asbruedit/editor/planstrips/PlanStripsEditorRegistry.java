package at.ac.tuwien.ieg.asbruedit.editor.planstrips;

import java.util.LinkedList;


/**
 * Registry used to perform global changes in visuals in PlanStrips boxes
 * @author Vevusio
 *
 */
public class PlanStripsEditorRegistry {
	private LinkedList<PlanStripsEditor> boxes = new LinkedList<PlanStripsEditor>();
	private static PlanStripsEditorRegistry instance;
	
	public void register(PlanStripsEditor box) {
		boxes.add(box);
	}
	
	public void unregister(PlanStripsEditor box) {
		boxes.remove(box);
	}
	
	public LinkedList<PlanStripsEditor> all() {
		return boxes;
	}
	
	public static PlanStripsEditorRegistry instance() {
		if(instance == null) {
			instance = new PlanStripsEditorRegistry();
		}
		return instance;
	}
}
