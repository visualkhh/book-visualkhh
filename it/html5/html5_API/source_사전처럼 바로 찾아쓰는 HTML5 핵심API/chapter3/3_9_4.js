document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 패스를 리셋
  context.beginPath(); 
  // 바깥쪽 삼각형의 서브 패스(오른쪽으로 그리기 진행)
  context.moveTo(150, 30); 
  context.lineTo(220, 120); 
  context.lineTo(80, 120); 
  context.closePath(); 
  // 내부 사각형의 서브 패스 
  context.moveTo(125, 75); 
  context.lineTo(125, 100); 
  context.lineTo(175, 100); 
  context.lineTo(175, 75); 
  context.closePath(); 
  // 노랑 색으로 채우기
  context.fillStyle = "#ffff00"; 
  context.fill(); 
  // 갈색으로 윤곽선 그리기
  context.strokeStyle = "#440000"; 
  context.stroke(); 
}, false);
