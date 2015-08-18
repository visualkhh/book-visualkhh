// 페이지가 로드 되었을 때의 처리
window.addEventListener("load", function() {
  // 워커의 수
  var n = 2;
  // 색을 균일화할 범위
  var range = 2;
  // img 요소
  var img = document.querySelector('img');
  var iw = parseInt(img.width);
  var ih = parseInt(img.height);
  // canvas 요소
  var canvas = document.querySelector('canvas');
  var context = canvas.getContext('2d');
  // 작업용 canvas 요소를 생성
  var canvas2 = document.createElement('canvas');
  canvas2.width = iw;
  canvas2.height = ih;
  var context2 = canvas2.getContext('2d');
  // img 요소의 이미지를 작업용 canvas에 그리기
  context2.drawImage(img, 0, 0);
  // 이미지 데이터 얻기
  var imagedata = context2.getImageData(0, 0, iw, ih);
  // Worker 오브젝트 생성
  var workers = [];
  for( var i=0; i<n; i++ ) {
    workers[i] = new Worker('multiple.js');
  }
  // 워커에 송신할 메시지 생성
  var messages = [];
  var trim_height = Math.ceil( ih / n );
  for( var i=0; i<n; i++ ) {
    // 분할된 이미지의 위쪽 y좌표
    var y = trim_height * i;
    // 분할된 이미지의 세로 폭
    var th = (y + trim_height > ih) ? (ih - y) : trim_height;
    // 메시지
    messages[i] = {
      w: iw,
      h: ih,
      y: y,
      trim_height: th,
      range: range,
pixels: imagedata.data
    };
  }
  // 변환 버튼에 click 이벤트 리스너 지정
  var button = document.querySelector('button');
  var done, start;
  button.addEventListener("click", function() {
    // 처리가 끝난 워커의 수를 기록하는 변수를 선언
    done = 0;
    // 처리를 시작한 시간을 저장
    start = ( new Date() ).getTime();
    // 각 워커에 메시지를 보내고 처리를 시작
    for( var i=0; i<n; i++ ) {
      workers[i].postMessage(messages[i]);
    }
  }, false);
  // 워커에서 보낸 메시지를 받는 핸들러 정의
  for( var i=0; i<n; i++ ) {
    workers[i].onmessage = function(event) {
      // 워커에서 보낸 메시지를 저장
      var message = event.data;
      // 빈 Canvas의 픽셀 데이터 생성
      var imagedata = context.createImageData(iw, message.trim_height);
      // 변환 후의 픽셀 데이터를 지정
      var len = message.pixels.length;
      for( var idx=0; idx<len; idx++ ) {
        imagedata.data[idx] = message.pixels[idx];
      }
      // canvas요소에 그리기
      context.putImageData(imagedata, 0, message.y);
      // 모든 워커의 처리가 끝나면 처리 시간을 표시
      done += message.trim_height;
      if( done == ih ) {
        var now = ( new Date() ).getTime();
        var tm = ( ( now - start ) / 1000 ).toFixed(3);
        document.querySelector('#tm').textContent = tm;
      }
    };
  }
}, false);
