package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class SkinPopupMenuUI extends BasicPopupMenuUI {

    public static ComponentUI createUI(JComponent c) {
	return new SkinPopupMenuUI();
    }
    
    private Skin skin = SkinLookAndFeel.getSkin();

    public void installDefaults() {
	super.installDefaults();
	skin.getPersonality().installSkin(popupMenu);
	popupMenu.setOpaque(false);
    }

    protected void uninstallDefaults() {
	super.uninstallDefaults();
	popupMenu.setOpaque(true);
    }

    public void paint(Graphics g, JComponent c) {
	skin.getPersonality().paintDialog(g, c);
	super.paint(g, c);
    }

}
