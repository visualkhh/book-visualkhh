/* This file is part of NanoXML.
 *
 * $Revision: 1.1.1.1 $
 * $Date: 2000/07/26 19:30:49 $
 * $Name:  $
 *
 * Copyright (C) 2000 Marc De Scheemaecker, All Rights Reserved.
 *
 * This software is provided 'as-is', without any express or implied warranty.
 * In no event will the authors be held liable for any damages arising from the
 * use of this software.
 *
 * Permission is granted to anyone to use this software for any purpose,
 * including commercial applications, and to alter it and redistribute it
 * freely, subject to the following restrictions:
 *
 *  1. The origin of this software must not be misrepresented; you must not
 *     claim that you wrote the original software. If you use this software in
 *     a product, an acknowledgment in the product documentation would be
 *     appreciated but is not required.
 *
 *  2. Altered source versions must be plainly marked as such, and must not be
 *     misrepresented as being the original software.
 *
 *  3. This notice may not be removed or altered from any source distribution.
 */


package nanoxml;


import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;


/**
 * XMLElement is a representation of an XML object. The object is able to parse
 * XML code.
 * <P>
 * $Revision: 1.1.1.1 $<BR>
 * $Date: 2000/07/26 19:30:49 $<P>
 *
 * @see nanoxml.XMLParseException
 *
 * @author Marc De Scheemaecker
 *         &lt;<A HREF="mailto:Marc.DeScheemaecker@advalvas.be"
 *         >Marc.DeScheemaecker@advalvas.be</A>&gt;
 * @version 1.4
 */
public class XMLElement
{

   /**
    * Major version of NanoXML.
    */
   public static final int NANOXML_MAJOR_VERSION = 1;


   /**
    * Minor version of NanoXML.
    */
   public static final int NANOXML_MINOR_VERSION = 4;

   
   /**
    * The attributes given to the object.
    */
   private Properties attributes;
   
   
   /**
    * Subobjects of the object. The subobjects are of class XMLElement
    * themselves.
    */
   private Vector children;
   
   
   /**
    * The class of the object (the name indicated in the tag).
    */
   private String tagName;
   

   /**
    * The #PCDATA content of the object. If there is no such content, this
    * field is null.
    */
   private String contents;
   
   
   /**
    * Conversion table for &amp;...; tags.
    */
   private Properties conversionTable;


   /**
    * Whether to skip leading whitespace in CDATA.
    */
   private boolean skipLeadingWhitespace;
   
   
   /**
    * The line number where the element starts.
    */
   private int lineNr;

   
   /**
    * Creates a new XML element. The following settings are used:
    * <DL><DT>Conversion table</DT>
    *     <DD>Minimal XML conversions: <CODE>&amp;amp; &amp;lt; &amp;gt;
    *         &amp;apos; &amp;quot;</CODE></DD>
    *     <DT>Skip whitespace in contents</DT>
    *     <DD><CODE>false</CODE></DD>
    * </DL>
    *
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties)
    * @see nanoxml.XMLElement#XMLElement(boolean)
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties,boolean)
    */
   public XMLElement()
   {
      this(new Properties(), false, true);
   }
   

   /**
    * Creates a new XML element. The following settings are used:
    * <DL><DT>Conversion table</DT>
    *     <DD><I>conversionTable</I> combined with the minimal XML
    *         conversions: <CODE>&amp;amp; &amp;lt; &amp;gt;
    *         &amp;apos; &amp;quot;</CODE></DD>
    *     <DT>Skip whitespace in contents</DT>
    *     <DD><CODE>false</CODE></DD>
    * </DL>
    *
    * @see nanoxml.XMLElement#XMLElement()
    * @see nanoxml.XMLElement#XMLElement(boolean)
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties,boolean)
    */
   public XMLElement(Properties conversionTable)
   {
      this(conversionTable, false, true);
   }
   

   /**
    * Creates a new XML element. The following settings are used:
    * <DL><DT>Conversion table</DT>
    *     <DD>Minimal XML conversions: <CODE>&amp;amp; &amp;lt; &amp;gt;
    *         &amp;apos; &amp;quot;</CODE></DD>
    *     <DT>Skip whitespace in contents</DT>
    *     <DD><I>skipLeadingWhitespace</I></DD>
    * </DL>
    *
    * @see nanoxml.XMLElement#XMLElement()
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties)
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties,boolean)
    */
   public XMLElement(boolean skipLeadingWhitespace)
   {
      this(new Properties(), skipLeadingWhitespace, true);
   }
   

   /**
    * Creates a new XML element. The following settings are used:
    * <DL><DT>Conversion table</DT>
    *     <DD><I>conversionTable</I> combined with the minimal XML
    *         conversions: <CODE>&amp;amp; &amp;lt; &amp;gt;
    *         &amp;apos; &amp;quot;</CODE></DD>
    *     <DT>Skip whitespace in contents</DT>
    *     <DD><I>skipLeadingWhitespace</I></DD>
    * </DL>
    *
    * @see nanoxml.XMLElement#XMLElement()
    * @see nanoxml.XMLElement#XMLElement(boolean)
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties)
    */
   public XMLElement(Properties conversionTable,
                     boolean    skipLeadingWhitespace)
   {
      this(conversionTable, skipLeadingWhitespace, true);
   }
   

   /**
    * Creates a new XML element. The following settings are used:
    * <DL><DT>Conversion table</DT>
    *     <DD><I>conversionTable</I>, eventually combined with the minimal XML
    *         conversions: <CODE>&amp;amp; &amp;lt; &amp;gt;
    *         &amp;apos; &amp;quot;</CODE>
    *         (depending on <I>fillBasicConversionTable</I>)</DD>
    *     <DT>Skip whitespace in contents</DT>
    *     <DD><I>skipLeadingWhitespace</I></DD>
    * </DL>
    * <P>
    * This constructor should <I>only</I> be called from XMLElement itself
    * to create child elements.
    *
    * @see nanoxml.XMLElement#XMLElement()
    * @see nanoxml.XMLElement#XMLElement(boolean)
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties)
    * @see nanoxml.XMLElement#XMLElement(java.util.Properties,boolean)
    */
   private XMLElement(Properties conversionTable,
                      boolean    skipLeadingWhitespace,
                      boolean    fillBasicConversionTable)
   {
      this.skipLeadingWhitespace = skipLeadingWhitespace;
      this.tagName = null;
      this.contents = null;
      this.attributes = new Properties();
      this.children = new Vector();
      this.conversionTable = conversionTable;
      this.lineNr = 0;

      if (fillBasicConversionTable)
         {
            this.conversionTable.put("lt", "<");
            this.conversionTable.put("gt", ">");
            this.conversionTable.put("quot", "\"");
            this.conversionTable.put("apos", "'");
            this.conversionTable.put("amp", "&");
         }
   }
   
   
   /**
    * Returns the number of subobjects of the object.
    */
   public int countChildren()
   {
      return this.children.size();
   }
   
   
   /**
    * Enumerates the subobjects of the object.
    */
   public Enumeration enumerateChildren()
   {
      return this.children.elements();
   }
   
   
   /**
    * Returns the subobjects of the object.
    */
   public Vector getChildren()
   {
      return this.children;
   }
   
   
   /**
    * Returns the #PCDATA content of the object. If there is no such content,
    * <CODE>null</CODE> is returned.
    */
   public String getContents()
   {
      return this.contents;
   }
   
   
   /**
    * Returns the line nr on which the element is found.
    */
   public int getLineNr()
   {
      return this.lineNr;
   }
   
   
   /**
    * Returns a property by looking up a key in a hashtable. The property has
    * to be specified in capital letters.
    * If the property doesn't exist, the value corresponding to defaultValue
    * is returned.
    */
   public int getIntProperty(String    key,
                             Hashtable valueSet,
                             String    defaultValue)
   {
      String val = this.attributes.getProperty(key);
      Integer result;
      
      if (val == null)
         {
            val = defaultValue;
         }
      
      try
         {
            result = (Integer)(valueSet.get(val));
         }
      catch (ClassCastException e)
         {
            throw this.invalidValueSet(key);
         }
      
      if (result == null)
         {
            throw this.invalidValue(key, val, this.lineNr);
         }
      
      return result.intValue();
   }
   

   /**
    * Returns a property of the object. The property has to be specified in
    * capital letters. If there is no such property, this method returns
    * <CODE>null</CODE>.
    */
   public String getProperty(String key)
   {
      return this.attributes.getProperty(key);
   }
   
   
   /**
    * Returns a property of the object. The property has to be specified in
    * capital letters.
    * If the property doesn't exist, <I>defaultValue</I> is returned.
    */
   public String getProperty(String key,
                             String defaultValue)
   {
      return this.attributes.getProperty(key, defaultValue);
   }
   
   
   /**
    * Returns an integer property of the object. The property has to be
    * specified in capital letters.
    * If the property doesn't exist, <I>defaultValue</I> is returned.
    */
   public int getProperty(String key,
                        int    defaultValue)
   {
      String val = this.attributes.getProperty(key);
      
      if (val == null)
         {
            return defaultValue;
         }
      else
         {
            try
               {
                  return Integer.parseInt(val);
               }
            catch (NumberFormatException e)
               {
                  throw this.invalidValue(key, val, this.lineNr);
               }
         }
   }
   
   
   /**
    * Returns a floating point property of the object. The property has to be
    * specified in capital letters.
    * If the property doesn't exist, <I>defaultValue</I> is returned.
    */
   public double getProperty(String key,
                             double defaultValue)
   {
      String val = this.attributes.getProperty(key);
      
      if (val == null)
         {
            return defaultValue;
         }
      else
         {
            try
               {
                  return Double.valueOf(val).doubleValue();
               }
            catch (NumberFormatException e)
               {
                  throw this.invalidValue(key, val, this.lineNr);
               }
         }
   }
   
   
   /**
    * Returns a boolean property of the object. If the property is missing,
    * <I>defaultValue</I> is returned.
    */
   public boolean getProperty(String  key,
                              String  trueValue,
                              String  falseValue,
                              boolean defaultValue)
   {
      String val = this.attributes.getProperty(key);
      
      if (val == null)
         {
            return defaultValue;
         }
      else if (val.equals(trueValue))
         {
            return true;
         }
      else if (val.equals(falseValue))
         {
            return false;
         }
      else
         {
            throw this.invalidValue(key, val, this.lineNr);
         }
   }
   
   
   /**
    * Returns a property by looking up a key in the hashtable <I>valueSet</I>.
    * The property has to be specified in capital letters.
    * If the property doesn't exist, the value corresponding to
    * <I>defaultValue</I>  is returned.
    */
   public Object getProperty(String    key,
                             Hashtable valueSet,
                             String    defaultValue)
   {
      String val = this.attributes.getProperty(key);
      
      if (val == null)
         {
            val = defaultValue;
         }
      
      Object result = valueSet.get(val);
      
      if (result == null)
         {
            throw this.invalidValue(key, val, this.lineNr);
         }
      
      return result;
   }
   
                                       
   /**
    * Returns a property by looking up a key in the hashtable <I>valueSet</I>.
    * The property has to be specified in capital letters.
    * If the property doesn't exist, the value corresponding to
    * <I>defaultValue</I>  is returned.
    */
   public String getStringProperty(String    key,
                                   Hashtable valueSet,
                                   String    defaultValue)
   {
      String val = this.attributes.getProperty(key);
      String result;
      
      if (val == null)
         {
            val = defaultValue;
         }
      
      try
         {
            result = (String)(valueSet.get(val));
         }
      catch (ClassCastException e)
         {
            throw this.invalidValueSet(key);
         }
      
      if (result == null)
         {
            throw this.invalidValue(key, val, this.lineNr);
         }
      
      return result;
   }
   
                                       
   /**
    * Returns a property by looking up a key in the hashtable <I>valueSet</I>.
    * The property has to be specified in capital letters.
    * If the value is not defined in the hashtable, the value is considered to
    * be an integer.
    * If the property doesn't exist, the value corresponding to
    * <I>defaultValue</I> is returned.
    */
   public int getSpecialIntProperty(String    key,
                                    Hashtable valueSet,
                                    String    defaultValue)
   {
      String val = this.attributes.getProperty(key);
      Integer result;
      
      if (val == null)
         {
            val = defaultValue;
         }
      
      try
         {
            result = (Integer)(valueSet.get(val));
         }
      catch (ClassCastException e)
         {
            throw this.invalidValueSet(key);
         }
      
      if (result == null)
         {
            try
               {
                  return Integer.parseInt(val);
               }
            catch (NumberFormatException e)
               {
                  throw this.invalidValue(key, val, this.lineNr);
               }
         }
      
      return result.intValue();
   }
   
                                       
   /**
    * Returns a property by looking up a key in the hashtable <I>valueSet</I>.
    * The property has to be specified in capital letters.
    * If the value is not defined in the hashtable, the value is considered to
    * be a floating point number.
    * If the property doesn't exist, the value corresponding to
    * <I>defaultValue</I> is returned.
    */
   public double getSpecialDoubleProperty(String    key,
                                          Hashtable valueSet,
                                          String    defaultValue)
   {
      String val = this.attributes.getProperty(key);
      Double result;
      
      if (val == null)
         {
            val = defaultValue;
         }
      
      try
         {
            result = (Double)(valueSet.get(val));
         }
      catch (ClassCastException e)
         {
            throw this.invalidValueSet(key);
         }
      
      if (result == null)
         {
            try
               {
                  result = Double.valueOf(val);
               }
            catch (NumberFormatException e)
               {
                  throw this.invalidValue(key, val, this.lineNr);
               }
         }
      
      return result.doubleValue();
   }
   
                                       
   /**
    * Returns the class (i.e. the name indicated in the tag) of the object.
    */
   public String getTagName()
   {
      return this.tagName;
   }


   /**
    * Checks whether a character may be part of an identifier.
    */
   private boolean isIdentifierChar(char ch)
   {
      return (((ch >= 'A') && (ch <= 'Z')) || ((ch >= 'a') && (ch <= 'z'))
               || ((ch >= '0') && (ch <= '9')) || (".-_:".indexOf(ch) >= 0));
   }
    

   /**
    * Reads an XML definition from a java.io.Reader and parses it.
    *
    * @exception java.io.IOException
    *    if an error occured while reading the input
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the read data
    */
   public void parseFromReader(Reader reader)
      throws IOException, XMLParseException
   {
      this.parseFromReader(reader, 1);
   }
   

   /**
    * Reads an XML definition from a java.io.Reader and parses it.
    *
    * @exception java.io.IOException
    *    if an error occured while reading the input
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the read data
    */
   public void parseFromReader(Reader reader,
                               int    startingLineNr)
      throws IOException, XMLParseException
   {
      int blockSize = 4096;
      char[] input = null;
      int size = 0;
      
      while (reader.ready())
         {
            if (input == null)
               {
                  input = new char[blockSize];
               }
            else
               {
                  char[] oldInput = input;
                  input = new char[input.length + blockSize];
                  System.arraycopy(oldInput, 0, input, 0, oldInput.length);
               }
            
            size += reader.read(input, size, blockSize);
         }
         
      this.parseCharArray(input, 0, size, startingLineNr);
   }
 
 
   /**
    * Parses an XML definition.
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the string
    */
   public void parseString(String string)
      throws XMLParseException
   {
      this.parseCharArray(string.toCharArray(), 0, string.length(), 1);
   }
   
   
   /**
    * Parses an XML definition starting at <I>offset</I>.
    *
    * @return the offset of the string following the XML data
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the string
    */
   public int parseString(String string,
                          int    offset)
      throws XMLParseException
   {
      return this.parseCharArray(string.toCharArray(), offset,
                                 string.length(), 1);
   }
   
   
   /**
    * Parses an XML definition starting at <I>offset</I>.
    *
    * @return the offset of the string following the XML data (<= end)
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the string
    */
   public int parseString(String string,
                          int    offset,
                          int    end)
      throws XMLParseException
   {
      return this.parseCharArray(string.toCharArray(), offset, end, 1);
   }
   
   
   /**
    * Parses an XML definition starting at <I>offset</I>.
    *
    * @return the offset of the string following the XML data (<= end)
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the string
    */
   public int parseString(String string,
                          int    offset,
                          int    end,
                          int    startingLineNr)
      throws XMLParseException
   {
      return this.parseCharArray(string.toCharArray(), offset, end,
                                 startingLineNr);
   }
   
   
   /**
    * Parses an XML definition starting at <I>offset</I>.
    *
    * @return the offset of the array following the XML data (<= end)
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   public int parseCharArray(char[] input,
                             int    offset,
                             int    end)
      throws XMLParseException
   {
      return this.parseCharArray(input, offset, end, 1);
   }
   
   
   /**
    * Parses an XML definition starting at <I>offset</I>.
    *
    * @return the offset of the array following the XML data (<= end)
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   public int parseCharArray(char[] input,
                             int    offset,
                             int    end,
                             int    startingLineNr)
      throws XMLParseException
   {
      int[] lineNr = new int[1];
      lineNr[0] = startingLineNr;
      return this.parseCharArray(input, offset, end, lineNr);
   }
   
   
   /**
    * Parses an XML definition starting at <I>offset</I>.
    *
    * @return the offset of the array following the XML data (<= end)
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int parseCharArray(char[] input,
                              int    offset,
                              int    end,
                              int[]  currentLineNr)
      throws XMLParseException
   {
      offset = this.skipWhitespace(input, offset, end, currentLineNr);
      offset = this.skipPreamble(input, offset, end, currentLineNr);
      offset = this.scanTagName(input, offset, end, currentLineNr);
      this.lineNr = currentLineNr[0];
      offset = this.scanAttributes(input, offset, end, currentLineNr);
      int[] contentOffset = new int[1];
      int[] contentSize = new int[1];
      int contentLineNr = currentLineNr[0];
      offset = this.scanContent(input, offset, end,
                                contentOffset, contentSize, currentLineNr);

      if (contentSize[0] > 0)
         {
            this.scanChildren(input, contentOffset[0], contentSize[0],
                              contentLineNr);
            
            if (this.children.size() > 0)
               {
                  this.contents = null;
               }
            else 
               {
                  this.processContents(input, contentOffset[0],
                                       contentSize[0], contentLineNr);
               }
         }
      
      return offset;
   }

   
   /**
    * Decodes the entities in the contents and, if skipLeadingWhitespace is
    * <CODE>true</CODE>, removes extraneous whitespaces after newlines and
    * convert those newlines into spaces.
    *
    * @see nanoxml.XMLElement#decodeString
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private void processContents(char[] input,
                                int    contentOffset,
                                int    contentSize,
                                int    contentLineNr)
      throws XMLParseException
   {
      //System.out.println("parseContents(offset=" + contentOffset
      //+ ", size="+ contentSize + ", lineNr=" + contentLineNr + ")");
      int[] lineNr = new int[1];
      lineNr[0] = contentLineNr;
      
      if (! this.skipLeadingWhitespace)
         {
            String str = new String(input, contentOffset, contentSize);
            this.contents = this.decodeString(str, lineNr[0]);
            return;
         }
      
      StringBuffer result = new StringBuffer(contentSize);
      int end = contentSize + contentOffset;
      
      for (int i = contentOffset; i < end; i++)
         {
            char ch = input[i];
            
            // The end of the contents is always a < character, so there's
            // no danger for bounds violation
            while ((ch == '\r') || (ch == '\n'))
               {
                  lineNr[0]++;
                  result.append(ch);
                  
                  i++;
                  ch = input[i];
                  
                  if (ch != '\n')
                     {
                        result.append(ch);
                     }
                  
                  do
                     {
                        i++;
                        ch = input[i];
                     } while ((ch == ' ') || (ch == '\t'));
               }
            
            if (i < end) 
               {
                  result.append(ch);
               }
         }
         
      this.contents = this.decodeString(result.toString(), lineNr[0]);
   }
   
   
   /**
    * Scans the attributes of the object.
    *
    * @return the offset in the string following the attributes, so that
    *         input[offset] in { '/', '>' }
    *
    * @see nanoxml.XMLElement#scanOneAttribute
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int scanAttributes(char[] input,
                              int    offset,
                              int    end,
                              int[]  lineNr)
      throws XMLParseException
   {
      //System.out.println("scanAttributes(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      String key, value;
      
      for (;;)
         {
            offset = this.skipWhitespace(input, offset, end, lineNr);
            
            char ch = input[offset];
         
            if ((ch == '/') || (ch == '>'))
               {
                  break;
               }
            
            offset = this.scanOneAttribute(input, offset, end, lineNr);
         }
      
      return offset;
   }
   
   
   /**
    * Searches the content for child objects. If such objects exist, the
    * content is reduced to <CODE>null</CODE>.
    *
    * @see nanoxml.XMLElement#parseCharArray
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private void scanChildren(char[] input,
                             int    contentOffset,
                             int    contentSize,
                             int    contentLineNr)
      throws XMLParseException
   {
      //System.out.println("scanChildren(offset=" + contentOffset
      //      + ", size=" + contentSize
      //      + ", lineNr=" + contentLineNr + ")");
      int end = contentOffset + contentSize;
      int offset = contentOffset;
      int lineNr[] = new int[1];
      lineNr[0] = contentLineNr;
      
      while (offset < end)
         {
            try
               {
                  offset = this.skipWhitespace(input, offset, end, lineNr);
               }
            catch (XMLParseException e)
               {
                  return;
               }
            
            if (input[offset] != '<')
               {
                  return;
               }
               
            XMLElement child = new XMLElement(this.conversionTable,
                                              this.skipLeadingWhitespace,
                                              false);
            offset = child.parseCharArray(input, offset, end, lineNr);
            this.children.addElement(child);
         }
   }
   
   
   /**
    * Scans the content of the object.
    *
    * @return the offset after the XML element; contentOffset points to the
    *         start of the content section; contentSize is the size of the
    *         content section
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int scanContent(char[] input,
                           int    offset,
                           int    end,
                           int[]  contentOffset,
                           int[]  contentSize,
                           int[]  lineNr)
      throws XMLParseException
   {
      //System.out.println("scanContent(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      if (input[offset] == '/')
         {
            contentSize[0] = 0;
            
            if (input[offset + 1] != '>')
               {
                  throw this.expectedInput("'>'", lineNr[0]);
               }
            
            return offset + 2;
         }
      
      if (input[offset] != '>')
         {
            throw this.expectedInput("'>'", lineNr[0]);
         }

      if (this.skipLeadingWhitespace)
         {
            offset = this.skipWhitespace(input, offset + 1, end, lineNr);
         }
      else
         {
            offset++;
         }
      
      int begin = offset;
      contentOffset[0] = offset;
      int level = 0;
      char[] tag = this.tagName.toCharArray();
      end -= (tag.length + 2);

      while ((offset < end) && (level >= 0))
         {
            if (input[offset] == '<')
               {
                  boolean ok = true;
                  
                  for (int i = 0; ok && (i < tag.length); i++)
                     {
                        ok &= (input[offset + (i + 1)] == tag[i]);
                     }
                  
                  if (ok)
                     {
                        int off2 = offset + tag.length + 1;
                        offset = this.skipWhitespace(input, off2, end,
                                                     lineNr);
                        
                        // Open level if /<BLAH[ \t]+[^\/]/ or /<BLAH[ \t]*>/
                        if ((input[offset] == '>')
                           || ((off2 != offset) && (input[offset] != '/')))
                           {
                              level++;
                           }
                           
                        continue;
                     }
                  else if (input[offset + 1] == '/')
                     {
                        ok = true;
                  
                        for (int i = 0; ok && (i < tag.length); i++)
                           {
                              ok &= (input[offset + (i + 2)] == tag[i]);
                           }
                        
                        if (ok)
                           {
                              contentSize[0] = offset - contentOffset[0];
                              offset += tag.length + 2;
                              offset = this.skipWhitespace(input, offset, end,
                                                           lineNr);
                              
                              if (input[offset] == '>')
                                 {
                                    level--;
                                    offset++;
                                 }
                                 
                              continue;
                           }                        
                     }
               }
            
            if (input[offset] == '\r')
               {
                  lineNr[0]++;
                  
                  if ((offset != end) && (input[offset + 1] == '\n'))
                     {
                        offset++;
                     }
               }
            else if (input[offset] == '\n')
               {
                  lineNr[0]++;
               }
               
            offset++;
         }
         
      if (level >= 0)
         {
            throw this.unexpectedEndOfData(lineNr[0]);
         }
         
      if (this.skipLeadingWhitespace)
         {
            int i = contentOffset[0] + contentSize[0] - 1;
            
            while ((contentSize[0] >= 0) && (input[i] <= ' '))
               {
                  i--;
                  contentSize[0]--;
               }
         }
         
      return offset;
   }

   
   /**
    * Scans an identifier.
    *
    * @return the identifier, or <CODE>null</CODE> if offset doesn't point
    *         to an identifier
    */
   private String scanIdentifier(char[] input,
                                 int    offset,
                                 int    end)
   {
      //System.out.println("scanIdentifier(offset=" + offset + ", end="
      // + end + ")");
      int begin = offset;
      
      while ((offset < end) && (this.isIdentifierChar(input[offset])))
         {
            offset++;
         }
      
      if ((offset == end) || (offset == begin))
         {
            return null;
         }
      else 
         {
            return new String(input, begin, offset - begin);
         }
   }
   
   
   /**
    * Scans one attribute of an object.
    *
    * @return the offset after the attribute
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int scanOneAttribute(char[] input,
                                int    offset,
                                int    end,
                                int[]  lineNr)
      throws XMLParseException
   {
      //System.out.println("scanOneAttribute(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      String key, value;
      
      key = this.scanIdentifier(input, offset, end);

      if (key == null)
         {
            throw this.syntaxError("an attribute key", lineNr[0]);
         }
      
      offset = this.skipWhitespace(input, offset + key.length(), end, lineNr);
      key = key.toUpperCase();
      
      if (input[offset] != '=')
         {
            throw this.valueMissingForAttribute(key, lineNr[0]);
         }
      
      offset = this.skipWhitespace(input, offset + 1, end, lineNr);
      
      value = this.scanString(input, offset, end, lineNr);
      
      if (value == null)
         {
            throw this.syntaxError("an attribute value", lineNr[0]);
         }
      
      if (value.charAt(0) == '"')
         {
            value = value.substring(1, (value.length() - 1));
            offset += 2;
         }

      this.attributes.put(key, this.decodeString(value, lineNr[0]));
      return offset + value.length();    
   }
   
   
   /**
    * Scans a string. Strings are either identifiers, or text delimited by
    * double quotes.
    *
    * @return the string found, without delimiting double quotes; or null
    *         if offset didn't point to a valid string
    *
    * @see nanoxml.XMLElement#scanIdentifier
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private String scanString(char[] input,
                             int    offset,
                             int    end,
                             int[]  lineNr)
      throws XMLParseException
   {
      //System.out.println("scanString(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      char delim = input[offset];
      
      if (delim == '"')
         {
            int begin = offset;
            offset++;
            
            while ((offset < end) && (input[offset] != delim))
               {
                  if (input[offset] == '\r')
                     {
                        lineNr[0]++;
                        
                        if ((offset != end) && (input[offset + 1] == '\n'))
                           {
                              offset++;
                           }
                     }
                  else if (input[offset] == '\n')
                     {
                        lineNr[0]++;
                     }
                     
                  offset++;
               }
            
            if (offset == end)
               {
                  return null;
               }
            else
               {
                  return new String(input, begin, offset - begin + 1);
               }
         }
      else
         {
            return this.scanIdentifier(input, offset, end);
         }
   }
   
   
   /**
    * Scans the class (tag) name of the object.
    *
    * @return the position after the tag name
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int scanTagName(char[] input,
                           int    offset,
                           int    end,
                           int[]  lineNr)
      throws XMLParseException
   {
      //System.out.println("scanTagName(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      this.tagName = this.scanIdentifier(input, offset, end);

      if (this.tagName == null)
         {
            throw this.syntaxError("a tag name", lineNr[0]);
         }
      
      return offset + this.tagName.length();
   }
   
   
   /**
    * Skips a tag that don't contain any useful data: &lt;?...?&gt;,
    * &lt;!...&gt; and comments.
    *
    * @return the position after the tag
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int skipBogusTag(char[] input,
                            int    offset,
                            int    end,
                            int[]  lineNr)
   {
      //System.out.println("skipBogusTag(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      if ((input[offset + 1] == '-') && (input[offset + 2] == '-'))
         {
            while ((offset < end)
                   && ((input[offset] != '-')
                       || (input[offset + 1] != '-')
                       || (input[offset + 2] != '>')))
               {
                  if (input[offset] == '\r')
                     {
                        lineNr[0]++;
                        
                        if ((offset != end) && (input[offset + 1] == '\n'))
                           {
                              offset++;
                           }
                     }
                  else if (input[offset] == '\n')
                     {
                        lineNr[0]++;
                     }
                     
                  offset++;
               }


            if (offset == end)
               {
                  throw unexpectedEndOfData(lineNr[0]);
               }
            else
               {
                  return offset + 3;
               }
         }
      
      int level = 1;
      
      while (offset < end)
         {
            char ch = input[offset++];
            
            switch (ch)
               {
                  case '\r':
                     if ((offset < end) && (input[offset] == '\n'))
                        {
                           offset++;
                        }
                        
                     lineNr[0]++;
                     break;
                     
                  case '\n':
                     lineNr[0]++;
                     break;
                     
                  case '<':
                     level++;
                     break;
                  
                  case '>':
                     level--;
   
                     if (level == 0) {
                        return offset;
                     }
                     
                     break;
                     
                  default:
               }
         }
      
      throw this.unexpectedEndOfData(lineNr[0]);
   }
   
   
   /**
    * Skips a tag that don't contain any useful data: &lt;?...?&gt;,
    * &lt;!...&gt; and comments.
    *
    * @return the position after the tag
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int skipPreamble(char[] input,
                            int    offset,
                            int    end,
                            int[]  lineNr)
      throws XMLParseException
   {
      //System.out.println("skipPreamble(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      char ch;

      do
         {
            offset = this.skipWhitespace(input, offset, end, lineNr);

            if (input[offset] != '<')
               {
                  this.expectedInput("'<'", lineNr[0]);
               }
         
            offset++;
         
            if (offset >= end)
               {
                  throw this.unexpectedEndOfData(lineNr[0]);
               }
         
            ch = input[offset];
            
            if ((ch == '!') || (ch == '?'))
               {
                  offset = this.skipBogusTag(input, offset, end, lineNr);
               }
         } while (! isIdentifierChar(ch));
         
      return offset;
   }
   
   
   /**
    * Skips whitespace characters.
    *
    * @return the position after the whitespace
    *
    * @exception nanoxml.XMLParseException
    *    if an error occured while parsing the array
    */
   private int skipWhitespace(char[] input,
                              int    offset,
                              int    end,
                              int[]  lineNr)
   {      
      //System.out.println("skipWhitespace(offset=" + offset + ", end=" + end
      //      + ", lineNr=" + lineNr[0] + ")");
      while ((offset < end) && (input[offset] <= ' '))
         {
            if (input[offset] == '\r')
               {
                  lineNr[0]++;
                  
                  if ((offset != end) && (input[offset + 1] == '\n'))
                     {
                        offset++;
                     }
               }
            else if (input[offset] == '\n')
               {
                  lineNr[0]++;
               }
               
            offset++;
         }
   
      if (offset == end)
         {
            throw this.unexpectedEndOfData(lineNr[0]);
         }

      return offset;
   }


   /**
    * Converts &amp;...; sequences to "normal" chars.
    */
   private String decodeString(String s,
                               int    lineNr)
   {
      StringBuffer result = new StringBuffer(s.length());
      int index = 0;

      for (;;)
         {
            int index2 = (s + '&').indexOf('&', index);
            result.append(s.substring(index, index2));
   
            if (index2 == s.length())
               {
                  break;
               }
   
            index = s.indexOf(';', index2);
         
            if (index < 0)
               {
                  result.append(s.substring(index2));
                  break;
               }
   
            String key = s.substring(index2 + 1, index);
            
            if (key.charAt(0) == '#')
               {
                  if (key.charAt(1) == 'x')
                     {
                        result.append((char)(Integer.
                                                parseInt(key.substring(2),
                                                         16)));
                     }
                  else
                     {
                        result.append((char)(Integer.
                                             parseInt(key.substring(1), 10)));
                     }
               }
            else
               {
                  result.append(this.conversionTable
                                       .getProperty(key, "&" + key + ';'));
               }
            
            index++;
         }
   
      return result.toString();
   }
   
   
   /**
    * Creates a parse exception for when an invalid valueset is given to
    * a method.
    */
   private XMLParseException invalidValueSet(String key)
   {
      String msg = "Invalid value set (key = \"" + key + "\")";
      return new XMLParseException(this.getTagName(), msg);
   }


   /**
    * Creates a parse exception for when an invalid value is given to a
    * method.
    */
   private XMLParseException invalidValue(String key,
                                          String value,
                                          int    lineNr)
   {
      String msg = "Attribute \"" + key + "\" does not contain a valid "
                   + "value (\"" + value + "\")";
      return new XMLParseException(this.getTagName(), lineNr,  msg);
   }


   /**
    * The end of the data input has been reached.
    */
   private XMLParseException unexpectedEndOfData(int lineNr)
   {
      String msg = "Unexpected end of data reached";
      return new XMLParseException(this.getTagName(), lineNr,  msg);
   }
   
   
   /**
    * A syntax error occured.
    */
   private XMLParseException syntaxError(String context,
                                         int    lineNr)
   {
      String msg = "Syntax error while parsing " + context;
      return new XMLParseException(this.getTagName(), lineNr,  msg);
   }


   /**
    * A character has been expected.
    */
   private XMLParseException expectedInput(String charSet,
                                           int    lineNr)
   {
      String msg = "Expected: " + charSet;
      return new XMLParseException(this.getTagName(), lineNr,  msg);
   }


   /**
    * A value is missing for an attribute.
    */
   private XMLParseException valueMissingForAttribute(String key,
                                                      int    lineNr)
   {
      String msg = "Value missing for attribute with key \"" + key + "\"";
      return new XMLParseException(this.getTagName(), lineNr,  msg);
   }

}
