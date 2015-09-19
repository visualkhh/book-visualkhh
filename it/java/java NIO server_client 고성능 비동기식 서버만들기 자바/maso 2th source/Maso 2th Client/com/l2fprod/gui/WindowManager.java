package com.l2fprod.gui;

import java.awt.Container;

/**
 * WindowManager.
 * <br>
 * WindowManager is responsible for implementing L&F specific behaviors for the system.
 * SkinWindow implementations should delegate specific behaviors to the WindowManager.
 * For instance, if a WindowManager was asked to iconify, it should try: 
 * <pre>
 *      WindowManager.getWindowManager().iconifyWindow(window);
 * </pre>
 * <br><br>
 * <br><br>
 * Created on 27/05/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:54 $
 */
public abstract class WindowManager {

    static WindowManager manager;

    public static WindowManager getWindowManager() {
	if (manager == null)
	    manager = new DefaultWindowManager();
	return manager;
    }

    public static void setWindowManager(WindowManager newManager) {
	manager = newManager;
    }

    public abstract void activateWindow(SkinWindow w);
    public abstract void deactivateWindow(SkinWindow w);

    public abstract void openWindow(SkinWindow w);
    public abstract void closeWindow(SkinWindow w);

    public abstract void iconifyWindow(SkinWindow w);
    public abstract void deiconifyWindow(SkinWindow w);

    public abstract void maximizeWindow(SkinWindow w);
    public abstract void minimizeWindow(SkinWindow w);

    public abstract void shadeWindow(SkinWindow w);
    public abstract void unshadeWindow(SkinWindow w);

    public abstract void beginDraggingWindow(SkinWindow w);
    public abstract void dragWindow(SkinWindow w, int newX, int newY);
    public abstract void endDraggingWindow(SkinWindow w);

    public abstract void beginResizingWindow(SkinWindow w, int direction);
    public abstract void resizeWindow(SkinWindow w, int newX, int newY, int newWidth, int newHeight);
    public abstract void endResizingWindow(SkinWindow w);

    public abstract void setBoundsForWindow(Container f, int newX, int newY, int newWidth, int newHeight);
}



