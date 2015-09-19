package com.l2fprod.gui.plaf.skin;

import java.awt.AWTEvent;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import javax.swing.Icon;
import javax.swing.JInternalFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import com.l2fprod.gui.SkinWindow;
import com.l2fprod.gui.DefaultWindowManager;

/**
 * 
 * Created on 27/05/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public interface Window {

    public final static String IS_SHADED_PROPERTY = "shaded";

    Container getContainer();

    void addPropertyChangeListener(PropertyChangeListener listener);

    boolean isSelected();
    void setSelected(boolean b) throws PropertyVetoException;

    boolean isIcon();
    void setIcon(boolean b) throws PropertyVetoException;

    boolean isMaximum();
    void setMaximum(boolean b) throws PropertyVetoException;

    boolean isShaded();
    void setShaded(boolean b);

    boolean isMaximizable();

    boolean isIconifiable();

    boolean isClosable();
    void setClosed(boolean b) throws PropertyVetoException;

    boolean isResizable();

    String getTitle();

    Icon getFrameIcon();

    void dispatchEvent(AWTEvent event);

    public static class InternalFrameWindow implements Window {
	JInternalFrame frame;
	boolean shaded = false;
	public InternalFrameWindow(JInternalFrame frame) { this.frame = frame; }
	public Container getContainer() { return frame; }
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	    frame.addPropertyChangeListener(listener);
	}
	public boolean isSelected() { return frame.isSelected(); }
	public void setSelected(boolean b) throws PropertyVetoException { frame.setSelected(b); }
	public boolean isIcon() { return frame.isIcon(); }
	public void setIcon(boolean b) throws PropertyVetoException { frame.setIcon(b); }
	public boolean isMaximum() { return frame.isMaximum(); }
	public void setMaximum(boolean b) throws PropertyVetoException { frame.setMaximum(b); }
	public boolean isShaded() { return shaded; }
	public void setShaded(boolean b) {
	    if (b == shaded)
		return;

	    if (b == true) {
		Rectangle bounds = frame.getBounds();
		Rectangle p = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
		frame.putClientProperty(DefaultWindowManager.SHADE_BOUNDS_PROPERTY, p);
		frame.setBounds(p.x, p.y, p.width,
				frame.getMinimumSize().height);
	    } else {
		Point location = frame.getLocation();
		Rectangle p = (Rectangle)frame.getClientProperty(DefaultWindowManager.SHADE_BOUNDS_PROPERTY);
		frame.getDesktopPane().getDesktopManager().setBoundsForFrame(frame, location.x, location.y,
				   p.width, p.height);
	    }
	    shaded = b;
	}
	public boolean isMaximizable() { return frame.isMaximizable(); }
	public boolean isIconifiable() { return frame.isIconifiable(); }
	public boolean isClosable() { return frame.isClosable(); }
	public void setClosed(boolean b) throws PropertyVetoException { frame.setClosed(b); }
	public boolean isResizable() { return frame.isResizable(); }
	public String getTitle() { return frame.getTitle(); }
	public Icon getFrameIcon() { return frame.getFrameIcon(); }
	public void dispatchEvent(AWTEvent event) { frame.dispatchEvent(event); }
    }
    
    public static class SkinWindowWindow implements Window {
	SkinWindow frame;
	public SkinWindowWindow(SkinWindow frame) { this.frame = frame; }
	public Container getContainer() { return frame; }
	public void addPropertyChangeListener(PropertyChangeListener listener) {
	    frame.addPropertyChangeListener(listener);
	}
	public boolean isSelected() { return frame.isSelected(); }
	public void setSelected(boolean b) throws PropertyVetoException { frame.setSelected(b); }
	public boolean isIcon() { return frame.isIcon(); }
	public void setIcon(boolean b) throws PropertyVetoException { frame.setIcon(b); }
	public boolean isMaximum() { return frame.isMaximum(); }
	public void setMaximum(boolean b) throws PropertyVetoException { frame.setMaximum(b); }
	public boolean isMaximizable() { return frame.isMaximizable(); }
	public boolean isShaded() { return frame.isShaded(); }
	public void setShaded(boolean b) { frame.setShaded(b); }
	public boolean isIconifiable() { return frame.isIconifiable(); }
	public boolean isClosable() { return frame.isClosable(); }
	public void setClosed(boolean b) throws PropertyVetoException { frame.setClosed(b); }
	public boolean isResizable() { return frame.isResizable(); }
	public String getTitle() { return frame.getTitle(); }
	public Icon getFrameIcon() { return frame.getFrameIcon(); }
	public void dispatchEvent(AWTEvent event) { frame.dispatchEvent(event); }
    }

}
