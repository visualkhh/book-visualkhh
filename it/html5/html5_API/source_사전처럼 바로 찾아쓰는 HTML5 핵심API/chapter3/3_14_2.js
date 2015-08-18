(function () {
// 카운터
var counter = 0;
// 페이지가 로드되었을대 실행
document.addEventListener("DOMContentLoaded", function() {
  
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  
  // canvas의 사이즈
  var w = canvas.width;
  var h = canvas.height;
  
  // 청을 그릴 canvas요소를 생성
  var canvas1 = create_layer1(w, h);
  
  // 원을 그리기 위한 canvas요소를 생성
  var canvas2 = create_layer2(w, h);
  
  // 50ms마다 canvas를 다시 그림
  window.setInterval(function(){
   
   // 원 이미지를 갱신
    update_canvas2(canvas2);
   
   // 메인 canvas에 레이어를 겹치게 그림
    context.drawImage(canvas2, 0, 0);  // 원
    context.drawImage(canvas1, 0, 0);  // 창살
  }, 50);
}, false);

// 원을 갱신
function update_canvas2(canvas) {

// 2D컨텍스트
var context = canvas.getContext("2d");
  // canvas요소의 넓이와 높이
  var w = canvas.width;
  var h = canvas.height;
  
  // 카운터를 갱신
  counter ++;
  if( counter >= w * 2 ) { counter = 1; }
 
 // 검은 색으로 전체를 칠함
  context.fillStyle = "black";
  context.fillRect(0, 0, w, h);
  
  // 원을 그림
  var x = (counter > w) ? w - ( counter % w ) : counter;
  var y = h / 2;
  var r = 80;
  context.beginPath();
  context.arc(x, y, r, 0, Math.PI * 2, true);
  var grad  = context.createRadialGradient(x, y, 0, x, y, r);
  grad.addColorStop(0, 'white');
  grad.addColorStop(1, 'black');
  context.fillStyle = grad;
  context.fill();
}


// 원을 그리기 위한 canvas요소를 생성
function create_layer2(w, h) {
  var canvas = document.createElement("canvas");
  canvas.width = w;
  canvas.height = h;
  return canvas;
}


// 창살을 그릴 canvas요소를 생성
function create_layer1(w, h) {
  var canvas = document.createElement("canvas");
  canvas.width = w;
  canvas.height = h;
  var context = canvas.getContext("2d");
  var bw = 5; // 창살의 두께
  for( var x=20; x<canvas.width; x+=20 ) {
    var grad  = context.createLinearGradient(x, 0, x+bw, 0);
    grad.addColorStop(0,"#666666");
    grad.addColorStop(0.5,"#eeeeee");
    grad.addColorStop(1,"#666666");
context.fillStyle = grad;
    context.fillRect(x, 0, bw, canvas.height);
  }
  return canvas;
}
})();
