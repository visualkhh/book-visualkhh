document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 원형 그라데이션을 나타내는 CanvasGradient 오브젝트 얻기
  
  var gradient = context.createRadialGradient(150, 75, 10, 150, 75, 75); 
  // 색을 지정
  gradient.addColorStop(0.0, "#ffff00"); // 노랑 
  gradient.addColorStop(1.0, "#00aa00"); // 초록 
  // 원형 그라데이션을 지정
  context.fillStyle = gradient; 

  // 색 채우기
  context.fillRect(0, 0, 300, 150); 

}, false);
