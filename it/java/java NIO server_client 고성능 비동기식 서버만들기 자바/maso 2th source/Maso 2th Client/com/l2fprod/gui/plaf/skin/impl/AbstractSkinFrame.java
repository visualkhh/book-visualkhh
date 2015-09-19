package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkinFrame implements SkinFrame {

    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public SkinWindowButton[] getWindowButtons(int align) {
	return null;
    }

    public Dimension getTopPreferredSize() {
	return null;
    }

    public boolean paintTop(Graphics g, Component c, boolean isSelected, String title) {
	return false;
    }

}

