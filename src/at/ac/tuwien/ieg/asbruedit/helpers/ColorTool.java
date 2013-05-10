package at.ac.tuwien.ieg.asbruedit.helpers;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

public class ColorTool {
	/**
	 * Creates a {@link Color} from a string using the format "R,G,B" where
	 * R/G/B is a value ranging from 0-255
	 * 
	 * @param rgbString
	 *            String of the form "[0-255],[0-255],[0-255]"
	 * @return Associated {@link Color}
	 */
	public static Color fromRGBString(String rgbString) {
		String[] rgb = rgbString.split(",");
		return new Color(null, Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
	}

	/**
	 * Mixes two colors together producing a new color. The percent amount of
	 * the mix color is added to the base color. For example mixing with 0.5f
	 * (50%) would result in a 50/50 color mix. 0% Would result in only the base
	 * color and 100% in only the mix color.
	 * 
	 * @param base
	 *            Base color for the mix
	 * @param mix
	 *            Color to mix with the base
	 * @param percent
	 *            How many percent (0-1 ie. 0% - 100%) of the new color is the
	 *            mix color.
	 * @return New color
	 */
	public static Color mixColors(Color base, Color mix, float percent) {
		return new Color(null, (int) (base.getRed() + (mix.getRed() - base.getRed()) * percent), (int) (base.getGreen() + (mix.getGreen() - base.getGreen())
				* percent), (int) (base.getBlue() + (mix.getBlue() - base.getBlue()) * percent));
	}
	
	/**
	 * Creates an image of a box with a border and gradient. Disposes of the colors used to draw the box.
	 * @param size dimensions of the image
	 * @param borderThickness thickness of the border in pixels
	 * @param background background color of the box
	 * @param shading shading color used
	 * @return image of the box
	 */
	public static Image createGradientBox(Point size, int borderThickness, Color background, Color shading) {
		final Color colorC = mixColors(background, shading, 0.35f);
		return createGradientBox(size, borderThickness, background, shading, colorC);
	}
	
	/**
	 * Creates an image of a box with a border and gradient. Disposes of the colors used to draw the box.
	 * @param size dimensions of the image
	 * @param borderThickness thickness of the border in pixels
	 * @param background background color of the box
	 * @param shading shading color used
	 * @param border border color of the box
	 * @return image of the box
	 */
	public static Image createGradientBox(Point size, int borderThickness, Color background, Color shading, Color border) {
		final int width = size.x;
		final int height = size.y;

		final Image result = new Image(Display.getDefault(), width, height);

		final GC gc = new GC(result);
		final Color colorD = mixColors(background,
				shading, 0.45f);
		final Color colorE = mixColors(background,
				shading, 0.80f);
		final Color colorF = mixColors(background,
				shading, 0.70f);
		final Color colorG = mixColors(background,
				ColorConstants.white, 0.45f);
		final Color colorH = mixColors(shading,
				shading, 0.35f);

		try {
			int gradientY1 = borderThickness + (int) (height * 0.15f);
			int gradientY2 = height - borderThickness - 1 - (int) (height * 0.15f);
			
			//fill the entire thing with a background color
			gc.setBackground(colorE);
			gc.fillRectangle(0, 0, width - 1, height - 1);
			//create the top gradient
			gc.setForeground(colorD);
			gc.setBackground(colorE);
			gc.fillGradientRectangle(0, borderThickness, width - 1, gradientY1, true);
			//create the bottom gradient
			gc.setForeground(colorE);
			gc.setBackground(colorH);
			gc.fillGradientRectangle(0, gradientY2, width - 1, height - borderThickness - 1, true);
			
			//create the border
			for (int i = 0; i < borderThickness; i++) {
				// top
				drawLine(border, 0, i, width - 1, i, gc);
				// bottom
				drawLine(border, 0, height - i - 1, width - 1, height - i - 1, gc);
				// left
				drawLine(border, i, 0, i, height - 1, gc);
				// right
				drawLine(border, width - i - 1, 0, width - i - 1, height - 1, gc);
			}

		} finally {
			gc.dispose();

			colorD.dispose();
			colorE.dispose();
			colorF.dispose();
			colorG.dispose();
			colorH.dispose();
			background.dispose();
			shading.dispose();
			border.dispose();
		}

		return result;
	}

	private static void drawLine(Color color, int x1, int y1, int x2, int y2, GC gc) {
		gc.setForeground(color);
		gc.drawLine(x1, y1, x2, y2);
	}
}
