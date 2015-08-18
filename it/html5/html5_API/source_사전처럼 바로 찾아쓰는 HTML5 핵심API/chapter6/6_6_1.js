document.addEventListener("DOMContentLoaded", function() {
  /* -------------------------------------------------------
  * ■드래그 측의 처리
  * ----------------------------------------------------- */
  // 상품 목록의 이미지 리스트
  var imgs = document.querySelectorAll('#items>li>img');
  for( var i=0; i<imgs.length; i++ ) {
    // img 요소
    var img = imgs.item(i);
    // img요소에 dragstart이벤트 리스너 지정
    img.addEventListener("dragstart", function(event) {
      // 데이터 전송을 위한 데이터 지정
      event.dataTransfer.setData("src", event.target.src);
      event.dataTransfer.setData("title", event.target.title);
    }, false);
  }
  /* -------------------------------------------------------
  * ■드롭 측 처리
  * ----------------------------------------------------- */
  // 장바구니의 ul 요소에 dragenter 이벤트 리스너 지정
  var cart = document.querySelector('#cart');
  cart.addEventListener("dragenter", function(event) {
    // 기본 액션 해제
    event.preventDefault();
  }, false);
  // 장바구니의 ul 요소에 dragover 이벤트 리스너 지정
  cart.addEventListener("dragover", function(event) {
    // 기본 액션 해제
    event.preventDefault();
}, false);
  // 장바구니의 ul 요소에 drop 이벤트 리스너 지정
  cart.addEventListener("drop", function(event) {
    // 기본 액션 해제
    event.preventDefault();
    // img 요소를 생성
    var img = document.createElement("img");
    img.src = event.dataTransfer.getData("src");
    img.title = event.dataTransfer.getData("title");
    // li 요소를 생성하여 장바구니에 추가
    var li = document.createElement("li");
    li.appendChild(img);
    cart.appendChild(li);
  }, false);
}, false);
