// 카운터 값
var cnt = 0;
// 이 공유 워커에 연결되었을 때의 처리
self.onconnect = function(event) {
  // 메시지 채널
  var port = event.ports[0];
  // 현재 카운터를 보내기
  port.postMessage(cnt);
  // 메시지를 받았을 때의 처리
  port.onmessage = function(e) {
    // 카운터 더하기
    cnt += e.data;
    // 현재의 카운터 보내기
    port.postMessage(cnt);
  };
};
