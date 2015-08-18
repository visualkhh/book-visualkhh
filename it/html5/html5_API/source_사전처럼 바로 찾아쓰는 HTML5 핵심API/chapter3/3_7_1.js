document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 글로벌 알파를 정의
  context.globalAlpha = 0.2; 
  // 색의 종류를 정의
  var colors = ["red", "orange", "yellow", "green", "blue", "purple"]; 
  // 사각형의 위치를 어긋나게 하면서 그리기
  for( var i=0; i<14; i++ ) { 
    // 색을 결정
    context.fillStyle = colors[ i % colors.length ]; 
    // 사각형 그리기 
    var x = 10 * ( i + 1 ); 
    var y = 5 * ( i + 1 ); 
    context.fillRect(x, y, 150, 75); 
    context.strokeRect(x, y, 150, 75); 
  } 
}, false);
