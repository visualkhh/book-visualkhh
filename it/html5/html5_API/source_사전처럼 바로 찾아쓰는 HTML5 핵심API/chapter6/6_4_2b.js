document.addEventListener("DOMContentLoaded", function() {
/* -------------------------------------------------------
* ■드래그 측의 처리
* ----------------------------------------------------- */
// 상품 목록 이미지 리스트
var list = document.querySelectorAll('#items>li');
for( var i=0; i<list.length; i++ ) {
  // li요소
  var li = list.item(i);
  // li 요소에 dragstart이벤트 리스너 지정
  li.addEventListener("dragstart", function(event) {
    // 전송하기 위한 데이터 지정
    event.dataTransfer.setData("text", event.currentTarget.id);
  }, false);
  // li요소에 mousedown이벤트 리스너 지정
  li.addEventListener("mousedown", function(event) {
    // li요소 안의 모든 텍스트를 선택 상태로 하기
    var selection = window.getSelection();
    selection.selectAllChildren(event.currentTarget);
  }, false);
}
  /* -------------------------------------------------------
  * ■드롭 측 처리
  * ----------------------------------------------------- */
  // 장바구니의 ul요소에 dragenter 이벤트 리스너를 지정
  var cart = document.querySelector('#cart');
  cart.addEventListener("dragenter", function(event) {
    // 기본 액션을 무효화
    event.preventDefault();
  }, false);
  // 장바구니의 ul 요소에 dragover 이벤트 리스너 지정
  cart.addEventListener("dragover", function(event) {
    // 기본 액션을 무효화
    event.preventDefault();
  }, false);
  // 장바구니의 ul 요소에 drop 이벤트 리스너를 지정
  cart.addEventListener("drop", function(event) {
    // 기본 액션을 무효화
    event.preventDefault();
    // 전송된 데이터
    var id = event.dataTransfer.getData("text");
    // 드롭된 li 요소
    var li = document.getElementById(id);
    // li요소를  ul 요소에 추가
    cart.appendChild(li);
  }, false);
}, false);
