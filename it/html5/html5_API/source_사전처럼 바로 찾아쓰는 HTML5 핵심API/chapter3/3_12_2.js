document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 원의 그림자
  context.shadowColor = "#666666";
  context.shadowOffsetX = 5;
  context.shadowOffsetY = 5;
  context.shadowBlur = 10;
  // 원 그리기
  context.beginPath();
  context.arc(150, 75, 50, 0, Math.PI * 2, true);
  var grad = context.createRadialGradient(150, 75, 0, 150, 75, 50);
  grad.addColorStop(0,'#888888');
  grad.addColorStop(1,'#000000');
  //context.fillStyle = grad;
  //context.fill();
  // 문자의 그림자
  context.shadowColor = "#000000";
  context.shadowOffsetX = 2;
  context.shadowOffsetY = 2;
  context.shadowBlur = 2;
  // 문자 그리기
  context.fillStyle = "#ffffff";
  context.textAlign = "center";
  context.textBaseline = "middle";
  context.font = "20px Arial";
  context.fillText("Canvas", 150, 75);
}, false);
