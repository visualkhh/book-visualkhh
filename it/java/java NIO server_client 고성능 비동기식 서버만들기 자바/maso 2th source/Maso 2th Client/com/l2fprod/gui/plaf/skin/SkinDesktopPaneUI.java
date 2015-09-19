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
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:59 $
 */
public class SkinDesktopPaneUI extends BasicDesktopPaneUI {

    private Skin skin = SkinLookAndFeel.getSkin();

    public static ComponentUI createUI(JComponent c){
	return new SkinDesktopPaneUI();
    }

    public void paint(Graphics g, JComponent c) {
	if (c.getClientProperty("JDesktop.backgroundEnabled") != null)
	    skin.getPersonality().paintBackground(g, c);
    }

}    
