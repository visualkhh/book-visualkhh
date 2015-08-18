document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 직선 그리기
  for( var i=1; i<=6; i++ ) { 
    context.beginPath(); 
    context.moveTo(30, 20*i); 
    context.lineTo(270, 20*i); 
    context.lineWidth = i; 
    context.stroke(); 
  } 
}, false);
