package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;
import com.l2fprod.gui.plaf.skin.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.3 $, $Date: 2000/11/25 22:26:00 $
 */
class GtkUtils {

    public static boolean DEBUG = false;

    static javax.swing.JLabel bitmapCreator = new javax.swing.JLabel();

    public static DefaultButton newButton(GtkParser parser,
					  String style, String[] keys, String[] values) throws Exception {
	return newButton(parser, style, keys, values, false);
    }

    public static DefaultButton newButton(GtkParser parser,
					  String style, String[] keys, String[] values,
					  boolean useOverlay) throws Exception {
	try {
	DefaultButton button = null;
	GtkStyle gtkstyle = parser.getClass(style)!=null?parser.getClass(style).getStyle():parser.getStyle(style);
	if (gtkstyle != null) {
	    GtkImage image = gtkstyle.getEngine().findImage(keys, values);
	    if (image != null) {
		Image bitmap = null;
		GtkBorder border = (GtkBorder)image.getProperty(useOverlay?"overlay_border":"border");
		if (useOverlay && border==null)
		    border = (GtkBorder)image.getProperty("border");

		if (border == null)
		    border = new GtkBorder(0,0,0,0);

		bitmap = image.getImage(parser.getDirectory(),useOverlay?"overlay_file":"file");

		if (useOverlay && bitmap==null)
		    bitmap = image.getImage(parser.getDirectory(),"file");

		button = new DefaultButton(bitmap,
					   bitmap.getWidth(ImageUtils.producer),
					   bitmap.getHeight(ImageUtils.producer),
					   border.top, border.right, border.bottom, border.left);
	    }
	}

	// if the button is still null and style != default
	// try more general style, this can give unpredictable result,
	// keys must be sorted by importance
	if (button == null && (!"default".equals(style))) {
	    button = newButton(parser, "default", keys, values);

	    int length = keys.length;
	    while ((length>0) && (button == null)) {
		length--;
		String[] subkeys = new String[length];
		System.arraycopy(keys, 0, subkeys, 0, length);
		button = newButton(parser, "default", subkeys, values);
	    }
	}

	return button;
	} catch (RuntimeException e) {
	    return null;
	}
    }

}
