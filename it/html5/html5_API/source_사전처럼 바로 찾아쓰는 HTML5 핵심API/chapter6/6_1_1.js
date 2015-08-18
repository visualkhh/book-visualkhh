document.addEventListener("DOMContentLoaded", function() {
  
/* -------------------------------------------------------
* ■드래그에 대한 처리
* ----------------------------------------------------- */
  
// 상품목록 이미지 리스 
var imgs = document.querySelectorAll('#items>li>img');
  
for( var i=0; i<imgs.length; i++ ) {
    
// img
var img = imgs.item(i);
   
 // img요소에 dragstart이벤트 리스너를 지정
img.addEventListener("dragstart", function(event) {
      
// 데이터 전송을 위한 데이터 지
event.dataTransfer.setData("text", event.target.id);
    
}, false);
  }
  
/* -------------------------------------------------------
  
* ■드롭에 대한 처리
* ----------------------------------------------------- 
*/
  
// 장바구니의 ul요소에 dragenter이벤트 리스너 지정
var cart = document.querySelector('#cart');
  
cart.addEventListener("dragenter", function(event) {
    
// 기본 액션을 취소
 event.preventDefault();
  }, false);
  //장바구니의 ul요소에 dragover이벤트 리스너 지정
  cart.addEventListener("dragover", function(event) {
    // 기본 액션을 취
    event.preventDefault();
  }, false);
  // 장바구니의 ul 요소에 drop이벤트 리스너 지정
  cart.addEventListener("drop", function(event) {
    // 기본 액션을 취소
    event.preventDefault();
    // 데이터 전송으로 전송된 정보
    var id = event.dataTransfer.getData("text");
    // 드롭된 img요소
    var img = document.getElementById(id);
    // li요소를 생성하여 장바구니에 추가
    var li = document.createElement("li");
    li.appendChild(img);
    cart.appendChild(li);
  }, false);
}, false);

