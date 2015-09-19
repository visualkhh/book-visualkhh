package com.l2fprod.gui.plaf.skin;

/**
 * Skin Entry Class.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:58 $
 */
public interface Skin {

    public SkinPersonality getPersonality();

    public SkinButton getButton();

    public SkinFrame getFrame();

    public SkinTab getTab();

    public SkinProgress getProgress();

    public String[] getColors();

    public SkinScrollbar getScrollbar();

    public SkinSlider getSlider();

    public void unload();
}
