package com.l2fprod.gui.plaf.skin.impl.kde;

import java.net.URL;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:57 $
 */
class KdeFrame extends AbstractSkinFrame {

    static final String[] TEXT_ALIGNMENTS = {
	"left", "middle", "right"
    };

    static final int LEFT = 0;
    static final int MIDDLE = 1;
    static final int RIGHT = 2;

    DefaultButton topSelected;
    Image topUnselected;
    int topHeight = 17;
    java.util.Vector buttonList;
    int textShiftLeft = SkinTitlePane.ICON_OFFSET, textShiftRight = 0;
    int textAlignment = LEFT;

    boolean pixmapUnderTitle = false;

    KdeFrameBorder border;

    public KdeFrame(IniFile ini, URL skinURL) throws Exception {

	String path = ini.getKeyValue("Window Titlebar", "TitlebarPixmapActive");
	if (path != null) {
	    Image image = SkinUtils.loadImage(new URL(skinURL, path));
	    topSelected =
		new DefaultButton(image,
				  image.getWidth(SkinUtils.bitmapCreator),
				  image.getHeight(SkinUtils.bitmapCreator),
				  ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveTop"),
				  ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveRight"),
				  ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveBottom"),
				  ini.getKeyIntValue("Window Titlebar", "TitlebarPixmapActiveLeft"));
	    topHeight = topSelected.getHeight();
	}

	path = ini.getKeyValue("Window Titlebar", "TitlebarPixmapInactive");
	if (path != null) {
	    topUnselected = SkinUtils.loadImage(new URL(skinURL, path));
	    topHeight = topUnselected.getHeight(SkinUtils.bitmapCreator);
	}

	pixmapUnderTitle = "yes".equals(ini.getKeyValue("Window Titlebar", "PixmapUnderTitleText"));

	String textAlignmentValue = ini.getKeyValue("Window Titlebar", "TitleAlignment");
	if (textAlignmentValue != null) {
	    textAlignmentValue = textAlignmentValue.toLowerCase();
	    for (int i = 0, c = TEXT_ALIGNMENTS.length; i < c; i++)
		if (TEXT_ALIGNMENTS[i].equals(textAlignmentValue)) {
		    textAlignment = i;
		    break;
		}
	}
	buttonList = new java.util.Vector();

	String letters = "ABCDEF";
	if (ini.getSection("Window Button Layout") == null) {
	    ini.addSection("Window Button Layout");
	    ini.setKeyValue("Window Button Layout", "ButtonA", "Off");
	    ini.setKeyValue("Window Button Layout", "ButtonB", "Off");
	    ini.setKeyValue("Window Button Layout", "ButtonC", "Off");
	    ini.setKeyValue("Window Button Layout", "ButtonD", "Minimize");
	    ini.setKeyValue("Window Button Layout", "ButtonE", "Maximize");
	    ini.setKeyValue("Window Button Layout", "ButtonF", "Close");
	}

	for (int i = 0, c = letters.length(); i < c; i++) {
	    String button = ini.getKeyValue("Window Button Layout", "Button" + letters.charAt(i));
	    if ((button != null) && ("Off".equalsIgnoreCase(button)==false)) {
		FrameButton fb = new FrameButton(ini, skinURL, button);
		fb.setAlign((i<c/2)?SkinTitlePane.ALIGN_TOP_LEFT:
			    SkinTitlePane.ALIGN_TOP_RIGHT);
		if (fb.icon != null) {
		    if (i<c/2)
			textShiftLeft += fb.icon.getIconWidth();
		    else
			textShiftRight += fb.icon.getIconWidth();
		    topHeight = Math.max(topHeight, fb.icon.getIconHeight());
		}
		buttonList.addElement(fb);
	    }
	}
	textShiftLeft += 4;
	textShiftRight += 4;
	
	if ((ini.getSection("Window Border") != null) &&
	    (ini.getSection("Window Border").size() > 0)) {
	    border = new KdeFrameBorder(ini, skinURL);
	}
    }

    public boolean status() {
	return true;
    }

    public boolean installSkin(JComponent c) {
	if (border != null) {
	    ((JComponent)c).setBorder(border);
	    c.setOpaque(false);
	    return true;
	} else	
	    return false;
    }

    public Dimension getTopPreferredSize() {
	return new Dimension(50, topHeight);
    }

    public SkinWindowButton[] getWindowButtons(int align) {
	java.util.Vector buttons = new java.util.Vector();
	for (int i = 0, c = buttonList.size(); i < c; i++) {
	    FrameButton newB = (FrameButton)buttonList.elementAt(i);
	    if (newB.getAlign() == align)
		buttons.addElement(newB.createButton());
	}
	SkinWindowButton[] results = new SkinWindowButton[buttons.size()];
	buttons.copyInto(results);
	return results;
    }

    public boolean paintTop(Graphics g, Component c, boolean isSelected, String title) {
	if (topSelected!=null && topUnselected!=null) {
	    if (isSelected) {
		topSelected.paintIcon(c, g, 0, 0);
		//		ImageUtils.paintTile(c, g, topSelected, false);
	    } else {
		ImageUtils.paintTile(c, g, topUnselected, false);
	    }
	} else {
	    // fill a rectangle
	    Color oldColor = g.getColor();
	    if (isSelected)
		g.setColor(UIManager.getColor("InternalFrame.activeTitleBackground"));
	    else
		g.setColor(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
	    g.fillRect(0, 0, ((JComponent)c).getWidth(), ((JComponent)c).getHeight());
	    g.setColor(oldColor);
	}

	if (title != null) {
	    FontMetrics fm = g.getFontMetrics();
	    int fmHeight = fm.getHeight() - fm.getLeading();
	    int baseline = (topHeight - fmHeight) / 2 + fm.getAscent() + fm.getLeading();
	    int width = fm.stringWidth(title);

	    int x = 0;

	    switch (textAlignment) {
	    case LEFT:
		x = textShiftLeft;
		break;
	    case MIDDLE:
		x = (((JComponent)c).getWidth() - textShiftLeft - textShiftRight) / 2 + textShiftLeft - width / 2;
		break;
	    case RIGHT:
		x = ((JComponent)c).getWidth() - width - textShiftRight;
		break;
	    }

	    if (pixmapUnderTitle == false) {
		Color oldColor = g.getColor();
		if (isSelected)
		    g.setColor(UIManager.getColor("InternalFrame.activeTitleBackground"));
		else
		    g.setColor(UIManager.getColor("InternalFrame.inactiveTitleBackground"));
		g.fillRect(x, 0, width, ((JComponent)c).getHeight());
		g.setColor(oldColor);
	    }

	    g.drawString(title, x, baseline);
	}
	
	return true;
    }

    class FrameButton {
	ImageIcon icon, downIcon, inactiveIcon;
	int align;
	int action = SkinTitlePane.NO_ACTION;

	FrameButton(IniFile ini, URL skinURL, String command) throws Exception {
	    if ("Iconify".equals(command))
		command = "Minimize";

	    String path = ini.getKeyValue("Window Titlebar", command + "Button");
	    if (path != null) {
		icon = new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
		inactiveIcon = icon;
		downIcon = icon;
	    }
	    path = ini.getKeyValue("Window Titlebar", command + "DownButton");
	    if (path != null) {
		downIcon = new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
	    }
	    path = ini.getKeyValue("Window Titlebar", command + "InactiveButton");
	    if (path != null) {
		inactiveIcon = new ImageIcon(SkinUtils.loadImage(new URL(skinURL, path)));
	    }

	    if ("Maximize".equalsIgnoreCase(command))
		action = SkinTitlePane.MAXIMIZE_ACTION;
	    else if ("Minimize".equalsIgnoreCase(command))
		action = SkinTitlePane.MINIMIZE_ACTION;
	    else if ("Close".equalsIgnoreCase(command))
		action = SkinTitlePane.CLOSE_ACTION;
	}

	public int getAlign() {
	    return align;
	}

	public void setAlign(int align) {
	    this.align = align;
	}

	public SkinWindowButton createButton() {
	    SkinWindowButton button = new SkinWindowButton(-1, (topHeight - icon.getIconHeight())/2, align, action);
	    if (icon != null) {
		button.setSize(icon.getIconWidth(), icon.getIconHeight());
		button.setNoFocusIcon(inactiveIcon);
		button.setNoFocusRolloverIcon(inactiveIcon);
		button.setIcon(icon);
		button.setRolloverIcon(icon);
		button.setPressedIcon(downIcon);
	    }
	    return button;
	}

    }

}
