package com.l2fprod.util;

import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import java.io.*;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.Icon;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:50 $
 */
public class ImageUtils implements SwingConstants {
  
    public static final Component producer = new Label();
  
    public static Image rotateImage(Image anImage) {
	int w = anImage.getWidth(null);
	int h = anImage.getHeight(null);
	int[] pixels = new int[w * h];
    
	PixelGrabber pixel = new PixelGrabber(anImage, 0, 0, w, h, pixels, 0, w);
	try {
	    pixel.grabPixels();
	} catch(Exception e) {
	    e.printStackTrace();
	}
    
	int[] rot = new int[h * w];
	int pos = 0;
    
	for (int i = w; i > 0; i--) {
	    for (int j = 0; j < h; j++) {
		rot[pos] = pixels[i + (w * j) - 1];
		pos++;
	    }
	}    
    
	return convertBytesToImage(producer, rot, h, w);
    }
  
    public static Image convertBytesToImage(Component c, int[] pixels, int w, int h) {
	return c.createImage(new MemoryImageSource(w, h, pixels, 0, w));
    }

    public static void fillGradient(Graphics gr, Color start, Color end,
				    int x, int y, int w, int h, int n, int direction) {
	int space;

	if (direction == VERTICAL)
	    space = (int)(w/n);
	else
	    space = (int)(h/n);

	int r = start.getRed();
	int g = start.getGreen();
	int b = start.getBlue();
	int r2 = end.getRed();
	int g2 = end.getGreen();
	int b2 = end.getBlue();
    
	int rp = (int)((r2 - r)/n);
	int gp = (int)((g2 - g)/n);
	int bp = (int)((b2 - b)/n);
    
	for (int i = 0; i < n; i++) {
	    gr.setColor(new Color(r,g,b));
	    r = r + rp;
	    g = g + gp;
	    b = b + bp;
	    if (direction == VERTICAL)
		gr.fillRect(x+space*i,y,space,h);
	    else
		gr.fillRect(x,y+space*i,w,space);
	}
	if (direction == VERTICAL)
	    gr.fillRect(x+space*n,y,w - x+space*n,h);
	else
	    gr.fillRect(x,y+space*n,w,h-y+space*n);
    }

    public static void paintBackground(Component c, Graphics g, Icon icon) {
	if (icon == null)
	    return;
	int tw = icon.getIconWidth();
	int th = icon.getIconHeight();
	Dimension d = c.getSize();
	int nw = (d.width / tw) + 1;
	int nh = (d.height / th) + 1;
	for (int i = 0; i < nw; i++) {
	    for (int j = 0; j < nh; j++) {
		icon.paintIcon(c, g, i*tw, j*th);
	    }
	}
    }

    public static void paintTile(Component component, Graphics g, Image image) {
	paintTile(component, g, image, 0, 0, ((JComponent)component).getWidth(), ((JComponent)component).getHeight(), true);
    }
    
    public static void paintTile(Component component, Graphics g, Image image, boolean alignWithParent) {
	paintTile(component, g, image, 0, 0, ((JComponent)component).getWidth(), ((JComponent)component).getHeight(), alignWithParent);
    }

    public static void paintTile(Component component, Graphics g, Image image, int x, int y, int width, int height) {
	paintTile(component, g, image, x, y, width, height, true);
    }

    public static void paintTile(Component component, Graphics g, Image image, int x, int y, int width, int height, boolean alignWithParent) {
	/**
	 the next code includes a fix from Yiannis Paschalidis <aliasx@geocities.com>.
	 It fixes a problem with nested panels.
	 the background bitmap didn't tile correctly as each panel starts tiling from 0,0 in the bitmap.
	 Thanks for the fix.
	*/

	if (image == null)
	    return;
   
	java.awt.Shape shape = g.getClip();
	g.setClip(x, y, width, height);
   
	final int dx = image.getWidth(component);
	final int dy = image.getHeight(component);
   
	//work out the offset from (0,0) in the root frame.
	int xoff=0;
	int yoff=0;

	if (alignWithParent) {
	    Component parent = component.getParent();
	    xoff=component.getLocation().x;
	    yoff=component.getLocation().y;
	    
	    while (parent!=null && (parent instanceof javax.swing.JInternalFrame==false)) {
		//don't want the screen coords of the topmost container...
		if (parent.getParent()!=null) {
		    xoff+=parent.getLocation().x;
		    yoff+=parent.getLocation().y;
		}
		
		parent=parent.getParent();
	    }
	    
	    x-=(xoff%dx);
	    y-=(yoff%dy);
	}
	
	int maxX = x + width + dx;
	int maxY = y + height + dy;

	for ( ; x<=maxX ; x+=dx) {
	    for (int j=y ; j<=maxY ; j+=dy)
		g.drawImage(image, x, j, component);
	}     

	g.setClip(shape);
    }

    private Color decodeColor(String s) {
	int val = 0;
	try {
	    if (s.startsWith("0x")) {
		val = Integer.parseInt(s.substring(2), 16);
	    } else if (s.startsWith("#")) {
		val = Integer.parseInt(s.substring(1), 16);
	    } else if (s.startsWith("0") && s.length() > 1) {
		val = Integer.parseInt(s.substring(1), 8);
	    } else {
		val = Integer.parseInt(s, 10);
	    }
	    return new Color(val);
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    public final static int TRANSPARENT_RED = 255;
    public final static int TRANSPARENT_GREEN = 0;
    public final static int TRANSPARENT_BLUE = 255;

    public final static int TRANSPARENT_PIXEL = 8388608;
    
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

	Image newImage = producer.createImage(new MemoryImageSource(width, height, pixels, 0, width));
	resolve(newImage);
	return newImage;
    }

    public static Image buildTile(Image image, int factor) {
	int width = image.getWidth(producer);
	int height = image.getHeight(producer);

	int[] pixels = new int[width * height];
	PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
	try {
	    grabber.grabPixels();
	} catch (Exception e) {
	    e.printStackTrace();
	}

	// do an horizontal tiling
	int[] zoomed = new int[pixels.length * factor];
	for (int i = 0; i < height; i++)
	    for (int j = 0; j < factor; j++)
		System.arraycopy(pixels, width * i, zoomed, (width * factor * i) + width * j, width);

	pixels = zoomed;

	// do a vertical duplication
	int[] zoomed2 = new int[pixels.length * factor];
	for (int i = 0; i < factor; i++)
	    System.arraycopy(pixels, 0, zoomed2, i * pixels.length, pixels.length);

	return producer.createImage(new MemoryImageSource(width*factor,
							  height*factor,
							  zoomed2, 0, width*factor));
    }

    private static void resolve(Image image) {
	if (image != null) {
	    int width = image.getWidth(producer);
	    int height = image.getHeight(producer);
	    
	    int[] pixels = new int[width * height];
	    PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
	    try {
		grabber.grabPixels();
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
    }

}
