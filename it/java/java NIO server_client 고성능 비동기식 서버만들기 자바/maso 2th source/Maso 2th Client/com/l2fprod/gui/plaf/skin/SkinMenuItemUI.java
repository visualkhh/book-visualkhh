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
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:05 $
 */
public class SkinMenuItemUI extends BasicMenuItemUI {

    public static ComponentUI createUI(JComponent c) {
        return new SkinMenuItemUI();
    }
    
    private Skin skin = SkinLookAndFeel.getSkin();

    protected void installDefaults() {
	super.installDefaults();
	menuItem.setOpaque(false);
    }

    protected void uninstallDefaults() {
	super.uninstallDefaults();
	menuItem.setOpaque(true);
    }

    public void paint(Graphics g, JComponent c) {
	skin.getPersonality().paintMenuItem(g, (JMenuItem)c);
	super.paint(g, c);
    }

}
