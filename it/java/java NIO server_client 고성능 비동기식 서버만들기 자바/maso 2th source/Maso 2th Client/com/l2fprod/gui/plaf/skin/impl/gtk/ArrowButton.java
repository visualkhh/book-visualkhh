package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.*;
import javax.swing.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;
import com.l2fprod.gui.plaf.skin.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:58 $
 */
class ArrowButton {
    
    DefaultButton normal, focus, pressed;

    public ArrowButton(GtkParser parser, String direction) throws Exception {
	normal = GtkUtils.newButton(parser, "default",
				    new String[]{"function", "state", "arrow_direction"},
				    new String[]{"ARROW", "NORMAL", direction});
	focus = GtkUtils.newButton(parser, "default",
				     new String[]{"function", "state", "arrow_direction"},
				     new String[]{"ARROW", "PRELIGHT", direction});
	pressed = GtkUtils.newButton(parser, "default",
				     new String[]{"function", "shadow", "arrow_direction"},
				     new String[]{"ARROW", "IN", direction});
    }

    public int getWidth() {
	return normal.getWidth();
    }

    public int getHeight() {
	return normal.getHeight();
    }

    public Dimension getPreferredSize() {
	return new java.awt.Dimension(getWidth(), getHeight());
    }

    public void paint(Graphics g, AbstractButton b) {
	ButtonModel model = b.getModel();
	if (b.isEnabled() == false) {
	    normal.paint(g, b);
	} else if (model.isArmed() && model.isPressed()) {
	    pressed.paint(g, b);
	} else if (model.isRollover() && (focus!=null)) {
	    focus.paint(g, b);
	} else {
	    normal.paint(g, b);
	}
    }

}
