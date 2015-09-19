package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class SkinPanelUI extends BasicPanelUI {

    // Shared UI object
    private static PanelUI panelUI;

    public static ComponentUI createUI(JComponent c) {
	if(panelUI == null) {
            panelUI = new SkinPanelUI();
	}
        return panelUI;
    }

    private Skin skin = SkinLookAndFeel.getSkin();

    protected void installDefaults(JPanel p) {
	super.installDefaults(p);
	skin.getPersonality().installSkin(p);
    }

    public void paint(Graphics g, JComponent c) {
	// don't paint a glasspane
	if (c.getParent() instanceof JRootPane) {
	} else {
	    skin.getPersonality().paintDialog(g, c);
	}
    }

}
