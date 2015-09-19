package com.l2fprod.gui.plaf.skin;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;

import com.sun.jimi.core.*;
import com.sun.jimi.core.compat.*;
import com.sun.jimi.core.decoder.xpm.*;
import com.sun.jimi.core.raster.*;

/**
 * 
 * Created on 30/09/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1 $, $Date: 2000/09/29 22:42:30 $
 */
class JimiUtils {

    public static Image loadImage(URL url) throws Exception {
	// hack to xpm decoder
	if (url.toString().toLowerCase().endsWith(".xpm")) {	    
	    XPMDecoderFactory factory = new XPMDecoderFactory();
	    MyXPMDecoder decoder = new MyXPMDecoder();
	    AdaptiveRasterImage image = new AdaptiveRasterImage(new OneshotJimiImageFactory());
	    decoder.initDecoder(url.openStream(), image);
	    decoder.driveDecoder();
	    return decoder.createImage();
	}
	return Jimi.getImage(url.openStream(), Jimi.SYNCHRONOUS);
    }

    static class MyXPMDecoder extends XPMDecoder {
	public Image createImage() {
	    return Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(parser_.getWidth(),
										 parser_.getHeight(),
										 jimiImage_.getColorModel(),
									      parser_.getPixmap(), 0, parser_.getWidth()));
	}
    }

    public static JimiRasterImage loadJimiImage(File file) throws Exception {
	return Jimi.getRasterImage(new FileInputStream(file), Jimi.SYNCHRONOUS);
    }

}
