package com.l2fprod.gui.plaf.skin;

/**
 * Skin Slider.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:03 $
 */
public interface SkinSlider extends SkinComponent {

    java.awt.Dimension getPreferredSize(javax.swing.JSlider slider);

    boolean paintTrack(java.awt.Graphics g, javax.swing.JSlider slider, java.awt.Rectangle trackBounds);
    boolean paintThumb(java.awt.Graphics g, javax.swing.JSlider slider, java.awt.Rectangle thumbBounds);

}
