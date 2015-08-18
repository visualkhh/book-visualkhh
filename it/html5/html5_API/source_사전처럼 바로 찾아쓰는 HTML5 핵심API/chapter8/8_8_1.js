window.addEventListener("load", function() {
  // SharedWorker 오브젝트
  var worker = new SharedWorker('shared.js');
  // 더하기 버튼에 click 이벤트 리스너 지정
  document.querySelector('#add').addEventListener("click", function() {
    worker.port.postMessage(3);
}, false);
  // 공유 워커에서 메시지를 받았을 때의 처리
  worker.port.onmessage = function(event) {
    document.querySelector('#cnt').textContent = event.data;
  };
  // 자식 창 생성 버튼에 click 이벤트 리스너 지정
  document.querySelector('#win').addEventListener("click", function() {
    var window_name = "win" + ( new Date() ).getTime();
    window.open("shared_win.html", window_name, "width=150,height=50");
  }, false);
  // 반복 타이머 지정
  window.setInterval( function() {
    worker.port.postMessage(0);
  }, 1000);
}, false);
