document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드래그측의 처리
  * ----------------------------------------------------- */
  // 드래그 아이콘을 위한 이미지 생성
  var fb = document.createElement("img");
  fb.src = "fb.png";
  // 드래그할 img 요소에 dragstart 이벤트 리스너 지정
  var img = document.querySelector('#book');
  img.addEventListener("dragstart", function(event) {
    // 드래그 아이콘을 지정
    event.dataTransfer.setDragImage(fb, 30, 30);
  }, false);
}, false);
