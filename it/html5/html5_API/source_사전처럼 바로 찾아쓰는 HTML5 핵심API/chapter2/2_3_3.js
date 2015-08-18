// addEventListener()함수가 구현되어 있지 않으면 종료
if( ! document.addEventListener ) { return; } 
// Selectors API 가 구현되어 있지 않으면 종료 
if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  var form = document.querySelector('form'); 
  if( ! form ) { return; } 
  // validate API 가 구현되어 있지 않으면 종료 
  if( ! form.checkValidity ) { return; } 
  // form 요소의 noValidate 속성을 true 로 변경 
  form.noValidate = true; 
  // form 요소에 submit 이베느 리스너를 지정 
  form.addEventListener("submit", validate, false); 
}, false); 
 
// 전송 되었을 때의 처리
function validate(event) { 
  // 기본 액션을 취소
  event.preventDefault(); 
  // 대상  form 요소 
  var form = event.target; 
  // URL 입력을 위한 input 요소
  var input = form.querySelector('input[name="url"]'); 
  // 밸리데이션이 통과 되면 폼을 전송 하고 종료
  if( input.validity.valid == true ) { 
    form.submit(); 
    return; 
  } 
  // 오류 메시지를 원인과 함께 정의
  var msgs = { 
    valueMissing   : 'URL 이 입력되지 않았습니다. 이 항목은 필수 입니다.', 
    typeMismatch   : 'URL 형식과 맞지 않습니다. URL형식으로 입력해주세요', 
    patternMismatch: 'URL 은 http:// 이후의 내용만 입력해 주세요', 
    tooLong        : 'URL 이 너무 깁니다. 20 글자 이내로 입력해 주세요' 
  }; 
  // 오류의 원인을 찾고 해당 오류 메시지를 경고 창에 표시
  var errs = []; 
  for( var k in msgs ) { 
    if( input.validity[k] == true ) { 
      alert(msgs[k]); 
      break; 
    } 
  } }
