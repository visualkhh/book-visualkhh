'''
Created on 2015. 5. 12.

@author: Administrator
'''
from pymouse import PyMouse
from pykeyboard import PyKeyboard

m = PyMouse()
k = PyKeyboard()

x_dim, y_dim = m.screen_size()

print x_dim
print y_dim
m.click(x_dim/2, y_dim/2, 1)
k.type_string('Hello, World!')

x,y = m.position();

print x
print y