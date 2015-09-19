package com.l2fprod.gui.plaf.skin;

import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 
 * Created on 05/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:07 $
 */
public class SkinFileChooserUI extends MetalFileChooserUI {
    
    public static ComponentUI createUI(JComponent c) {
        return new SkinFileChooserUI((JFileChooser) c);
    }

    public SkinFileChooserUI(JFileChooser filechooser) {
	super(filechooser);
    }

    public void installComponents(JFileChooser fc) {
	super.installComponents(fc);	
	// we need to traverse the component tree and replace all combo renderers with our own.
    }

    protected FilterComboBoxRenderer createFilterComboBoxRenderer() {
	return new MyFilterComboBoxRenderer();
    }

    /**
     * Render different type sizes and styles.
     */
    public class MyFilterComboBoxRenderer extends FilterComboBoxRenderer {
	public MyFilterComboBoxRenderer() {
	    super();
	    setOpaque(false);
	    setBorder(null);
	}
	public Component getListCellRendererComponent(JList list,
						      Object value, int index, boolean isSelected,
						      boolean cellHasFocus) {	    
	    if (index == -1)
		setOpaque(false);
	    else
		setOpaque(true);

	    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	    
	    FileFilter filter = (FileFilter) value;
	    if(filter != null) {
		setText(filter.getDescription());
	    }

	    return this;
	}
    }

    protected MyDirectoryComboBoxRenderer createMyDirectoryComboBoxRenderer(JFileChooser fc) {
	return new MyDirectoryComboBoxRenderer();
    }

    class MyDirectoryComboBoxRenderer extends DefaultListCellRenderer {
	IndentIcon ii = new IndentIcon();
	public Component getListCellRendererComponent(JList list, Object value,
						      int index, boolean isSelected,
						      boolean cellHasFocus) {

	    if (index == -1)
		setOpaque(false);
	    else
		setOpaque(true);

	    super.getListCellRendererComponent(list, value, index,
					       isSelected, cellHasFocus);
	    File directory = (File) value;
	    if(directory == null) {
		setText("");
		return this;
	    }

	    String fileName = getFileChooser().getName(directory);
            setText(fileName);

	    // Find the depth of the directory
	    int depth = 0;
	    if(index != -1) {
		File f = directory;
		while(f.getParent() != null) {
		    depth++;
                    f = getFileChooser().getFileSystemView().createFileObject(
                        f.getParent()
                    );
		}
	    }
	    
	    Icon icon = getFileChooser().getIcon(directory);

	    ii.icon = icon;
	    ii.depth = depth;
	    
	    setIcon(ii);

	    return this;
	}
    }

    final static int space = 10;
    class IndentIcon implements Icon {

	Icon icon = null;
	int depth = 0;

	public void paintIcon(Component c, Graphics g, int x, int y) {
	    icon.paintIcon(c, g, x+depth*space, y);
	}

	public int getIconWidth() {
	    return icon.getIconWidth() + depth*space;
	}

	public int getIconHeight() {
	    return icon.getIconHeight();
	}

    }

}
