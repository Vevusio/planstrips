package at.ac.tuwien.ieg.asbruedit.model;

public interface ModelTransformer<SOURCE, DEST> {
	DEST transform(SOURCE model);
}
