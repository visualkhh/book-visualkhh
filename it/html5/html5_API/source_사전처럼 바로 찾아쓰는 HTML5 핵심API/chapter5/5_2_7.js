document.addEventListener("DOMContentLoaded", function() { 
// p 요소에 mouseup 이벤트 리스너를 지정
p = document.querySelector('p[contenteditable="true"]'); 
p.addEventListener("mouseup", function() {
// Selection 객체
var selection = window.getSelection(); 
// 선택 영역의 수
var n = selection.rangeCount;
// 선택 영역이 하나 이하라면 종료
if( n <= 1 ) { return; }
// 마지막 선택 영역
var range = selection.getRangeAt(n-1); 
// 마지막 선택 영역을 제거 
selection.removeRange(range);
  }, false);
}, false);