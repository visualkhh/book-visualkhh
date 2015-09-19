package com.l2fprod.gui.plaf.skin.impl.kde;

import javax.swing.border.*;
import java.net.URL;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;

import com.l2fprod.util.IniFile;
import com.l2fprod.util.ImageUtils;
import com.l2fprod.gui.plaf.skin.*;
import com.l2fprod.gui.plaf.skin.impl.*;

/**
 * 
 * Created on 08/04/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.3 $, $Date: 2000/11/25 22:27:34 $
 */
class KdeFrameBorder extends SkinBorder {

    public KdeFrameBorder(IniFile ini, URL skinURL) throws Exception {
	super(getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapTop")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapBottom")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapLeft")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapRight")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapTopLeft")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapTopRight")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapBottomLeft")),
	      getIcon(skinURL, ini.getKeyValue("Window Border", "shapePixmapBottomRight")));
    }

}
