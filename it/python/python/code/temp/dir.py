import os
import re


#wf = open('./TASK_COMMON_NEWPACKAGE.txt','w')
#wf = open('./TASK_BP2_KWLAND197.txt','w')
#wf = open('./TASK_BP2_KWLAND_CVS.txt','w')
#wf = open('./TASK_BP_HANYANGUNIV197.txt','w')
#wf = open('./TASK_BP_HANYANGUNIV.txt','w')
#wf = open('./TASK_BP2_DONGBU_SCH.txt','w')
#wf = open('./TASK_BP2_DONGBU.txt','w')
#wf = open('./TASK_DECOMPILE_CUSTOMAIZINGHANYANG.txt','w')


#wf = open('./TASK_BP2_CHEST197.txt','w')
#wf = open('./TASK_BP2_SSYENC_197.txt','w')
#wf = open('./TASK_BP_AIG197.txt','w')
#wf = open('./TASK_BP_BUCHON194.txt','w')
#wf = open('./TASK_BP2_SSENG1_197.txt','w')
#wf = open('./TASK_BP_ISAMT197.txt','w')
#wf = open('./TASK_BP_PARADISEHOTEL197.txt','w')
#wf = open('./TASK_BPT_NEXON.txt','w')
#wf = open('./TASK_BPT_KOREA7197.txt','w')
#wf = open('./TASK_BTP_GULMAKE_G1230_197.txt','w')




#wf = open('./TASK_DECOMPILE_CUSTOMAIZING_DONGBU_SCH.txt','w')
wf = open('./TASK_DECOMPILE_CUSTOMAIZING_DONGBU.txt','w')

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

#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BPT_0000_TEST"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\COMMON_NEWPACKAGE"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_KWLAND197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_KWLAND_CVS"):


#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_HANYANGUNIV197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_HANYANGUNIV"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_DONGBU_SCH"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_DONGBU"):
#for (path, dir, files) in os.walk("D:\\temp\\hanyang_webroot_decompile"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_CHEST197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_SSYENC_197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_AIG197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_BUCHON194"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP2_SSENG1_197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_ISAMT197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_PARADISEHOTEL197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BPT_NEXON"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BPT_KOREA7197"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BTP_GULMAKE_G1230_197"):

#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_DONGBU_SCH_DECOMPILE\\WebRoot\\WEB-INF\\src"):
for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BP_DONGBU_DECOMPILE\\WebRoot\\WEB-INF\\src"):



#for (path, dir, files) in os.walk("d:\\code\\python"):
#for (path, dir, files) in os.walk("D:\\code\\web\\bizpartner\\BPT_0000_TEST\\WebRoot\\WEB-INF\\src\\com\\shinhan\\spb\\common\\popup"):
    for filename in files:
        ext = os.path.splitext(filename)[-1]
        if ext == '.svn' or ext == '.log'  or ext == '.svn-base' or ext == '.class':
        	continue
        	
        #print(path+'\\'+filename)
        #print("%s\%s  %s" % (path, filename, os.path.isfile(path+'\\'+filename)))
        f = open(path+'\\'+filename,'r')
        
        firstFind = True;
        index=0;
        for	line in f:
        	index+=1
        	#if line.find("sfg.")>=0:
        	if line.find("sfg.")>=0 and path.find("custo")>=0:
        	#if path.find("custo")>=0 :
        		#if firstFind:
        		#	printV(path+'\\'+filename)
        		#	firstFind=False
        		
        		printV(path.replace('D:\\code\\web\\bizpartner\\COMMON_NEWPACKAGE\\','')+'\\'+filename)
        		line = line.replace("\t",'').strip().replace('\\"',"'").replace('"',"'")
        		try:
	        		task = getSub("task='","'",line);
	        		if len(task)<=0:
	        			task = getSub("value='","'",line);
	        		action = getSub("action='","'",line);
	        		printV('\t---\t[LINE:'+str(index)+']\t'+line+'\t'+task+'\t'+action+'\n')
	        		#printV('\t---\t'+line+'\t'+task+'\t'+action+'\n')
        		except Exception as inst:
        			printV('\t---\t[LINE:'+str(index)+']\t'+line+'\n')
        			#printV('\t---\t'+line+'\n')
        		
				
print 'end'
f.close();