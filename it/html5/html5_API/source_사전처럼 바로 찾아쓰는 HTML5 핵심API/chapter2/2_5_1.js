// document 오브젝트에 DOMContentLoaded 이벤트리스너를 지정
document.addEventListener("DOMContentLoaded", function() {   
// textarea 요소 
  var textarea = document.querySelector('#comment'); 
  // textarea 요소에 input 이벤트 리스너를 지정 
  textarea.addEventListener("input", count, false); 
}, false); 
 
// 텍스트 에리어의 입력 글자수를 표시 
function count(event) { 
  // textarea 요소 
  var textarea = event.target; 
  // 텍스트 에리어의 입력 글자수
  var num = textarea.textLength; 
  // 결과를 나타낼 output요소
  var output = textarea.form.querySelector('output'); 
  // 결과를 output 요소에 지정 
  output.value = num; 
  // output 요소를 지원하지 않는 브라우저를 위해 
  // output 요소의 콘텐츠도 다시 쓴다.
  if( output.defaultValue === undefined ) { 
    output.textContent = num; 
  } 
}
