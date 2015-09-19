package com.l2fprod.gui.plaf.skin;

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Enumeration;
import java.awt.event.*;
import java.awt.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:06 $
 */
public class SkinTableHeaderUI extends BasicTableHeaderUI {

    TableCellRenderer renderer;
    int focusedColumn = -1;
    Skin skin = SkinLookAndFeel.getSkin();

    public static ComponentUI createUI(JComponent h) {
        return new SkinTableHeaderUI();
    }

    public SkinTableHeaderUI() {
	super();
	renderer = skin.getPersonality().createTableHeaderRenderer();
    }

    public void installUI(JComponent c) {
	super.installUI(c);

	//if jdk1.3 we can use the default renderer for columns on Jtableheader
	//header.setDefaultRenderer(renderer);
        Enumeration enumeration = header.getColumnModel().getColumns();
        while (enumeration.hasMoreElements()) {
            TableColumn aColumn = (TableColumn)enumeration.nextElement();
	    aColumn.setHeaderRenderer(renderer);
	}
    }

}

