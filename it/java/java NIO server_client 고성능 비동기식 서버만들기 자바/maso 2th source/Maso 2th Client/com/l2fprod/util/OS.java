package com.l2fprod.util;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:51 $
 */
public final class OS {

    private OS() {
    }

    public static void openDocument(String path) throws Exception {
	if (isWindows()) {	    
	    // we simply use the start.exe command available on win95/98/NT
	    // what about win2000 ?
	    Runtime.getRuntime().exec(new String[]{"start", path});
	} else {
	    System.err.println("OS.openDocument() not supported on this platform (" + System.getProperty("os.name"));
	}
    }

    public static boolean isWindows() { return isWindows95() || isWindowsNT(); }
    public static boolean isWindows95() { return isWindows95; }
    public static boolean isWindowsNT() { return isWindowsNT; }
    public static boolean isMacintosh() { return isMacintosh; }
    public static boolean isSolaris() { return isSolaris; }
    public static boolean isCaseSensitive() { return isCaseSensitive; }

    private static boolean isWindows95;
    private static boolean isWindowsNT;
    private static boolean isMacintosh;
    private static boolean isSolaris;
    private static boolean isCaseSensitive;

    static {
        String s = System.getProperty("os.name");
        if(s.equals("Windows NT"))
            isWindowsNT = true;
        else if(s.startsWith("Windows")) // win95 or win98
	    isWindows95 = true;
	else if(s.equals("Macintosh") || s.equals("macos") || s.equals("Mac OS") || s.equals("MacOS"))
	    isMacintosh = true;
        else if(s.equals("SunOS") || s.equals("Solaris")) {
            isSolaris = true;
            isCaseSensitive = true;
        }
    }
}
