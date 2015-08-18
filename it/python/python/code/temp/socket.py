import socket
import sys
import os
import time


HOST = '211.115.79.194'    # The remote host
#HOST = '211.115.79.197'    # The remote host
PORT = 80              # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST, PORT))
s.sendall('Hello, world')
data = s.recv(1024)
s.close()
print 'Received', repr(data)

'''
import socket
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('211.115.79.194', 555))
'''