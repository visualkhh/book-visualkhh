document.addEventListener("DOMContentLoaded", function() { 
  // URL 로부터 쿼리 부분 얻기
  var q = window.location.search; 
  // 파리미터를 얻어 플래그를 지정
  var open = true; 
  if( q.match(/^\?open\=false$/) ) { 
    open = false; 
  } 
  // div요소의 표시 비표시의 전환
  toggle(open); 
  // h1 요소에 click이벤트 리스너를 지정
  document.querySelector('h1').addEventListener("click", function() { 
    // 플래그를 반전
    open = open ? false : true; 
    // 콘텐츠를 표시하는 div 요소의 표시, 비표시 전환
    toggle(open); 
    // 세션 히스토리에 추가
    var title = document.title + (open ? " - open" : " - close"); 
    var url = "?open=" + open; 
    window.history.pushState(open, title, url); 
  }, false); 
}, false); 
 
// 콘텐츠를 표시하는 div 요소의 표시, 비표시 전환
function toggle(open) { 
  var div = document.querySelector('#contents'); 
  div.className = open ? "open" : "close"; 
} 
