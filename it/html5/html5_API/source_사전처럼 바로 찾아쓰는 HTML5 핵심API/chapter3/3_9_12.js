document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas");   var context = canvas.getContext("2d"); 
  // 계란모양 그리기
  context.beginPath(); 
  context.moveTo(200, 30); 
  context.quadraticCurveTo(30, 30, 30, 75); 
  context.quadraticCurveTo(30, 120, 200, 120); 
  context.quadraticCurveTo(270, 120, 270, 75); 
  context.quadraticCurveTo(270, 30, 200, 30); 
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
