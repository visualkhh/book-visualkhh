document.addEventListener("DOMContentLoaded", function() {
  // button 요소에 click이벤트 리스너를 지정
  button = document.querySelector('button');
  button.addEventListener("click", function() {
    // p요소
    var p = document.querySelector('#p');
    // 편집 가능여부로 조건 분기
    if( p.isContentEditable == false ) {
// 편집 불가면 가능으로 변경
      p.contentEditable = "true";
      // 포커스 주기
      p.focus();
      // 버튼 표기 변경
      button.textContent = "저장 ";
    } else {
      // 편집 가능이면 불가능으로 변경
      p.contentEditable = "false";
      // 버튼 표기를 변경
      button.textContent = "편집 ";
    }
  }, false);
}, false);
