import os
import re
import errno

#targetPath="D:\\code\\web\\bizpartner\\COMMON_NEWPACKAGE\\WebRoot\\WEB-INF\\src\\com\\shinhan\\spb\\user";
#goPath="D:\\code\\web\\bizpartner\\COMMON_NEWPACKAGE\\WebRoot\\WEB-INF\\src\\com\\shinhan\\biz\\user";
targetPath="D:\\code\\web\\bizpartner\\BPT_0000_TEST\\WebRoot\\WEB-INF\\src\\com\\shinhan\\spb\\user";
goPath="D:\\code\\web\\bizpartner\\BPT_0000_TEST\\WebRoot\\WEB-INF\\src\\com\\shinhan\\biz\\user";

searchStr="com.shinhan.spb.user";
goStr="com.shinhan.biz.user";

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

for (path, dir, files) in os.walk(targetPath):
    for filename in files:
		ext = os.path.splitext(filename)[-1]
		if ext == '.java':
			print path+'\\'+filename
			print path.replace(targetPath,goPath)+'\\'+filename
			mkdir_p(path.replace(targetPath,goPath))
		else:
			continue

		#print path;
		#print dir;

		f = open(path+'\\'+filename,'r')
		wf = open(path.replace(targetPath,goPath)+'\\'+filename,'w')
		for	line in f:
			if(line.find(searchStr)>=0):
				line=line.replace(searchStr,goStr)
				print line
			#wf.write(line+'\n')
			wf.write(line)
		print 'end'
		f.close();
		wf.close();