from pywinauto import application
from pywinauto.findwindows import find_windows

app = application.Application();
handles = application.findwindows.find_windows()
for w_handle in handles:
	wind = app.window_(handle=w_handle)
	#print type(wind.Texts())
	print wind.Texts()[0]
	print wind.Texts()
	#print type(wind.Texts()[0])
	if wind.Texts()[0].find(unicode(u"\uc778\uc99d\uc11c \uc120\ud0dd"))>=0:
		print '-----------'
		#wind.Edit.SetFocus();
		wind.Edit.TypeKeys("Rnadmfdnlgobbb01!");
		#wind.Edit.SetFocus();
		wind.TypeKeys("{TAB}")
		wind.TypeKeys("{ENTER}");
		#wind.GetFocus().click()
		#wind[u"\ud655\uc778"].click()
	#if wind.Texts().cintain('EmEditor')
	#	print wind.Texts()
	

#a.edit.TypeKeys("ffffffff");

