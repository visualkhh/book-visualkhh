package com.l2fprod.gui.plaf.skin.impl.kde;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Component;

import java.net.URL;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JComponent;

import com.l2fprod.util.IniFile;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:18 $
 */
class KdePersonality extends AbstractSkinPersonality {

    Image wallpaper;

    public KdePersonality(IniFile ini, URL skinURL) throws Exception {
	String path = ini.getKeyValue("Display", "Wallpaper0");
	if (path != null) {
	    try {
		wallpaper = SkinUtils.loadImage(new URL(skinURL, path));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    public boolean installSkin(JComponent c) {
	return false;
    }

    public boolean paintBackground(Graphics g, Component c) {
	if (wallpaper != null) {
	    g.drawImage(wallpaper, 0, 0, ((JComponent)c).getWidth(), ((JComponent)c).getHeight(), c);
	    return true;
	} else {
	    return false;
	}
    }

}

