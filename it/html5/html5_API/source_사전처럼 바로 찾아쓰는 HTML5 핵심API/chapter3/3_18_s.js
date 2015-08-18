
document.addEventListener("DOMContentLoaded", function() {
  // 2D 컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  //변환을 적용
  context.scale(0.5, 0.5);
  //context.rotate(15*Math.PI/180);
  //context.translate(30,15);
  
  
  // 격자모양으로 배경을 그림
  var yflag = true;
  for( var y=0; y<canvas.height; y+=10 ) {
    var xflag = true;
    for( var x=0; x<canvas.width; x+=10 ) {
      context.globalAlpha = xflag ^ yflag ? 0.3 : 0;
      context.fillRect(x, y, 10, 10);
      xflag = xflag ? false : true; // 플래그를 반전
    }
    yflag = yflag ? false : true; // 플래그를 반전
  }
  // Canvas의 중심에 위치하도록 사각형을 그림
  var rw = 100;
  var rh = 50;
  var rx = (canvas.width - rw) / 2;
  var ry = (canvas.height - rh) / 2;
  context.globalAlpha = 1;
  context.fillRect(rx, ry, rw, rh);
}, false);
