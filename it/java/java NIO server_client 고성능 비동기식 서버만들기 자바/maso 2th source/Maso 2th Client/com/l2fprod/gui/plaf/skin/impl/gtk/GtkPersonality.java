package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Color;
import java.awt.Image;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.3 $, $Date: 2000/11/25 22:26:00 $
 */
class GtkPersonality implements SkinPersonality {

    static int MINIMAL_SIZE = 50;

    Image dialog;

    DefaultButton menu;    
    //    Border menuBorder;
    DefaultButton menuitemSelected;

    DefaultButton itemSelected, itemUnselected;

    DefaultButton comboBox;

    public GtkPersonality(GtkParser parser) throws Exception {
	GtkStyle windowStyle = parser.getClass("GtkWindow").getStyle();
	dialog = windowStyle.getEngine().findImage(new String[]{"function"},
						    new String[]{"FLAT_BOX"}).getImage(parser.getDirectory());
	if (dialog != null) {
	    int width = dialog.getWidth(ImageUtils.producer);
	    int height = dialog.getHeight(ImageUtils.producer);
	    int factor = Math.max(MINIMAL_SIZE/height, MINIMAL_SIZE/width);
	    if (factor > 1)
		dialog = ImageUtils.buildTile(dialog, factor);
	}

	menu = GtkUtils.newButton(parser, "GtkMenu",
				  new String[]{"function"},
				  new String[]{"BOX"});

	/*
	DefaultButton menuBorderImage = GtkUtils.newButton(parser, "GtkMenu",
							   new String[]{"function"},
							   new String[]{"BOX"}, true);
	menuBorder = new SkinBorder(new ImageIcon(menuBorderImage.top),
				    new ImageIcon(menuBorderImage.bottom),
				    new ImageIcon(menuBorderImage.left),
				    new ImageIcon(menuBorderImage.right),
				    new ImageIcon(menuBorderImage.topleft),
				    new ImageIcon(menuBorderImage.topright),
				    new ImageIcon(menuBorderImage.bottomleft),
				    new ImageIcon(menuBorderImage.bottomright));
	*/

	menuitemSelected = GtkUtils.newButton(parser, "GtkMenuItem",
					  new String[]{"function"},
					  new String[]{"BOX"});

	itemUnselected = GtkUtils.newButton(parser, "GtkListItem",
					  new String[]{"function", "state"},
					  new String[]{"FLAT_BOX", "INSENSITIVE"});

	itemSelected = GtkUtils.newButton(parser, "GtkListItem",
					  new String[]{"function", "state"},
					  new String[]{"FLAT_BOX", null});

	comboBox = GtkUtils.newButton(parser, "GtkOptionMenu",
				      new String[]{"function"},
				      new String[]{"BOX"});
    }

    public boolean status() {
	return true;
    }

    public boolean installSkin(JComponent c) {
	if (dialog != null)
	    c.setOpaque(false);
	else
	    c.setOpaque(true);

	/*
	if (c instanceof JPopupMenu) {
	    c.setBorder(menuBorder);
	}
	*/
	return true;
    }

    public boolean paintDialog(java.awt.Graphics g, java.awt.Component c) {
	if (dialog != null) {
	    ImageUtils.paintTile(c, g, dialog);
	    return true;
	} else {
	    Color holdC = g.getColor();
	    g.setColor(c.getBackground());
	    g.fillRect(0, 0, ((JComponent)c).getWidth(), ((JComponent)c).getHeight());
	    g.setColor(holdC);
	    return true;
	}
    }

    public boolean paintMenu(java.awt.Graphics g, javax.swing.JMenu c) {
	return paintMenuItem(g, c); //Dialog(g, c);
    }

    public boolean paintMenuItem(java.awt.Graphics g, javax.swing.JMenuItem c) {
	if (menuitemSelected != null) {
	    if (c.isArmed() || (c instanceof JMenu && c.getModel().isSelected())) {	    
		menuitemSelected.paint(g, c);
	    } else {
		paintDialog(g, c);
	    }
	    return true;
	} else
	    return false;
    }

    public boolean paintComboBox(java.awt.Graphics g,
				 javax.swing.JComboBox c,
				 java.awt.Rectangle bounds, boolean hasFocus) {
	if (comboBox != null) {
	    comboBox.paint(g, c);
	    return true;
	}
	return false;
    }

    public java.awt.Dimension getComboBoxPreferredSize(javax.swing.JComboBox c) {
	if (comboBox != null)
	    return comboBox.getMinimumSize();
	else
	    return null;
    }

    public boolean paintBackground(java.awt.Graphics g, java.awt.Component c) {
	return false;
    }

    public TableCellRenderer createTableHeaderRenderer() {
	return new GtkTableHeaderRenderer();
    }

    class GtkTableHeaderRenderer extends DefaultTableCellRenderer {
	boolean isSelected;
	boolean hasFocus;
	public GtkTableHeaderRenderer() {
	    setOpaque(false);
	}
	public Component getTableCellRendererComponent(JTable table, Object value,
						       boolean isSelected, boolean hasFocus, int row, int column) {
	    if (table != null) {
		JTableHeader header = table.getTableHeader();
		if (header != null) {
		    setForeground(header.getForeground());
		    setBackground(header.getBackground());
		    setFont(header.getFont());
		}
	    }
	    
	    this.isSelected = isSelected;
	    this.hasFocus = hasFocus;
	    setText((value == null) ? "" : value.toString());
	    return this;
	}
	protected void paintComponent(Graphics g) {
	    if (isSelected || hasFocus)
		itemSelected.paint(g, this);
	    else
		itemUnselected.paint(g, this);
	    super.paintComponent(g);
	}
    }

    public ListCellRenderer createListCellRenderer() {
	return new GtkListCellRenderer();
    }

    class GtkListCellRenderer extends DefaultListCellRenderer {
	boolean isSelected;
	boolean cellHasFocus;
	public GtkListCellRenderer() {
	    setOpaque(false);
	}
	public Component getListCellRendererComponent(JList list,
						      Object value,
						      int index,
						      boolean isSelected,
						      boolean cellHasFocus) {
	    this.isSelected = isSelected;
	    this.cellHasFocus = cellHasFocus;
	    return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
	protected void paintComponent(Graphics g) {
	    if (isSelected)
		itemSelected.paint(g, this);
	    //	    else if (cellHasFocus)
	    //		itemUnselected.paint(g, this);
	    super.paintComponent(g);
	}
    }

}

