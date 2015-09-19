package com.l2fprod.gui;

import java.awt.*;
import java.util.EventListener;
import com.l2fprod.gui.event.*;

/**
 * 
 * Created on 12/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:55 $
 */
public class SkinEventMulticaster extends AWTEventMulticaster implements SkinWindowListener {

    protected SkinEventMulticaster(EventListener a, EventListener b) {
	super(a, b);
    }

    protected EventListener remove(EventListener oldl) {
	if (oldl == a)  return b;
	if (oldl == b)  return a;
	EventListener a2 = SkinEventMulticaster.removeInternal(a, oldl);
	EventListener b2 = SkinEventMulticaster.removeInternal(b, oldl);
	if (a2 == a && b2 == b) {
	    return this;	// it's not here
	}
	return SkinEventMulticaster.addInternal(a2, b2);
    }

    public void windowShaded(SkinWindowEvent e) {
	((SkinWindowListener)a).windowShaded(e);
	((SkinWindowListener)b).windowShaded(e);
    }

    public void windowUnshaded(SkinWindowEvent e) {
	((SkinWindowListener)a).windowUnshaded(e);
	((SkinWindowListener)b).windowUnshaded(e);
    }

    public void windowMaximized(SkinWindowEvent e) {
	((SkinWindowListener)a).windowMaximized(e);
	((SkinWindowListener)b).windowMaximized(e);
    }

    public void windowUnmaximized(SkinWindowEvent e) {
	((SkinWindowListener)a).windowUnmaximized(e);
	((SkinWindowListener)b).windowUnmaximized(e);
    }

    public static SkinWindowListener add(SkinWindowListener a, SkinWindowListener b) {
	return (SkinWindowListener)SkinEventMulticaster.addInternal(a, b);
    }

    public static SkinWindowListener remove(SkinWindowListener a, SkinWindowListener b) {
	return (SkinWindowListener)SkinEventMulticaster.removeInternal(a, b);
    }

    protected static EventListener addInternal(EventListener a, EventListener b) {
	if (a == null)  return b;
	if (b == null)  return a;
	return new SkinEventMulticaster(a, b);
    }

    protected static EventListener removeInternal(EventListener l, EventListener oldl) {
	if (l == oldl || l == null) {
	    return null;
	} else if (l instanceof SkinEventMulticaster) {
	    return ((SkinEventMulticaster)l).remove(oldl);
	} else {
	    return l;		// it's not here
	}
    }

}





