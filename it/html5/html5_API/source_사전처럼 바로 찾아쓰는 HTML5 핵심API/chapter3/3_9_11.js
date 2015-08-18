document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 하트 그리기 
  context.beginPath(); 
  context.moveTo(150, 30); 
  context.bezierCurveTo(130, 0, 80, 0, 80, 50); 
  context.bezierCurveTo(80, 90, 120, 100, 150, 140); 
  context.bezierCurveTo(180, 100, 220, 90, 220, 50); 
  context.bezierCurveTo(220, 0, 170, 0, 150, 30); 
  context.closePath(); 
  // 그라데이션으로 채우기
  var grad = context.createRadialGradient(150, 70, 0, 150, 40, 150); 
  grad.addColorStop(0, "#ffaaaa"); 
  grad.addColorStop(1, "#ff0000"); 
  context.fillStyle = grad; 
  context.fill(); 
  // 윤곽선 그리기
  context.strokeStyle = "#990000"; 
  context.stroke(); 
}, false);
