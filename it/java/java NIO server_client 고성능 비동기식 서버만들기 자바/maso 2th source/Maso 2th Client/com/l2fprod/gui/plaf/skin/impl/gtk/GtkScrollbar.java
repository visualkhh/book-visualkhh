package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.JComponent;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:58 $
 */
class GtkScrollbar implements SkinScrollbar, SwingConstants {

    DefaultButton h_track, v_track;
    DefaultButton h_thumb, v_thumb;

    DefaultButton h_handle, v_handle;

    ArrowButton up, down, left, right;

    public GtkScrollbar(GtkParser parser) throws Exception {
	h_thumb = GtkUtils.newButton(parser, "GtkScrollbar",
				      new String[]{"function", "detail", "orientation"},
				      new String[]{"BOX", "slider", "HORIZONTAL"});
	v_thumb = GtkUtils.newButton(parser, "GtkScrollbar",
				      new String[]{"function", "detail", "orientation"},
				      new String[]{"BOX", "slider", "VERTICAL"});

	h_track = GtkUtils.newButton(parser, "GtkScrollbar",
				     new String[]{"function", "detail", "orientation"},
				     new String[]{"BOX", "trough", "HORIZONTAL"});

	v_track = GtkUtils.newButton(parser, "GtkScrollbar",
				     new String[]{"function", "detail", "orientation"},
				     new String[]{"BOX", "trough", "VERTICAL"});

	h_handle = GtkUtils.newButton(parser, "default",
				      new String[]{"function", "orientation"},
				      new String[]{"HANDLE", "HORIZONTAL"}, true);

	v_handle = GtkUtils.newButton(parser, "default",
				      new String[]{"function", "orientation"},
				      new String[]{"HANDLE", "VERTICAL"}, true);

	up = new ArrowButton(parser, "UP");
	down = new ArrowButton(parser, "DOWN");
	left = new ArrowButton(parser, "LEFT");
	right = new ArrowButton(parser, "RIGHT");
    }

    public boolean status() {
	return true;
    }

    public boolean installSkin(JComponent c) {
	return true;
    }

    public Dimension getPreferredSize(JScrollBar scrollbar) {
	return (scrollbar.getOrientation() == JScrollBar.VERTICAL)
	    ? new Dimension(Math.max(10, Math.min(up.getWidth(), v_thumb.getWidth())), 48)
		: new Dimension(48, Math.max(10, Math.min(left.getHeight(), h_thumb.getHeight())));
    }

    public Dimension getArrowPreferredSize(int direction) {
	switch (direction) {
	case NORTH:
	    return up.getPreferredSize();
	case SOUTH:
	    return down.getPreferredSize();
	case WEST:
	    return left.getPreferredSize();
	case EAST:
	    return right.getPreferredSize();
	default:
	    throw new Error("Invalid direction " + direction);
	}
    }

    public boolean paintArrow(java.awt.Graphics g, javax.swing.AbstractButton b, int direction) {
	switch (direction) {
	case NORTH:
	    up.paint(g, b);
	    break;
	case SOUTH:
	    down.paint(g, b);
	    break;
	case WEST:
	    left.paint(g, b);
	    break;
	case EAST:
	    right.paint(g, b);
	}
	return true;
    }

    // track is under thumb
    public boolean paintTrack(Graphics g, JScrollBar scrollbar, Rectangle trackBounds) {
	if (h_track != null) {
	    if (scrollbar.getOrientation() == HORIZONTAL) {		
		h_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, scrollbar);
	    } else {
		v_track.paint(g, 0, 0, trackBounds.width, trackBounds.height, scrollbar);
	    }
	    return true;
	}
	return false;
    }

    // thumb is the variable area
    public boolean paintThumb(Graphics g, JScrollBar scrollbar, Rectangle thumbBounds) {
	// the UI translate the graphics to thumbBounds.x and .y
	if (h_thumb != null) {
	    if (scrollbar.getOrientation() == HORIZONTAL) {
		h_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, scrollbar);
		if (h_handle != null)
		    h_handle.paint(g, Math.max(0, (thumbBounds.width - h_handle.getWidth())/2),
				   (thumbBounds.height - h_handle.getHeight())/2,
				   Math.min(thumbBounds.width, h_handle.getWidth()),
				   h_handle.getHeight(), scrollbar);
	    } else {
		v_thumb.paint(g, 0, 0, thumbBounds.width, thumbBounds.height, scrollbar);
		if (v_handle != null)
		    v_handle.paint(g, (thumbBounds.width - v_handle.getWidth())/2,
				   Math.max(0, (thumbBounds.height - v_handle.getHeight())/2),
				   v_handle.getWidth(),
				   Math.min(thumbBounds.height, v_handle.getHeight()), scrollbar);
	    }
	    return true;
	}
	return false;
    }

}
