package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:59 $
 */
class GtkSlider implements SkinSlider, SwingConstants {

    DefaultButton h_track, v_track;
    DefaultButton h_thumb, v_thumb;

    public GtkSlider(GtkParser parser) throws Exception {
	h_thumb = GtkUtils.newButton(parser, "GtkRange",
					new String[]{"function", "orientation"},
					new String[]{"SLIDER", "HORIZONTAL"}, true);
	v_thumb = GtkUtils.newButton(parser, "GtkRange",
				      new String[]{"function", "orientation"},
				      new String[]{"SLIDER", "VERTICAL"}, true);

	h_track = GtkUtils.newButton(parser, "GtkRange",
				     new String[]{"function", "detail", "orientation"},
				     new String[]{"BOX", "trough", "HORIZONTAL"});
	v_track = GtkUtils.newButton(parser, "GtkRange",
				     new String[]{"function", "detail", "orientation"},
				     new String[]{"BOX", "trough", "VERTICAL"});
    }

    public boolean status() {
	return true;
    }

    public boolean installSkin(JComponent c) {
	c.setOpaque(false);
	return true;
    }

    public Dimension getPreferredSize(JSlider slider) {
	return null;
    }

    public boolean paintTrack(Graphics g, JSlider slider, Rectangle trackBounds) {
	if (h_track != null) {
	    if (slider.getOrientation() == HORIZONTAL) {
		h_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, slider);
	    } else {
		v_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, slider);
	    }
	    return true;
	} else {
	    return false;
	}
    }

    public boolean paintThumb(Graphics g, JSlider slider, Rectangle thumbBounds) {
	// the UI translate the graphics to thumbBounds.x and .y
	if (h_thumb != null) {
	    if (slider.getOrientation() == HORIZONTAL) {
		h_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, slider);
	    } else {
		v_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, slider);
	    }
	    return true;
	} else {
	    return false;
	}
    }

}    
