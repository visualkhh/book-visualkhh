import os
import re
import datetime


#name = "BPT_0000_211.115.7.197";
name = "BPT_GULMAKE_G1230_197";
wf = open('./BIZBANK_'+name+'.txt','w')
target_path = "D:\\code\\web\\bizpartner\\"+name


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


for (path, dir, files) in os.walk(target_path):
	for filename in files:
		ext = os.path.splitext(filename)[-1]
		if ext == '.svn' or ext == '.log'  or ext == '.svn-base' or ext == '.class':
			continue
		fileFullPath = path+'\\'+filename
		ext=ext.replace('.','');
		f = open(path+'\\'+filename,'r')
		index=0;
		for	line in f:
			index+=1
			if line.find("bizbank.s")>=0:
				line = line.replace("\t",'').strip().replace('\\"',"'").replace('"',"'")
				#printV( path+'\t'+filename+'\t'+ext+'\t'+str(getSize(fileFullPath))+'\t'+str(modification_date(fileFullPath)) + '\t---\t[LINE:'+str(index)+']\t'+line)
				printV( path+'\t'+filename+'\t'+ext+'\t'+str(getSize(fileFullPath))+'\t'+str(modification_date(fileFullPath)) +'\t--\t'+line+'\n')
		f.close();
print 'end'
wf.close();