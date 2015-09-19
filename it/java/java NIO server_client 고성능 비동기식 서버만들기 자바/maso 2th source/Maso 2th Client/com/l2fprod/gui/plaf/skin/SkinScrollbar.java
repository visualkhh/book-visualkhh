package com.l2fprod.gui.plaf.skin;

/**
 * Skin Scrollbar.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:01 $
 */
public interface SkinScrollbar extends SkinComponent {

    java.awt.Dimension getPreferredSize(javax.swing.JScrollBar scrollbar);
    java.awt.Dimension getArrowPreferredSize(int direction);

    boolean paintArrow(java.awt.Graphics g, javax.swing.AbstractButton b, int direction);
    boolean paintThumb(java.awt.Graphics g, javax.swing.JScrollBar scrollbar, java.awt.Rectangle thumbBounds);
    boolean paintTrack(java.awt.Graphics g, javax.swing.JScrollBar scrollbar, java.awt.Rectangle trackBounds);

}
