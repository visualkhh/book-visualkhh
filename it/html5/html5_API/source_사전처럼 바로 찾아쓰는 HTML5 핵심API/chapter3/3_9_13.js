document.addEventListener("DOMContentLoaded", function() { 
  // 2D 컨텍스트 
  var canvas = document.querySelector("canvas"); 
  var context = canvas.getContext("2d");   // 반시계 방향에 대한 여부
  var anticlockwise = true; 
  // 패스를 리셋
  context.beginPath(); 
  // 같은 중심을 가진 복수의 사각형 서브 패스 생성
  for( var i=canvas.width; i>=50; i-=50 ) { 
    // rect()인자 값을 산출
    var w = i; 
    var h = w * canvas.height / canvas.width; 
    var x = (canvas.width - w) / 2; 
    var y = (canvas.height - h) / 2; 
    // 사각형의 서브 패스를 생성
    if( anticlockwise == true ) { 
      // 왼쪽 방향으로 그리기
      context.moveTo(x, y); 
      context.lineTo(x, y+h); 
      context.lineTo(x+w, y+h); 
      context.lineTo(x+w, y); 
      context.closePath(); 
    } else { 
      // 오른쪽 방향으로 그리기
      context.rect(x, y, w, h); 
    } 
    // 반시계 방향에 대한 플래그를 반전
    anticlockwise = anticlockwise ? false : true; 
  } 
  // 검은 색으로 채우기
  context.fillStyle = "black"; 
  context.fill(); 
}, false);
