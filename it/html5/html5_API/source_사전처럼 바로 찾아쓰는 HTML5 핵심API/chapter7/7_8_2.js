document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드랍 측의 스크립트
  * ----------------------------------------------------- */
  // textarea요소
  var textarea = document.querySelector('#editor');
  // textarea 요소에서 발생하는 dragenter, dragover이벤트의
  // 기본 액션을 해제
  textarea.addEventListener("dragenter", cancel_default, false);
  textarea.addEventListener("dragover", cancel_default, false);
  // canvas요소에 drop이벤트의 리스너를 지정
  textarea.addEventListener("drop", drop_file, false);
}, false);
// 기본 액션을 해제
function cancel_default(event) {
  event.preventDefault();
}
// textarea 요소에 파일이 드랍되었을 때의 처리
function drop_file(event) {
  // 기본 액션을 해제
  event.preventDefault();
  // FileList 오브젝트
  var files = event.dataTransfer.files;
  // 드랍된 파일의 File 오브젝트
  var file = files[0];
  if( ! file ) { return; }
  // 파일의 MIME 타입을 확인
  if( ! file.type.match(/^text\//) ) { return; }
  // 드랍된 파일의 URI를 생성
  var uri = window.webkitURL.createObjectURL(file);
  // XHR로 파일을 읽어 들여 textarea 요소에 표시
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if( this.readyState != 4 || this.status != 200 ) { return; }
    document.querySelector('#editor').textContent = this.responseText;
};
  xhr.open("GET", uri);
  xhr.send();
}
