package com.l2fprod.gui.plaf.skin;

/**
 * Skin Button.
 * <br>
 * Include support for buttons, toggle buttons and radio.
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:58 $
 */
public interface SkinButton extends SkinComponent {

    java.awt.Dimension getCheckBoxIconSize();
    javax.swing.Icon getRadioIcon(javax.swing.AbstractButton b);
    boolean paintButton(java.awt.Graphics g, javax.swing.AbstractButton b);

}
