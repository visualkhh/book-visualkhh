package com.l2fprod.gui.plaf.skin;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.*;

public class SkinArrowButton extends JButton implements SwingConstants {

    protected int direction;
    protected Skin skin = SkinLookAndFeel.getSkin();

    public SkinArrowButton(int direction) {
	super();
	setRequestFocusEnabled(false);
	setDirection(direction);
	setBackground(UIManager.getColor("control"));
    }

    public int getDirection() { return direction; }
    
    public void setDirection(int dir) { direction = dir; }
    
    public Dimension getPreferredSize() {
	return skin.getScrollbar().getArrowPreferredSize(direction);
    }
    
    public Dimension getMinimumSize() {
	return new Dimension(5, 5);
    }
    
    public Dimension getMaximumSize() {
	return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public boolean isFocusTraversable() {
	return false;
    }
    
    public void paint(Graphics g) {
	skin.getScrollbar().paintArrow(g, this, direction);
    }
	
}

