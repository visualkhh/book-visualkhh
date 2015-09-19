package com.l2fprod.gui;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.beans.PropertyChangeEvent;
import javax.swing.border.Border;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.l2fprod.gui.plaf.skin.SkinUtils;
import com.l2fprod.gui.sound.NoSoundComponent;

/**
 * DefaultWindowManager.
 * <br>
 * This is an implementation of the WindowManager. It currently implements
 * the basic behaviors for managing SkinWindows.
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class DefaultWindowManager extends WindowManager {

    final static String PREVIOUS_BOUNDS_PROPERTY = "windowpreviousBounds";
    public final static String SHADE_BOUNDS_PROPERTY = "windowshadeBounds";
    final static String HAS_BEEN_ICONIFIED_PROPERTY = "wasIconOnce";
    
    final static int DEFAULT_DRAG_MODE = 0;
    //    final static int OUTLINE_DRAG_MODE = 1;
    final static int FASTER_DRAG_MODE = 2; // use ghost window
    
    int dragMode = DEFAULT_DRAG_MODE;
    
    private static SkinWindow currentActiveWindow = null;

    private transient Rectangle currentBounds = null;
    private transient Point currentLoc = null;

    private transient Graphics desktopGraphics = null;
    private transient Rectangle desktopBounds = null;   
    private transient Rectangle[] floatingItems = {};
    
    private transient Window ghostWindow = null;

    public void openWindow(SkinWindow f) {
    }

    public void closeWindow(SkinWindow f) {
        removeIconFor(f);
        if(getPreviousBounds(f) != null)
            setPreviousBounds(f, null);
        if(wasIcon(f))
            setWasIcon(f, null);
	SkinWindowList.getSkinWindowList().unregisterWindow(f);
    }

    public void maximizeWindow(SkinWindow f) {
        Rectangle p;
	Dimension newSize = Toolkit.getDefaultToolkit().getScreenSize();
	p = new Rectangle(0,0,newSize.width,newSize.height);

        if(!f.isIcon()) {
	    setPreviousBounds(f, f.getBounds());
        } else {
            try { f.setIcon(false); } catch (PropertyVetoException e2) { }
        }
        setBoundsForWindow(f, 0, 0, p.width, p.height);
        try { f.setSelected(true); } catch (PropertyVetoException e2) { }

        removeIconFor(f);
    }

    public void minimizeWindow(SkinWindow f) {
        if(getPreviousBounds(f) != null) {
            Rectangle r = getPreviousBounds(f);
            setPreviousBounds(f, null);
            try { f.setSelected(true); } catch (PropertyVetoException e2) { }
            if(f.isIcon())
                try { f.setIcon(false); } catch (PropertyVetoException e2) { }
            setBoundsForWindow(f, r.x, r.y, r.width, r.height);
        }
        removeIconFor(f);
    }

    public void shadeWindow(SkinWindow w) {
	Rectangle p = null;
	Rectangle bounds = w.getBounds();
	p = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
	w.putClientProperty(SHADE_BOUNDS_PROPERTY, p);
	setBoundsForWindow(w, p.x, p.y, p.width, w.getBorderInsets().top + w.northPane.getHeight() + w.getBorderInsets().bottom + 2);
    }

    public void unshadeWindow(SkinWindow w) {
	Point location = w.getLocation();
	Rectangle p = (Rectangle)w.getClientProperty(SHADE_BOUNDS_PROPERTY);
	setBoundsForWindow(w, location.x, location.y,
			   p.width, p.height);
    }

    public void iconifyWindow(SkinWindow f) {
	boolean findNext = f.isSelected();

        try { f.setSelected(false); } catch (PropertyVetoException e2) { }
	f.setVisible(false);

	if (findNext) {
	    SkinWindowList.getSkinWindowList().activateNextWindow(f);
	}

	/*        Window.JDesktopIcon desktopIcon;
        Container c;

        desktopIcon = f.getDesktopIcon();
        if(!wasIcon(f)) {
            Rectangle r = getBoundsForIconOf(f);
            desktopIcon.setBounds(r.x, r.y, r.width, r.height);
            setWasIcon(f, Boolean.TRUE);
        }

        c = f.getParent();

	if (c instanceof JLayeredPane) {
	    JLayeredPane lp = (JLayeredPane)c;
	    int layer = lp.getLayer(f);
	    lp.putLayer(desktopIcon, layer);
	}

        c.remove(f);
        c.add(desktopIcon);
        c.repaint(f.getX(), f.getY(), f.getWidth(), f.getHeight());
        try { f.setSelected(false); } catch (PropertyVetoException e2) { }
	*/
    }

    public void deiconifyWindow(SkinWindow f) {
	f.setVisible(true);
	f.toFront();
	try { f.setSelected(true); } catch (PropertyVetoException e2) { }
	/*
        Window.JDesktopIcon desktopIcon;
        Dimension size;

        desktopIcon = f.getDesktopIcon();
        if(desktopIcon.getParent() != null) {
            desktopIcon.getParent().add(f);
            removeIconFor(f);
        }
	*/
    }

    public void activateWindow(SkinWindow f) {
	// we only need to keep track of the currentActive InternalFrame, if any
	if (currentActiveWindow == null){
	  currentActiveWindow = f;
	} else if (currentActiveWindow != f) {  
	    // if not the same frame as the current active
	    // we deactivate the current 
	    if (currentActiveWindow.isSelected()) { 
		try {
		    currentActiveWindow.setSelected(false);
		}
		catch(PropertyVetoException e2) {}
	    }
	    currentActiveWindow = f;
	}
	f.toFront();
    }
    
    public void deactivateWindow(SkinWindow f) {
      if (currentActiveWindow == f)
	currentActiveWindow = null;
    }

    public void setBoundsForWindow(final Container f,
				   final int newX, final int newY,
				   final int newWidth, final int newHeight) {
	Dimension dim = f.getSize();
        boolean didResize = (dim.width != newWidth || dim.height != newHeight);
        f.setBounds(newX, newY, newWidth, newHeight);
        if(didResize) {
            f.validate();
        }
    }

    public void beginDraggingWindow(SkinWindow w) {
	setupDragMode(w);
	currentLoc = w.getLocation();
	if (dragMode == FASTER_DRAG_MODE) {
	    ghostWindow.setBounds(w.getBounds());
	}
	w.isDragging = true;
    }

    public void dragWindow(SkinWindow w, int newX, int newY) {
	currentLoc.x = newX;
	currentLoc.y = newY;
	if (dragMode == FASTER_DRAG_MODE) {
	    ghostWindow.setVisible(true);
	    ghostWindow.toFront();
	    ghostWindow.setLocation(currentLoc.x, currentLoc.y);
	} else {
	    w.setLocation(currentLoc.x, currentLoc.y);
	}
    }

    public void endDraggingWindow(SkinWindow w) {
	w.isDragging = false;
	if (dragMode == FASTER_DRAG_MODE) {
	    ghostWindow.setVisible(false);
	    //	    ghostWindow = null;
	}
	if (currentLoc != null) {
	    w.setLocation(currentLoc.x, currentLoc.y);
	    currentLoc = null;
	}
    }

    public void beginResizingWindow(SkinWindow w, int direction) {
	setupDragMode(w);
	if (dragMode == FASTER_DRAG_MODE) {
	    ghostWindow.setBounds(w.getBounds());
	    ghostWindow.setVisible(true);
	    ghostWindow.toFront();
	}
	w.isDragging = true;
    }

    public void resizeWindow(SkinWindow w, int newX, int newY, int newWidth, int newHeight) {
	currentBounds = new Rectangle(newX, newY, newWidth, newHeight);
	if (dragMode == FASTER_DRAG_MODE) {
	    ghostWindow.setBounds(newX, newY, newWidth, newHeight);
	} else {
	    setBoundsForWindow(w, newX, newY, newWidth, newHeight);
	}
    }

    public void endResizingWindow(SkinWindow w) {
	w.isDragging = false;
	if (dragMode == FASTER_DRAG_MODE) {
	    ghostWindow.setVisible(false);
	    ghostWindow = null;
	}
	if (currentBounds != null) {
	    setBoundsForWindow(w, currentBounds.x, currentBounds.y,
			       currentBounds.width, currentBounds.height);
	    currentBounds = null;
	}
    }

    protected void removeIconFor(SkinWindow f) {
	/*
        Window.JDesktopIcon di = f.getDesktopIcon();
        Container c = di.getParent();
        if(c != null) {
            c.remove(di);
            c.repaint(di.getX(), di.getY(), di.getWidth(), di.getHeight());
        }
	*/
    }

    private void setupDragMode(SkinWindow f) {
	String mode = (String)f.getClientProperty("Window.dragMode");
	if (mode != null && mode.equals("faster")) {
	    dragMode = FASTER_DRAG_MODE;
	    if (ghostWindow == null) {
		ghostWindow = new NoEventWindow(new Frame("GhostWindow"));
	    }
	} else {
	    dragMode = DEFAULT_DRAG_MODE;
	}
    }

    class NoEventWindow extends Window implements NoSoundComponent {
	public NoEventWindow(Frame f) {
	    super(f);
	    disableEvents(AWTEvent.WINDOW_EVENT_MASK);
	    setBackground(javax.swing.UIManager.getColor("Panel.background"));
	    setForeground(javax.swing.UIManager.getColor("Panel.foreground"));
	}
	// draw a border around the window
	public void paint(Graphics g) {
	    g.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
	}
    }

    /** The iconifyWindow() code calls this to determine the proper bounds
      * for the desktopIcon.
      */

    protected Rectangle getBoundsForIconOf(SkinWindow f) {
	return null;
	/*
      //
      // Get the parent bounds and child components.
      //

      Container c = f.getParent();
      Rectangle parentBounds = c.getBounds();
      Component [] components = c.getComponents();

      //
      // Get the icon for this internal frame and its preferred size
      //

      Window.JDesktopIcon icon = f.getDesktopIcon();
      Dimension prefSize = icon.getPreferredSize();

      //
      // Iterate through valid default icon locations and return the
      // first one that does not intersect any other icons.
      //

      Rectangle availableRectangle = null;
      Window.JDesktopIcon currentIcon = null;

      int x = 0;
      int y = parentBounds.height - prefSize.height;
      int w = prefSize.width;
      int h = prefSize.height;

      boolean found = false;

      while (!found) {

	availableRectangle = new Rectangle(x,y,w,h);

	found = true;

	for ( int i=0; i<components.length; i++ ) {

	  //
	  // Get the icon for this component
	  //

	  if ( components[i] instanceof Window ) {
	    currentIcon = ((Window)components[i]).getDesktopIcon();
	  }
	  else if ( components[i] instanceof Window.JDesktopIcon ){
	    currentIcon = (Window.JDesktopIcon)components[i];
	  }

	  //
	  // If this icon intersects the current location, get next location.
	  //

	  if ( !currentIcon.equals(icon) ) {
	    if ( availableRectangle.intersects(currentIcon.getBounds()) ) {
	      found = false;
	      break;
	    }
	  }
	}

	x += currentIcon.getBounds().width;

	if ( x + w > parentBounds.width ) {
	  x = 0;
	  y -= h;
	}
      }
	    
      return(availableRectangle);
	*/
    }

    protected void setPreviousBounds(SkinWindow f, Rectangle r)     {
	if (r != null) {
	    f.putClientProperty(PREVIOUS_BOUNDS_PROPERTY, r);
	}
    }

    protected Rectangle getPreviousBounds(SkinWindow f)     {
        return (Rectangle)f.getClientProperty(PREVIOUS_BOUNDS_PROPERTY);
    }

    /** Sets that the component has been iconized and the bounds of the
      * desktopIcon are valid.
      */
    protected void setWasIcon(SkinWindow f, Boolean value)  {
	if (value != null) {
	    f.putClientProperty(HAS_BEEN_ICONIFIED_PROPERTY, value);
	}
    }

    protected boolean wasIcon(SkinWindow f) {
        return (f.getClientProperty(HAS_BEEN_ICONIFIED_PROPERTY) == Boolean.TRUE);
    }


    /*    JDesktopPane getDesktopPane( JComponent frame ) {
        JDesktopPane pane = null;
	Component c = frame.getParent();

        // Find the JDesktopPane
        while ( pane == null ) {
	    if ( c instanceof JDesktopPane ) {
	        pane = (JDesktopPane)c;
	    }
	    else if ( c == null ) {
	        break;
	    }
	    else {
	        c = c.getParent();
	    }
	}

	return pane;
	}*/

}

