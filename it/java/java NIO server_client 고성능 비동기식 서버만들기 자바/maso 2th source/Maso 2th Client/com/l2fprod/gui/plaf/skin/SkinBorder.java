package com.l2fprod.gui.plaf.skin;

import javax.swing.border.*;
import java.net.URL;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * 
 * Created on 08/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1 $, $Date: 2000/10/31 18:18:51 $
 */
public class SkinBorder implements Border {

    final ImageIcon top, bottom, left, right, topleft, topright, bottomleft, bottomright;
    final Insets insets;

    public static ImageIcon getIcon(URL skinURL, String path) throws Exception {
	if (path != null && path.length() > 0)
	    return new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
	else
	    return new ImageIcon() {
		    public int getIconHeight() { return 0; }
		    public int getIconWidth() { return 0; }
		    public void paintIcon(Component c, Graphics g, int x, int y) {
		    }
		};
    }

    public SkinBorder(ImageIcon top, ImageIcon bottom,
		      ImageIcon left, ImageIcon right,
		      ImageIcon topleft, ImageIcon topright,
		      ImageIcon bottomleft, ImageIcon bottomright) {
	this.top = top;
	this.bottom = bottom;
	this.left = left;
	this.right = right;
	this.topleft = topleft;
	this.topright = topright;
	this.bottomleft = bottomleft;
	this.bottomright = bottomright;

	insets = new Insets(top.getIconHeight(), right.getIconWidth(),
			    bottom.getIconHeight(), left.getIconWidth());
    }

    public Insets getBorderInsets(Component c) {
	return insets;
    }

    public boolean isBorderOpaque() {
	return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

	// top
	topleft.paintIcon(c, g, x, y);
	topright.paintIcon(c, g, x + width - topright.getIconWidth(), y);

	// bottom
	bottomleft.paintIcon(c, g, x, y + height - bottomleft.getIconHeight());
	bottomright.paintIcon(c, g, x + width - bottomright.getIconWidth(),
			      y + height - bottomright.getIconWidth());

	// left
	ImageUtils.paintTile(c, g, left.getImage(),
			     x,
			     y + topleft.getIconHeight(),
			     left.getIconWidth(),
			     height - topleft.getIconHeight() - bottomleft.getIconHeight(), false);

	// right
	ImageUtils.paintTile(c, g, right.getImage(),
			     x + width - right.getIconWidth(),
			     y + topleft.getIconHeight(),
			     right.getIconWidth(),
			     height - topright.getIconHeight() - bottomright.getIconHeight(), false);

	// top
	ImageUtils.paintTile(c, g, top.getImage(),
			     x + topleft.getIconWidth(),
			     y,
			     width - topleft.getIconWidth() - topright.getIconWidth(),
			     top.getIconHeight(), false);

	// bottom
	ImageUtils.paintTile(c, g, bottom.getImage(),
			     x + bottomleft.getIconWidth(),
			     y + height - bottom.getIconHeight(),
			     width - bottomleft.getIconWidth() - bottomright.getIconWidth(),
			     bottom.getIconHeight(), false);
    }

}
