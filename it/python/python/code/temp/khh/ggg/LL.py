from LaunchServices import *
from AppKit import *
import os

from threading import Timer

def poll_clipboard():
    pasteboard = NSPasteboard.generalPasteboard()
    print pasteboard.changeCount()

def main():
    while True:
        t = Timer(1, poll_clipboard)
        t.start()
        t.join()

if __name__ == "__main__":
    main()