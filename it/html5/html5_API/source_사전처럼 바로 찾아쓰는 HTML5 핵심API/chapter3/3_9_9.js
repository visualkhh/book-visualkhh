document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 서브 패스를 정의
  context.beginPath(); 
  context.moveTo(20, 20); 
  context.arcTo(250, 75, 20, 130, 30); 
  // 윤곽선 그리기
  context.stroke(); 
}, false);
