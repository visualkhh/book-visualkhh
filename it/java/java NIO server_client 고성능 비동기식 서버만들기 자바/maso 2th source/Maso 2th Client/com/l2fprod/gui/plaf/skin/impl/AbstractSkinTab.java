package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/10/31 18:20:31 $
 */
public class AbstractSkinTab implements SkinTab {

    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public boolean paintTab(Graphics g, int tabPlacement, boolean isSelected, int x, int y, int w, int h) {
	return false;
    }

    public boolean paintContent(java.awt.Graphics g, int tabPlacement, int selectedIndex,
				int x, int y, int w, int h) {
	return false;
    }

}
