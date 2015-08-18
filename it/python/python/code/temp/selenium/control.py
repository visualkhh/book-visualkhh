from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

driver = webdriver.Chrome()
driver.get("http://www.naver.com/")
print driver.title;
#assert "Python" in driver.title

#elem = driver.find_element_by_name("q")
#elem.send_keys("pycon")
#elem.send_keys(Keys.RETURN)

## Element Access
#element = driver.find_element_by_id("passwd-id")
#element = driver.find_element_by_name("passwd")
element = driver.find_element_by_xpath("//*[@id='query']")
#driver.find_element(By.XPATH, '//button[text()="Some text"]')
#el = driver.find_elements(By.XPATH, '//button')
#print type (el);
#print el
#element = driver.find_element_by_id('query');
element.send_keys("some text")
#element.send_keys(Keys.RETURN)

element = driver.find_element_by_xpath("//*[@id='search_btn']")
element.click()


print driver.page_source
#assert "No results found." not in driver.page_source
driver.close()