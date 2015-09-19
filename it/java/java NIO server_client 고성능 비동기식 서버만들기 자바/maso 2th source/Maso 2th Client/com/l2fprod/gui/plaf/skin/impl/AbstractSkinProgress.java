package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkinProgress implements SkinProgress {

    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public Dimension getMinimumSize() {
	return null;
    }

    public boolean paintProgress(Graphics g, JProgressBar progress) {
	return false;
    }

}
