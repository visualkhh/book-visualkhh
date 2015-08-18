document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드래그 측의 처리
  * ----------------------------------------------------- */
  // li 요소에 dragstart 이벤트 리스너를 지정
  var lis = document.querySelectorAll('#items>li');
  for( var i=0; i<lis.length; i++ ) {
    lis.item(i).addEventListener("dragstart", function(event) {
      // 아이콘 표시를 위한 요소를 추가
      event.dataTransfer.addElement(event.currentTarget);
    }, false);
  }
}, false);
