package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class SkinMenuUI extends BasicMenuUI {

    public static ComponentUI createUI(JComponent c) {
	return new SkinMenuUI();
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
	skin.getPersonality().paintMenu(g, (JMenu)c);
	super.paint(g, c);
    }

}
