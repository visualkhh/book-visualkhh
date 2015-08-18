document.addEventListener("DOMContentLoaded", function() { 
  // form 요소 
  var form = document.querySelector("form"); 
  // form 요소에 forminput 이벤트 리스너를 지정
  form.addEventListener("forminput", calc, false); 
}, false); 
 
function calc(event) { 
  // form 요소
  var form = document.querySelector("form"); 
  // HTMLFormControlsCollection 오브젝트
  var ctrls = form.elements; 
  // 변수를 입력할  input 요소 
  var a = form.elements.namedItem("a"); 
  var b = form.elements.namedItem("b"); 
  var c = form.elements.namedItem("c"); 
  // 계산 결과를 표시할 output 요소 
  var output = form.querySelector("output"); 
  // 계산 결과를  output 요소에 표시
  var sum = parseInt(a.value) + parseInt(b.value) + parseInt(c.value); 
output.value = sum;
}
