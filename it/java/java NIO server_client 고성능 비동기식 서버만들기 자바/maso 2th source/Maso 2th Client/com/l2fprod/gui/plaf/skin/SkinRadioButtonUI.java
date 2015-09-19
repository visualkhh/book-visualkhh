package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.plaf.*;

import java.awt.*;

public class SkinRadioButtonUI extends BasicRadioButtonUI {

    private static final SkinRadioButtonUI radioButtonUI = new SkinRadioButtonUI();

    protected int dashedRectGapX;
    protected int dashedRectGapY;
    protected int dashedRectGapWidth;
    protected int dashedRectGapHeight;

    protected Color focusColor;

    private boolean initialized = false;
    
    // ********************************
    //          Create PLAF
    // ********************************
    public static ComponentUI createUI(JComponent c) {
	return radioButtonUI;
    }
    
    protected Skin skin = SkinLookAndFeel.getSkin();

    // ********************************
    //           Defaults
    // ********************************
    public void installDefaults(AbstractButton b) {
	super.installDefaults(b);
	if(!initialized) {
	    dashedRectGapX = ((Integer)UIManager.get("Button.dashedRectGapX")).intValue();
	    dashedRectGapY = ((Integer)UIManager.get("Button.dashedRectGapY")).intValue();
	    dashedRectGapWidth = ((Integer)UIManager.get("Button.dashedRectGapWidth")).intValue();
	    dashedRectGapHeight = ((Integer)UIManager.get("Button.dashedRectGapHeight")).intValue();
	    focusColor = UIManager.getColor(getPropertyPrefix() + "focus");
	    initialized = true;
	}
	b.setOpaque(false);
    }

    protected Color getFocusColor() {
	return focusColor;
    }
    
    // ********************************
    //          Paint Methods
    // ********************************
    /* These Dimensions/Rectangles are allocated once for all 
     * RadioButtonUI.paint() calls.  Re-using rectangles 
     * rather than allocating them in each paint call substantially 
     * reduced the time it took paint to run.  Obviously, this 
     * method can't be re-entered.
     */
    private static Dimension size = new Dimension();
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();

    /**
     * paint the radio button
     */
    public synchronized void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();

        size = b.getSize(size);
        viewRect.x = viewRect.y = 0;
        viewRect.width = size.width;
        viewRect.height = size.height;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;
        textRect.x = textRect.y = textRect.width = textRect.height = 0;

        Icon altIcon = b.getIcon();
        Icon selectedIcon = null;
        Icon disabledIcon = null;

        String text = SwingUtilities.layoutCompoundLabel(
            c, fm, b.getText(), altIcon != null ? altIcon : getDefaultIcon(),
            b.getVerticalAlignment(), b.getHorizontalAlignment(),
            b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
            viewRect, iconRect, textRect, getDefaultTextIconGap(b));

        // fill background
        if(c.isOpaque()) {
            g.setColor(b.getBackground());
            g.fillRect(0,0, size.width, size.height); 
        }


	altIcon = skin.getButton().getRadioIcon(b);

	if(altIcon != null) {
	    altIcon.paintIcon(c, g, iconRect.x, iconRect.y);		  
	} else {
	    getDefaultIcon().paintIcon(c, g, iconRect.x, iconRect.y);
	}

        // Draw the Text
        if(text != null) {
            if(model.isEnabled()) {
                // *** paint the text normally
                g.setColor(b.getForeground());
                BasicGraphicsUtils.drawString(g,text,model.getMnemonic(),
                                              textRect.x, 
                                              textRect.y + fm.getAscent());
            } else {
                // *** paint the text disabled
                g.setColor(b.getBackground().brighter());
                BasicGraphicsUtils.drawString(g,text,model.getMnemonic(),
                                              textRect.x + 1, 
                                              textRect.y + fm.getAscent() + 1);
                g.setColor(b.getBackground().darker());
                BasicGraphicsUtils.drawString(g,text,model.getMnemonic(),
                                              textRect.x, 
                                              textRect.y + fm.getAscent());
            }
            if(b.hasFocus() && b.isFocusPainted() && 
               textRect.width > 0 && textRect.height > 0 ) {
                paintFocus(g, textRect, size);
            }
        }
    }

    protected void paintFocus(Graphics g, Rectangle textRect, Dimension d){
	g.setColor(getFocusColor());
	BasicGraphicsUtils.drawDashedRect(g, textRect.x, textRect.y, textRect.width, textRect.height);
    } 

}

