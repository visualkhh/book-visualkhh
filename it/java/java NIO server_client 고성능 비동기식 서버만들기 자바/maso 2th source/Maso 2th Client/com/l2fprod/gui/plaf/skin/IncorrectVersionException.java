package com.l2fprod.gui.plaf.skin;

/**
 * Thrown when a Theme Pack requires a SkinLF version greater than the current.
 * <br>
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:31:09 $
 */
public class IncorrectVersionException extends Exception {

    public IncorrectVersionException(String required, String current) {
	super("Incorrect Skin Look And Feel version, " +
	      "current version is " + current + ", required version is " + required);
    }

}
