package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class SkinMenuBarUI extends BasicMenuBarUI {

    // Shared UI object
    private static PanelUI panelUI;

    public static ComponentUI createUI(JComponent c) {
	return new SkinMenuBarUI();
    }

    private Skin skin = SkinLookAndFeel.getSkin();

    protected void installDefaults() {
	super.installDefaults();
	skin.getPersonality().installSkin(menuBar);
    }

    protected void uninstallDefaults() {
	super.uninstallDefaults();
	menuBar.setOpaque(true);
    }

    public void paint(Graphics g, JComponent c) {
	skin.getPersonality().paintDialog(g, c);
    }

}
