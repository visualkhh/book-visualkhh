package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.*;
import javax.swing.text.View;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:56 $
 */
public class SkinButtonUI extends BasicButtonUI {

    protected int dashedRectGapX;
    protected int dashedRectGapY;
    protected int dashedRectGapWidth;
    protected int dashedRectGapHeight;

    protected Color focusColor;
    
    private boolean defaults_initialized = false;
    protected Skin skin = SkinLookAndFeel.getSkin();

    // ********************************
    //          Create PLAF
    // ********************************
    public static ComponentUI createUI(JComponent c){
	return new SkinButtonUI();
    }
    
    // ********************************
    //         Create Listeners
    // ********************************
    //    protected BasicButtonListener createButtonListener(AbstractButton b) {
    //	return new SkinButtonListener(b); 
    //    }

    // ********************************
    //            Defaults
    // ********************************
    protected void installDefaults(final AbstractButton b) {
	super.installDefaults(b);
	if(!defaults_initialized) {
	    String pp = getPropertyPrefix();
	    dashedRectGapX = UIManager.getInt(pp + "dashedRectGapX");
	    dashedRectGapY = UIManager.getInt(pp + "dashedRectGapY");
	    dashedRectGapWidth = UIManager.getInt(pp + "dashedRectGapWidth");
	    dashedRectGapHeight = UIManager.getInt(pp + "dashedRectGapHeight");
	    focusColor = UIManager.getColor(pp + "focus");
	    defaults_initialized = true;
	}
	
	b.setBorderPainted(false);
	b.setFocusPainted(false);
	b.setOpaque(false);
	b.setRolloverEnabled(true);

	skin.getButton().installSkin(b);

    }
      
    protected void uninstallDefaults(AbstractButton b) {
	super.uninstallDefaults(b);
	defaults_initialized = false;
	b.setBorderPainted(true);
	b.setFocusPainted(true);
	b.setOpaque(true);
    }
    
    protected Color getFocusColor() {
	return focusColor;
    }
    
    // ********************************
    //         Paint Methods
    // ********************************

    /* These rectangles/insets are allocated once for all 
     * ButtonUI.paint() calls.  Re-using rectangles rather than 
     * allocating them in each paint call substantially reduced the time
     * it took paint to run.  Obviously, this method can't be re-entered.
     */
    private static Rectangle viewRect = new Rectangle();
    private static Rectangle textRect = new Rectangle();
    private static Rectangle iconRect = new Rectangle();

    // ********************************
    //          Paint Methods
    // ********************************

    public void paint(Graphics g, JComponent c) 
    {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        FontMetrics fm = g.getFontMetrics();

        Insets i = c.getInsets();

        viewRect.x = i.left;
        viewRect.y = i.top;
        viewRect.width = b.getWidth() - (i.right + viewRect.x);
        viewRect.height = b.getHeight() - (i.bottom + viewRect.y);

        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        Font f = c.getFont();
        g.setFont(f);

        // layout the text and icon
        String text = SwingUtilities.layoutCompoundLabel(
            c, fm, b.getText(), b.getIcon(), 
            b.getVerticalAlignment(), b.getHorizontalAlignment(),
            b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
            viewRect, iconRect, textRect, 
            b.getText() == null ? 0 : defaultTextIconGap
        );

        clearTextShiftOffset();

        // perform UI specific press action, e.g. Windows L&F shifts text
        if (model.isArmed() && model.isPressed()) {
            paintButtonPressed(g,b); 
        }

	skin.getButton().paintButton(g, b);

        // Paint the Icon
        if(b.getIcon() != null) { 
            paintIcon(g,c,iconRect);
        }

        if (text != null && !text.equals("")){
	    //PENDING(fred): BasicHTML is not used because package private in JDK1.2 (not in 1.3)
	    View v = (View) c.getClientProperty("html" /*BasicHTML.propertyKey*/);
	    if (v != null) {
		v.paint(g, textRect);
	    } else {
		paintText(g, c,textRect, text);
	    }
        }

        if (b.isFocusPainted() && b.hasFocus()) {
            // paint UI specific focus
            paintFocus(g,b,viewRect,textRect,iconRect);
        }

    }

    protected void paintButtonPressed(Graphics g, AbstractButton b){
	setTextShiftOffset();
    }
    
}

