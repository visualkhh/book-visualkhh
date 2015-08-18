document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 서브패스를 정의
  context.beginPath(); 
  context.moveTo(150, 130); 
  context.arcTo(220, 130, 150, 20, 10);   context.arcTo(150, 20, 80, 130, 10); 
  context.arcTo(80, 130, 220, 130, 10); 
  context.closePath(); 
  // 노란 색으로 채우기
  context.fillStyle = "#ffff00"; 
  context.fill(); 
  // 갈색으로 윤곽 그리기
  context.strokeStyle = "#440000"; 
  context.stroke(); 
}, false);
