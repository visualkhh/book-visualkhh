document.addEventListener("DOMContentLoaded", function() {
  // button 요소에 click 이벤트 리스너를 지정
  button = document.querySelector('button');
  button.addEventListener("click", function() {
    // p요소
    var p = document.querySelector('#p');
    // 편집 가능 여부로 조건 분기
    if( p.contentEditable == "false" ) {
      // 편집 불가능 하다면 편집 가능으로 변경
      p.contentEditable = "true";
      // Selection 오브젝트
      var selection = window.getSelection();
      // p 요소의 텍스트를 모두 선택 상태로 하기
      selection.selectAllChildren(p);
      // 캐럿의 위치를 4번째 글자 다음으로 이동
      selection.collapse(p.firstChild, 4);
      // 버튼 표기 변경
      button.textContent = "저장 ";
    } else {
      // 편집 가능한 상태라면 편집 불가능상태로 변경
      p.contentEditable = "false";
      // 버튼의 표기 변경
      button.textContent = "편집 ";
    }
  }, false);
}, false);
