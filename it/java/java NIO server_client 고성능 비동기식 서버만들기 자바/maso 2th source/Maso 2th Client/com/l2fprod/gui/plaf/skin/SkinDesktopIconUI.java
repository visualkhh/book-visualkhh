package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.beans.*;
import java.util.EventListener;
import java.io.Serializable;

public class SkinDesktopIconUI extends BasicDesktopIconUI {

    JLabel iconPane;
    MouseInputListener mouseInputListener;
    protected PropertyChangeListener propertyChangeListener;

    public static ComponentUI createUI(JComponent c)    {
        return new SkinDesktopIconUI();
    }

    protected void installComponents() {
	frame = desktopIcon.getInternalFrame();
	desktopIcon.setBorder(null);
	iconPane = new JLabel(frame.getTitle(),
			      frame.getFrameIcon(),
			      JLabel.CENTER);
	iconPane.setHorizontalTextPosition(JLabel.CENTER);
	iconPane.setVerticalTextPosition(JLabel.BOTTOM);
	iconPane.setHorizontalAlignment(JLabel.CENTER);

	desktopIcon.setLayout(new BorderLayout());
	desktopIcon.add(iconPane, BorderLayout.CENTER);
	desktopIcon.setOpaque(true);
    }

    protected void installListeners() {
	mouseInputListener = createMouseInputListener();
	iconPane.addMouseMotionListener(mouseInputListener);
	iconPane.addMouseListener(mouseInputListener);
        if( propertyChangeListener == null ) {
            propertyChangeListener = createPropertyChangeListener();
        }
	desktopIcon.getInternalFrame().addPropertyChangeListener(propertyChangeListener);
    }

    protected void uninstallListeners() {
	super.uninstallListeners();
	desktopIcon.getInternalFrame().removePropertyChangeListener(propertyChangeListener);
    }

    protected PropertyChangeListener createPropertyChangeListener() {
        return new PropertyChangeHandler();
    }

    protected void uninstallComponents() {
	desktopIcon.setLayout(null);
	desktopIcon.remove(iconPane);
    }

    public Dimension getMinimumSize(JComponent c) {
	return iconPane.getMinimumSize();
    } 

    public Dimension getMaximumSize(JComponent c){
	return iconPane.getMaximumSize();
    }

    public Dimension getPreferredSize(JComponent c) {
	return iconPane.getPreferredSize();
    }

    public class PropertyChangeHandler implements PropertyChangeListener {
	public void propertyChange(PropertyChangeEvent evt) {
	    String prop = (String)evt.getPropertyName();
	    if (JInternalFrame.TITLE_PROPERTY.equals(prop)) {
		// PENDING(fred): we need to trim the title or define a maximum size
		// or display it on multiple lines ?
		iconPane.setText((String)evt.getNewValue());
	    } else if (JInternalFrame.FRAME_ICON_PROPERTY.equals(prop)) {
		iconPane.setIcon((Icon)evt.getNewValue());
	    }
	}
    }

}


