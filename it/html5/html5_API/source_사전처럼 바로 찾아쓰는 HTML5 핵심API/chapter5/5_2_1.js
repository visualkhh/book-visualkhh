document.addEventListener("DOMContentLoaded", function() {
  // document오브젝트에 select이벤트 리스너를 지정
  document.addEventListener("mouseup", function() {
    // Selection오브젝트
    var selection = window.getSelection();
    // 선택된 텍스트
    var text = selection.toString();
    // 선택된 텍스트를 경고 창으로 표시
    if( text ) {
      alert( text );
    }
  }, false);
}, false);
