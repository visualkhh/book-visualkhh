package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

import com.l2fprod.gui.plaf.skin.impl.AbstractSkin;

/**
 * Assembles two skins to create a new one.
 * <br>
 * This can be used to combine features from two skins.
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/10/31 18:18:51 $
 */
public class CompoundSkin extends AbstractSkin {

    private Skin skina, skinb;

    /**
     * Construct a new Skin by merging two skins.<BR>
     * If a feature is missing in the first skin, the second skin is used.
     *
     * @param the first skin
     * @param the second skin
     */
    public CompoundSkin(Skin a, Skin b) {
	skina = a;
	skinb = b;

	if ((a==null) || (b==null))
	    throw new IllegalArgumentException("Skins must not be null!");

	button = new CompoundButton();
	frame = new CompoundFrame();
	personality = new CompoundPersonality();
	progress = new CompoundProgress();
	scrollbar = new CompoundScrollbar();
	slider = new CompoundSlider();
	tab = new CompoundTab();
    }

    public String[] getColors() {
	Vector v = new Vector();
	int colorSize = 0;
	String[] result = skina.getColors();
	if (result != null)
	    addColors(result, v);
	result = skinb.getColors();
	if (result != null)
	    addColors(result, v);
	result = new String[v.size()];
	v.copyInto(result);
	return result;
    }
    
    protected void addColors(String[] colors, Vector v) {   
	for (int i = 0, c = colors.length; i < c; i = i+2) {
	    if ("".equals(colors[i+1]))
		continue;
	    v.addElement(colors[i]);
	    v.addElement(colors[i+1]);
	}
    }

    private class CompoundButton implements SkinButton {
	public boolean status() {
	    boolean result = false;
	    if (skina.getButton() != null)
		result = skina.getButton().status();
	    if (!result && (skinb.getButton() != null))
		result = skinb.getButton().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getButton() != null)
		result = skina.getButton().installSkin(c);
	    if (!result && (skinb.getButton() != null))
		result = skinb.getButton().installSkin(c);
	    return result;
	}
	public Dimension getCheckBoxIconSize() {
	    Dimension dimension = null;
	    if (skina.getButton() != null)
		dimension = skina.getButton().getCheckBoxIconSize();
	    if ((dimension==null) && (skinb.getButton()!=null))
		dimension = skinb.getButton().getCheckBoxIconSize();
	    return dimension;
	}
	public Icon getRadioIcon(AbstractButton b) {
	    Icon icon = null;
	    if (skina.getButton() != null)
		icon = skina.getButton().getRadioIcon(b);
	    if ((icon == null) && (skinb.getButton() != null))
		icon = skinb.getButton().getRadioIcon(b);
	    return icon;
	}
	public boolean paintButton(Graphics g, AbstractButton b) {
	    boolean result = false;
	    result = skina.getButton().paintButton(g, b);
	    if (!result)
		result = skinb.getButton().paintButton(g, b);
	    return result;
	}
    }
	
    private class CompoundFrame implements SkinFrame {
	public boolean status() {
	    boolean result = false;
	    if (skina.getFrame() != null)
		result = skina.getFrame().status();
	    if (!result && (skinb.getFrame() != null))
		result = skinb.getFrame().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getFrame() != null)
		result = skina.getFrame().installSkin(c);
	    if (!result && (skinb.getFrame() != null))
		result = skinb.getFrame().installSkin(c);
	    return result;
	}
	public SkinWindowButton[] getWindowButtons(int align) {
	    SkinWindowButton[] buttons = null;
	    if (skina.getFrame() != null)
		buttons = skina.getFrame().getWindowButtons(align);
	    if ((buttons==null) && (skinb.getFrame()!=null))
		buttons = skinb.getFrame().getWindowButtons(align);
	    return buttons;
	}

	public Dimension getTopPreferredSize() {
	    Dimension dimension = null;
	    if (skina.getFrame() != null)
		dimension = skina.getFrame().getTopPreferredSize();
	    if ((dimension==null) && (skinb.getFrame()!=null))
		dimension = skinb.getFrame().getTopPreferredSize();
	    return dimension;
	}

	public boolean paintTop(Graphics g, Component c, boolean isSelected, String title) {
	    boolean result = false;
	    if (skina.getFrame() != null)
		result = skina.getFrame().paintTop(g, c, isSelected, title);
	    if (!result && (skinb.getFrame() != null))
		result = skinb.getFrame().paintTop(g, c, isSelected, title);
	    return result;
	}
    }

    private class CompoundPersonality implements SkinPersonality {
	public boolean status() {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().status();
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().installSkin(c);
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().installSkin(c);
	    return result;
	}
	public boolean paintDialog(Graphics g, Component c) {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().paintDialog(g, c);
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().paintDialog(g, c);
	    return result;	    
	}

	public boolean paintMenu(Graphics g, JMenu c) {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().paintMenu(g, c);
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().paintMenu(g, c);
	    return result;
	}

	public boolean paintMenuItem(Graphics g, JMenuItem c) {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().paintMenuItem(g, c);
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().paintMenuItem(g, c);
	    return result;
	}

	public boolean paintComboBox(Graphics g, JComboBox c, Rectangle bounds, boolean hasFocus) {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().paintComboBox(g, c, bounds, hasFocus);
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().paintComboBox(g, c, bounds, hasFocus);
	    return result;
	}

	public java.awt.Dimension getComboBoxPreferredSize(javax.swing.JComboBox c) {
	    Dimension dimension = null;
	    if (skina.getPersonality() != null)
		dimension = skina.getPersonality().getComboBoxPreferredSize(c);
	    if ((dimension==null) && (skinb.getPersonality()!=null))
		dimension = skinb.getPersonality().getComboBoxPreferredSize(c);
	    return dimension;
	}

	public boolean paintBackground(Graphics g, Component c) {
	    boolean result = false;
	    if (skina.getPersonality() != null)
		result = skina.getPersonality().paintBackground(g, c);
	    if (!result && (skinb.getPersonality() != null))
		result = skinb.getPersonality().paintBackground(g, c);
	    return result;
	}

	public TableCellRenderer createTableHeaderRenderer() {
	    TableCellRenderer renderer = null;
	    if (skina.getPersonality() != null)
		renderer = skina.getPersonality().createTableHeaderRenderer();
	    if ((renderer==null) && (skinb.getPersonality() != null))
		renderer = skinb.getPersonality().createTableHeaderRenderer();
	    return renderer;
	}

	public ListCellRenderer createListCellRenderer() {
	    ListCellRenderer renderer = null;
	    if (skina.getPersonality() != null)
		renderer = skina.getPersonality().createListCellRenderer();
	    if ((renderer==null) && (skinb.getPersonality() != null))
		renderer = skinb.getPersonality().createListCellRenderer();
	    return renderer;
	}
    }

    private class CompoundProgress implements SkinProgress {
	public boolean status() {
	    boolean result = false;
	    if (skina.getProgress() != null)
		result = skina.getProgress().status();
	    if (!result && (skinb.getProgress() != null))
		result = skinb.getProgress().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getProgress() != null)
		result = skina.getProgress().installSkin(c);
	    if (!result && (skinb.getProgress() != null))
		result = skinb.getProgress().installSkin(c);
	    return result;
	}
	public Dimension getMinimumSize() {
	    Dimension dimension = null;
	    if (skina.getProgress() != null)
		dimension = skina.getProgress().getMinimumSize();
	    if ((dimension==null) && (skinb.getProgress()!=null))
		dimension = skinb.getProgress().getMinimumSize();
	    return dimension;
	}

	public boolean paintProgress(Graphics g, JProgressBar progress) {
	    boolean result = false;
	    if (skina.getProgress() != null)
		result = skina.getProgress().paintProgress(g, progress);
	    if (!result && (skinb.getProgress() != null))
		result = skinb.getProgress().paintProgress(g, progress);
	    return result;
	}
    }

    private class CompoundScrollbar implements SkinScrollbar {
	public boolean status() {
	    boolean result = false;
	    if (skina.getScrollbar() != null)
		result = skina.getScrollbar().status();
	    if (!result && (skinb.getScrollbar() != null))
		result = skinb.getScrollbar().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getScrollbar() != null)
		result = skina.getScrollbar().installSkin(c);
	    if (!result && (skinb.getScrollbar() != null))
		result = skinb.getScrollbar().installSkin(c);
	    return result;
	}
	public Dimension getPreferredSize(JScrollBar scrollbar) {
	    Dimension dimension = null;
	    if (skina.getScrollbar() != null)
		dimension = skina.getScrollbar().getPreferredSize(scrollbar);
	    if ((dimension==null) && (skinb.getScrollbar()!=null))
		dimension = skinb.getScrollbar().getPreferredSize(scrollbar);
	    return dimension;
	}
	public Dimension getArrowPreferredSize(int direction) {
	    Dimension dimension = null;
	    if (skina.getScrollbar() != null)
		dimension = skina.getScrollbar().getArrowPreferredSize(direction);
	    if ((dimension==null) && (skinb.getScrollbar()!=null))
		dimension = skinb.getScrollbar().getArrowPreferredSize(direction);
	    return dimension;
	}
	public boolean paintArrow(Graphics g, AbstractButton b, int direction) {
	    boolean result = false;
	    if (skina.getScrollbar() != null)
		result = skina.getScrollbar().paintArrow(g, b, direction);
	    if (!result && (skinb.getScrollbar() != null))
		result = skinb.getScrollbar().paintArrow(g, b, direction);
	    return result;
	}
	public boolean paintThumb(Graphics g, JScrollBar scrollbar, Rectangle thumbBounds) {
	    boolean result = false;
	    if (skina.getScrollbar() != null)
		result = skina.getScrollbar().paintThumb(g, scrollbar, thumbBounds);
	    if (!result && (skinb.getScrollbar() != null))
		result = skinb.getScrollbar().paintThumb(g, scrollbar, thumbBounds);
	    return result;
	}
	public boolean paintTrack(Graphics g, JScrollBar scrollbar, Rectangle trackBounds) {
	    boolean result = false;
	    if (skina.getScrollbar() != null)
		result = skina.getScrollbar().paintTrack(g, scrollbar, trackBounds);
	    if (!result && (skinb.getScrollbar() != null))
		result = skinb.getScrollbar().paintTrack(g, scrollbar, trackBounds);
	    return result;
	}
    }

    private class CompoundSlider implements SkinSlider {
	public boolean status() {
	    boolean result = false;
	    if (skina.getSlider() != null)
		result = skina.getSlider().status();
	    if (!result && (skinb.getSlider() != null))
		result = skinb.getSlider().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getSlider() != null)
		result = skina.getSlider().installSkin(c);
	    if (!result && (skinb.getSlider() != null))
		result = skinb.getSlider().installSkin(c);
	    return result;
	}
	public Dimension getPreferredSize(JSlider slider) {
	    Dimension dimension = null;
	    if (skina.getSlider() != null)
		dimension = skina.getSlider().getPreferredSize(slider);
	    if ((dimension==null) && (skinb.getSlider()!=null))
		dimension = skinb.getSlider().getPreferredSize(slider);
	    return dimension;
	}
	public boolean paintTrack(Graphics g, JSlider slider, Rectangle trackBounds) {
	    boolean result = false;
	    if (skina.getSlider() != null)
		result = skina.getSlider().paintTrack(g, slider, trackBounds);
	    if (!result && (skinb.getSlider() != null))
		result = skinb.getSlider().paintTrack(g, slider, trackBounds);
	    return result;
	}
	public boolean paintThumb(Graphics g, JSlider slider, Rectangle thumbBounds) {
	    boolean result = false;
	    if (skina.getSlider() != null)
		result = skina.getSlider().paintThumb(g, slider, thumbBounds);
	    if (!result && (skinb.getSlider() != null))
		result = skinb.getSlider().paintThumb(g, slider, thumbBounds);
	    return result;
	}
    }

    private class CompoundTab implements SkinTab {
	public boolean status() {
	    boolean result = false;
	    if (skina.getTab() != null)
		result = skina.getTab().status();
	    if (!result && (skinb.getTab() != null))
		result = skinb.getTab().status();
	    return result;
	}
	public boolean installSkin(JComponent c) {
	    boolean result = false;
	    if (skina.getTab() != null)
		result = skina.getTab().installSkin(c);
	    if (!result && (skinb.getTab() != null))
		result = skinb.getTab().installSkin(c);
	    return result;
	}
	public boolean paintTab(Graphics g, int tabPlacement, 
				boolean isSelected, int x, int y, int w, int h) {
	    boolean result = false;
	    if (skina.getTab() != null)
		result = skina.getTab().paintTab(g, tabPlacement, isSelected, x, y, w, h);
	    if (!result && (skinb.getTab() != null))
		result = skinb.getTab().paintTab(g, tabPlacement, isSelected, x, y, w, h);
	    return result;
	}
	public boolean paintContent(java.awt.Graphics g, int tabPlacement, int selectedIndex,
				    int x, int y, int w, int h) {
	    boolean result = false;
	    if (skina.getTab() != null)
		result = skina.getTab().paintContent(g, tabPlacement, selectedIndex, x,y,w,h);
	    if (!result && (skinb.getTab() != null))
		result = skinb.getTab().paintContent(g, tabPlacement, selectedIndex, x,y,w,h);
	    return result;
	}
    }

}



