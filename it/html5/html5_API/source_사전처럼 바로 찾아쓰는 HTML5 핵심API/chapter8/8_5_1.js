window.addEventListener("load", function() {
  // Worker 오브젝트
  var worker = new Worker('error2.js');
  // message 이벤트 핸들러 지정
  worker.onmessage = function(event) {
    alert(event.data);
  };
  // button 요소에 click 이벤트 리스너 지정
  var button = document.querySelector('button');
  button.addEventListener("click", function() {
    worker.postMessage(null);
  }, false);
}, false);
