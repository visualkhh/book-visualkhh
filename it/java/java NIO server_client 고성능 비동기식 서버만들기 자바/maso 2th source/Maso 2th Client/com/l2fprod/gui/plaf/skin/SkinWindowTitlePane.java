package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.util.EventListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyVetoException;

import com.l2fprod.gui.SkinWindow;
import com.l2fprod.gui.WindowManager;

/**
 * The class that manages a basic title bar
 */
public class SkinWindowTitlePane extends SkinTitlePane {

    protected MouseInputListener borderListener;

    public SkinWindowTitlePane(SkinWindow f) {
	super(f);
    }

    protected void installListeners() {
	super.installListeners();
	borderListener = new BorderListener();
	frame.getContainer().addMouseListener(borderListener);
	frame.getContainer().addMouseMotionListener(borderListener);

	addMouseListener(borderListener);
	addMouseMotionListener(borderListener);

	//Container contentPane = ((JWindow)frame.getContainer()).getContentPane();
	//contentPane.addMouseListener(borderListener);
	//contentPane.addMouseMotionListener(borderListener);	

	MouseInputListener glassPaneDispatcher = createGlassPaneDispatcher();
	((JWindow)frame.getContainer()).getGlassPane().addMouseListener(glassPaneDispatcher);
	((JWindow)frame.getContainer()).getGlassPane().addMouseMotionListener(glassPaneDispatcher);
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        return new PropertyChangeHandler();
    }

    public class PropertyChangeHandler implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {
	    SkinWindow f = (SkinWindow)evt.getSource();
	    String prop = (String)evt.getPropertyName();
	    Object newValue = evt.getNewValue();
	    Object oldValue = evt.getOldValue();

	    if (JInternalFrame.IS_CLOSED_PROPERTY.equals(prop)) {
		if(newValue == Boolean.TRUE){
		    WindowManager.getWindowManager().closeWindow(f);
		}
	    } else if(JInternalFrame.IS_MAXIMUM_PROPERTY.equals(prop)) {
		if(newValue == Boolean.TRUE)
		    WindowManager.getWindowManager().maximizeWindow(f);
		else 
		    WindowManager.getWindowManager().minimizeWindow(f);
	    } else if(JInternalFrame.IS_ICON_PROPERTY.equals(prop)) {
		if (newValue == Boolean.TRUE)
		    WindowManager.getWindowManager().iconifyWindow(f);
		else
		    WindowManager.getWindowManager().deiconifyWindow(f);
	    } else if(JInternalFrame.IS_SELECTED_PROPERTY.equals(prop)) {
		Component glassPane = f.getGlassPane();
		if(newValue == Boolean.TRUE && oldValue == Boolean.FALSE) {
		    WindowManager.getWindowManager().activateWindow(f);
		    //	glassPane.removeMouseListener(glassPaneDispatcher);
		    //	glassPane.removeMouseMotionListener(glassPaneDispatcher);
		    glassPane.setVisible(false);
		} else if(newValue == Boolean.FALSE && oldValue == Boolean.TRUE) {
		    WindowManager.getWindowManager().deactivateWindow(f);
		    //	glassPane.addMouseListener(glassPaneDispatcher);
		    //	glassPane.addMouseMotionListener(glassPaneDispatcher);
		    glassPane.setVisible(true);
		}
	    } else if (Window.IS_SHADED_PROPERTY.equals(prop)) {
		if (newValue == Boolean.TRUE)
		    WindowManager.getWindowManager().shadeWindow(f);
		else
		    WindowManager.getWindowManager().unshadeWindow(f);
	    }

	    enableActions();
	}
    }

    protected MouseInputListener createGlassPaneDispatcher(){
	return new GlassPaneDispatcher();
    }

    protected class GlassPaneDispatcher implements MouseInputListener {

	public void mousePressed(MouseEvent e) {
	    if (borderListener != null)
		borderListener.mousePressed(e);
	    forwardMouseEvent(e);
	}
	public void mouseEntered(MouseEvent e) {
	    forwardMouseEvent(e);
	}
	public void mouseMoved(MouseEvent e) {
	    forwardMouseEvent(e);
	}
	public void mouseExited(MouseEvent e) {
	    forwardMouseEvent(e);
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	    forwardMouseEvent(e);
	}
	public void mouseDragged(MouseEvent e) {
	}
	
	private void forwardMouseEvent(MouseEvent e) {
	    Component target = SkinUtils.findComponentAt( ((JWindow)frame.getContainer()).getContentPane(), e.getX(), e.getY());
	    if (target != null) {
		if (target != mouseEventTarget) {
		    setMouseTarget(target, e);
		}
		retargetMouseEvent(e.getID(), e);
	    }
	}
	
	private Component mouseEventTarget = null;
	
	/*
	 * Set the child component to which events are forwarded, and
	 * synthesize the appropriate mouseEntered and mouseExited events.
	 */
	private void setMouseTarget(Component target, MouseEvent e) {
	    if (mouseEventTarget != null) {
		retargetMouseEvent(MouseEvent.MOUSE_EXITED, e);
	    }
	    mouseEventTarget = target;
	    if (mouseEventTarget != null) {
		retargetMouseEvent(MouseEvent.MOUSE_ENTERED, e);
	    }
	}
	
	/* 
	 * Dispatch an event clone, retargeted for the current mouse target.
	 */
	void retargetMouseEvent(int id, MouseEvent e) {
	    Point p = SwingUtilities.convertPoint(((JWindow)frame.getContainer()).getContentPane(),
						  e.getX(), e.getY(),
						  mouseEventTarget);
	    MouseEvent retargeted = new MouseEvent(mouseEventTarget, 
						   id, 
						   e.getWhen(), 
						   e.getModifiers(),
						   p.x, 
						   p.y, 
						   e.getClickCount(), 
						   e.isPopupTrigger());
	    mouseEventTarget.dispatchEvent(retargeted);
	}
	
    }

    protected class BorderListener extends MouseInputAdapter implements SwingConstants
    {
	int startX, startY;
	
        Rectangle startingBounds;
	Rectangle newBounds = new Rectangle();
        int resizeDir;
        
        protected final int NO_ACTION  = -1;
        protected final int RESIZE_NONE  = 0;
                
        int resizeCornerSize = 5;
        
	public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() > 1 && e.getSource() == SkinWindowTitlePane.this) {
		if(frame.isIconifiable() && frame.isIcon()) {
                    try { frame.setIcon(false); } catch (PropertyVetoException e2) { }
		} else if(frame.isMaximizable()) {
                    if(!frame.isMaximum())
                        try { frame.setMaximum(true); } catch (PropertyVetoException e2) { }
                    else
                        try { frame.setMaximum(false); } catch (PropertyVetoException e3) { }
		} 
            }
	}

        public void mouseReleased(MouseEvent e) {
	    switch (resizeDir) {
	    case NO_ACTION:
		break;
	    case RESIZE_NONE:
		WindowManager.getWindowManager().endDraggingWindow((SkinWindow)frame.getContainer());
		break;
	    default:
		WindowManager.getWindowManager().endResizingWindow((SkinWindow)frame.getContainer());
	    }

            startingBounds = null;
            resizeDir = NO_ACTION;
        }
                
        public void mousePressed(MouseEvent e) {
	    startX = e.getX();
	    startY = e.getY();

            startingBounds = frame.getContainer().getBounds();

            if(!frame.isSelected()) {
                try { frame.setSelected(true); } catch (PropertyVetoException e1) { }
            }
            if((!frame.isResizable() || frame.isShaded()) || e.getSource() == SkinWindowTitlePane.this) {
                resizeDir = RESIZE_NONE;
		WindowManager.getWindowManager().beginDraggingWindow((SkinWindow)frame.getContainer());
                return;
            }       

	    if(e.getSource() == frame.getContainer()) {
		Dimension dim = frame.getContainer().getSize();
                if(e.getX() <= resizeCornerSize) {
                    if(e.getY() < resizeCornerSize)
                        resizeDir = NORTH_WEST;
                    else if(e.getY() > dim.height - resizeCornerSize)
                        resizeDir = SOUTH_WEST;
                    else                
                        resizeDir = WEST;
                } else if(e.getX() >= dim.width - resizeCornerSize) {
                    if(e.getY() < resizeCornerSize)
                        resizeDir = NORTH_EAST;
                    else if(e.getY() > dim.height - resizeCornerSize)
                        resizeDir = SOUTH_EAST;
                    else                
                        resizeDir = EAST;
                } else if(e.getY() <= resizeCornerSize) {
                    if(e.getX() < resizeCornerSize)
                        resizeDir = NORTH_WEST;
                    else if(e.getX() > dim.width - resizeCornerSize)
                        resizeDir = NORTH_EAST;
                    else                
                        resizeDir = NORTH;
                } else if(e.getY() >= dim.height - resizeCornerSize) {
                    if(e.getX() < resizeCornerSize)
                        resizeDir = SOUTH_WEST;
                    else if(e.getX() > dim.width - resizeCornerSize)
                        resizeDir = SOUTH_EAST;
                    else                
                        resizeDir = SOUTH;
                } 
		if ((resizeDir != RESIZE_NONE) && (resizeDir != NO_ACTION))
		    WindowManager.getWindowManager().beginResizingWindow((SkinWindow)frame.getContainer(),
									 resizeDir);
                return;
	    }
        }

        public void mouseDragged(MouseEvent e) {   

	    if ( startingBounds == null ) {
		return;
	    }
                                     
            Point p; 
	    int newX, newY, newW, newH;
            int deltaX;
            int deltaY;
	    Dimension min;
	    Dimension max;
            p = SwingUtilities.convertPoint((Component)e.getSource(), 
                                        e.getX(), e.getY(), null);
        
	    deltaX = e.getX() - startX;
	    deltaY = e.getY() - startY;
	    
	    p = frame.getContainer().getLocation();

            // Handle a MOVE 
            if(e.getSource() == SkinWindowTitlePane.this) {
                if (frame.isMaximum()) {
                    return;  // don't allow moving of maximized frames.
                }


		WindowManager.getWindowManager().dragWindow((SkinWindow)frame.getContainer(),
							    p.x + deltaX, p.y + deltaY);

		/*		Insets i = frame.getContainer().getInsets();
				int pWidth, pHeight;
				Dimension s = frame.getContainer().getSize();
				pWidth = s.width;
				pHeight = s.height;
				
				newX = startingBounds.x - (_x - p.x);
				newY = startingBounds.y - (_y - p.y);

		// Make sure we stay in-bounds
		if(newX + i.left <= -__x)
		    newX = -__x - i.left;
		if(newY + i.top <= -__y)
		    newY = -__y - i.top;
		if(newX + __x + i.right > pWidth)
		    newX = pWidth - __x - i.right;
		if(newY + __y + i.bottom > pHeight)
		    newY =  pHeight - __y - i.bottom;

		*/

                return;
            }

            if(!frame.isResizable() || frame.isShaded()) {
                return;
            }

	    min = frame.getContainer().getMinimumSize();
	    max = frame.getContainer().getMaximumSize();
        
	    newX = p.x;
	    newY = p.y;
	    newW = ((Component)frame.getContainer()).getSize().width;
	    newH = ((Component)frame.getContainer()).getSize().height;

            switch(resizeDir) {
            case RESIZE_NONE:
                return;
            case NORTH:      
		/*if(startingBounds.height + deltaY < min.height)
		    deltaY = -(startingBounds.height - min.height);
		else if(startingBounds.height + deltaY > max.height)
		deltaY = (startingBounds.height - min.height);*/

		newX = startingBounds.x;
		newY = startingBounds.y + deltaY;
		newW = startingBounds.width;
		newH = startingBounds.height - deltaY;
                break;
            case NORTH_EAST:     
		/*		if(startingBounds.height + deltaY < min.height)
		    deltaY = -(startingBounds.height - min.height);
		else if(startingBounds.height + deltaY > max.height)
		deltaY = (startingBounds.height - min.height);

		if(startingBounds.width - deltaX < min.width)
		    deltaX = (startingBounds.width - min.width);
		else if(startingBounds.width - deltaX > max.width)
		deltaX = -(startingBounds.width - min.width);*/

		newX = startingBounds.x;
		newY = startingBounds.y + deltaY;
		newW = startingBounds.width + deltaX;
		newH = startingBounds.height - deltaY;
                break;
            case EAST:      
		/*		if(startingBounds.width - deltaX < min.width)
		    deltaX = (startingBounds.width - min.width);
		else if(startingBounds.width - deltaX > max.width)
		deltaX = -(startingBounds.width - min.width);*/

		newW = startingBounds.width + deltaX;
		newH = startingBounds.height;
                break;
            case SOUTH_EAST:     
		/*		if(startingBounds.width - deltaX < min.width)
		    deltaX = (startingBounds.width - min.width);
		else if(startingBounds.width - deltaX > max.width)
		    deltaX = -(startingBounds.width - min.width);

		if(startingBounds.height - deltaY < min.height)
		    deltaY = (startingBounds.height - min.height);
		else if(startingBounds.height - deltaY > max.height)
		deltaY = -(startingBounds.height - min.height);*/
	
		newW = startingBounds.width + deltaX;
		newH = startingBounds.height + deltaY;
                break;
            case SOUTH:      
		/*if(startingBounds.height - deltaY < min.height)
		    deltaY = (startingBounds.height - min.height);
		else if(startingBounds.height - deltaY > max.height)
		deltaY = -(startingBounds.height - min.height);*/

 		newW = startingBounds.width;
		newH = startingBounds.height + deltaY;
                break;
            case SOUTH_WEST:
		/*		if(startingBounds.height - deltaY < min.height)
		    deltaY = (startingBounds.height - min.height);
		else if(startingBounds.height - deltaY > max.height)
		deltaY = -(startingBounds.height - min.height);

		if(startingBounds.width + deltaX < min.width)
		    deltaX = -(startingBounds.width - min.width);
		else if(startingBounds.width + deltaX > max.width)
		deltaX = (startingBounds.width - min.width);*/

		newX = startingBounds.x + deltaX;
		newY = startingBounds.y;
		newW = startingBounds.width - deltaX;
		newH = startingBounds.height + deltaY;
                break;
            case WEST:      
		/*if(startingBounds.width + deltaX < min.width)
		    deltaX = -(startingBounds.width - min.width);
		else if(startingBounds.width + deltaX > max.width)
		deltaX = (startingBounds.width - min.width);*/

		newX = startingBounds.x + deltaX;
		newY = startingBounds.y;
		newW = startingBounds.width - deltaX;
		newH = startingBounds.height;
                break;
            case NORTH_WEST:     
		/*if(startingBounds.width + deltaX < min.width)
		    deltaX = -(startingBounds.width - min.width);
		else if(startingBounds.width + deltaX > max.width)
		    deltaX = (startingBounds.width - min.width);

		if(startingBounds.height + deltaY < min.height)
		    deltaY = -(startingBounds.height - min.height);
		else if(startingBounds.height + deltaY > max.height)
		deltaY = (startingBounds.height - min.height);*/

		newX = startingBounds.x + deltaX;
		newY = startingBounds.y + deltaY;
		newW = startingBounds.width - deltaX;
		newH = startingBounds.height - deltaY;
                break;
            default:
                return;
            }

	    newBounds.x = newX;
	    newBounds.y = newY;
	    newBounds.width = newW;
	    newBounds.height = newH;

	    WindowManager.getWindowManager().resizeWindow((SkinWindow)frame.getContainer(),
							  newX, newY, newW, newH);
	}

        public void mouseMoved(MouseEvent e)    {
	    if(!frame.isResizable())
		return;

            if(e.getSource() == frame.getContainer()) {
		Component comp = frame.getContainer();
		Dimension dim = comp.getSize();
                if(e.getX() <= resizeCornerSize) {
                    if(e.getY() < resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    else if(e.getY() > dim.height - resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    else                
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
                } else if(e.getX() >= dim.width - resizeCornerSize) {
                    if(e.getY() < resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    else if(e.getY() > dim.height - resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    else                
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                } else if(e.getY() <= resizeCornerSize) {
                    if(e.getX() < resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
                    else if(e.getX() > dim.width - resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
                    else                
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else if(e.getY() >= dim.height - resizeCornerSize) {
                    if(e.getX() < resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
                    else if(e.getX() > dim.width - resizeCornerSize)
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                    else                
			comp.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else {
		    comp.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
		}
		return;
            }

	    frame.getContainer().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
	}         

        public void mouseExited(MouseEvent e) {
	    frame.getContainer().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));	
	}
    };    /// End BorderListener Class

}   // End Title Pane Class

