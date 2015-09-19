package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:01 $
 */
public class SkinProgressBarUI extends BasicProgressBarUI {

    public static ComponentUI createUI(JComponent x) {
	return new SkinProgressBarUI();
    }

    private Skin skin = SkinLookAndFeel.getSkin();

    protected void installDefaults() {
	progressBar.setOpaque(true);
	progressBar.setBorderPainted(false);
	progressBar.setMinimumSize(skin.getProgress().getMinimumSize());
    }

    protected void uninstallDefaults() {
	super.uninstallDefaults();
	progressBar.setOpaque(true);
	progressBar.setBorderPainted(true);
    }

    public void paint(Graphics g, JComponent c) {

	skin.getProgress().paintProgress(g, progressBar);
	
	int barRectX = 0;
	int barRectY = 0;
	int barRectWidth = progressBar.getWidth();
	int barRectHeight = progressBar.getHeight();
	Insets b = progressBar.getInsets(); // area for border
	barRectX += b.left;
	barRectY += b.top;
	barRectWidth -= (b.right + barRectX);
	barRectHeight -= (b.bottom + barRectY);
	int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

	// Deal with possible text painting
	if (progressBar.isStringPainted()) {
	    paintString(g, barRectX, barRectY,
			barRectWidth, barRectHeight,
			amountFull, b);
	}
    }
}

