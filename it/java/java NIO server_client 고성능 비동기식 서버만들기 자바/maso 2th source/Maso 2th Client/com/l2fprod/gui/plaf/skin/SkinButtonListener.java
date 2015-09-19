package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.event.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:58 $
 */
public class SkinButtonListener extends BasicButtonListener {
    public SkinButtonListener(AbstractButton b ) {
	super(b);
    }

    /*    
    // Here for rollover purposes
    public void mouseEntered(MouseEvent e) {
	AbstractButton button = (AbstractButton)e.getSource();
	button.getModel().setRollover(true);
    }
    
    // Here for rollover purposes
    public void mouseExited(MouseEvent e) {
	AbstractButton button = (AbstractButton)e.getSource();
	button.getModel().setRollover(false);
    }
    */
}


