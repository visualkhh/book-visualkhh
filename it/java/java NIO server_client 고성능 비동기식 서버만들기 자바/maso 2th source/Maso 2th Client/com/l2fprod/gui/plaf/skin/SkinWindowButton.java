package com.l2fprod.gui.plaf.skin;

import javax.swing.*;

import com.l2fprod.gui.SkinWindow;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:02 $
 */
public class SkinWindowButton extends JButton {

    int align, action;
    int xcoord, ycoord;

    Icon noFocusIcon, noFocusRolloverIcon;
    
    Window window;

    public SkinWindowButton(int align, int action) {
	this(-1, -1, align, action);
    }

    public SkinWindowButton(int xcoord, int ycoord, int align, int action) {
	this.xcoord = xcoord;
	this.ycoord = ycoord;
	this.align = align;
	this.action = action;
	setBorderPainted(false);
	setFocusPainted(false);
	setOpaque(false);
	setIcon(getIcon());
	setRolloverIcon(getRolloverIcon());
    }

    public String getUIClassID() {
	return "WindowButtonUI";
    }

    public void setWindow(Window window) {
	this.window = window;
    }

    public int getXCoord() { return xcoord; }
    public int getYCoord() { return ycoord; }

    public int getAlign() { return align; }
    public int getWindowAction() { return action; }

    public Icon getIcon() {
	if (isSelected())
	    return super.getIcon();
	else
	    return noFocusIcon;
    }

    public Icon getRolloverIcon() {
    	if (isSelected())
	    return super.getRolloverIcon();
	else
	    return noFocusRolloverIcon;
    }

    public boolean isSelected() {
	if (window != null)
	    return window.isSelected();
	else
	    return true;
    }

    public void setNoFocusIcon(Icon icon) { noFocusIcon = icon; }
    public void setNoFocusRolloverIcon(Icon icon) { noFocusRolloverIcon = icon; }

}



