// addEventListener()함수가 동작하지 않으면 종료 
if( ! document.addEventListener ) { return; } 
// Selectors API 가 동작하지 않으면 종료
if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트의 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  // 버튼의 input 요소 
  var btn = document.querySelector('input[type="button"]'); 
  // 버튼에 click 이벤트 리스너를 지정
  btn.addEventListener("click", text_select, false); 
}, false); 
 
// 텍스트 입력필드에 포커싱 되었을때의 처리
function text_select(event) { 
  // 텍스트 입력 필드의 input 요소
  var input = document.querySelector('input[type="text"]'); 
  // 입력된 문자 모두를 선택 상태로
  input.select(); 
}
