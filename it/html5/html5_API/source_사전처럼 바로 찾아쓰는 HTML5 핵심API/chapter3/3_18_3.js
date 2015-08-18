document.addEventListener("DOMContentLoaded", function() {
  // 페이지 상의 canvas요소
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 좌표 변환을 정의 (이동)
context.translate(canvas.width / 2, canvas.height /2);
// 별을 50ms마다 다시 그림
  window.setInterval( function() {
    draw_star(canvas);
  }, 50);
}, false);

//별그리기
function draw_star(canvas) {
  // 2D컨텍스트
  var context = canvas.getContext("2d");
  // Canvas의 폭과 높이
  var w = canvas.width;
  var h = canvas.height;
  // Canvas를 클리어
  context.clearRect(-w, -h, w*2, h*2);
  // 좌표 변환을 정의 (회전)
  context.rotate(1 * Math.PI / 180);
  // 원주의 반경을 정의
  var r = Math.min(w, h) * 0.5;
  // 별의 꼭지점의 좌표를 계산하는 함수
  var sx = function(idx) {
    var rad = Math.PI * 2 * idx / 5;
    return r * Math.cos(rad);
  };
  var sy = function(idx) {
    var rad = Math.PI * 2 * idx / 5;
    return r * Math.sin(rad);
  };
  //  별을 그림
  context.beginPath();
  context.moveTo(sx(0), sy(0));
  context.lineTo(sx(2), sy(2));
  context.lineTo(sx(4), sy(4));
  context.lineTo(sx(1), sy(1));
  context.lineTo(sx(3), sy(3));
  context.closePath();
  context.fill();
}
