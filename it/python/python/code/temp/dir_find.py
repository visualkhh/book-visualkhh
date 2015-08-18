import os
import re
import datetime


#wf = open('./TASK_DECOMPILE_KWLAND.txt','w')
#wf = open('./TASK_DECOMPILE_NEXON_H.txt','w')
#wf = open('./TASK_DECOMPILE_NEXON.txt','w')
wf = open('./TASK_DECOMPILE_HANYANG.txt','w')



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
#target_path = "D:\\temp\\kwland_webroot_decompile\\classes"
#target_path = "D:\\temp\\nexon_h_webroot_decompile\\classes"
#target_path = "D:\\temp\\nexon_webroot_decompile\\classes"
target_path = "D:\\temp\\hanyang_webroot_decompile\\classes"
for (path, dir, files) in os.walk(target_path):
	for filename in files:
		ext = os.path.splitext(filename)[-1]
		if ext == '.svn' or ext == '.log'  or ext == '.svn-base' or ext == '.class':
			continue
		fileFullPath = path+'\\'+filename
		ext=ext.replace('.','');
        #print(path+'\\'+filename)
        #print("%s\%s  %s" % (path, filename, os.path.isfile(path+'\\'+filename)))
		f = open(path+'\\'+filename,'r')
		index=0;
		for	line in f:
			index+=1
			if line.find("sfg.")>=0:
				line = line.replace("\t",'').strip().replace('\\"',"'").replace('"',"'")
				printV( path+'\t'+filename+'\t'+ext+'\t'+str(getSize(fileFullPath))+'\t'+str(modification_date(fileFullPath)) )
				try:
					task = getSub("task='","'",line);
					if len(task)<=0:
						task = getSub("value='","'",line);
					action = getSub("action='","'",line);
					printV('\t---\t[LINE:'+str(index)+']\t'+line+'\t'+task+'\t'+action+'\n')
				except Exception as inst:
					printV('\t---\t[LINE:'+str(index)+']\t'+line+'\n')


		f.close();
print 'end'
wf.close();