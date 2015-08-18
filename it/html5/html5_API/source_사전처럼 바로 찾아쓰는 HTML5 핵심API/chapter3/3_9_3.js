document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  /* ------------------------------------------------ 
  * 좌측의 삼각형
  * ---------------------------------------------- */ 
  // 삼각형의 패스 정의
  context.beginPath(); 
  context.moveTo(75, 30); 
  context.lineTo(140, 120); 
  context.lineTo(10, 120); 
  context.closePath(); 
  // 노랑으로 채우기
  context.fillStyle = "#ffff00"; 
  context.fill(); 
  // 갈색으로 윤곽선 그리기
  context.strokeStyle = "#440000"; 
  context.stroke(); 
  /* ------------------------------------------------ 
  * 우측의 삼각형
  * ---------------------------------------------- */ 
  // 삼각형의 패스 정의
  context.beginPath(); 
  context.moveTo(225, 30); 
  context.lineTo(290, 120); 
  context.lineTo(160, 120); 
  context.closePath(); 
  // 빨강으로 채우기
  context.fillStyle = "#ff0000"; 
  context.fill(); 
  // 검은색으로 윤곽선 그리기
  context.strokeStyle = "#000000"; 
  context.stroke(); 
}, false);
