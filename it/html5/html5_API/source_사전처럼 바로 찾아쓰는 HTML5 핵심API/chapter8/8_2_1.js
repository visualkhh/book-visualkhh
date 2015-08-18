// 페이지가 로드 되었을 때의 처리
window.addEventListener("load", function() {
  // output 요소
  var output = document.querySelector('output');
  // Worker 오브젝트
  var worker = new Worker('worker_8_2_1.js');
  // message 이벤트 핸들러 지정
  worker.onmessage = function(event) {
    // 워커가 발견한 소수
    var n = event.data;
    // output 요소에 표시
    output.value = n;
    output.textContent = n;
  };
  // 워커에 처리 시작 메시지 보내기
  worker.postMessage("start");
// button 요소에 click 이벤트 리스너 지정
var button = document.querySelector('button');
  button.addEventListener("click", function() {
    worker.terminate();
  }, false);
}, false);
