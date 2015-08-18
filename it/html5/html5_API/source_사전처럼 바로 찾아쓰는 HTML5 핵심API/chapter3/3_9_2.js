document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 패스를 리셋
  context.beginPath(); 
  // 삼각형의 서브 패스(왼쪽)
  context.moveTo(75, 30); 
  context.lineTo(140, 120); 
  context.lineTo(10, 120); 
  context.closePath();   // 삼각형의 서브 패스(오른쪽)
  context.moveTo(225, 30); 
  context.lineTo(290, 120); 
  context.lineTo(160, 120); 
  context.closePath(); 
  // 노랑으로 채우기
  context.fillStyle = "#ffff00"; 
  context.fill(); 
  // 갈색으로 윤곽선 그리기
  context.strokeStyle = "#440000"; 
  context.stroke(); 
}, false);
