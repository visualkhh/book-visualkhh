package com.l2fprod.gui.plaf.skin.impl.gtk;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class GtkProps {

    java.util.Hashtable properties;
    java.util.Vector keys;

    public GtkProps() {
	properties = new java.util.Hashtable();
	keys = new java.util.Vector();
    }

    public void setProperty(String key, Object value) {
	properties.put(key, value);
	if (!keys.contains(key))
	    keys.addElement(key);
    }

    public Object getProperty(Object key) {
	return properties.get(key);
    }

    public java.util.Hashtable getProperties() {
	return properties;
    }

    public java.util.Vector getKeys() {
	return keys;
    }

}
