package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.3 $, $Date: 2000/11/25 22:26:00 $
 */
class GtkButton implements SkinButton {

    ImageIcon checkIN, checkOUT;
    ImageIcon optionIN, optionOUT;

    DefaultButton disabledButton, pressedButton, focusButton, normalButton, rolloverButton;
    DefaultButton disabledButtonIN;

    DefaultButton toggleIN, toggleOUT;

    public GtkButton(GtkParser parser) throws Exception {
	normalButton = GtkUtils.newButton(parser, "GtkButton",
					  new String[]{"function", "state", "shadow"},
					  new String[]{"BOX", "NORMAL", "OUT"});

	pressedButton = GtkUtils.newButton(parser, "GtkButton",
					  new String[]{"function", "state", "shadow"},
					  new String[]{"BOX", "ACTIVE", "IN"});

	focusButton = normalButton;
	/*GtkUtils.newButton(parser, "GtkButton",
	  new String[]{"function", "state", "shadow"},
	  new String[]{"BOX", "SELECTED", "IN"});*/

	disabledButton = GtkUtils.newButton(parser, "GtkButton",
					   new String[]{"function", "state", "shadow"},
					   new String[]{"BOX", "INSENSITIVE", "OUT"});

	disabledButtonIN = GtkUtils.newButton(parser, "GtkButton",
					      new String[]{"function", "state", "shadow"},
					      new String[]{"BOX", "INSENSITIVE", "IN"});

	rolloverButton = GtkUtils.newButton(parser, "GtkButton",
					   new String[]{"function", "state"},
					   new String[]{"BOX", "PRELIGHT"});

	// toggle button
	toggleIN = GtkUtils.newButton(parser, "GtkToggleButton",
				      new String[]{"function", "shadow"},
				      new String[]{"BOX", "IN"});

	toggleOUT = GtkUtils.newButton(parser, "GtkToggleButton",
				       new String[]{"function", "shadow"},
				       new String[]{"BOX", "OUT"});

	// the radio button
	GtkStyle radioStyle = parser.getClass("GtkRadioButton").getStyle();
	
	GtkStyle defaultStyle = parser.getStyle("default");

	checkIN = new ImageIcon(defaultStyle.getEngine().findImage(new String[]{"function", "shadow"},
						     new String[]{"CHECK", "IN"}).getImage(parser.getDirectory()));

	checkOUT = new ImageIcon(defaultStyle.getEngine().findImage(new String[]{"function", "shadow"},
								    new String[]{"CHECK", "OUT"}).getImage(parser.getDirectory()));

	optionIN = new ImageIcon(defaultStyle.getEngine().findImage(new String[]{"function", "shadow"},
								    new String[]{"OPTION", "IN"}).getImage(parser.getDirectory()));

	optionOUT = new ImageIcon(defaultStyle.getEngine().findImage(new String[]{"function", "shadow"},
								     new String[]{"OPTION", "OUT"}).getImage(parser.getDirectory()));
    }

    public boolean status() {
	return true;
    }

    public boolean installSkin(JComponent c) {
	return true;
    }

    public Dimension getCheckBoxIconSize() {
	if (checkIN != null)
	    return new Dimension(checkIN.getIconWidth(), checkIN.getIconHeight());
	else
	    return new Dimension(13,13);
    }

    public javax.swing.Icon getRadioIcon(javax.swing.AbstractButton b) {
	ButtonModel model = b.getModel();
	if (b instanceof JRadioButton) {
	    if (model.isSelected() || (model.isPressed() && model.isArmed())) {
		return optionIN;
	    } else
		return optionOUT;
	    /*if (model.isEnabled() == false) {
	      if (model.isSelected()) {
	      return optionOUT;
	      } else {
	      return optionOUT;
	      }
	      } else {
	      return optionIN;
		}*/
	} else if ((b instanceof JCheckBox) || (b instanceof JCheckBoxMenuItem)) {
	    if (model.isSelected() || (model.isPressed() && model.isArmed())) {
		return checkIN;
	    } else if (model.isEnabled() == false) {
		if (model.isSelected()) {
		    return checkOUT;
		} else {
		    return checkOUT;
		}
	    } else {
		return checkOUT;
	    }
	} else {
	    return null;
	}
    }

    public boolean paintButton(java.awt.Graphics g, javax.swing.AbstractButton b) {
	ButtonModel model = b.getModel();
	if (b.isEnabled() == false) {
	    disabledButton.paint(g, b);
	} else {
	    //PENDING(fred): should handle disabledINButton,
	    // when the toggle button is disabled but pressed (armed)
	    if (b instanceof JToggleButton) {
		if ((model.isArmed() && model.isPressed()) || model.isSelected())
		    toggleIN.paint(g, b);
		else if (model.isRollover())
		    focusButton.paint(g, b);
		else
		    toggleOUT.paint(g, b);
	    } else if (b instanceof JButton) {
		if (model.isPressed())
		    pressedButton.paint(g,b);
		else if (!model.isArmed() && !model.isPressed() && b.hasFocus())
		    focusButton.paint(g,b);
		else if (model.isRollover())
		    rolloverButton.paint(g, b);
		else
		    normalButton.paint(g, b);	    
	    }
	}

	return true;
    }

}
