// addEventListener()함수가 구현되어 있지 않으면 종료 
if( ! document.addEventListener ) { return; } 
// Selectors API 가 구현되어 있지 않으면 종료 
if( ! document.querySelector ) { return; } 
 
document.addEventListener("DOMContentLoaded", function() { 
  // form 요소 
  var form = document.querySelector("form"); 
  // form 요소에 submit 이벤트의 리스너를 지정 
  form.addEventListener("submit", post, false); 
}, false); 
 
function post(event) { 
  // 기본 액션을 취소
  event.preventDefault(); 
  // 확인 다이얼로그를 표시
  var res = confirm("전송해도 좋습니까?"); 
  // OK라면 폼을 전송
  if(res == true) { 
    var form = event.target; 
    form.submit(); 
  } 
}
