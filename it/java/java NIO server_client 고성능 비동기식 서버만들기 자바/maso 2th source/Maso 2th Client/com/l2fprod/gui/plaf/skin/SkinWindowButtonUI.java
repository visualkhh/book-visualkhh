package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:03 $
 */
public class SkinWindowButtonUI extends BasicButtonUI {

    private final static SkinWindowButtonUI buttonUI = new SkinWindowButtonUI();

    protected int dashedRectGapX;
    protected int dashedRectGapY;
    protected int dashedRectGapWidth;
    protected int dashedRectGapHeight;

    protected Color focusColor;
    
    private boolean defaults_initialized = false;
    
    // ********************************
    //          Create PLAF
    // ********************************
    public static ComponentUI createUI(JComponent c){
	return buttonUI;
    }
    
    // ********************************
    //         Create Listeners
    // ********************************
    protected BasicButtonListener createButtonListener(AbstractButton b) {
	return new SkinButtonListener(b); 
    }

    // ********************************
    //            Defaults
    // ********************************
    protected void installDefaults(final AbstractButton b) {
	super.installDefaults(b);
	b.setBorderPainted(false);
	b.setFocusPainted(false);
	b.setOpaque(false);

    }
    
    protected void uninstallDefaults(AbstractButton b) {
	super.uninstallDefaults(b);
	b.setOpaque(true);
	b.setBorderPainted(true);
	b.setFocusPainted(true);
    }

    private static Rectangle iconRect = new Rectangle();

    // ********************************
    //          Paint Methods
    // ********************************

    public void paint(Graphics g, JComponent c) 
    {
        AbstractButton b = (AbstractButton) c;

	iconRect.x = iconRect.y = 0;
	iconRect.width = b.getWidth();
	iconRect.height = b.getHeight();

        // Paint the Icon
        if(b.getIcon() != null) { 
            paintIcon(g,c,iconRect);
        }

    }
    
}

