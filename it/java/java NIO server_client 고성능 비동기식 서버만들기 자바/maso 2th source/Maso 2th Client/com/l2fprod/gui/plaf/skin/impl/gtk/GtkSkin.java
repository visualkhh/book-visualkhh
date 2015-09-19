package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;
import com.l2fprod.gui.plaf.skin.impl.AbstractSkin;

/**
 * GTK (The Gimp Toolkit) Skin Support.
 * <BR>
 * GtkSkin can be used in conjunction with a gtkrc file.<BR>
 * You can find skins at:
 * <LI><A HREF="http://gtk.themes.org">gtk.themes.org</A>
 * <BR><BR>
 * Simply extract the skin file in a directory and use:<BR><BR>
 * <B>
 * <CODE>
 * SkinLookAndFeel.setSkin(new GtkSkin("c:\downloads\myskin\gtk\gtkrc"));<BR>
 * UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
 * </CODE>
 * </B>
 * <BR><BR>to enable skins in your application !
 * <BR><BR>
 * <BR><BR>
 * Created on 28/01/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class GtkSkin extends AbstractSkin {

    String[] colors;

    static String[] swingToGtk = {
	"desktop", "",
	"activeCaption", "",
	"activeCaptionText", "", 
	"activeCaptionBorder", "",
	"inactiveCaption", "",
	"inactiveCaptionText", "",
	"inactiveCaptionBorder", "",
	"window", "window.bg[NORMAL]",
	"windowBorder", "window.bg[NORMAL]",
	"windowText", "window.fg[NORMAL]",
	"menu", "menu.bg[NORMAL]",
	"menuPressedItemB", "bg[ACTIVE]",
	"menuPressedItemF", "fg[ACTIVE]",
	"menuText", "fg[NORMAL]",
	"text", "bg[NORMAL]",
	"textText", "fg[NORMAL]",
	"textHighlight", "bg[SELECTED]",
	"textHighlightText", "fg[SELECTED]",
	"textInactiveText", "fg[INSENSITIVE]",
	"control", "button.bg[NORMAL]",
	"controlText", "button.fg[NORMAL]",
	"controlHighlight", "",
	"controlLtHighlight", "",
	"controlShadow", "",
	"controlDkShadow", "",
	"scrollbar", "",
	"info", "",
	"infoText", "",
    };

    /**
     * Construct a new GtkSkin using the given filename
     * @param filename path to a gtk skin (gtkrc) file
     */
    public GtkSkin(String filename) throws Exception {
	this(SkinUtils.toURL(new File(filename)));
    }

    /**
     * Construct a new GtkSkin using the given url
     * @param url path to a gtk skin (gtkrc) file
     */
    public GtkSkin(java.net.URL url) throws Exception {
	GtkParser parser = new GtkParser(url);
	parser.buildStructure();

	init(parser);
    }

    public GtkSkin(GtkParser parser) throws Exception {
	init(parser);
    }

    private void init(GtkParser parser) throws Exception {
	personality = new GtkPersonality(parser);
	button = new GtkButton(parser);
	tab = new GtkTab(parser);
	progress = new GtkProgress(parser);
	scrollbar = new GtkScrollbar(parser);
	slider = new GtkSlider(parser);

	java.util.Vector colorList = new java.util.Vector();
	for (int i = 0, c = swingToGtk.length / 2; i < swingToGtk.length; i = i+2) {
	    String colorName = swingToGtk[i+1];
	    String color = null;

	    if ("".equals(colorName))
		continue;

	    int index = colorName.indexOf(".");
	    if (index != -1) {
		if (parser.getStyle(colorName.substring(0, index)) != null)
		    color = (String)parser.getStyle(colorName.substring(0, index)).getProperty(colorName);
	    }

	    if (color == null)
		color = (String)parser.getStyle("default").getProperty(colorName);

	    if (color != null) {
		colorList.addElement(swingToGtk[i]);
		colorList.addElement(decodeColor(color));
	    }
	}
	colors = new String[colorList.size()];
	colorList.copyInto(colors);
    }

    static String decodeColor(String color) {
	if (color.startsWith("#"))
	    return color;
	// if not #xxxxxx it's {float,float,float}
	java.util.StringTokenizer token = new java.util.StringTokenizer(color, ",{}");
	String result = "#";
	result += Integer.toHexString((int)(255 * Float.valueOf(token.nextToken()).floatValue()));
	result += Integer.toHexString((int)(255 * Float.valueOf(token.nextToken()).floatValue()));
	result += Integer.toHexString((int)(255 * Float.valueOf(token.nextToken()).floatValue()));
	return result;
    }

    public SkinPersonality getPersonality() {
	return personality;
    }

    public SkinButton getButton() {
	return button;
    }

    public SkinFrame getFrame() {
	return null;
    }

    public SkinTab getTab() {
	return tab;
    }

    public SkinProgress getProgress() {
	return progress;
    }

    public String[] getColors() {
	return colors;
    }

    public SkinScrollbar getScrollbar() {
	return scrollbar;
    }

    public SkinSlider getSlider() {
	return slider;
    }

    /**
     * Get the user's current GTK skin location.<br>
     * This could be used on a Linux platform.
     * It looks for the user theme in the ~/.gtkrc user file.
     * @return the skin location or null if the user's current skin can't be found
     */
    public static String getDefaultSkinLocation() {
	String home = System.getProperty("user.home");
	if (home == null) return null;
	
	String gtkrc = home + File.separator + ".gtkrc";
	File f = new File(gtkrc);
	if (!f.exists()) return null;
	
	try {
	    BufferedReader br = new BufferedReader(new FileReader(f));
	    String s = br.readLine();
	    while (s != null) {
		if (s.indexOf("gtkrc") != -1) {
		    String s2 = s.substring(9, s.length() - 1);
		    File f2 = new File(s2);
		    if (f2.exists()) return f2.getCanonicalPath();
		}
		s = br.readLine();
	    }
	} catch (Exception e) {
	    return null;
	}
	return null;
    }

}
