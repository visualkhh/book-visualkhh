package com.l2fprod.gui.event;

import java.awt.event.WindowListener;

/**
 * 
 * Created on 12/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:55 $
 */
public interface SkinWindowListener extends WindowListener {

    public void windowShaded(SkinWindowEvent event);
    public void windowUnshaded(SkinWindowEvent event);

    public void windowMaximized(SkinWindowEvent event);
    public void windowUnmaximized(SkinWindowEvent event);

}
