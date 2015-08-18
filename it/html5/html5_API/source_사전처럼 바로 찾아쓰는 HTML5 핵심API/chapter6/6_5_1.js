document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드롭 측의 처리
  * ----------------------------------------------------- */
  // div요소에 dragenter이벤트 리스너 지정
  var div = document.querySelector('#drop');
  div.addEventListener("dragenter", function(event) {
    // 기본 액션 해제
    event.preventDefault();
  }, false);
  // div요소에 dragover이벤트 리스너 지정
  div.addEventListener("dragover", function(event) {
    // 기본 액션 해제
    event.preventDefault();
  }, false);
  // div요소에 drop이벤트 리스너 지정
  div.addEventListener("drop", function(event) {
    // 기본 액션 해제
    event.preventDefault();
    // 선택한 텍스트 얻기
    var text = event.dataTransfer.getData("text");
    // 선택한 텍스트를 div요소에 추가
    div.appendChild( document.createTextNode(text) );
  }, false);
}, false);
