
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
import time
import os
from WindowHide import WindowHide

"""
 from pywinauto import application
from pywinauto.findwindows import find_windows
from cThread import cThread
"""





d = DesiredCapabilities.CHROME
d['loggingPrefs'] = { 'browser':'ALL' }
browser = webdriver.Chrome(desired_capabilities=d)

#caps = DesiredCapabilities.INTERNETEXPLORER
#caps['ignoreProtectedModeSettings'] = True
'''
browser = webdriver.Chrome()
browser.get("http://naver.com")
'''
'''
caps = DesiredCapabilities.INTERNETEXPLORER
caps['ignoreProtectedModeSettings'] = True
browser = webdriver.Ie(capabilities=caps)
'''
browser.get("http://naver.com")


################

hider = WindowHide('chrome.exe')
#To hide the window
hider.hide()
#hider.show()
################


for entry in browser.get_log('browser'):
    print entry

print browser.title;


time.sleep(2);

'''
print browser.find_element_by_id("loginframe")
'''
browser.switch_to_frame(browser.find_element_by_id("loginframe"));
element = browser.find_element_by_id("id")
element.send_keys("bunker09")
element = browser.find_element_by_id("pw")
element.send_keys("gdgdgd")
element.send_keys(Keys.RETURN)

time.sleep(2);
browser.switch_to_frame(browser.find_element_by_id("minime"));
#element = browser.find_element_by_id("mail_count_profile");
element = browser.find_element_by_css_selector("#mail_count_profile > span")
print 'MAIL COUNT: ' + element.get_attribute('innerHTML') 

