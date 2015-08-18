document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
// 배경 원(녹색)
  context.beginPath();
  context.arc(150, 75, 40, 0, Math.PI * 2, true);
  context.fillStyle = "rgba(0, 255, 0, 0.3)";
  context.fill();
  // 그림자（파랑색）
  context.shadowColor = "blue";
  context.shadowOffsetX = 20;
  context.shadowOffsetY = 20;
  // 사각형（반투명한 빨간색）
  context.beginPath();
  context.rect(50, 50, 200, 50);
  context.fillStyle = "rgba(255, 0, 0, 0.5)";
  context.fill();
}, false);
