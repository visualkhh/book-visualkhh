// addEventListener()함수가 동작하지 않으면 종료
if( ! document.addEventListener ) { return; } 
// Selectors API 가 동작하지 않으면 종료
if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  // 텍스트 입력 필드의 nput 요소 
  var input = document.querySelector('input[name="txt"]'); 
  // focus 이벤트 리스너를 지정
  input.addEventListener("focus", function(event) { 
    var el = event.target; 
    // 캐럿을 맨뒤로 이동
    el.selectionStart = el.value.length; 
  }, false); 
  // 캐럿을 맨뒤로 이동
  if(input.autofocus == true) { 
    input.selectionStart = input.value.length; 
  } 
}, false);
