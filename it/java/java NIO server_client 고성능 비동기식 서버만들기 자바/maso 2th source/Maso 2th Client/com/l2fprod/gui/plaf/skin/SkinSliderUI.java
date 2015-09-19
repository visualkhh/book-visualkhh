package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:05 $
 */
public class SkinSliderUI extends BasicSliderUI {

    public static ComponentUI createUI(JComponent x) {
	return new SkinSliderUI((JSlider)x);
    }

    private Skin skin = SkinLookAndFeel.getSkin();

    public SkinSliderUI(JSlider b) {
	super(b);
    }

    protected void installDefaults(JSlider b) {
	super.installDefaults(b);
	skin.getSlider().installSkin(b);
    }

    public void paintTrack(Graphics g) {
        Rectangle trackBounds = trackRect;

	skin.getSlider().paintTrack(g, slider, trackBounds);
    }

    public void paintThumb(Graphics g) {
        Rectangle knobBounds = thumbRect;
        int w = knobBounds.width;
        int h = knobBounds.height;      

        g.translate(knobBounds.x, knobBounds.y);

	skin.getSlider().paintThumb(g, slider, knobBounds);

        g.translate(-knobBounds.x, -knobBounds.y);
    }

}
