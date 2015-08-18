import os
import re
import datetime
#wf = open('./IDC_KWLAND.txt','w')
#wf = open('./IDC_KWLAND_CLASS.txt','w')
#wf = open('./CVS_KWLAND_CLASS.txt','w')
#wf = open('./IDC_NEXON_CLASS.txt','w')
#wf = open('./IDC_NEXON.txt','w')
#wf = open('./CVS_NEXON_CLASS.txt','w')

#wf = open('./CVS_NEXON_HOL.txt','w')

#wf = open('./IDC_HANYANG.txt','w')
#wf = open('./IDC_HANYANG_CLASS.txt','w')
#wf = open('./CVS_HANYANG_CLASS.txt','w')



#wf = open('./IDC_NEXON.txt','w')
#wf = open('./IDC_NEXON_CLASS.txt','w')


#wf = open('./KYO_CLASS_06.txt','w')

def printV(str):
	wf.write(str)
	print str,


def getSub(startStr,endStr,str):
	pattern = startStr+'(.*?)'+endStr;
	print pattern
	m = re.search(pattern,str);
	if m==None:
		return ''
	else :
		return m.group(1);

def modification_date(filename):
	t = os.path.getmtime(filename)
	return datetime.datetime.fromtimestamp(t)
	
def getSize(filename):
	return os.path.getsize(filename)

def isFile(filename):
	os.path.isfile(filename)

def isDir(filename):
	os.path.isdir(filename)

#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_KWLAND197\\WebRoot"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_KWLAND197\\build"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_KWLAND_CVS\\build\\classes"): #KWLAND CVS
#for (path, dir, files) in os.walk("D:\\code\web\\bizpartner\\BTP_NEXON_197\\WebRoot"): 
#for (path, dir, files) in os.walk("D:\\code\web\\bizpartner\\BTP_NEXON_CVS\\build"): #NEXON CVS

#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BTP_NEXON_HOL_CVS\\build"): 
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_HANYANGUNIV197\\WebRoot"): 
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_HANYANGUNIV_CVS\\build"): 
for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_HANYANG_CVS\\build"): 

#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BTP_NEXON_197\WebRoot"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BTP_NEXON_197\\build"):


#for (path, dir, files) in os.walk("D:\\kyoPC\\classes"):
	for filename in files:
		ext = os.path.splitext(filename)[-1]
		pathext = ( path.find(".svn")>=0 or path.find("\\CVS")>=0 )
		#if ext == '.svn'  or ext == '.svn-base' or ext == '.class' or ext == '.java'  or ext == '.log' or ext == '.txt' or ext == '.text' or ext == '.cvs' or filename=='.cvsignore' or pathext:
		if ext == '.svn'  or ext == '.svn-base'  or ext == '.log' or ext == '.txt' or ext == '.text' or ext == '.cvs' or filename=='.cvsignore' or pathext:
			continue
		fileFullPath = path+'\\'+filename
		ext=ext.replace('.','');
		print path+'\t'+filename+'\t'+ext+'\t'+str(getSize(fileFullPath))+'\t'+str(modification_date(fileFullPath))
		printV(path+'\t'+filename+'\t'+ext+'\t'+str(getSize(fileFullPath))+'\t'+str(modification_date(fileFullPath))+'\n')
print 'end'
wf.close();
