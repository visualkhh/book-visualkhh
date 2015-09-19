package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import javax.swing.*;
import com.l2fprod.util.ImageUtils;

/**
 * Stretch/Tile Button.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/10/31 18:18:51 $
 */
public class DefaultButton implements Icon {
    public Image topleft;
    public Image topright;
    public Image bottomleft;
    public Image bottomright;	
    public Image top,right,bottom,left,center;
    
    int topHeight, bottomHeight, leftWidth, rightWidth;
    int imageWidth, imageHeight;

    boolean tile;

    public DefaultButton(Image bitmap, int imageWidth, int imageHeight,
			 int topHeight, int rightWidth, int bottomHeight, int leftWidth) {
	this(bitmap, imageWidth, imageHeight,
	     topHeight, rightWidth, bottomHeight, leftWidth, false);
    }

    public DefaultButton(Image bitmap, int imageWidth, int imageHeight,
			 int topHeight, int rightWidth, int bottomHeight, int leftWidth, boolean tile) {
	this.topHeight = topHeight;
	this.rightWidth = rightWidth;
	this.bottomHeight = bottomHeight;
	this.leftWidth = leftWidth;
	this.imageWidth = imageWidth;
	this.imageHeight = imageHeight;
	this.tile = tile;

	// corners
	topleft = ImageUtils.grab(bitmap, 0, 0,
				leftWidth,
				topHeight);
	    
	topright = ImageUtils.grab(bitmap,
				 imageWidth - rightWidth, 0,
				 rightWidth,
				 topHeight);
	bottomleft = ImageUtils.grab(bitmap,
				   0,
				   imageHeight - bottomHeight,
				   leftWidth,
				   bottomHeight);
	bottomright = ImageUtils.grab(bitmap,
				    imageWidth - rightWidth,
				    imageHeight - bottomHeight,
				    rightWidth,
				    bottomHeight);
	// borders
	top = ImageUtils.grab(bitmap,
			    leftWidth, 0,
			    imageWidth - leftWidth - rightWidth,
			    topHeight);
	if (rightWidth > 0)
	    right = ImageUtils.grab(bitmap,
				  imageWidth - rightWidth, topHeight,
				  rightWidth,
				  imageHeight - topHeight - bottomHeight);
	if (bottomHeight > 0)
	    bottom = ImageUtils.grab(bitmap,
				   leftWidth, imageHeight - bottomHeight,
				   imageWidth - leftWidth - rightWidth,
				   bottomHeight);
	if (leftWidth > 0)
	    left = ImageUtils.grab(bitmap,
				 0,
				 topHeight,
				 leftWidth,
				 imageHeight - topHeight - bottomHeight);
	// center
	center = ImageUtils.grab(bitmap,
			       leftWidth,
			       topHeight,
			       imageWidth - leftWidth - rightWidth,
			       imageHeight - topHeight - bottomHeight);
    }
    
    public Dimension getMinimumSize() {
	return new Dimension(imageWidth, imageHeight);
    }

    public int getWidth() {
	return imageWidth;
    }

    public int getIconWidth() {
	return getWidth();
    }

    public int getHeight() {
	return imageHeight;
    }

    public int getIconHeight() {
	return getHeight();
    }

    public String toString() {
	return "DefaultButton(" + getWidth() + "x" + getHeight() + ")";
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
	paint(g, x, y, c);	
    }

    public void paint(Graphics g, Component b) {
	paint(g, 0, 0, b);
    }

    public void paint(Graphics g, int x, int y, Component b) {
	paint(g, x, y, ((JComponent)b).getWidth(), ((JComponent)b).getHeight(), b);
    }

    public void paint(Graphics g, int x, int y, int width, int height, Component b) {
	
	// draw corners
	if (topleft != null)
	    g.drawImage(topleft, x, y, b);
	if (topright != null)
	    g.drawImage(topright, x + width - rightWidth, y, b);
	if (bottomleft != null)
	    g.drawImage(bottomleft, x, y + height - bottomHeight, b);
	if (bottomright != null)
	    g.drawImage(bottomright, x + width - rightWidth, y + height - bottomHeight, b);
	
	// PENDING(fred): borders and center should be drawn as tiles!!!
	// borders
	// center
	if (top != null)
	    g.drawImage(top, x + leftWidth, y, width -  leftWidth - rightWidth, topHeight, b);

	if (right != null)
	    g.drawImage(right, x + width - rightWidth, y + topHeight,
			rightWidth, height - topHeight - bottomHeight, b);

	if (bottom != null)
	    g.drawImage(bottom, x + leftWidth, y + height - bottomHeight,
			width - leftWidth - rightWidth, bottomHeight, b);

	if (left != null)
	    g.drawImage(left, x, y + topHeight,
			leftWidth, height - topHeight - bottomHeight, b);

	if (center != null)
	    g.drawImage(center, x + leftWidth, y + topHeight,
			width - leftWidth - rightWidth,
			height - topHeight - bottomHeight, b);
	
	/*if (GtkUtils.DEBUG) {
	    g.setColor(Color.black);
	    g.drawLine(x + leftWidth, y, x + leftWidth, y + height);
	    g.drawLine(x + width - rightWidth, y, x + width -  rightWidth, y + height);
	    g.drawLine(x, y + topHeight, x + width, y + topHeight);
	    g.drawLine(x, y + height - bottomHeight, x + width, y + height - bottomHeight);
	    }*/

    }
    
}
