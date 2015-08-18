document.addEventListener("DOMContentLoaded", function() 
{ // URL로부터 쿼리 부분 얻기
var q = window.location.search;
// 매개변수를 얻어 플래그를 지정
var open = true;
if( q.match(/^\?open\=false$/) ) 
{
	open = false;
}
// 콘텐츠 표시를 위한 div의 표시, 비표시 전환
toggle(open);
// 세션기록다시쓰기
var title = document.title + (open ? " - open" : " - close");
var url = "?open=" + open;
window.history.replaceState(open, title, url);
// h1 요소에 click 이벤트 리스너를 지정 
document.querySelector('h1').addEventListener("click", function() {
// 플래그 반전
open = open ? false : true;
// 콘텐츠 표시를 위한 div의 표시, 비표시 전환
toggle(open);
// 세션 기록에 추가
var title = document.title + (open ? " - open" : " - close"); var url = "?open=" + open;
window.history.pushState(open, title, url);
}, false);
// window 객체에 popstate 이벤트 리스너를 지정 
window.addEventListener("popstate", function(event) {
// pushState()에 의해 만들어진 항목이 아니면 종료 
if( event.state == null ) { return; }
// 콘텐츠 표시를 위한 div의 표시, 비표시 전환 
toggle(event.state);
  }, false);
}, false);
// 콘텐츠 표시를 위한 div의 표시, 비표시 전환 
function toggle(open) {
  var div = document.querySelector('#contents');
  div.className = open ? "open" : "close";
}
