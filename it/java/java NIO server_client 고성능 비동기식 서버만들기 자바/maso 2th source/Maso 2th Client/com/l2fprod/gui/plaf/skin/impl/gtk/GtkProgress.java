package com.l2fprod.gui.plaf.skin.impl.gtk;

import java.awt.Image;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JComponent;

import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.gtk.parser.*;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:22 $
 */
class GtkProgress implements SkinProgress, SwingConstants {

    DefaultButton progressBar;
    DefaultButton progressBack;

    public GtkProgress(GtkParser parser) throws Exception {
	//PENDING(fred): progress needs to be improved with vertical and horizontal progress
	progressBar = GtkUtils.newButton(parser, "GtkProgressBar",
					 new String[]{"function", "detail"},
					 new String[]{"BOX", "bar"});

	progressBack = GtkUtils.newButton(parser, "GtkProgressBar",
					  new String[]{"function", "detail"},
					  new String[]{"BOX", "trough"});
    }

    public boolean status() {
	return progressBar != null;
    }

    public boolean installSkin(JComponent c) {
	return true;
    }

    public java.awt.Dimension getMinimumSize() {
	if (progressBack != null)
	    return progressBack.getMinimumSize();
	else
	    return new Dimension(50, 17);
    }

    public boolean paintProgress(java.awt.Graphics g, javax.swing.JProgressBar progress) {
	if (progressBack != null)
	    progressBack.paint(g, 0, 0, progress.getWidth(), progress.getHeight(), progress);
	if ((progressBar != null) && (progress.getValue() > progress.getMinimum()))
	    if (progress.getOrientation() == HORIZONTAL)
		progressBar.paint(g, 0, 0,
				  (int)((double)progress.getValue() * progress.getWidth()/(double)progress.getMaximum()),
				  progress.getHeight(), progress);
	    else
		progressBar.paint(g, 0, 0,
				  progress.getWidth(),
				  (int)((double)progress.getValue() * progress.getHeight()/(double)progress.getMaximum()), progress);
	return true;
    }

}
