// 부모에서 메시지가 왔을 때의 처리
self.onmessage = function(event) {
  // 부모로부터 전송된 명령
  var command = event.data;
  // 처리 시작
  if( command == "start" ) {
    find_prime();
  }
};
//소수를 찾아서 부모에서 전송
function find_prime() {
// 소수 찾기 시작
for( var n=3; n<10000000; n++ ) {
    // 소수 여부 확인
    if( is_prime(n) ) {
      // 소수를 찾으면 부모에 메시지 보내기
      self.postMessage(n);
    }
  }
}
// 소수 여부 확인
function is_prime(n) {
  if(n % 2 == 0) { return false; }
  for( var i=3; i*i <= n; i+=2 ) {
    if( n % i == 0 ) { return false; }
  }
  return true;
}
