from pywinauto import application
app = application.Application.start("notepad.exe");
#app = application.Application();
#app.connect_(title_re=".*EmEditor");
print type(app);
print app;
app.notepad.TypeKeys("fffffdddfff");