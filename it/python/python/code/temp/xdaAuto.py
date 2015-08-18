from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities

from pywinauto import application
from pywinauto.findwindows import find_windows

from cThread import cThread

import time
import os

 

os.system("taskkill /F /IM iexplore.exe");
os.system("taskkill /F /IM IEDriverServer.exe");


caps = DesiredCapabilities.INTERNETEXPLORER
caps['ignoreProtectedModeSettings'] = True
browser = webdriver.Ie(capabilities=caps)
browser.get("http://125.130.60.183:9100/proworks/admin/login.html")
print browser.title;

element = browser.find_element_by_xpath("//*[@name='sUserID']")
element.send_keys("SGO1004016")
element = browser.find_element_by_xpath("//*[@name='sPwd']")
element.send_keys("SGO1004016")
element.send_keys(Keys.RETURN)

print browser.title;
time.sleep(2)
element = browser.execute_script("location.href='http://125.130.60.183:9100/proworks/menu/console.html?controllerServer=nxbwb1d&controllerServerName=&system=studio'");
print browser.title;
time.sleep(3)
print browser.title;
browser.execute_script("document.frames[0].callToolBarPage('XDA','/proworks/studio/xda/XDA.html')")

time.sleep(3)
print '-------------'

#browser.execute_script("document.frames[1].bodyFrame1.frames[1].findXDAList()");

browser.execute_script('	xmlDoc = getDocument("SYSLA01"); ')
browser.execute_script('	setAction(xmlDoc, "findXDAList"); ')
browser.execute_script('	setTask(xmlDoc, "com.inswave.system.sims.task.XDATask"); ')
browser.execute_script('	setProcessName(xmlDoc, "sims"); ')

browser.execute_script('	setString(xmlDoc, "sID", "" ); ')
browser.execute_script('	setString(xmlDoc, "sStartUpdateDate", "" ); ')
browser.execute_script('	setString(xmlDoc, "sEndUpdateDate", "" ); ')
browser.execute_script('	setString(xmlDoc, "nApproval", "0" ); ')
browser.execute_script('	setString(xmlDoc, "sUserID", ""); ')
browser.execute_script('	setString(xmlDoc, "sXDAName", "" ); ')
browser.execute_script('	setInt(xmlDoc, "nProjectID", 3); ')
browser.execute_script('	setInt(xmlDoc, "nModuleID", 11); ')
browser.execute_script('	callSocketXMLService( "callback:document.frames[1].bodyFrame1.frames[1]._findXDAListCall; processMsg:ing..; sync:false; debug:false;", "com.inswave.system.sims.task.XDATask", xmlDoc ); ')

	
browser.switch_to_frame(browser.find_element_by_name("bodyFrame"));
browser.switch_to_frame(browser.find_element_by_name("bodyFrame1"));
browser.switch_to_frame(browser.find_element_by_name("XDABody"));	
#browser.execute_script('alert(_getXDADetail);');
#browser.execute_script('alert(f_SetSelectedIndex)');
time.sleep(5)
#headers = browser.find_elements_by_class_name("FieldL1");
headers = browser.find_elements_by_xpath("//*[@class='FieldL1']/a");
print len(headers)
for at in headers:
	print at.innerHTML


'''
browser.execute_script('tsMain.enableTab(2);')
browser.execute_script('tsMain.selectedIndex = 2;')

browser.execute_script('xmlDoc = getDocument("<request/>"); ');
browser.execute_script('	setAction(xmlDoc, "getXDADetail"); ');
browser.execute_script('	setTask(xmlDoc, "com.inswave.system.sims.task.XDATask"); ');
browser.execute_script('	setProcessName(xmlDoc, "sims"); ');
browser.execute_script('	setString(xmlDoc, "nProjectID", "3"); ');
browser.execute_script('	setString(xmlDoc, "nModuleID", "11"); ');
browser.execute_script('	setString(xmlDoc, "sID", "XDATest.tmp7044.testSelect"); ');
browser.execute_script('	setString(xmlDoc, "nRevision", "1"); ');
browser.execute_script('	setString(xmlDoc, "sUserID", "SGO1004016"); ');
browser.execute_script('	callSocketXMLService( "callback:_getXDADetail; processMsg:ing..; sync:false; debug:false;", "com.inswave.system.sims.task.XDATask", xmlDoc ); ');
'''

#document.frames[1].bodyFrame1.frames[1].document.getElementById("Wrapper")

#document.frames[1].bodyFrame1.document.frames[1].document.body.innerHTML
#print browser.execute_script("document.frames[1].bodyFrame1.frames[1].document.body.innerHTML")
#print browser.execute_script("document.frames[1].bodyFrame1.frames[1].f_SetSelectedIndex(6);")
#browser.execute_script("document.frames[1].bodyFrame1.frames[1].f_SetSelectedIndex(6);")
#element = browser.find_element_by_xpath("//*[@name='XDABody']")
#print element.get_attribute("name");
#function f_TabOut() {

#element = browser.find_element_by_xpath("//*[@name='main']")
#print element.get_attribute("src");
#element.src='/mng/bpt/O_INSM6020.jsp?mid=53'
#browser.execute_script("document.frames[0].document.href='/mng/bpt/O_INSM6020.jsp?mid=53'");
#print "END: %s " % time.ctime()
#element = browser.find_element_by_xpath("//*[@id='login']")
#element.click();
#browser.switch_to_frame("main.0.child")
#browser.execute_script("alert(1)");








#print driver.page_source
#driver.close()ffffffff