package com.l2fprod.gui.plaf.skin;

/**
 * Skin Tab.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/10/31 18:18:51 $
 */
public interface SkinTab extends SkinComponent {

    boolean paintTab(java.awt.Graphics g, int tabPlacement, 
		     boolean isSelected, int x, int y, int w, int h);
    boolean paintContent(java.awt.Graphics g, int tabPlacement, int selectedIndex,
			 int x, int y, int w, int h);
}
