// document 오브젝트에 DOMContentLoaded 이벤트리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  // textarea 요소에 input 이벤트의 리스너를 지정
  var textarea = document.querySelector('#comment'); 
  textarea.addEventListener("input", count, false); 
  // form 요소에 reset 이벤트의 리스너를 지정
  textarea.form.addEventListener("reset", formreset, false); 
 
}, false); 
 
// 텍스트 에리어의 입력 문자수를 표시
function count(event) { 
  // textarea 요소 
  var textarea = event.target; 
  // 텍스트 에리어의 입력 문자수
  var num = textarea.textLength; 
  // 결과를 표시 할 output 요소 
  var output = textarea.form.querySelector('output'); 
  // 결과를 output 요소에 지정 
  output.value = num; 
  // output 요소를 지원하지 않는 브라우저를위해
  // output 요소의 콘텐츠도 다시 쓴다
  if( output.defaultValue === undefined ) { 
    output.textContent = num; 
  } 
  // meter 요소를 업데이트
  var meter = textarea.form.querySelector('meter'); 
  meter.value = num > meter.max ? meter.max : num; } 
 
function formreset(event) { 
  // form 요소
  var form = event.target; 
  // output 요소를 지원하지 않는 브라우저를 위해
  // output 요소의 콘텐츠도 다시 쓴다 
  var output = form.querySelector('output'); 
  if( output.defaultValue === undefined ) { 
    output.textContent = "0"; 
  } 
  // meter 요소의 값을 0 으로 갱신 
  var meter = form.querySelector('meter'); 
  meter.value = 0; 
}
