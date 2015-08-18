// addEventListener()함수가 동작하지 않으면 종료 
//if( ! document.addEventListener ) { return; } 
// Selectors API 가 동작 하지 않으면 종료 
//if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정 
document.addEventListener("DOMContentLoaded", function() {   
var form = document.querySelector('form'); 
  if( ! form ) { return; } 
  // validate API 가 동작하지 않으면 종료 
  if( ! form.checkValidity ) { return; } 
  // URL 입력을 위한 input 요소 
  var input = form.querySelector('input[name="url"]'); 
  // input 요소에 input 이벤트의 리스너를 지정
  form.addEventListener("input", validate, false); 
}, false); 
 
// 글자가 입력될때의 처리 
function validate(event) { 
  // URL 입력을 위한 input 요소 
  var input = event.target; 
  // 커스텀 밸리데이션
  if( !is_kr_domain(input.value) ) 
  {
    input.setCustomValidity("kr도메인이 아닙니다."); 
  } 
  else 
  { 
    input.setCustomValidity(""); 
  } 
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
