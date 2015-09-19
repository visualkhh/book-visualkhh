package com.l2fprod.gui;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;

import com.l2fprod.gui.sound.NoSoundComponent;

/**
 * Skin Window List.
 * <BR>
 * Skin Window List acts as Window pager.
 * <BR>
 * It maintains the list of existing windows. When a window is iconified, it can be deiconified by double-clicking its name in the list.
 * <BR><BR>
 * <BR><BR>
 * Created on 27/05/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:53 $
 */
public class SkinWindowList extends SkinWindow implements NoSoundComponent {

    static SkinWindowList shared;

    public static SkinWindowList getSkinWindowList() {
	if (shared == null)
	    shared = new SkinWindowList();
	return shared;
    }

    JList list;
    DefaultListModel model;

    private SkinWindowList() {
	// WindowList should be a property!
	super(UIManager.getString("WindowList.title"));

	getContentPane().add("Center", list = new JList(model = new DefaultListModel()));

	// nor closable nor iconifiable by the user
	setIconifiable(false);
	setClosable(false);

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent event) {
		    if (event.getClickCount() == 2) {
			int index = list.locationToIndex(event.getPoint());
			if (index != -1)
			    try {
				((SkinWindow.SkinDesktopIcon)model.get(index)).getWindow().setIcon(false);
				((SkinWindow.SkinDesktopIcon)model.get(index)).getWindow().setSelected(true);
			    } catch (PropertyVetoException e) {}
		    }
		}
	    };
	list.addMouseListener(mouseListener);

	setSize(200, 200);
    }

    public void registerWindow(SkinWindow w) {
	model.addElement(w.getDesktopIcon());
    }

    public void unregisterWindow(SkinWindow w) {
	model.removeElement(w.getDesktopIcon());
    }

    public void activateNextWindow(SkinWindow w) {
	int index = model.indexOf(w);
	SkinWindow nextWindow = null;
	if (index > 0) { // !=-1 and !=0
	    // next is
	    index = (index+1<model.size())?index+1:0;
	    nextWindow = (SkinWindow)model.get(index);
	} else {
	    nextWindow = this;
	}
	try { nextWindow.setSelected(true); } catch (PropertyVetoException e) {}
    }

}
    
