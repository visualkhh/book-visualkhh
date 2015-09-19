package com.l2fprod.gui.plaf.skin.impl.gtk;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:19 $
 */
public class GtkClass {

    public String name;
    public String stylename;
    public GtkStyle style;

    public GtkClass() {
    }

    public void setName(String s) {
	name = s;
    }

    public void setStyleName(String s) {
	stylename = s;
    }

    public String getStyleName() {
	return stylename;
    }

    public GtkStyle getStyle() {
	return style;
    }
    
    public void setStyle(GtkStyle style) {
	this.style = style;
    }

}
