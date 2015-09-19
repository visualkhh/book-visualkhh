package com.l2fprod.util;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Dimension;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:51 $
 */
public class WindowUtils {

    public static void centerOnScreen(Window w) {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension size = w.getSize();
	w.setLocation((screenSize.width - size.width) / 2,
		      (screenSize.height - size.height) / 2);
    }

    public static void sizeTo(Window w, double x, double y) {
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	w.setSize((int)(size.width * x), (int)(size.height * y));
    }
    
}
