document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드래그 측의 처리
  * ----------------------------------------------------- */
  // p요소에 dragstart 이벤트 리스너 지정
  var p = document.querySelector('#text');
  p.addEventListener("dragstart", function(event) {
    // 선택한 텍스트 얻기
    var selection = window.getSelection();
    var text = selection.toString();
    // 허용하는 기능
    //event.dataTransfer.effectAllowed = "move";
    event.dataTransfer.effectAllowed = "copy";
    // 전송하기 위한 데이터 지정
    event.dataTransfer.setData("text", text);
  }, false);
  /* -------------------------------------------------------
  * ■드롭 측의 처리
  * ----------------------------------------------------- */
  // div요소에 dragenter 이벤트 리스너 지정
  var div = document.querySelector('#drop');
  div.addEventListener("dragenter", function(event) {
    // 기본 액션 해제
    event.preventDefault();
    // 드래그 포인터 지정
    event.dataTransfer.dropEffect = "copy";
  }, false);
  // div요소에 dragover이벤트 리스너 지정
  div.addEventListener("dragover", function(event) {
    // 기본 액션 해제
    event.preventDefault();
    // 드래그 포인터 지정
    event.dataTransfer.dropEffect = "copy";
  }, false);
  // div 요소에 drop이벤트 리스너 지정
  div.addEventListener("drop", function(event) {
    // 기본 액션 해제
    event.preventDefault();
// 선택된 텍스트 얻기
    var text = event.dataTransfer.getData("text");
    // 선택된 텍스트를 div 요소에 추가
    div.appendChild(document.createTextNode(text));
  }, false);
}, false);
