package com.l2fprod.gui.plaf.skin;

/**
 * Skin Frame.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:59 $
 */
public interface SkinFrame extends SkinComponent {

    SkinWindowButton[] getWindowButtons(int align);

    java.awt.Dimension getTopPreferredSize();
    boolean paintTop(java.awt.Graphics g, java.awt.Component c, boolean isSelected, String title);

}
