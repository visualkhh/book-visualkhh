package com.l2fprod.gui.plaf.skin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.InternalFrameEvent;
import java.util.EventListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.beans.PropertyVetoException;

import com.l2fprod.gui.SkinWindow;

public class SkinTitlePane extends JComponent {

    public final static int ICON_OFFSET = 16;

    public final static int ALIGN_TOP_LEFT  = 0; // Align button relative to top left of window 
    public final static int ALIGN_TOP_RIGHT = 1; // Align button relative to the top right of window 

    public final static int CLOSE_ACTION = 0;

    public final static int MAXIMIZE_ACTION = 22;
    public final static int MINIMIZE_ACTION = 23;

    public final static int NO_ACTION = -1;

    private Skin skin = SkinLookAndFeel.getSkin();

    protected JMenuBar menuBar;

    protected JMenu windowMenu;
    protected Window frame;

    protected Color selectedTitleColor;
    protected Color selectedTextColor;
    protected Color notSelectedTitleColor;
    protected Color notSelectedTextColor;

    protected PropertyChangeListener propertyChangeListener;

    protected Action closeAction;
    protected Action maximizeAction;
    protected Action iconifyAction;
    protected Action restoreAction;
    protected Action moveAction;
    protected Action sizeAction;
    protected Action shadeAction;

    protected static final String CLOSE_CMD = "Close";
    protected static final String ICONIFY_CMD = "Minimize";
    protected static final String RESTORE_CMD = "Restore";
    protected static final String MAXIMIZE_CMD = "Maximize";
    protected static final String MOVE_CMD = "Move";  
    protected static final String SIZE_CMD = "Size";  
    protected static final String SHADE_CMD = "Shade";  

    public SkinTitlePane(SkinWindow f) {
	this(new Window.SkinWindowWindow(f));
    }

    public SkinTitlePane(JInternalFrame f) {
	this(new Window.InternalFrameWindow(f));
    }

    public SkinTitlePane(Window f) {
	frame = f;
	installTitlePane();
    }

    protected void installTitlePane() {
	installDefaults();
	installListeners();
	
	createActions();
	enableActions();
	
	setLayout(createLayout());
	
	assembleSystemMenu();

	add(menuBar);

	createButtons();

	setOpaque(true);
    }

    protected void createActions() {
	maximizeAction = new MaximizeAction();
	iconifyAction = new IconifyAction();
	closeAction = new CloseAction();
	restoreAction = new RestoreAction();
	moveAction = new MoveAction();
	sizeAction = new SizeAction();
	shadeAction = new ShadeAction();
    }

    protected void installListeners() {
	propertyChangeListener = createPropertyChangeListener();
	frame.addPropertyChangeListener(propertyChangeListener);
    }

    protected void installDefaults() {
	selectedTitleColor = UIManager.getColor("InternalFrame.activeTitleBackground");
	selectedTextColor = UIManager.getColor("InternalFrame.activeTitleForeground");
	notSelectedTitleColor = UIManager.getColor("InternalFrame.inactiveTitleBackground");
	notSelectedTextColor = UIManager.getColor("InternalFrame.inactiveTitleForeground");
    }


    protected void uninstallDefaults() {
    }


    public void addNotify() {
	super.addNotify();
	addSystemMenuItems(windowMenu);
	enableActions();
    }

    public void removeNotify() {
	super.removeNotify();
	if (windowMenu!=null) {
	    windowMenu.removeAll();
	    systemMenuAdded = false;
	}
	uninstallDefaults();
    }

    protected void createButtons() {
	SkinWindowButton[] buttons =
	    skin.getFrame().getWindowButtons(ALIGN_TOP_LEFT);
	if (buttons != null)
	    for (int i = 0, c = buttons.length; i < c; i++) {
		addButton(buttons[i]);
	    }
	
	buttons =
	    skin.getFrame().getWindowButtons(ALIGN_TOP_RIGHT);
	if (buttons != null)
	    for (int i = 0, c = buttons.length; i < c; i++) {
		addButton(buttons[i]);
	    }
    }

    protected void addButton(SkinWindowButton button) {
	button.setWindow(frame);
	switch (button.getWindowAction()) {
	case CLOSE_ACTION:
	    button.addActionListener(closeAction);
	    registerButtonForAction(button, closeAction);
	    break;
	case MAXIMIZE_ACTION:
	    button.addActionListener(maximizeAction);
	    registerButtonForAction(button, maximizeAction);
	    break;
	case MINIMIZE_ACTION:
	    button.addActionListener(iconifyAction);
	    registerButtonForAction(button, iconifyAction);
	    break;
	}
	add(button);
    }

    protected void setButtonIcons() {
    }


    protected void assembleSystemMenu() {
        menuBar = createSystemMenuBar();
	windowMenu = createSystemMenu();	    
	menuBar.add(windowMenu);
	// moved to addNotify - addSystemMenuItems(windowMenu);
	enableActions();
    }

    boolean systemMenuAdded = false;
    protected void addSystemMenuItems(JMenu systemMenu) {
	if (!systemMenuAdded) {
	    JMenuItem mi = (JMenuItem)systemMenu.add(restoreAction);
	    mi.setMnemonic('R');
	    mi = (JMenuItem)systemMenu.add(moveAction);
	    mi.setMnemonic('M');
	    mi = (JMenuItem)systemMenu.add(sizeAction);
	    mi.setMnemonic('S');
	    mi = (JMenuItem)systemMenu.add(iconifyAction);
	    mi.setMnemonic('n');
	    mi = (JMenuItem)systemMenu.add(maximizeAction);
	    mi.setMnemonic('x');
	    systemMenu.add(shadeAction);	
	    systemMenu.add(new JSeparator());
	    mi = (JMenuItem)systemMenu.add(closeAction);
	    mi.setMnemonic('C');
	    systemMenuAdded = true;
	}
    }

    protected JMenu createSystemMenu() {
	return new JMenu("    ");
    }

    protected JMenuBar createSystemMenuBar() {
	menuBar = new SystemMenuBar();
	menuBar.setBorderPainted(false);
	return menuBar;
    }
      
    protected void showSystemMenu(){
	//      windowMenu.setPopupMenuVisible(true);
      //      windowMenu.setVisible(true);
      windowMenu.doClick();
    }

    public void paintComponent(Graphics g)  {
	boolean isSelected = frame.isSelected();

	Font f = g.getFont();
	
	if (frame.getTitle() != null) {
	    if(isSelected)
		g.setColor(selectedTextColor);
	    else
		g.setColor(notSelectedTextColor);
	    g.setFont(UIManager.getFont("InternalFrame.titleFont"));
	}
	
	skin.getFrame().paintTop(g, this, isSelected, frame.getTitle());
	
	g.setFont(f);
	
    }

    /**
     * Post a WINDOW_CLOSING-like event to the frame, so that it can
     * be treated like a regular Frame.
     */
    protected void postClosingEvent(JInternalFrame frame) {
        InternalFrameEvent e = new InternalFrameEvent(
            frame, InternalFrameEvent.INTERNAL_FRAME_CLOSING);
        // Try posting event, unless there's a SecurityManager.
        if (JInternalFrame.class.getClassLoader() == null) {
            try {
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(e);
                return;
            } catch (SecurityException se) {
                // Use dispatchEvent instead.
            }
        }
        frame.dispatchEvent(e);
    }


    protected void enableActions() {
        restoreAction.setEnabled(frame.isMaximum() || frame.isIcon()); 
	maximizeAction.setEnabled(frame.isMaximizable()); // && !frame.isMaximum() ); 
	iconifyAction.setEnabled(frame.isIconifiable() && !frame.isIcon()); 
	closeAction.setEnabled(frame.isClosable());
	sizeAction.setEnabled(false);
	moveAction.setEnabled(false);
	shadeAction.setEnabled(!frame.isMaximum() && !frame.isIcon());
    }


    protected PropertyChangeListener createPropertyChangeListener() {
        return new PropertyChangeHandler();
    }

    protected LayoutManager createLayout() {
        return new TitlePaneLayout();
    }

    public Dimension getPreferredSize() {
	return skin.getFrame().getTopPreferredSize();
    }

    public class PropertyChangeHandler implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent evt) {

	    String prop = (String)evt.getPropertyName();

	    if(JInternalFrame.IS_SELECTED_PROPERTY.equals(prop)) {
	        repaint();
		return;
	    } 

	    /*if(JInternalFrame.IS_ICON_PROPERTY.equals(prop) ||
	       JInternalFrame.IS_MAXIMUM_PROPERTY.equals(prop)) {
		setButtonIcons();

		return;
		} */
	    enableActions();
	}


    }

    public class TitlePaneLayout implements LayoutManager {
        public void addLayoutComponent(String name, Component c) {}
        public void removeLayoutComponent(Component c) {}    
        public Dimension preferredLayoutSize(Container c)  {
	    return new Dimension(100, 18);
	}
    
        public Dimension minimumLayoutSize(Container c) {
	    return preferredLayoutSize(c);
	}
    
        public void layoutContainer(Container c) {
	    int w = getWidth();
	    int nmembers = c.getComponentCount();
	    int atlX = 0, atlY = 0;
	    int atrX = 0, atrY = 0;

	    menuBar.setBounds(atlX, (getHeight() - 16)/2, 16, 16);
	    atlX += 18;

	    for (int i = 1 /* skip menubar */; i < nmembers; i++) {
		SkinWindowButton m = (SkinWindowButton)c.getComponent(i);
		if (m.isEnabled()) {
		    if (m.getAlign() == ALIGN_TOP_LEFT) {
			if (m.getXCoord() == -1) {
			    m.setLocation(atlX, Math.max(m.getYCoord(), 1));
			    atlX += m.getWidth();
			} else {
			    m.setLocation(m.getXCoord(), m.getYCoord());
			}
		    } else if (m.getAlign() == ALIGN_TOP_RIGHT) {
			if (m.getXCoord() == -1) {
			    m.setLocation(w - atrX - m.getWidth(), Math.max(m.getYCoord(), 1));
			    atrX += m.getWidth();
			} else {
			    m.setLocation(w - m.getXCoord(), m.getYCoord());
			}
		    }
		}
	    }
	}
    } // end TitlePaneLayout

    private void registerButtonForAction(AbstractButton b, Action a) {
        PropertyChangeListener actionPropertyChangeListener = 
            createActionChangeListener(b);
        a.addPropertyChangeListener(actionPropertyChangeListener);
    }

    protected PropertyChangeListener createActionChangeListener(AbstractButton b) {
        return new ActionChangedListener(b);
    }

    private class ActionChangedListener implements PropertyChangeListener {
        AbstractButton button;
        
        ActionChangedListener(AbstractButton b) {
            super();
            setTarget(b);
        }
        public void propertyChange(PropertyChangeEvent e) {
            String propertyName = e.getPropertyName();
            if (e.getPropertyName().equals(Action.NAME)) {
                String text = (String) e.getNewValue();
                button.setText(text);
		button.repaint();
            } else if (propertyName.equals("enabled")) {
                Boolean enabledState = (Boolean) e.getNewValue();
                button.setEnabled(enabledState.booleanValue());
		button.repaint();
            } else if (e.getPropertyName().equals(Action.SMALL_ICON)) {
                Icon icon = (Icon) e.getNewValue();
                button.setIcon(icon);
                button.invalidate();
		button.repaint();
            } 
        }
	public void setTarget(AbstractButton b) {
	    this.button = b;
	}
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */  
    public class CloseAction extends AbstractAction {
        public CloseAction() {
	    super(CLOSE_CMD);
        }

        public void actionPerformed(ActionEvent e) {
	    if(frame.isClosable()) {
	      try {
		frame.setClosed(true);
	      } catch (PropertyVetoException e0) { }
	    }
	}      
    } // end CloseAction

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */
    public class MaximizeAction extends AbstractAction {
        public MaximizeAction() {
	    super(MAXIMIZE_CMD);
        }

        public void actionPerformed(ActionEvent e) {
	    if(frame.isMaximizable()) {
	        if(!frame.isMaximum()) {
		    try { frame.setMaximum(true); } catch (PropertyVetoException e5) { }
		} else {
		    try { 
		        frame.setMaximum(false); 
			if (frame.isIconifiable() && frame.isIcon()) {
			    frame.setIcon(false); 
			}
		    } catch (PropertyVetoException e6) { }
		}
	    }
	}      
    } // MaximizeAction

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */
    public class IconifyAction extends AbstractAction {
        public IconifyAction() {
	    super(ICONIFY_CMD);
        }

        public void actionPerformed(ActionEvent e) {
	    if(frame.isIconifiable()) {
	        if(!frame.isIcon())
		    try { frame.setIcon(true); } catch (PropertyVetoException e1) { }
		else {
		    try { 
		        frame.setIcon(false); 
			if (frame.isMaximizable() && frame.isMaximum()) {
			    frame.setMaximum(false);
			}
		    } catch (PropertyVetoException e1) { }
		}
	    }
	}      
    } // end IconifyAction

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */
    public class RestoreAction extends AbstractAction {
        public RestoreAction() {
	    super(RESTORE_CMD);
        }

        public void actionPerformed(ActionEvent e) {
	    if(frame.isMaximizable() && frame.isMaximum()) {
	        try { frame.setMaximum(false); } catch (PropertyVetoException e4) { }
	    } 
	    else if ( frame.isIconifiable() && frame.isIcon() ) {
		    try { frame.setIcon(false); } catch (PropertyVetoException e4) { }
	    }
	}      
    } // end RestoreAction
    
    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */
    public class MoveAction extends AbstractAction {
        public MoveAction() {
	    super(MOVE_CMD);
        }

        public void actionPerformed(ActionEvent e) {
	    // This action is currently undefined
	}      
    } // end MoveAction

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */
    public class SizeAction extends AbstractAction {
        public SizeAction() {
	    super(SIZE_CMD);
        }

        public void actionPerformed(ActionEvent e) {
	    // This action is currently undefined
	}      
    } // end SizeAction


    public class ShadeAction extends AbstractAction {
	public ShadeAction() {
	    super(SHADE_CMD);
	}
	public void actionPerformed(ActionEvent event) {
	    frame.setShaded(!frame.isShaded());
	}
    }

    /**
     * This inner class is marked &quot;public&quot; due to a compiler bug.
     * This class should be treated as a &quot;protected&quot; inner class.
     * Instantiate it only within subclasses of <Foo>.
     */
    public class SystemMenuBar extends JMenuBar {
	public boolean isFocusTraversable() { return false; }
	public void requestFocus() {}
	public void paint(Graphics g) {
	    Icon icon = frame.getFrameIcon();
	    if (icon == null) {
	      icon = UIManager.getIcon("InternalFrame.icon");
	    }
	    if (icon != null) {
	        // Resize to 16x16 if necessary.
	        if (icon instanceof ImageIcon && (icon.getIconWidth() > 16 || icon.getIconHeight() > 16)) {
		    Image img = ((ImageIcon)icon).getImage();
		    ((ImageIcon)icon).setImage(img.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		}
		icon.paintIcon(this, g, 0, 0);
	    }
	}

	public boolean isOpaque() { 
	    return true; 
	}
    } // end SystemMenuBar


    private class NoFocusButton extends JButton {
      public NoFocusButton() { setFocusPainted(false); }
	public boolean isFocusTraversable() { return false; }
	public void requestFocus() {};
        public boolean isOpaque() { return true; }
    };  // end NoFocusButton

}   // End Title Pane Class

