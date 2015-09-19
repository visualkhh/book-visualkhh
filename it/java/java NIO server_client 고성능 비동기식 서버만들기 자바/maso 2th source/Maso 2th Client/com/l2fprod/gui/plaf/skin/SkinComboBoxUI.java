package com.l2fprod.gui.plaf.skin;

import javax.swing.plaf.basic.*;
import javax.swing.*;
import javax.swing.plaf.*;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * 
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:05 $
 */
public class SkinComboBoxUI extends BasicComboBoxUI {

    protected PropertyChangeListener editableChangeListener;
    private Skin skin = SkinLookAndFeel.getSkin();

    public static ComponentUI createUI(JComponent c) {
        return new SkinComboBoxUI();
    }

    protected JButton createArrowButton() {
	return new SkinArrowButton(SkinArrowButton.SOUTH);
    }

    protected void installComponents() {
	super.installComponents();
        arrowButton.setVisible(comboBox.isEditable());
    }

    protected void installListeners() {
	super.installListeners();
        if ( (editableChangeListener = createEditableChangeListener()) != null ) {
            comboBox.addPropertyChangeListener( editableChangeListener );
        }
    }

    public void paint( Graphics g, JComponent c ) {
        hasFocus = comboBox.hasFocus();
        if ( !comboBox.isEditable() ) {	    
            Rectangle r = rectangleForCurrentValue();
	    skin.getPersonality().paintComboBox(g, comboBox, r, hasFocus);
	    paintCurrentValue(g,r,false);
        }
    }

    public Dimension getPreferredSize(JComponent c) {
	Dimension prefs = super.getPreferredSize(c);
	Dimension combo = skin.getPersonality().getComboBoxPreferredSize(comboBox);
	prefs.width = Math.max(prefs.width, combo.width);
	prefs.height = Math.max(prefs.height, combo.height);
	return prefs;
    }

    protected Rectangle rectangleForCurrentValue() {
        int width = comboBox.getWidth();
        int height = comboBox.getHeight();
        Insets insets = getInsets();
        int buttonSize = 0;
        if ( (arrowButton != null) && arrowButton.isVisible()) {
            buttonSize = arrowButton.getWidth();
        }
        return new Rectangle(insets.left, insets.top,
                             width - (insets.left + insets.right + buttonSize),
                             height - (insets.top + insets.bottom));
    }

    protected LayoutManager createLayoutManager() {
        return new SkinComboBoxLayoutManager();
    }

    protected ListCellRenderer createRenderer() {
        return new SkinComboBoxRenderer();
    }

    protected PropertyChangeListener createEditableChangeListener() {
        return new EditableChangeHandler();
    }

    protected JButton arrowButton() {
	return arrowButton;
    }

    protected Component editor() {
	return editor;
    }

    public class EditableChangeHandler implements PropertyChangeListener {
        public void propertyChange(PropertyChangeEvent e) {
            String propertyName = e.getPropertyName();
	    if (propertyName.equals("editable")) {
		SkinComboBoxUI.this.arrowButton().setVisible(((Boolean)e.getNewValue()).booleanValue());
	    }
	}
    }

    public class SkinComboBoxLayoutManager implements LayoutManager {
        public void addLayoutComponent(String name, Component comp) {}

        public void removeLayoutComponent(Component comp) {}

        public Dimension preferredLayoutSize(Container parent) {
            JComboBox cb = (JComboBox)parent;
            return parent.getPreferredSize();
        }

        public Dimension minimumLayoutSize(Container parent) {
            JComboBox cb = (JComboBox)parent;
            return parent.getMinimumSize();
        }

        public void layoutContainer(Container parent) {
            JComboBox cb = (JComboBox)parent;
            int width = cb.getWidth();
            int height = cb.getHeight();
            Insets insets = cb.getInsets();
            int buttonSize = height - (insets.top + insets.bottom);
            Rectangle cvb;

            if ( (SkinComboBoxUI.this.arrowButton() != null) && SkinComboBoxUI.this.arrowButton().isVisible()) {
                SkinComboBoxUI.this.arrowButton().setBounds( width - (insets.right + buttonSize),
                                       insets.top,
                                       buttonSize, buttonSize);
            }
            if ( SkinComboBoxUI.this.editor() != null ) {
                cvb = rectangleForCurrentValue();
                SkinComboBoxUI.this.editor().setBounds(cvb);
            }
        }
    }

}
