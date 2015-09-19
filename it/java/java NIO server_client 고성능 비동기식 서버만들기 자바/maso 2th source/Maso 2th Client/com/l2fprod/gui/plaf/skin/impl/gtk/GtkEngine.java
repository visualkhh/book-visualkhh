package com.l2fprod.gui.plaf.skin.impl.gtk;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:21 $
 */
public class GtkEngine {

    java.util.Vector images = new java.util.Vector();

    public GtkStyle style;

    public GtkEngine() {
    }

    public java.util.Vector getImages() {
	return images;
    }

    public void addImage(GtkImage image) {
	images.addElement(image);
	image.style = style;
    }

    public GtkImage findImage(Object[] keys, Object[] values) {
	GtkImage image = null;

	for (int i = 0, c = images.size(); i < c; i++) {
	    GtkImage currentImage = (GtkImage)images.elementAt(i);
	    int j = 0;

	    while ((j < keys.length) &&
		   ((values[j] == null && currentImage.getProperty(keys[j])==null) ||
		    (values[j] != null) && values[j].equals(currentImage.getProperty(keys[j])))) {
		j++;
	    }
	    if (j == keys.length) {
		image = currentImage;
		break;
	    }
	}

	return image;
    }

}
