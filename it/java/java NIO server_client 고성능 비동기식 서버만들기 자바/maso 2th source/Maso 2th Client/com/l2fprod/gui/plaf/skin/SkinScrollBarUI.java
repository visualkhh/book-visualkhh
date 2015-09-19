package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.plaf.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.l2fprod.gui.plaf.xtra.XTraScrollBarUI;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:01 $
 */
public class SkinScrollBarUI extends XTraScrollBarUI {
    
    public static ComponentUI createUI(JComponent x) {
	return new SkinScrollBarUI();
    }

    private Skin skin = SkinLookAndFeel.getSkin();

    protected JButton createDecreaseButton(int orientation)  {
        return new SkinArrowButton(orientation);
    }

    protected JButton createIncreaseButton(int orientation)  {
        return new SkinArrowButton(orientation);
    }

    public Dimension getPreferredSize(JComponent c) {
	return skin.getScrollbar().getPreferredSize((JScrollBar)c);
    }

    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
	//        g.setColor(trackColor);
	//        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);	

	g.translate(trackBounds.x, trackBounds.y);
	skin.getScrollbar().paintTrack(g, scrollbar, trackBounds);
	g.translate(-trackBounds.x, -trackBounds.y);

	if(trackHighlight == DECREASE_HIGHLIGHT) {
	    paintDecreaseHighlight(g);
	} else if(trackHighlight == INCREASE_HIGHLIGHT) {
	    paintIncreaseHighlight(g);
	}
    }

    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
	if(thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
	    return;
	}

        int w = thumbBounds.width;
        int h = thumbBounds.height;		
	
	g.translate(thumbBounds.x, thumbBounds.y);

	skin.getScrollbar().paintThumb(g, scrollbar, thumbBounds);

	g.translate(-thumbBounds.x, -thumbBounds.y);

    }    

}

