package com.l2fprod.gui.plaf.skin.impl.kde;

import java.awt.Toolkit;
import java.awt.AWTEvent;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javax.swing.JWindow;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.LazyTable;
import com.l2fprod.gui.event.SkinWindowEvent;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.AbstractSkin;
import com.l2fprod.gui.sound.*;
import com.l2fprod.gui.*;

/**
 * KDE (The K Desktop Environment) Skin Support.
 * <BR>
 * KdeSkin can be used in conjunction with a kde.themerc file.<BR>
 * You can find skins at:
 * <LI><A HREF="http://kde.themes.org">kde.themes.org</A>
 * <BR><BR>
 * Simply extract the skin file in a directory and use:<BR><BR>
 * <B>
 * <CODE>
 * SkinLookAndFeel.setSkin(new KdeSkin("c:\downloads\myskin\kde\kde.themerc"));<BR>
 * UIManager.setLookAndFeel("com.l2fprod.gui.plaf.skin.SkinLookAndFeel");
 * </CODE>
 * </B>
 * <BR><BR>to enable skins in your application !
 * <BR><BR>
 * <BR><BR>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.3 $, $Date: 2000/11/25 22:27:34 $
 */
public class KdeSkin extends AbstractSkin {

    SkinPersonality personality;
    SkinButton button;
    SkinFrame frame;
    SkinTab tab;
    SkinProgress progress;
    SkinScrollbar scrollbar;
    SkinSlider slider;
    String[] colors;
    
    static Object[] swingToKde = {
	"desktop", new String[]{ "desktop", "background" },
	"activeCaption", new String[]{ "activeBackground", "background" },
	"activeCaptionText", new String[]{ "activeForeground", "foreground" },
	"activeCaptionBorder", new String[]{ "" },
	"inactiveCaption", new String[]{ "inactiveBackground" , "background" },
	"inactiveCaptionText", new String[]{ "inactiveForeground" , "foreground" },
	"inactiveCaptionBorder", new String[]{ "" },
	"window", new String[]{ "windowBackground", "background" },
	"windowBorder", new String[]{ "" },
	"windowText", new String[]{ "windowForeground", "foreground" },
	"menu", new String[]{ "background" },
	"menuPressedItemB", new String[]{ "selectBackground" },
	"menuPressedItemF", new String[]{ "selectForeground" },
	"menuText", new String[]{ "foreground" },
	"text", new String[]{ "background" },
	"textText", new String[]{ "foreground" },
	"textHighlight", new String[]{ "selectBackground" },
	"textHighlightText", new String[]{ "selectForeground" },
	"textInactiveText", new String[]{ "" },
	"control", new String[]{ "background" },
	"controlText", new String[]{ "foreground" },
	"controlHighlight", new String[]{ "" },	    
	"controlLtHighlight", new String[]{ "" },
	"controlShadow", new String[]{ "" },
	"controlDkShadow", new String[]{ "" },
	"scrollbar", new String[]{ "" },
	"info", new String[]{ "" },
	"infoText", new String[]{ "" },
    };

    /**
     * Construct a new KDE skin with the given filename
     * @param filename path to a kde (themerc) skin file
     */
    public KdeSkin(String filename) throws Exception {
	this(SkinUtils.toURL(new File(filename)));
    }
	
    public KdeSkin(URL skinURL) throws Exception {
	IniFile ini = new IniFile(skinURL);

	personality = new KdePersonality(ini, skinURL);
	frame = new KdeFrame(ini, skinURL);

	java.util.Vector colorList = new java.util.Vector();
	for (int i = 0, c = swingToKde.length / 2; i < swingToKde.length; i = i+2) {
	    // "swingcolor", { "c1", "c2" }
	    String[] locals = (String[])swingToKde[i+1];
	    if (locals != null && locals.length > 0) {
		for (int j = 0, d = locals.length; j < d; j++) {
		    if (ini.getKeyValue("Colors", locals[j]) != null) {
			colorList.addElement(swingToKde[i]);
			colorList.addElement(decodeColor(ini.getKeyValue("Colors", locals[j])));
			break;
		    }
		}
	    }		
	}
	colors = new String[colorList.size()];
	colorList.copyInto(colors);

    }

    // color is R,G,B
    static String decodeColor(String color) {
	java.util.StringTokenizer token = new java.util.StringTokenizer(color, ",. ");
	String result = "#";
	result += Integer.toHexString(Integer.parseInt(token.nextToken()));
	result += Integer.toHexString(Integer.parseInt(token.nextToken()));
	result += Integer.toHexString(Integer.parseInt(token.nextToken()));
	return result;
    }

    public SkinPersonality getPersonality() {
	return personality;
    }

    public SkinButton getButton() {
	return button;
    }

    public SkinFrame getFrame() {
	return frame;
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

}
