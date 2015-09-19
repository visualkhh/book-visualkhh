package com.l2fprod.gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
//import javax.swing.*;
//import javax.swing.event.*;
//import javax.swing.plaf.*;
import javax.swing.Icon;
import javax.swing.WindowConstants;
import java.beans.*;
import java.util.Dictionary;
import java.util.Hashtable;

import com.l2fprod.gui.plaf.skin.SkinLookAndFeel;
import com.l2fprod.gui.plaf.skin.SkinWindowTitlePane;
import com.l2fprod.gui.event.*;

/**
 * Skin Window.
 * <BR>
 * By extending javax.swing.JWindow, SkinWindow looks like JInternalFrame when using Skin Look And Feel.
 * <BR><BR>
 * <BR><BR>
 * Created on 15/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class SkinWindow extends Window implements WindowConstants {

    /*
    static {
	// PENDING(fred): this is not the best way to display it
	// move the code in show() ??? (ensureWindowListVisible())
	// the window list should always be visible
	SkinWindowList.getSkinWindowList().setVisible(true);
	try { SkinWindowList.getSkinWindowList().setSelected(true);
	} catch (PropertyVetoException e) {}
    }
    */

    public static final int     NORMAL          = 0;
    public static final int     ICONIFIED       = 1;

    private int defaultCloseOperation = HIDE_ON_CLOSE;

    boolean isSelected;
    boolean isMaximum;
    boolean isIcon;
    boolean maximizable;
    boolean iconifiable;
    boolean closable;
    boolean resizable;
    boolean isClosed;
    boolean shaded;

    //JRootPane rootPane;
    Panel rootPane;
    Component northPane;

    String title;

    Icon frameIcon;

    private Hashtable clientProperties;
    private VetoableChangeSupport vetoableChangeSupport;
    private java.beans.PropertyChangeSupport changeSupport;

    SkinDesktopIcon desktopIcon;

    transient boolean isDragging = false;

    transient SkinWindowListener skinwindowListener;

    public SkinWindow() {
	this("");
    }

    public SkinWindow(String title) {
	this(new Frame(), title);
    }

    public SkinWindow(Frame owner, String title) {
        super(owner);
	this.title = title;
    }

    protected void windowInit() {
//	super.windowInit();

	isSelected = false;

	isMaximum = false;

	maximizable = true;
	iconifiable = true;
	closable = true;
	isClosed = false;

	resizable = true;

	shaded = false;

	//Container realContentPane = super.getContentPane();
	//realContentPane.setLayout(new BorderLayout());
	//realContentPane.add("Center", rootPane = new JRootPane());
	//realContentPane.add("North", northPane = createNorthPanel());
	
	setLayout(new BorderLayout());
	add("Center", rootPane = new Panel());
	add("North", northPane = createNorthPanel());
	
	// set a default border
	//LookAndFeel.installBorder((JComponent)realContentPane, "InternalFrame.border");

	//SkinLookAndFeel.getSkin().getFrame().installSkin((JComponent)realContentPane);

	desktopIcon = new SkinDesktopIcon(this);

	if (this instanceof SkinWindowList == false) {
	    SkinWindowList.getSkinWindowList().registerWindow(this);
	}

	putClientProperty("Window.dragMode", "faster");
    }

    protected void finalize() throws Throwable {
	// is this really good ?
	// windowlist retains an instance of window
	// so "this" can't be garbage collected ?
	// finalize is never called ?
	SkinWindowList.getSkinWindowList().unregisterWindow(this);
	super.finalize();
    }

    public SkinDesktopIcon getDesktopIcon() {
	return desktopIcon;
    }

    public Container getContentPane() {
	//return rootPane.getContentPane();
	return null;
    }

    public Insets getBorderInsets() {
	//return ((JComponent)super.getContentPane()).getBorder().getBorderInsets(super.getContentPane());
	return null;
    }

    protected Container createContentPane() {
	Panel panel = new Panel();
	/* this code could be used to "optimize" the window moving/resizing
	  {
		public void paint(Graphics g) {
		    //		protected void paintChildren(Graphics g) {
		    if (SkinWindow.this.isDragging == false) {
			super.paint(g);
		    } else {
			System.out.println("not painting children");
		    }
		}
	    };
	*/
	panel.setLayout(new BorderLayout());
	return panel;
    }

    protected Component createNorthPanel() {
	return new SkinWindowTitlePane(this);
    }

   /**
    * Sets the menubar for this frame.
    * @param menubar the menubar being placed in the frame
    *
    * @see #getJMenuBar
    *
    * @beaninfo
    *      hidden: true
    * description: The menubar for accessing pulldown menus from this frame.
    */
    public void setJMenuBar(MenuBar menubar) {
        //rootPane.setMenuBar(menubar);
    }

   /**
    * Returns the menubar set on this frame.
    *
    * @see #setJMenuBar
    */
    public MenuBar getMenuBar() { 
        //return rootPane.getMenuBar(); 
        return null;
    }

    /**                   
     * Sets the operation which will happen by default when
     * the user initiates a "close" on this frame.
     * The possible choices are defined in the <code>WindowConstants</code>
     * interface:
     * <p>
     * <ul>
     * <li>DO_NOTHING_ON_CLOSE - do not do anything - require the
     * program to handle the operation in the windowClosing
     * method of a registered WindowListener object.
     * <li>HIDE_ON_CLOSE - automatically hide the frame after
     * invoking any registered WindowListener objects
     * <li>DISPOSE_ON_CLOSE - automatically hide and dispose the 
     * frame after invoking any registered WindowListener objects
     * <li>EXIT_ON_CLOSE - Exit the application by way of System.exit.
     * Only use this in applications.
     * </ul>
     * <p>
     * The value is set to HIDE_ON_CLOSE by default.
     * @see #addWindowListener
     * @see #getDefaultCloseOperation
     *
     * @beaninfo
     *   preferred: true
     *        enum: DO_NOTHING_ON_CLOSE WindowConstants.DO_NOTHING_ON_CLOSE
     *              HIDE_ON_CLOSE       WindowConstants.HIDE_ON_CLOSE
     *              DISPOSE_ON_CLOSE    WindowConstants.DISPOSE_ON_CLOSE
     *              EXIT_ON_CLOSE       3
     * description: The frame's default close operation.
     */
    public void setDefaultCloseOperation(int operation) {
        this.defaultCloseOperation = operation;
    }

   /**
    * Returns the operation which occurs when the user
    * initiates a "close" on this frame.
    *
    * @return an int indicating the window-close operation
    * @see #setDefaultCloseOperation
    */
    public int getDefaultCloseOperation() {
        return defaultCloseOperation;
    }

    public void show() {

	toFront();

	if (!isVisible()) { super.show(); }

	if (isIcon())
	    return;

	if (!isSelected()) {
	    try {
		setSelected(true);
	    } catch (PropertyVetoException e) {}
	}
    }

    public boolean isSelected() {
	return isSelected;
    }

    public void setSelected(boolean b) throws PropertyVetoException {
        if ((isSelected == b) || (b && !isShowing())) {
            return;
        }

        Boolean oldValue = isSelected ? Boolean.TRUE : Boolean.FALSE;
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
        //fireVetoableChange(JInternalFrame.IS_SELECTED_PROPERTY, oldValue, newValue);
        isSelected = b;
//        firePropertyChange(JInternalFrame.IS_SELECTED_PROPERTY, oldValue, newValue);
	if (isSelected)
	    fireWindowEvent(WindowEvent.WINDOW_ACTIVATED);
	else
	    fireWindowEvent(WindowEvent.WINDOW_DEACTIVATED);
        repaint();
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
        String oldValue = this.title;
        this.title = title;
        //firePropertyChange(JInternalFrame.TITLE_PROPERTY, oldValue, title);
	/*	if ((getOwner() != null) && (getOwner() instanceof Frame))
		((Frame)getOwner()).setTitle(title);*/
    }

    public boolean isMaximum() {
	return isMaximum;
    }

    public void setMaximum(boolean b) throws PropertyVetoException {
        if (isMaximum == b) {
            return;
        }

        Boolean oldValue = isMaximum ? Boolean.TRUE : Boolean.FALSE;
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
//        fireVetoableChange(JInternalFrame.IS_MAXIMUM_PROPERTY, oldValue, newValue);
        isMaximum = b;
//        firePropertyChange(JInternalFrame.IS_MAXIMUM_PROPERTY, oldValue, newValue);
	if (b)
	    fireWindowEvent(new SkinWindowEvent(this, SkinWindowEvent.WINDOW_MAXIMIZE));
	else
	    fireWindowEvent(new SkinWindowEvent(this, SkinWindowEvent.WINDOW_UNMAXIMIZE));	    
    }

    public boolean isIcon() {
	return isIcon;
    }

    public void setIcon(boolean b) throws PropertyVetoException {
        if (isIcon == b) {
            return;
        }

	validate();
	
        Boolean oldValue = isIcon ? Boolean.TRUE : Boolean.FALSE; 
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
//        fireVetoableChange(JInternalFrame.IS_ICON_PROPERTY, oldValue, newValue);
        isIcon = b;
//        firePropertyChange(JInternalFrame.IS_ICON_PROPERTY, oldValue, newValue);
	if (b)
	    fireWindowEvent(WindowEvent.WINDOW_ICONIFIED);
	else
	    fireWindowEvent(WindowEvent.WINDOW_DEICONIFIED);
    }

    public boolean isMaximizable() {
	return maximizable;
    }

    public void setMaximizable(boolean b) throws PropertyVetoException {
        Boolean oldValue = maximizable ? Boolean.TRUE : Boolean.FALSE; 
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
        maximizable = b;
        firePropertyChange("maximizable", oldValue, newValue);
    }

    public boolean isShaded() {
	return shaded;
    }

    public void setShaded(boolean b) {
        Boolean oldValue = shaded ? Boolean.TRUE : Boolean.FALSE; 
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
        shaded = b;
        firePropertyChange(com.l2fprod.gui.plaf.skin.Window.IS_SHADED_PROPERTY, oldValue, newValue);
	if (b)
	    fireWindowEvent(new SkinWindowEvent(this, SkinWindowEvent.WINDOW_SHADE));
	else
	    fireWindowEvent(new SkinWindowEvent(this, SkinWindowEvent.WINDOW_UNSHADE));	    
    }

    public boolean isIconifiable() {
	return iconifiable;
    }
    
    public void setIconifiable(boolean b) {
        Boolean oldValue = iconifiable ? Boolean.TRUE : Boolean.FALSE; 
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
        iconifiable = b;
        firePropertyChange("iconifiable", oldValue, newValue);
    }
    
    public boolean isClosable() {
	return closable;
    }

    public void setClosable(boolean b) {
        Boolean oldValue = closable ? Boolean.TRUE : Boolean.FALSE; 
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
        closable = b;
        firePropertyChange("closable", oldValue, newValue);
    }

    public boolean isResizable() {
        return isMaximum ? false : resizable;
    }

    public void setResizable(boolean b) {
	Boolean oldValue = resizable ? Boolean.TRUE : Boolean.FALSE; 
	Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
        resizable = b;
	firePropertyChange("resizable", oldValue, newValue);
    }

    public void setClosed(boolean b) throws PropertyVetoException {
        if (isClosed == b) {
            return;
        }

        Boolean oldValue = isClosed ? Boolean.TRUE : Boolean.FALSE; 
        Boolean newValue = b ? Boolean.TRUE : Boolean.FALSE;
	if (b) {
	    fireWindowEvent(WindowEvent.WINDOW_CLOSING);
	}
        //fireVetoableChange(JInternalFrame.IS_CLOSED_PROPERTY, oldValue, newValue);
        isClosed = b;
	//firePropertyChange(JInternalFrame.IS_CLOSED_PROPERTY, oldValue, newValue);
        if (isClosed) {
	  dispose();
        }
    }

    /**
     * Gets the image to be displayed in the minimized icon
     * for this frame.
     * @return    the icon image for this frame, or <code>null</code> 
     *                    if this frame doesn't have an icon image.
     */
    public Icon getFrameIcon() {
	return frameIcon;
    }

    /**
     * Sets the image to displayed in the minimized icon for this frame. 
     * @param     image the icon image to be displayed.
     */
    public void setFrameIcon(Icon icon) {
        Icon oldIcon = frameIcon;
        frameIcon = icon;
//        firePropertyChange(JInternalFrame.FRAME_ICON_PROPERTY, oldIcon, icon);  
    }

    /**
     * Sets the state of this frame.
     * @param  state <code>SkinWindow.ICONIFIED</code> if this frame is in 
     *           iconic state; <code>SkinWindow.NORMAL</code> if this frame is 
     *           in normal state.
     */
    public void setState(int state) {
	switch (state) {
	case SkinWindow.NORMAL:
	    try { setIcon(false); } catch (PropertyVetoException e) {}
	    break;
	case SkinWindow.ICONIFIED:
	    try { setIcon(true); } catch (PropertyVetoException e) {}
	    break;
	}
    }

    /**
     * Gets the state of this frame.
     * @return   <code>Frame.ICONIFIED</code> if frame in iconic state;
     *           <code>Frame.NORMAL</code> if frame is in normal state.
     */
    public int getState() {
	if (isIcon())
	    return SkinWindow.ICONIFIED;
	else
	    return SkinWindow.NORMAL;
    }

    /**
     * @return a small Hashtable
     * @see #putClientProperty
     * @see #getClientProperty
     */
    private Dictionary getClientProperties() {
        if (clientProperties == null) {
            clientProperties = new Hashtable(2);
        }
        return clientProperties;
    }


    /**
     * Returns the value of the property with the specified key.  Only
     * properties added with <code>putClientProperty</code> will return
     * a non-null value.  
     * 
     * @return the value of this property or null
     * @see #putClientProperty
     */
    public final Object getClientProperty(Object key) {
         if(clientProperties == null) {
 	    return null;
 	}
 	else {
 	    return getClientProperties().get(key);
	}
    }


    /**
     * Add an arbitrary key/value "client property" to this component.
     * <p>
     * The <code>get/putClientProperty<code> methods provide access to 
     * a small per-instance hashtable. Callers can use get/putClientProperty
     * to annotate components that were created by another module, e.g. a 
     * layout manager might store per child constraints this way.  For example:
     * <pre>
     * componentA.putClientProperty("to the left of", componentB);
     * </pre>
     * <p>
     * If value is null this method will remove the property.
     * Changes to client properties are reported with PropertyChange
     * events.  The name of the property (for the sake of PropertyChange
     * events) is <code>key.toString()</code>.  
     * <p>
     * The clientProperty dictionary is not intended to support large 
     * scale extensions to JComponent nor should be it considered an 
     * alternative to subclassing when designing a new component.
     * 
     * @see #getClientProperty
     * @see #addPropertyChangeListener
     */
    public final void putClientProperty(Object key, Object value) {
        Object oldValue = getClientProperties().get(key);

        if (value != null) {
            getClientProperties().put(key, value);
        } else {
            getClientProperties().remove(key);
        }

        firePropertyChange(key.toString(), oldValue, value);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (changeSupport != null) {
            changeSupport.firePropertyChange(propertyName, oldValue, newValue);
        }
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport == null) {
//            changeSupport = new SwingPropertyChangeSupport(this);
        }
        changeSupport.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
        if (changeSupport != null) {
            changeSupport.removePropertyChangeListener(listener);
        }
    }

    protected void fireVetoableChange(String propertyName, Object oldValue, Object newValue)
        throws java.beans.PropertyVetoException
    {
        if (vetoableChangeSupport == null) {
            return;
        }
        vetoableChangeSupport.fireVetoableChange(propertyName, oldValue, newValue);
    }


    /**
     * Add a VetoableChangeListener to the listener list.
     * The listener is registered for all properties.
     * <p>
     * This method will migrate to java.awt.Component in the next major JDK release
     *
     * @param listener  The VetoableChangeListener to be added
     */
    public synchronized void addVetoableChangeListener(VetoableChangeListener listener) {
        if (vetoableChangeSupport == null) {
            vetoableChangeSupport = new java.beans.VetoableChangeSupport(this);
        }
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }


    /**
     * Remove a VetoableChangeListener from the listener list.
     * This removes a VetoableChangeListener that was registered
     * for all properties.
     * <p>
     * This method will migrate to java.awt.Component in the next major JDK release
     *
     * @param listener  The VetoableChangeListener to be removed
     */
    public synchronized void removeVetoableChangeListener(VetoableChangeListener listener) {
        if (vetoableChangeSupport == null) {
            return;
        }
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }

    protected synchronized void fireWindowEvent(int id) {
	fireWindowEvent(new WindowEvent(this, id));
    }

    protected synchronized void fireWindowEvent(AWTEvent event) {
	Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(event);
    }

    /** 
     * Processes window events occurring on this component.
     * Hides the window or disposes of it, as specified by the setting
     * of the <code>defaultCloseOperation</code> property.
     *
     * @param  e  the window event
     * @see    #setDefaultCloseOperation
     * @see    java.awt.Window#processWindowEvent
     */
    protected void processWindowEvent(WindowEvent e) {
	if (skinwindowListener != null) {
	    switch (e.getID()) {
	    case SkinWindowEvent.WINDOW_SHADE:
		skinwindowListener.windowShaded((SkinWindowEvent)e);
		break;
	    case SkinWindowEvent.WINDOW_UNSHADE:
		skinwindowListener.windowUnshaded((SkinWindowEvent)e);
		break;
	    case SkinWindowEvent.WINDOW_MAXIMIZE:
		skinwindowListener.windowMaximized((SkinWindowEvent)e);
		break;
	    case SkinWindowEvent.WINDOW_UNMAXIMIZE:
		skinwindowListener.windowUnmaximized((SkinWindowEvent)e);
		break;
	    default:
		super.processWindowEvent(e);
	    }
	}

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            switch(defaultCloseOperation) {
              case HIDE_ON_CLOSE:
                 setVisible(false);
                 break;
              case DISPOSE_ON_CLOSE:
                 setVisible(false);
                 dispose();
                 break;
              case DO_NOTHING_ON_CLOSE:
                 default: 
                 break;
	      case 3: // EXIT_ON_CLOSE:
		System.exit(0);
		break;
            }
        }
    }

    /**
     * Adds the specified window listener to receive window events from
     * this window.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param 	l the window listener
     */ 
    public synchronized void addWindowListener(SkinWindowListener l) {
	if (l == null) {
	    return;
	}
        skinwindowListener = SkinEventMulticaster.add(skinwindowListener, l);
	super.addWindowListener(l);
    }

    /**
     * Removes the specified window listener so that it no longer
     * receives window events from this window.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param 	l the window listener
     */ 
    public synchronized void removeWindowListener(SkinWindowListener l) {
	if (l == null) {
	    return;
	}
        skinwindowListener = SkinEventMulticaster.remove(skinwindowListener, l);
	super.removeWindowListener(l);
    }

    public class SkinDesktopIcon extends Component {
	SkinWindow window;
	public SkinDesktopIcon(SkinWindow window) {
	    this.window = window;
	}
	//	public String getUIClassID() { return "SkinDesktopIconUI"; }
	public SkinWindow getWindow() { return window; }
	public String toString() { return window.getTitle(); }
    }
}
