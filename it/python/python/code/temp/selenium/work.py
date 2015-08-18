from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

from pywinauto import application
from pywinauto.findwindows import find_windows

import threading
import time
import os






os.system("taskkill /F /T /IM iexplore.exe");
os.system("taskkill /F /T /IM IEDriverServer.exe");


caps = DesiredCapabilities.INTERNETEXPLORER
caps['ignoreProtectedModeSettings'] = True

browser = webdriver.Ie(capabilities=caps)
#driver = webdriver.Chrome()
browser.get("http://insadmin.shinhan.com:8080/mng/login.jsp")
print browser.title;

print "start : %s " % time.ctime()
time.sleep(5)
print "end : %s " % time.ctime()
print browser.title;



#########
cThread(1, "Thread-1", 1).start()
##########
browser.execute_script("doLogin()");
print "login END: %s " % time.ctime()
time.sleep(5)









browser.execute_script("location.href='/mng/bpt/O_INSM6020.jsp?mid=53'");

#element = browser.find_element_by_xpath("//*[@name='main']")
#print element.get_attribute("src");
#element.src='/mng/bpt/O_INSM6020.jsp?mid=53'
#browser.execute_script("document.frames[0].document.href='/mng/bpt/O_INSM6020.jsp?mid=53'");
print "END: %s " % time.ctime()
#element = browser.find_element_by_xpath("//*[@id='login']")
#element.click();
#browser.switch_to_frame("main.0.child")
#browser.execute_script("alert(1)");








#print driver.page_source
#driver.close()