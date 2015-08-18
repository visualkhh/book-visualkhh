document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
// 선형 그라데이션을 나타내는CanvasGradient 오브젝트 얻기
   var gradient = context.createLinearGradient(0, 75, 300, 75); 
  // 색을 지정
  gradient.addColorStop(0.2, "#ff0000"); // 빨 
  gradient.addColorStop(0.3, "#ffa500"); // 주 
  gradient.addColorStop(0.4, "#ffff00"); // 노
  gradient.addColorStop(0.5, "#008000"); // 초 
  gradient.addColorStop(0.6, "#0000ff"); // 파 
  gradient.addColorStop(0.7, "#4b0082"); // 남 
  gradient.addColorStop(0.8, "#800080"); // 보 
  // 선형 그라데이션을 지정
  context.fillStyle = gradient; 
  // 사각형 채우기
  context.fillRect(0, 0, 300, 150); 
}, false);
