package com.l2fprod.gui.plaf.skin.impl;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

import com.l2fprod.gui.plaf.skin.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkinPersonality implements SkinPersonality {

    public boolean status() {
	return false;
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public boolean paintDialog(Graphics g, Component c) {
	return false;
    }

    public boolean paintMenu(Graphics g, JMenu c) {
	return false;
    }

    public boolean paintMenuItem(Graphics g, JMenuItem c) {
	return false;
    }
 
    public boolean paintBackground(Graphics g, Component c) {
	return false;
    }

    public boolean paintComboBox(Graphics g, JComboBox c, Rectangle bounds, boolean hasFocus) {
	return false;
    }

    public java.awt.Dimension getComboBoxPreferredSize(javax.swing.JComboBox c) {
	return null;
    }

    public TableCellRenderer createTableHeaderRenderer() {
	return null;
    }

    public ListCellRenderer createListCellRenderer() {
	return null;
    }

}
