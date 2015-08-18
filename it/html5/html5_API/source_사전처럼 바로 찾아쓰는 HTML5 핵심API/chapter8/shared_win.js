// 페이지가 로드 되었을 때의 처리
window.addEventListener("load", function() {
  // SharedWorker 오브젝트
  var worker = new SharedWorker('shared.js');
// message 이벤트 한들러 지정
worker.port.onmessage = function(event) {
    document.querySelector('#cnt').textContent = event.data;
  };
  // 더하기 버튼에 click 이벤트 리스너 지정
  document.querySelector('#add').addEventListener("click", function() {
    worker.port.postMessage(1);
  }, false);
  // 반복 타이머 지정
  window.setInterval( function() {
    worker.port.postMessage(0);
  }, 1000);
}, false);
