package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:59 $
 */
public class SkinInternalFrameUI extends BasicInternalFrameUI {

    private Skin skin = SkinLookAndFeel.getSkin();

    public static ComponentUI createUI(JComponent b)    {
        return new SkinInternalFrameUI((JInternalFrame)b);
    }

    public SkinInternalFrameUI(JInternalFrame b) {
	super(b);
    }

    public void installUI(JComponent c) {
	super.installUI(c);
	skin.getFrame().installSkin(c);
    }

    protected JComponent createNorthPane(JInternalFrame w) {
	return new SkinTitlePane(w);
    }
    
}
