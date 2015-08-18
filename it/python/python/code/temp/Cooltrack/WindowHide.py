
import win32gui
import subprocess

class WindowHide:
    def __init__(self, exe="notepad.exe"):
        self.exe = exe
        self.get_hwnd()


    def get_hwnd(self):
      win_name = get_win_name(self.exe)
      self.hwnd = win32gui.FindWindow(0,win_name)


    def hide(self):
        win32gui.ShowWindow(self.hwnd, 6)
        win32gui.ShowWindow(self.hwnd, 0)

    def show(self):
        win32gui.ShowWindow(self.hwnd, 5)
        win32gui.ShowWindow(self.hwnd, 3)

def get_win_name(exe):
    '''simple function that gets the window name of the process with the given name'''
    info = subprocess.STARTUPINFO()
    info.dwFlags |= subprocess.STARTF_USESHOWWINDOW
    raw=subprocess.check_output('tasklist /v /fo csv', startupinfo=info).split('\n')[1:-1]
    for proc in raw:
        try:
            proc=eval('['+proc+']')
            if proc[0]==exe:
                return proc[8]             
        except:
            pass
    raise ValueError('Could not find a process with name '+exe)