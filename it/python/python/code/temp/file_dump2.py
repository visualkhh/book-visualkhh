import os
import re
import errno
targetPath="C:\\Users\\Administrator\\Downloads\\log";
goPath="C:\\Users\\Administrator\\Downloads\\logfilter";
searchStr="customize";
searchNotStr="customize.schedule";
# goStr="com.shinhan.biz.user";

def is_non_zero_file(fpath):  
	return True if os.path.isfile(fpath) and os.path.getsize(fpath) > 0 else False
def mkdir_p(path):
	try:
		os.makedirs(path)
	except OSError as exc: # Python >2.5
		if exc.errno == errno.EEXIST and os.path.isdir(path):
			pass
		else: raise

def getSub(startStr,endStr,str):
	pattern = startStr+'(.*?)'+endStr;
	print pattern
	m = re.search(pattern,str);
	if m==None:
		return ''
	else :
		return m.group(1);

def isFind(path, str):
	if not(is_non_zero_file(path)):
		return False;
		
	sw = False;
	fs = open(path,'r')
	for line in fs:
		if(line.find(str)>=0):
			return True;
	fs.close();	
	return sw;

for (path, dir, files) in os.walk(targetPath):
	for filename in files:
		ext = os.path.splitext(filename)[-1]
		print path+'\\'+filename
		f = open(path+'\\'+filename,'r')
		
		for line in f:
			if(line.find(searchNotStr)>=0):
				continue;
			if(line.find(searchStr)>=0):
				print str(isFind(path.replace(targetPath,goPath)+'\\'+filename,line))
				print line
				if (isFind(path.replace(targetPath,goPath)+'\\'+filename,line) == False):
					wf = open(path.replace(targetPath,goPath)+'\\'+filename,'a')
					wf.write(line)
					wf.close();
				

		print 'end'
  		f.close();
  		#wf.close();
