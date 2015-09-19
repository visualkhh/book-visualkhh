package com.l2fprod.gui.plaf.skin;

/**
 * Skin Progress.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:01 $
 */
public interface SkinProgress extends SkinComponent {

    java.awt.Dimension getMinimumSize();
    boolean paintProgress(java.awt.Graphics g, javax.swing.JProgressBar progress);

}
