window.addEventListener("load", function() {
  // 적용할 comamndID를 정의
  var command_id = "bold";
  // iframe 요소
  var iframe = document.querySelector('iframe');
  // iframe요소의 window 오브젝트와 document 오브젝트
  var win = iframe.contentWindow;
  var doc = win.document;
  // iframe 요소의 콘텐츠를 편집 가능하게 지정
  doc.designMode = "on";
  // textarea요소
  var textarea = document.querySelector('textarea');
  // button 요소에 click 이벤트 리스너를 지정
  document.querySelector('button').addEventListener("click", function() {
    // Selection 오브젝트
    var selection = win.getSelection();
    // 선택 영역이 없으면 종료
    if( selection.rangeCount == 0 ) { return; }
    // 브라우저가 commandID에 "bold"를 지원하는지 확인
    var supported = true;
    try {
      supported = doc.queryCommandSupported(command_id);
    } catch(e) {};
    if( supported == false ) { return; }
    // 현재 이용가능 한지를 확인
    try {
      supported = doc.queryCommandEnabled(command_id);
    } catch(e) {};
    if( supported == false ) { return; }
    // 선택영역의 글자를 두껍게
    doc.execCommand(command_id, true, null);
    // textarea 요소에 값을 지정
    textarea.value = doc.body.innerHTML;
    //포커스를 iframe 요소의 콘텐츠로
    iframe.focus();
  }, false);
}, false);
