// addEventListener()함수가 동작하지 않으면 종료 
if( ! document.addEventListener ) { return; } 
// Selectors API 가 동작 하지 않으면 종료
if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  // 버튼에 click 이벤트 리스너를 지정
  var btn = document.querySelector('input[type="button"]'); 
  btn.addEventListener("click", check, false); 
}, false); 
 
// 금지단어 확인
function check() { 
  // textarea 요소 
  var textarea = document.querySelector('textarea[name="ad"]'); 
  // 금지 단어 검사 
  var li_list = document.querySelectorAll('ul#ngwords>li'); 
  for( var i=0; i<li_list.length; i++ ) { 
    // 금지 단어
    var ngword = li_list.item(i).textContent; 
    if( ! ngword ) { continue; } 
    // 금지 단어의 존재를 체크 
    var offset = textarea.value.indexOf(ngword); 
    if(offset == -1) { continue; } 
    // 금지단어 부분을 선택상태로 만듬
    textarea.setSelectionRange(offset, offset + ngword.length); 
    break; 
  } 
}
