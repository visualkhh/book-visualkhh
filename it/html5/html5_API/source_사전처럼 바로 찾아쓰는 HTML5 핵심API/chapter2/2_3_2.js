// addEventListener()함수가 구현되어 있지 않으면 종료 
if( !document.addEventListener ) { return; } 
// Selectors API가 구현 되어 있지 않으면 종료 
if( !document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  var form = document.querySelector("#login"); 
  if( ! form ) { return; } 
  // validate API 가 구현되어 있지 않으면 종료
  if( ! form.checkValidity ) { return; } 
  // form 요소의 noValidate 속성을 true 로 변경
  form.noValidate = true; 
  // form 요소에 submit 이벤트 리스너를 지정
  form.addEventListener("submit", validate, false); 
}, false); 
 
// 전송 되었을 때의 처리
function validate(event) { 
  // 기본 액션을 취소
  event.preventDefault(); 
  // 대상이 되는 form 요소 
  var form = event.target; 
  // 밸리데이션 
  var errs = []; 
  var ctrls = form.querySelectorAll('input[type="text"], input[type="password"]'); 
  for( var i=0; i<ctrls.length; i++ ) { 
    // 컨트롤의 오브젝트
    var ctrl = ctrls.item(i); 
    // 에러 스타일을 해제
    class_remove(ctrl, "err"); 
    // 밸리데이션이 통과 되면 다음으로
    if(ctrl.validity.valid == true) { continue; } 
    //에러 배열에 추가
    errs.push(ctrl); 
  } 
  // 밸리데이션이 통과 되면, 폼을 전송하고 종료
  if(errs.length == 0) { 
    form.submit(); 
    return; 
  } 
  // 에러 메시지 생성
  var msgs = []; 
  for( var i=0; i<errs.length; i++ ) {     // 에러가 발생한 컨트롤
    var ctrl = errs[i]; 
    // 에러 스타일을 지정
    class_add(ctrl, "err"); 
    // title 속성의 값을 에러 메시지 배열에 추가
    msgs.push(ctrl.title); 
  } 
  // 에러 메시지 표시용 ul요소를 생성하고
  // form 요소의 앞에 삽입 
  var ul = document.querySelector("#errs"); 
  if( !ul ) { 
    ul = document.createElement("ul"); 
    ul.id = "errs"; 
    ul.className = "errs"; 
    form.parentNode.insertBefore(ul, form); 
  } 
  // 에러 내용（li 요소）를 삽입 
  ul.innerHTML = ""; 
  for( var i=0; i<errs.length; i++ ) { 
    var li = document.createElement("li"); 
    li.appendChild( document.createTextNode(msgs[i]) ); 
    ul.appendChild(li); 
  } 
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
