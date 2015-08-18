// Worker 오브젝트
var worker = new Worker('error.js');
// error 이벤트의 핸들러 지정
worker.onerror = function(event) {
  var msg = "";
  msg += event.message + "\n";
  msg += "[" + event.filename + " 의 " + event.lineno + " 번째 줄]";
  alert(msg);
};
