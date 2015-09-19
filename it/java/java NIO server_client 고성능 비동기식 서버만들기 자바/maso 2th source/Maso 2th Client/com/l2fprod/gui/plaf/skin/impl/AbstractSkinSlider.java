package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkinSlider implements SkinSlider {
  
    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public java.awt.Dimension getPreferredSize(javax.swing.JSlider slider) {
	return slider.getPreferredSize();
    }

    public boolean paintTrack(java.awt.Graphics g, javax.swing.JSlider slider, java.awt.Rectangle trackBounds) {
	return false;
    }

    public boolean paintThumb(java.awt.Graphics g, javax.swing.JSlider slider, java.awt.Rectangle thumbBounds) {
	return false;
    }

}
