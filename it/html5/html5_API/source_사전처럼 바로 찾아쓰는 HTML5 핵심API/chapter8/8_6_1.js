window.addEventListener("load", function() {
// Worker 오브젝트
  var worker = new Worker('xhr.js');
  // message 이벤트 핸들러 지정
  worker.onmessage = function(event) {
    // 워커가 보내온 메시지 표시
    document.querySelector('#console').textContent = event.data;
  };
}, false);
