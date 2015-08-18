document.addEventListener("DOMContentLoaded", function() {
  // button요소에 click 이벤트 리스너 지정
  button = document.querySelector('button');
  button.addEventListener("click", function() {
    // ul요소
    var ul = document.querySelector('ul');
    // Selection오브젝트
    var selection = window.getSelection();
    // ul 요소의 텍스트 모두를 선택 상태로 하기
    selection.selectAllChildren(ul);
  }, false);
}, false);
