package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkinScrollbar implements SwingConstants, SkinScrollbar {
  
    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public java.awt.Dimension getPreferredSize(javax.swing.JScrollBar scrollbar) {
	return scrollbar.getPreferredSize();
    }

    public java.awt.Dimension getArrowPreferredSize(int direction) {
	return null;
    }

    public boolean paintArrow(Graphics g, AbstractButton b, int direction) {
	return false;
    }

    public boolean paintTrack(Graphics g, JScrollBar scrollbar, Rectangle trackBounds) {
	return false;
    }

    public boolean paintThumb(Graphics g, JScrollBar scrollbar, Rectangle thumbBounds) {
	return false;
    }

}
