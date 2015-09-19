package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import com.l2fprod.util.ImageUtils;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class SkinUtils {

    public final static int TRANSPARENT_RED = 255;
    public final static int TRANSPARENT_GREEN = 0;
    public final static int TRANSPARENT_BLUE = 255;

    public final static int TRANSPARENT_PIXEL = 8388608;

    public static Component bitmapCreator = new javax.swing.JLabel();
    
    public static Image grab(Image image, int x, int y, int width, int height) {
	if (width * height < 0)
	    return null;

	int[] pixels = new int[width * height];
	PixelGrabber grabber = new PixelGrabber(image, x, y, width, height, pixels, 0, width);
	try {
	    grabber.grabPixels();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	int pixel, alpha, red, green, blue;
	for (int j = 0; j < height; j++) {
	    for (int i = 0; i < width; i++) {
		pixel = pixels[j * width + i];
		alpha = (pixel >> 24) & 0xff;
		red   = (pixel >> 16) & 0xff;
		green = (pixel >>  8) & 0xff;
		blue  = (pixel      ) & 0xff;
		// PENDING(fred): transparent only if Trans = 1 in Buttons/Taskbars
		if ((red == TRANSPARENT_RED) &&
		    (green == TRANSPARENT_GREEN) &&
		    (blue == TRANSPARENT_BLUE)) {
		    pixels[j * width + i] = TRANSPARENT_PIXEL;
		}
	    }
	} 
	return bitmapCreator.createImage(new MemoryImageSource(width, height, pixels, 0, width));
    }

    public static Image loadImage(String filename) throws Exception {
	return loadImage(toURL(new File(filename)));
    }

    public static Image loadImage(URL url) throws Exception {
	if (hasJimi) {
	    return JimiUtils.loadImage(url);
	} else {
	    return new ImageIcon(url).getImage();
	}
    }

    public static URL toURL(File f) throws java.net.MalformedURLException {
	String path = f.getAbsolutePath();
	if (File.separatorChar != '/') {
	    path = path.replace(File.separatorChar, '/');
	}
	if (!path.startsWith("/")) {
	    path = "/" + path;
	}
	if (!path.endsWith("/") && f.isDirectory()) {
	    path = path + "/";
	}
	return new URL("file", "", path);
    }

    public static Component findComponentAt(Container container, int x, int y) {
	if (!container.contains(x, y)) {
	    return null;
	}
        int ncomponents = container.getComponentCount();
        Component component[] = container.getComponents();
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = component[i];
            if (comp != null) {
		Point p = comp.getLocation();
		if (comp instanceof Container) {
		    comp = findComponentAt((Container)comp, x - p.x, y - p.y);
		} else {
		    comp = comp.locate(x - p.x, y - p.y);
		}
                if (comp != null && comp.isVisible()) {
                    return comp;
                }
            }
        }
	return container;
    }

    static boolean hasJimi = true;

    static {
        try {
            Class.forName("com.sun.jimi.core.Jimi");
        } catch (ClassNotFoundException e) {
            hasJimi = false;
        }

	if (!hasJimi) {
	    System.err.println("warning: Jimi library not found, only native image format will be supported");
	}
    }

}
