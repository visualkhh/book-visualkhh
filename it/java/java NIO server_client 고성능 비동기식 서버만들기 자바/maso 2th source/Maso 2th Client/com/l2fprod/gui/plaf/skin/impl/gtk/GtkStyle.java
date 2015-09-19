package com.l2fprod.gui.plaf.skin.impl.gtk;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import javax.swing.tree.MutableTreeNode;

import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:22 $
 */
public class GtkStyle extends GtkProps implements MutableTreeNode {

    public String name;

    public GtkEngine engine;

    public MutableTreeNode parent;

    public GtkParser parser;

    public GtkStyle() {
    }

    public GtkEngine getEngine() {
	return engine;
    }

    public GtkParser getParser() {
	return parser;
    }

    public Enumeration children() {
	throw new Error("Not implemented");
    }

    public boolean getAllowsChildren() {
	return true;
    }

    public TreeNode getChildAt(int childIndex) {
	return (TreeNode)engine.getImages().elementAt(childIndex);
    }

    public int getChildCount() {
	if (engine == null)
	    return 0;
	else
	    return engine.getImages().size();
    }

    public int getIndex(TreeNode node) {
	return engine.getImages().indexOf(node);
    }

    public TreeNode getParent() {
	return parent;
    }

    public boolean isLeaf() {
	return false;
    }
    
    public String toString() {
	return name;
    }

    public void insert(MutableTreeNode child, int index) {}

    public void remove(int index) {}

    public void remove(MutableTreeNode node) {}

    public void removeFromParent() {}

    public void setParent(MutableTreeNode newParent) {
	this.parent = newParent;
    }

    public void setUserObject(Object object) {}

}
