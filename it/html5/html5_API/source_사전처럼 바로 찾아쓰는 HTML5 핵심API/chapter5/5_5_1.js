window.addEventListener("load", function() {
  var div = document.querySelector('div.wisywig');
  // iframe요소
  var iframe = div.querySelector('iframe');
  // iframe요소의  window 오브젝트와 document 오브젝트
  var win = iframe.contentWindow;
  var doc = win.document;
  // iframe 요소를 편집 가능하게 지정
  doc.designMode = "on";
  // textarea요소
  var textarea = div.querySelector('textarea');
  // menu요소
  var menu = div.querySelector('menu');
  // button요소에 click 이벤트 리스너 지정
  var buttons = menu.querySelectorAll('button');
  for( var i=0; i<buttons.length; i++ ) {
    var button = buttons.item(i);
    // 커멘드의 종류
    var command_id = button.getAttribute("data-command");
    // 만일 지원하지 않는 명열이라면 무효처리
    if( ! is_supported(doc, command_id) ) { button.disabled = true; continue;}
    // 리스너 지정
    button.addEventListener("click", function(event) {
      // 실행
      exec_command(event, win, doc);
      // 포커스를 iframe 요소로 되돌림
      iframe.focus();
    }, false);
  }
  // form요소에 submit이벤트 리스너 지정
  var form = document.forms.item(0);
  form.addEventListener("submit", function() {
    // textarea요소에 생성된 HTML을 지정
    textarea.value = doc.body.innerHTML;
  }, false);
}, false);

// 명령 실행
function exec_command(event, win, doc) {
  // Selection 오브젝트
var selection = win.getSelection();
// 선택 영역이 없으면 종료
  if( selection.rangeCount == 0 ) { return; }
  // 눌린 버튼
  var button = event.currentTarget;
  // 명령의 종류
  var command_id = button.getAttribute("data-command");
  // 지정된 명령이 이용가능한지 확인
  if( ! is_enabled(doc, command_id) ) { return; }
  // 텍스트가 선택되어 있는지 확인
  if( ! command_id.match(/^(unlink|undo|redo)$/) ) {
    if( selection.isCollapsed == true ) { return; }
  }
  // 명령에 대한 처리
  if( command_id == "insertHTML" ) {
    // 선택된 텍스트 얻기
    var text = selection.toString();
    // data-value속성에서 색 얻기
    var color = button.getAttribute("data-value");
    // 바꿔쓸 HTML생성
    var code = '<span style="color:' + color + '">' + text + '</span>';
    // 명령 실행
    doc.execCommand("insertHTML", false, code);
  } else if( command_id == "createLink" ) {
    // 입력 다이얼로그 표시
    var url = window.prompt("URL을 지정해 주세요。", "http://");
    // 입력된 URL 확인
    if( url.match(/^http\:\/\/[a-zA-Z0-9\.\-]+/) ) {
      // 명령 실행
      doc.execCommand(command_id, false, url);
    }
  } else {
    // 명령 실행
    doc.execCommand(command_id, false, null);
  }
}

// 브라우저가 지정된 명령을 지원하는지 확인
function is_supported(doc, command_id) {
  var supported = true;
  try {
    supported = doc.queryCommandSupported(command_id);
  } catch(e) {};
  return supported;
}

// 현재 이용가능한지 확인
function is_enabled(doc, command_id) {
  var supported = true;
  try {
    supported = doc.queryCommandEnabled(command_id);
  } catch(e) {
    supported = false;
  };
  return supported;
}
