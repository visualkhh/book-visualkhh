package com.l2fprod.gui.plaf.skin.impl.gtk;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:58 $
 */
public class GtkBorder {
    
    public int top, right, bottom, left;

    public GtkBorder(int top, int right, int bottom, int left) {
	this.top = top;
	this.right = right;
	this.bottom = bottom;
	this.left = left;
    }

    public GtkBorder() {
    }

    public String toString() {
	return "{ " + top +", " + right + ", " + bottom + ", " + left + " }";
    }

}
