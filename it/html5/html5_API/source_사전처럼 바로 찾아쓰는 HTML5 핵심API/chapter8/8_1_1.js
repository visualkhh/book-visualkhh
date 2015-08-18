window.addEventListener("load", function() {
// output 요소
var output = document.querySelector('output'); 
// 소수 찾기 시작
for( var n=3; n<100000000; n++ ) {
// 소수인지 판단
if( is_prime(n) ) {
// 소수를 발견하면 output 요소에 표시 
output.value = n; output.textContent = n;
} }
}, false);
// 소수인지 검사
function is_prime(n) {
	if(n % 2 == 0) { return false; } for( var i=3; i*i <= n; i+=2 ) {
	if( n % i == 0 ) { return false; } }
	return true;
}