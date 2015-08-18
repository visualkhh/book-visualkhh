document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드롭 측의 처리
  * ----------------------------------------------------- */
  // div요소에 dragenter이벤트 리스너 지정
  var div = document.querySelector('#drop');
  div.addEventListener("dragenter", function(event) {
    // 기본 액션을 해제
    event.preventDefault();
  }, false);
  // div요소에 dragover이벤트 리스너를 지정
  div.addEventListener("dragover", function(event) {
    // 기본 액션을 해제
    event.preventDefault();
  }, false);
  // div요소에 drop이벤트 리스너 지정
  div.addEventListener("drop", function(event) {
    // 기본 액션을 해제
    event.preventDefault();
    // 전송된 데이터
    var dt = event.dataTransfer;
    if( dt.types.contains("text/plain") == true ) 
    {
      // 텍스트 일 때 
      var text = dt.getData("text/plain");
      div.appendChild( document.createTextNode(text) );
    } 
    else if( dt.types.contains("text/x-moz-url") == true ) 
    {
      // 바탕화면의 이미지 파일일 때
      var url = dt.getData("text/x-moz-url");
      if( url.match(/\.(jpg|jpeg|gif|png)$/i) ) 
      {
        var img = document.createElement("img");
        img.src = url;
        div.appendChild(img);
      }
    }
  }, false);
}, false);
