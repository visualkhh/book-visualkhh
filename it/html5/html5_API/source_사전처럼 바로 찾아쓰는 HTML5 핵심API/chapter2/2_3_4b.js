// addEventListener()함수가 동작하지 않으면 종료 
//if( ! document.addEventListener ) { return; } 
// Selectors API 가 동작하지 않으면 종료 
//if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정 
document.addEventListener("DOMContentLoaded", function() { 
  var form = document.querySelector('form'); 
  if( ! form ) { return; } 
  // validate API 가 동작하지 않으면 종료
  if( ! form.checkValidity ) { return; } 
  // form 요소의 noValidate 속성을 true 로 변경
  form.noValidate = true; 
  // form 요소에 submit 이벤트의 리스너를 지정
  form.addEventListener("submit", submit, false); 
  // URL 입력을 위한 input 요소 
  var input = form.querySelector('input[name="url"]'); 
  // input 요소에 input 이벤트의 리스너를 지정 
  form.addEventListener("input", validate, false); 
}, false); 
 
// 글자가 입력되고 있을때의 처리
function validate(event) { 
  // URL 입력을 위한  input 요소 
  var input = event.target; 
  // 커스텀 밸리데이션
  if( !is_kr_domain(input.value) ) { 
    input.setCustomValidity("kr도메인이 아닙니다."); 
  } else { 
    input.setCustomValidity(""); 
  } 
} 
 
// 전송 되었을 때의 처리 
function submit(event) { 
  // 기본 액션을 취소
  event.preventDefault(); 
  // 대상이 되는 form 요소 
  var form = event.target; 
  // URL 입력을 위한 input 요소 
  var input = form.querySelector('input[name="url"]'); 
  // 밸리데이션이 통과 되면 폼을 전송하고 종료
  if( input.validity.valid == true ) { 
    form.submit(); 
    return; 
  } 
  // 오류를 경고 창에 표시
  var msg = input.validationMessage; 
  if( ! msg ) { 
   // msg = input.title; 
  } 
  alert(msg); 
}  

 
// url 의 FQDN 가 kr 도메인으로 적절한지 확인 
function is_kr_domain(url) { 
  // 입려된URL 을 소문자로 변환
  var url = url.toLowerCase(); 
  // FQDN 
  var m = url.match(/^http?\:\/\/([^\:\/]+)/); 
  if( ! m ) { return false; } 
  var fqdn = m[1]; 
  // FQDN 이 적절한지 확인 
var parts = fqdn.split(/\./); 
  var tld = parts.pop(); 
  if(tld != "kr") { return false; } 
  var sld = parts.pop(); 
  if( /^(ac|ad|co|ed|go|gr|lg|ne|or)$/.test(sld) ) { 
    if( parts.length <= 2 ) { return false; } 
  } 
  // 
  return true; 
}
