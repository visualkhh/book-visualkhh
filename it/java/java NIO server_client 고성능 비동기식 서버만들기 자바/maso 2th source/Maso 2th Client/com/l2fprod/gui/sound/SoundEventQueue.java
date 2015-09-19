package com.l2fprod.gui.sound;

import java.awt.EventQueue;
import java.awt.AWTEvent;

/**
 * SoundEventQueue.
 * <br>
 * Easily add support to all events by intercepting the EventQueue.
 * <br><br>
 * <br><br>
 * Created on 08/06/2000 by Frederic Lavigne, fred@L2FProd.com
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.2 $, $Date: 2000/09/29 22:35:57 $
 */
public class SoundEventQueue extends EventQueue {

    private boolean firstEvent = true;
    public SoundEventQueue() {
    }

    protected final void dispatchEvent(AWTEvent event) {
	playSound(event);
	//not in jdk1.1, a JDK1.2 only version of skinlf should be created
	//super.dispatchEvent(event);
    }

    protected void playSound(AWTEvent event) {
    }

}
