package com.l2fprod.gui.plaf.skin;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.Icon;
import javax.swing.plaf.basic.*;

/**
 * 
 * Created on 05/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:07 $
 */
public class SkinComboBoxRenderer extends BasicComboBoxRenderer {

    public SkinComboBoxRenderer() {
	super();
	setOpaque(false);
	setBorder(null);
    }

    public Component getListCellRendererComponent(
                                                 JList list, 
                                                 Object value,
                                                 int index, 
                                                 boolean isSelected, 
                                                 boolean cellHasFocus)
    {

	// if index == -1 then we are painting the selected value (but not a value in the list)
	if (index == -1)
	    setOpaque(false);
	else
	    setOpaque(true);

        if ((index!=-1) && isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setFont(list.getFont());

        if (value instanceof Icon) {
            setIcon((Icon)value);
        }
        else {
            setText((value == null) ? "" : value.toString());
        }
        return this;
    }

}
