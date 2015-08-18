import threading
import time
from pywinauto import application
from pywinauto.findwindows import find_windows


class cThread (threading.Thread):
    def __init__(self, threadID, name, counter):
    	print '****1*****'
        threading.Thread.__init__(self)
        print '****2*****'
    def run(self):
        print "Starting "

        time.sleep(5)
        app = application.Application();
        handles = application.findwindows.find_windows()
        execute(app,handles)
        print "Exiting "

def execute(app,handles):
	for w_handle in handles:
		wind = app.window_(handle=w_handle)
		print wind.Texts()[0]
		print wind.Texts()
		if wind.Texts()[0].find(unicode(u"\uc778\uc99d\uc11c \uc120\ud0dd"))>=0:
		#wind.Edit.SetFocus();
			wind.Edit.TypeKeys("Rnadmfdnlgobbb01!");
		#wind.Edit.SetFocus();
			wind.TypeKeys("{TAB}")
			wind.TypeKeys("{ENTER}");
	
#print_time(self.name, self.counter, 5)
# Create new threads
#thread1 = cThread(1, "Thread-1", 1)

# Start new Threads
#thread1.start()