// addEventListener()함수가 동작하지 않으면 종료
if( ! document.addEventListener ) { return; } 
// Selectors API 가 동작하지않으면 종료
if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  // 텍스트 입력 필드의  input 요소 
  var input = document.querySelector('input[name="txt"]'); 
  // select 이벤트 리스너를 지정
  input.addEventListener("select", function(event) { 
    var el = event.target; 
    var stxt = el.value.substring(el.selectionStart, el.selectionEnd); 
    alert(stxt); 
  }, false); 
}, false);
