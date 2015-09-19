package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:05 $
 */
public class SkinListUI extends BasicListUI {

    public static ComponentUI createUI(JComponent list) {
	return new SkinListUI();
    }

    protected void installDefaults() {
	super.installDefaults();
	list.setCellRenderer(SkinLookAndFeel.getSkin().getPersonality().createListCellRenderer());
    }

}
