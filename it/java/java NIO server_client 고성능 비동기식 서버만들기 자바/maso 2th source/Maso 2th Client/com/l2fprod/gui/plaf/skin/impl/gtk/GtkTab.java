package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Image;
import javax.swing.JComponent;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/11/25 22:26:00 $
 */
class GtkTab implements SkinTab {

    DefaultButton selected;
    DefaultButton unselected;

    DefaultButton border;

    public GtkTab(GtkParser parser) throws Exception {
	unselected = GtkUtils.newButton(parser, "GtkNotebook",
				      new String[]{"function", "state"},
				      new String[]{"EXTENSION", "ACTIVE"});
	
	selected = GtkUtils.newButton(parser, "GtkNotebook",
					new String[]{"function", "state"},
					new String[]{"EXTENSION", null});

	border = GtkUtils.newButton(parser, "GtkNotebook",
				    new String[]{"function", "gap_side"},
				    new String[]{"BOX_GAP", "TOP"});
	if (border != null)
	    border.center = null;
    }

    public boolean status() {
	return selected != null;
    }

    public boolean installSkin(JComponent c) {
	return true;
    }

    public boolean paintTab(java.awt.Graphics g, int tabPlacement, boolean isSelected, int x, int y, int w, int h) {
	if (selected != null) {
	    if (isSelected) {
		selected.paint(g, x, y, w, h, ImageUtils.producer);
	    } else {
		unselected.paint(g, x, y, w, h, ImageUtils.producer);
	    }
	}
	return true;
    }
    
    public boolean paintContent(java.awt.Graphics g, int tabPlacement, int selectedIndex,
				int x, int y, int w, int h) {
	if (border != null) {
	    border.paint(g, x, y, w, h, ImageUtils.producer);
	    return true;
	} else {
	    return false;
	}
    }

}
