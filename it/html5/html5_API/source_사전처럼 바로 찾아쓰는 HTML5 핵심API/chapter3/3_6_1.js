document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 채울 색을 지정
  context.fillStyle = "#ffff00"; // 노랑 
  // 사각형의 내부를 채우기 
  context.fillRect(30, 15, 240, 120); 
  // 윤곽선의 색을 지정
  context.strokeStyle = "#663300"; // 갈색 
  // 사각형의 윤곽선 그리기
  context.strokeRect(30, 15, 240, 120); 
}, false);
