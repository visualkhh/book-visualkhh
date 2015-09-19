package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkinButton implements SkinButton {

    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public Dimension getCheckBoxIconSize() {
	return null;
    }

    public Icon getRadioIcon(AbstractButton b) {
	return null;
    }

    public boolean paintButton(Graphics g, AbstractButton b) {
	return false;
    }

}
