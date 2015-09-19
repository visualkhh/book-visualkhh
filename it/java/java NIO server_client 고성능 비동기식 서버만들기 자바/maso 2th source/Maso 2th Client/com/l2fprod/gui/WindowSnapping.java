package com.l2fprod.gui;

import java.awt.Window;
import java.awt.Rectangle;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Vector;
import javax.swing.SwingConstants;

/**
 * WindowSnapping.
 * <br>
 *
 * Created on 15/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class WindowSnapping {

    static SnapListener sharedSnap = new SnapListener();
    
    private static class SnapListener extends WindowAdapter implements ComponentListener {
	Vector snaps = new Vector();
	Window lastActivate = null;
	boolean ignoreEvents = false;
	public void windowActivated(WindowEvent e) {
	    if (ignoreEvents) {
		// ok the next event will be processed
		ignoreEvents = false;
		return;
	    }

	    if (lastActivate != null)
		lastActivate.removeComponentListener(this);
	    attachSnapTo(e.getWindow());
	    lastActivate = e.getWindow();
	    lastActivate.addComponentListener(this);
	}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) { attachSnapTo(lastActivate); }
	public void componentShown(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { }

	public void attachSnapTo(Window target) {
	    synchronized(snaps) {
		// notify the listener that it should not handle the next windowActivateEvent
		ignoreEvents = true;
		for (int i = 0, c = snaps.size(); i < c; i++) {		    
		    ((Snap)snaps.elementAt(i)).attachTo(target);
		}
		// restore focus to target
		target.requestFocus();
		target.toFront();
	    }
	}
    }

    public static void snap(Window snap, int position) {	
	// snap to active window
	sharedSnap.snaps.addElement(new Snap(snap, position));
    }

    public static void snap(Window snap, int position, Window target) {
	// snap to target
	SnapListener l = new SnapListener();
	l.snaps.addElement(new Snap(snap, position));
	target.addWindowListener(l);
    }

    public static void registerSnapping(Window target) {
	if (target != null)
	    target.addWindowListener(sharedSnap);
    }

    public static void unregisterSnapping(Window target) {
	if (target != null)
	    target.removeWindowListener(sharedSnap);
    }

    private static class Snap {
	Window snap;
	int position;
	public Snap(Window snap, int position) {
	    this.snap = snap;
	    this.position = position;
	}
	public void attachTo(Window target) {
	    // i'm a snap window, i should follow my target
	    Rectangle targetBounds = target.getBounds();
	    int x, y;
	    switch (position) {
	    default:
	    case SwingConstants.NORTH_WEST: // top left corner
		x = targetBounds.x;
		y = targetBounds.y - snap.getSize().height;
		break;
	    case SwingConstants.NORTH_EAST: // top right corner
		x = targetBounds.x + targetBounds.width - snap.getSize().width;
		y = targetBounds.y - snap.getSize().height;
		break;
	    case SwingConstants.SOUTH_WEST: // bottom left corner
		x = targetBounds.x;
		y = targetBounds.y + targetBounds.height;
		break;
	    case SwingConstants.SOUTH_EAST:
		x = targetBounds.x + targetBounds.width - snap.getSize().width;
		y = targetBounds.y + targetBounds.height;
		break;
	    case SwingConstants.WEST:
		x = targetBounds.x - snap.getSize().width;
		y = targetBounds.y;
		break;
	    case SwingConstants.EAST:
		x = targetBounds.x + targetBounds.width;
		y = targetBounds.y;
		break;
	    }
	    snap.setLocation(x, y);
	    //	    snap.toFront();
	}
    }

}

