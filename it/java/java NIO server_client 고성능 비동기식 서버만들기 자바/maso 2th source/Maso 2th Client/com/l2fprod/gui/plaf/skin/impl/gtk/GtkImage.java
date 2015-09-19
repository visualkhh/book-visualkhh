package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Image;
import java.net.URL;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

import com.l2fprod.gui.plaf.skin.SkinUtils;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class GtkImage extends GtkProps implements TreeNode {

    GtkStyle style;
    Image preloaded;

    public GtkImage() {
    }

    public void setPreview(Image image) {
	preloaded = image;
    }

    public Image getPreview(int w, int h) throws Exception {
	if (preloaded == null) {
	    preloaded = getImage(style.getParser().getDirectory());
	    if (preloaded != null)
		preloaded = preloaded.getScaledInstance(w, h, 0);
	}
	return preloaded;
    }

    public String getFilename() {
	String filename = (String)getProperty("file");
	if (filename != null)
	    return filename;
	filename = (String)getProperty("overlay_file");
	return filename;
    }

    public Image getImage(URL skinDirectory) throws Exception {
	Image im = null;
	im = getImage(skinDirectory, "file");
	if (im == null)
	    im = getImage(skinDirectory, "overlay_file");
	return im;
    }

    public Image getOverlayImage(URL skinDirectory) throws Exception {
	return getImage(skinDirectory, "overlay_file");	
    }

    public Image getImage(URL skinDirectory, String prop) throws Exception {
	String filename = (String)getProperty(prop);
	if (filename == null)
	    return null;

	return SkinUtils.loadImage(new URL(skinDirectory, filename));
    }

    public Enumeration children() {
	throw new Error("Not implemented");
    }

    public boolean getAllowsChildren() {
	return false;
    }

    public TreeNode getChildAt(int childIndex) {
	return null;
    }

    public int getChildCount() { return 0; }

    public int getIndex(TreeNode node) { return -1; }

    public TreeNode getParent() {
	return style;
    }

    public boolean isLeaf() {
	return false;
    }
    
    public String toString() {
	return getProperty("function") + "";
    }

}
