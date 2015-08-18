document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 삼각형의 패스
  context.beginPath();   context.moveTo(150, 30); 
  context.lineTo(220, 120); 
  context.lineTo(80, 120); 
  context.closePath(); 
  // 노랑으로 채우기
  context.fillStyle = "#ffff00"; 
  context.fill(); 
  // 갈색으로 윤곽선 그리기
  context.strokeStyle = "#440000"; 
  context.stroke(); 
}, false);
