// addEventListener()함수가 구현되어 있지 않으면 종료
if( ! document.addEventListener ) { return; } 
// Selectors API 가 구현되어 있지 않으면 종료
if( ! document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  var form = document.querySelector("#login"); 
  if( ! form ) { return; } 
  // validate API 가 구현되어 있지 않으면 종료
  if( ! form.checkValidity ) { return; } 
  // form 요소의 noValidate 속성을 true 로 변경
  form.noValidate = true; 
  // 컨트롤에  input 이벤트의 리스너를 지정
  var ctrls = get_controls(form); 
  for( var i=0; i<ctrls.length; i++ ) { 
    var ctrl = ctrls.item(i); 
    ctrl.addEventListener("input", function(event){ 
      validate_control(event.target); 
    }, false); 
    validate_control(ctrl); 
  } 
}, false);
 
// 입력 대상 컨트롤 리스트를 얻음
function get_controls(form) { 
  var ctrls = form.querySelectorAll('input[type="text"], input[type="password"]'); 
  return ctrls; 
}  
// 컨트롤의 입력 오류를 체크
function validate_control(ctrl) { 
  // 지정된 커트롤의 입력 오류 상태를 확인
  if(ctrl.validity.valid == true) { 
    class_remove(ctrl, "err"); 
    class_add(ctrl, "ok"); 
  } else { 
    class_remove(ctrl, "ok"); 
    class_add(ctrl, "err"); 
  } 
  // 폼 전체에 입력 오류가 없는지 체크
  var ctrls = get_controls(ctrl.form); 
  var valid = true; 
  for( var i=0; i<ctrls.length; i++ ) { 
    if( ctrls.item(i).validity.valid == false ) { 
      valid = false; 
      break; 
    } 
  } 
  // 전송 버튼
  var btn = ctrl.form.querySelector('input[type="submit"]'); 
  btn.disabled = valid ? false : true; 
} 
 
// 요소의 class 속성에 키워드를 추가
function class_add(element, word) {
	if(element.classList) 
	{
	// classList 속성이 구현된 경우 
		element.classList.add(word);
	} 
	else 
	{
	// classList 속성이 구현되지 않은 경우
		var w = word.replace(/([^a-zA-Z0-9])/, "\\$1", "g"); var re = new RegExp("(^|\\s)" + w + "(\\s|$)");
	if( ! re.test(element.className) ) 
	{
		element.className += " " + word; }
	} 
}
 
// 요소의 class  속성에서 키워드를 제거 
function class_remove(element, word)
{
	if(element.classList)
	{
	// classList 속성이 구현된 경우
	element.classList.remove(word);
	} 
	else 
	{
		// classList 속성이 구현되지 않은 경우
		var w = word.replace(/([^a-zA-Z0-9])/, "\\$1", "g"); var re = new RegExp("(^|\\s)" + w + "(\\s|$)");
		if( re.test(element.className) ) 
		{
    	  var c = element.className;
    	  c = c.replace(re, " ");
    	  c = c.replace(/\s+/, " ");
    	  c = c.replace(/^\s/, "");
    	  c = c.replace(/\s$/, "");
    	  element.className = c;
		} 
	}
}
