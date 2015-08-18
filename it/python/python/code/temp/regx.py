import os
import re

def getSub(startStr,endStr,str):
	pattern = startStr+'(.*?)'+endStr;
	print pattern
	m = re.search(pattern,str);
	if m==None:
		return ''
	else :
		return m.group(1);
	

	
	
	
str ="<XDAID value='sfg.cib.xda.com.Select_CIB_MOKROK_01'/> +"
#print str
s = getSub("task='","'",str);
if len(s)<=0:
	s= getSub("value='","'",str);
print s
print getSub("action='","'",str);