package com.l2fprod.gui.event;

import java.awt.AWTEvent;
import java.awt.event.WindowEvent;

import com.l2fprod.gui.SkinWindow;

/**
 * SkinWindowEvent.
 * <br>
 * 
 * Created on 08/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:55 $
 */
public class SkinWindowEvent extends WindowEvent {

    public final static int SKINWINDOW_FIRST = 19750;

    public final static int WINDOW_SHADE = 19750;
    public final static int WINDOW_UNSHADE = 19751;

    public final static int WINDOW_MAXIMIZE = 19752;
    public final static int WINDOW_UNMAXIMIZE = 19753;

    public final static int SKINWINDOW_LAST = 19753;

    public SkinWindowEvent(SkinWindow window, int id) {
	super(window, id);
    }

    public String paramString() {
	String typeStr;
	switch (id) {
	case WINDOW_SHADE:
	    typeStr = "WINDOW_SHADE";
	    break;
	case WINDOW_UNSHADE:
	    typeStr = "WINDOW_UNSHADE";
	    break;
	case WINDOW_MAXIMIZE:
	    typeStr = "WINDOW_MAXIMIZE";
	    break;
	case WINDOW_UNMAXIMIZE:
	    typeStr = "WINDOW_UNMAXIMIZE";
	    break;
	default:
	    typeStr = super.paramString();
	}
	return typeStr;
    }

}
