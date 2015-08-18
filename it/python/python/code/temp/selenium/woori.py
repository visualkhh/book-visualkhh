from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait

browser = webdriver.Chrome()
browser.get("https://spib.wooribank.com/pib/Dream?withyou=CMLGN0001")
print browser.title;
print "start : %s " % time.ctime()
time.sleep(5*1000)
print "end : %s " % time.ctime()
browser.execute_script("doLogin()");
#element = driver.find_element_by_xpath("//*[@id='introHeader']/div[1]/div[1]/div/div/span/a[1]")
# //*[@id="introHeader"]/div[1]/div[1]/div/div/span/a[1]
#href = element.get_attribute('href');
#print href
#element.click()



#print driver.page_source
#driver.close()


