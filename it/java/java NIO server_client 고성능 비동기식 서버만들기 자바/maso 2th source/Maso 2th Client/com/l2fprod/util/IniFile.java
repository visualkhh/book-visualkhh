package com.l2fprod.util;

import java.io.*;
import java.util.*;
import java.net.URL;

/**
 *
 * @author $Author: l2fprod $
 * @version $Revision: 1.1.1.1 $, $Date: 2000/07/26 19:30:51 $
 */
public class IniFile {
    
    Hashtable sections;

    public IniFile() {
	sections = new Hashtable();
    }

    public IniFile(String filename) throws FileNotFoundException {
	this();
	load(filename);
    }

    public IniFile(URL url) throws IOException {
	this();
	load(url.openStream());
    }

    public void load(String filename) throws FileNotFoundException {
	load(new FileInputStream(filename));
    }
    
    public void save(String filename) throws IOException {
	save(new FileOutputStream(filename));
    }
    
    public void load(InputStream in) {	
	try {
	    BufferedReader input = new BufferedReader(new InputStreamReader(in)); 
	    String read;
	    Hashtable section = null;
	    String section_name;
	    while ( (read = input.readLine()) != null) {
		if (read.startsWith(";") || read.startsWith("#"))
		    continue;
		else if (read.startsWith("[")) { // new section
		    section_name = read.substring(1, read.indexOf("]")).toLowerCase();
		    section = (Hashtable)sections.get(section_name);
		    if (section == null) {
			section = new Hashtable();
			sections.put(section_name, section);
		    }
		} else if (read.indexOf("=") != -1 && section!=null) { // new key
		    String key = read.substring(0, read.indexOf("=")).trim().toLowerCase();
		    String value = read.substring(read.indexOf("=")+1).trim();
		    section.put(key, value);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    public void save(OutputStream out) { 
  	try {
	    PrintWriter output = new PrintWriter(out);
	    String section;
	    for (Enumeration e = sections.keys() ; e.hasMoreElements() ;) {
		section = (String)e.nextElement();
		output.println("["+section+"]");
		for (Enumeration f = getSection(section).keys(), g = getSection(section).elements();
		     f.hasMoreElements() ;)
		    output.println(f.nextElement() + "=" + g.nextElement());
	    }
	    output.flush();
	    output.close();
	    out.flush();
	    out.close();
  	} catch (Exception e) {
	    e.printStackTrace();
  	}
    }
    
    public Hashtable getSections() {
	return sections;
    }
    
    public Hashtable getSection(String section) {
	return (Hashtable)(sections.get(section.toLowerCase()));
    }
    
    public void addSection(String section) {
	sections.put(section.toLowerCase(), new Hashtable());
    }
    
    public void removeSection(String section) { }
    
    public String getKeyValue(String section, String key) {
  	try {
	    return (String)getSection(section).get(key.toLowerCase());
	} catch(NullPointerException e) {
	    return null;
	}
    }
    
    public int getKeyIntValue(String section, String key) {
	return getKeyIntValue(section, key, 0);
    }
    
    public int getKeyIntValue(String section, String key, int defaultValue) {
	String value = getKeyValue(section, key.toLowerCase());
	if (value == null) {
	    return defaultValue;
	} else {
	    try {
		return Integer.parseInt(value);
	    } catch (NumberFormatException e) {
		return 0;
	    }
	}
    }

    public void setKeyValue(String section, String key, String value) {
  	try {
	    getSection(section).put(key.toLowerCase(),value);
	} catch(NullPointerException e) {
	    e.printStackTrace();
	}
    }
    
    public String[][] getKeysAndValues(String aSection) {
	Hashtable section = getSection(aSection);
	if (section==null) return null;
	String[][] results = new String[section.size()][2];
	int i=0;
	for (Enumeration f=section.keys(), g=section.elements(); f.hasMoreElements() ; i++) {
	    results[i][0] = (String)f.nextElement();
	    results[i][1] = (String)g.nextElement();
	}
	return results;
    }
    
    /**
     * Simple test function
     */
    public static void main(String[] args) throws Exception {
	(new IniFile()).load(new FileInputStream(args[0]));
    }
}
