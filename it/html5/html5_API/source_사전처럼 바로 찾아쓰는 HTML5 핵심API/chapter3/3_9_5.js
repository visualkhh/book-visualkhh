document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d"); 
  // 반시계방향에 대한 플래그
  var anticlockwise = true; 
  // 패스를 리셋
  context.beginPath(); 
  // 원의 서브 패스를 만드는 함수
  var make_circle = function(r) { 
    // 서브 패스를 생성
    context.moveTo(150 + r, 75); 
    context.arc(150, 75, r, 0, Math.PI*2, anticlockwise); 
    context.closePath(); 
    // 반시계방향 플래그를 반전
    anticlockwise = anticlockwise ? false : true; 
  } 
  // 동심원 상에 복수의 원 서브패스를 생성
  for( var i=200; i>=10; i-=10 ) { 
    make_circle(i); 
  } 
  // 녹색으로 채우기
  context.fillStyle = "green"; 
  context.fill(); 
}, false);
