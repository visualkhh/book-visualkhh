package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.plaf.*;
import javax.swing.*;

/**
 * 
 * Created on 05/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:07 $
 */
public class SkinCheckBoxIcon implements Icon {

    int width;
    int height;

    private Skin skin = SkinLookAndFeel.getSkin();

    public SkinCheckBoxIcon() {
	Dimension dim = skin.getButton().getCheckBoxIconSize();
	width = dim.width;
	height = dim.height;
    }

    public int getIconHeight() {
	return height;
    }

    public int getIconWidth() {
	return width;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
	skin.getButton().getRadioIcon((AbstractButton)c).paintIcon(c, g, x, y);
    }

}
