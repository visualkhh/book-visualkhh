document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 큰 사각형 채우기
  context.fillRect(30, 15, 240, 120); 
  // 중간 크기의 사각형을 지우기
  context.clearRect(60, 30, 180, 90); 
  // 작은 사각형의 윤곽선 그리기
  context.strokeRect(90, 45, 120, 60); 
}, false);
