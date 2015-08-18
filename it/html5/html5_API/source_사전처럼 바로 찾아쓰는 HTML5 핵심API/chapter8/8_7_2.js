// 페이지가 로드 되었을 때의 처리
window.addEventListener("load", function() {
  // 커의 수
  var n = 4;
  // 조사할 숫자의 상한
  var max = 30000000;
  // Worker 오브젝트 생성
  var workers = [];
  for( var i=0; i<n; i++ ) {
    workers[i] = new Worker('multiple2.js');
  }
  // 변환 버튼에 click 이벤트 리스너 지정
  var button = document.querySelector('button');
  var done, start;
  button.addEventListener("click", function() {
    // 처리가 끝난 워커의 수를 저장할 변수 선언
    done = 0;
    // 처리를 시작할 시간을 저장
    start = ( new Date() ).getTime();
    // 각 워커에 메시지를 보내고 처리를 시작
    var s = 0;
    var e = 0;
    for( var i=0; i<n; i++ ) {
      s = e + 1;
      e = s + Math.ceil( max / n ) - 1;
if( e > max ) { e = max; }
      workers[i].postMessage([s, e]);
    }
  }, false);
  // 계산한 소스의 수計測した素数の数
  var total = 0;
  // 워커에서 보낸 메시지를 처리하는 핸들러 정의
  for( var i=0; i<n; i++ ) {
    workers[i].onmessage = function(event) {
      // 워커에서 보낸 메시지
      var num = event.data;
      // 전체 소수의 수에 가산 
      total += num;
      // 모든 워커의 처리가 끝났을 때의 처리
      done ++;
      if( done == n ) {
        // 처리 시간을 표시
        var now = ( new Date() ).getTime();
        var tm = ( ( now - start ) / 1000 ).toFixed(3);
        document.querySelector('#tm').textContent = tm;
        // 소수의 수를 표시
        document.querySelector('#total').textContent = total;
      }
    };
  }
}, false);
