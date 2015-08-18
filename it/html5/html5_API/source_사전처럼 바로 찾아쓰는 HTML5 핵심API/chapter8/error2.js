self.onmessage = function(event) {
  self.importScripts("dummy.js");
};
// 에러가 발생 했을 때의 처리
self.onerror = function(event) {
  var msg = "";
  msg += event.message + "\n";
  msg += "[" + event.filename + " 의 " + event.lineno + " 번째 줄]";
  self.postMessage(msg);
};
