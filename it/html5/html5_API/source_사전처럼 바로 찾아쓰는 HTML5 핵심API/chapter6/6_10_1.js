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
  // div 요소에 dragover 이벤트 리스너 지정
  div.addEventListener("dragover", function(event) {
    // 기본 액션 해제
    event.preventDefault();
  }, false);
  // div 요소에 drop 이벤트 리스너 지정
  div.addEventListener("drop", function(event) {
    // 기본 액션 해제
    event.preventDefault();
    // FileList 오브젝트
var files = event.dataTransfer.files;
    // 드롭된 파일의 File 오브젝트
    var file = files[0];
    if( ! file ) { return; }
    // 파일의 MIME 타입 확인
    if( ! file.type.match(/^image\//) ) { return; }
    // FileReader 오브젝트
    var reader = new FileReader();
    // Data URL 형식으로 파일 데이터 얻기
    reader.readAsDataURL(file);
    // 파일 데이터 읽기를 성공할 때의 처리
    reader.onload = function() {
      // img 요소를 생성하여 div 요소에 추가
      var img = document.createElement("img");
      img.src = reader.result;
      div.appendChild(img);
    };
  }, false);
}, false);
