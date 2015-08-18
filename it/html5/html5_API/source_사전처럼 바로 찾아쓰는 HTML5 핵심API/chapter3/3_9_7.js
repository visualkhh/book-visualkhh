document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 원의 서브 패스를 정의
  context.arc(150, 75, 70, 0, 135 * Math.PI / 180, true);
  // 윤곽 그리기
  context.stroke(); 
}, false);