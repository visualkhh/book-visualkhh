document.addEventListener("DOMContentLoaded", function() {
// 분을 나타내는 span 요소
var span_min = document.querySelector('p.watch>span.min'); 
// 초를 나타내는 span 요소
var span_sec = document.querySelector('p.watch>span.sec'); 
// 버튼을 나타내는 button 요소
var button = document.querySelector('p.watch>button');
// 타이머의 식별 ID를 받을 변수를 정의
var handle = 0;
// button 요소에 click 이벤트 리스너를 지정 
button.addEventListener("click", function() {
if( handle ) {
// 반복 타이머를 취소 
window.clearInterval(handle); 
handle = 0;
// 버튼 표시를 변경 button.textContent = "start";
} else {
// 현재 시간(시작 시간)
var stime = ( new Date() ).getTime() / 1000;
// 반복 타이머를 지정
handle = window.setInterval(start, 0, stime, span_min, span_sec);
// 버튼 표시를 변경 
button.textContent = "stop";
}
}, false);
}, false);

function start(stime, span_min, span_sec) { // 현재 시간
var now = ( new Date() ).getTime() / 1000; // 경과 시간 산출
var diff = now - stime;
// 분과 초를 계산
var min = parseInt( diff / 60 );
var sec = (diff % 60).toFixed(3);
// span 요소에 표시
span_min.textContent = (min<10) ? "0"+min : min; span_sec.textContent = (sec<10) ? "0"+sec : sec;
}