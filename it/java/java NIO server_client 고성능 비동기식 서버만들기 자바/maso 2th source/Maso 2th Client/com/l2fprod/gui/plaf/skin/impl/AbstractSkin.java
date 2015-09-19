package com.l2fprod.gui.plaf.skin.impl;

import com.l2fprod.gui.plaf.skin.*;

/**
 * Default Skin Support.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:16 $
 */
public class AbstractSkin implements Skin {

    protected SkinPersonality personality;
    protected SkinButton button;
    protected SkinFrame frame;
    protected SkinTab tab;
    protected SkinProgress progress;
    protected SkinScrollbar scrollbar;
    protected SkinSlider slider;

    public SkinPersonality getPersonality() {
	return personality;
    }

    public SkinButton getButton() {
	return button;
    }
    
    public SkinFrame getFrame() {
	return frame;
    }

    public SkinTab getTab() {
	return tab;
    }

    public SkinProgress getProgress() {
	return progress;
    }

    public String[] getColors() {
	return null;
    }

    public SkinScrollbar getScrollbar() {
	return scrollbar;
    }

    public SkinSlider getSlider() {
	return slider;
    }

    public void unload() {
    }

}
