document.addEventListener("DOMContentLoaded", function() {
// 2D컨텍스트
var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 선의 공통속성
  context.lineWidth = 30;
  context.strokeStyle = "black";
  context.lineJoin = "miter";
  // 첫 번째 두 직선의 연결 (miterLimit=7)
  context.miterLimit = 7;
  context.beginPath();
  context.moveTo(20, 15);
  context.lineTo(150, 35);
  context.lineTo(20, 55);
  context.stroke();
  // 두 번째 두 직선의 연결（miterLimit=3)
  context.miterLimit = 3;
  context.beginPath();
  context.moveTo(20, 95);
  context.lineTo(150, 115);
  context.lineTo(20, 135);
  context.stroke();
}, false);
