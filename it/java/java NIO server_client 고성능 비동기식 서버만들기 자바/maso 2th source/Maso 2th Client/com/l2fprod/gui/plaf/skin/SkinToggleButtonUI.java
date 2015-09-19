package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;

public class SkinToggleButtonUI extends SkinButtonUI {

    public static ComponentUI createUI(JComponent b) {
        return new SkinToggleButtonUI();
    }

    // ********************************
    //          Paint Methods
    // ********************************
    public void paint(Graphics g, JComponent c) {
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        Dimension size = b.getSize();
        FontMetrics fm = g.getFontMetrics();

        Insets i = c.getInsets();

        Rectangle viewRect = new Rectangle(size);

        viewRect.x += i.left;
        viewRect.y += i.top;
        viewRect.width -= (i.right + viewRect.x);
        viewRect.height -= (i.bottom + viewRect.y);

        Rectangle iconRect = new Rectangle();
        Rectangle textRect = new Rectangle();

        Font f = c.getFont();
        g.setFont(f);

        // layout the text and icon
        String text = SwingUtilities.layoutCompoundLabel(
            c, fm, b.getText(), b.getIcon(),
            b.getVerticalAlignment(), b.getHorizontalAlignment(),
            b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
            viewRect, iconRect, textRect, b.getText() == null ? 0 : getDefaultTextIconGap(b)
        );


        g.setColor(b.getBackground());

        if (model.isArmed() && model.isPressed() || model.isSelected())
          {
            paintButtonPressed(g,b);
          }

	skin.getButton().paintButton(g, b);

        // Paint the Icon
        if(b.getIcon() != null) { 
            paintIcon(g, b, iconRect);
        }

        // Draw the Text
        if(text != null && !text.equals("")) {
            paintText(g, b, textRect, text);
        }
          
    }

}
