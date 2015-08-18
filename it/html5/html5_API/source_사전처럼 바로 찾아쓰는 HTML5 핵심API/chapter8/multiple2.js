// 페이지에서 메시지를 받았을 때의 처리
self.onmessage = function(event) {
  // 페이지에서 보낸 메시지
  var message = event.data;
  // 처리를 시작
  var num = find_prime(message[0], message[1])
  // 페이지에 소수의 수를 반환
  self.postMessage(num);
};
// 소수의 수 바꾸기
function find_prime(s, e) {
  var num = 0;
for( var n=s; n<=e; n++ ) {
if( is_prime(n) ) {
      num ++;
    }
  }
  return num;
}
// 소수인지 여부 판단
function is_prime(n) {
  if(n % 2 == 0) { return false; }
  for( var i=3; i*i <= n; i+=2 ) {
    if( n % i == 0 ) { return false; }
  }
  return true;
}
