package com.l2fprod.gui.plaf.skin;

/**
 * Skin Personality.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:01 $
 */
public interface SkinPersonality extends SkinComponent {

    boolean paintDialog(java.awt.Graphics g, java.awt.Component c);
    boolean paintMenu(java.awt.Graphics g, javax.swing.JMenu c);
    boolean paintMenuItem(java.awt.Graphics g, javax.swing.JMenuItem c);
    boolean paintBackground(java.awt.Graphics g, java.awt.Component c);

    boolean paintComboBox(java.awt.Graphics g,
			  javax.swing.JComboBox c,
			  java.awt.Rectangle bounds, boolean hasFocus);
    java.awt.Dimension getComboBoxPreferredSize(javax.swing.JComboBox c);

    javax.swing.ListCellRenderer createListCellRenderer();
    javax.swing.table.TableCellRenderer createTableHeaderRenderer();

}
