import base64

with open("C:\\Users\\shinhan\\Documents\\RSUPPORT\\RemoteCall Viewer\\Capture\\20150203112902.zip", "rb") as image_file:
	encoded_string = base64.b64encode(image_file.read())

print encoded_string

print len(encoded_string)
fo = open("C:\\Users\\shinhan\\Documents\\RSUPPORT\\RemoteCall Viewer\\Capture\\20150203112902-2.txt", "wb")
fo.write(encoded_string)


fh = open("C:\\Users\\shinhan\\Documents\\RSUPPORT\\RemoteCall Viewer\\Capture\\20150203112902-2.zip", "wb")
fh.write(base64.decodestring(encoded_string));