document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 선의 공통속성
  context.lineWidth = 20;
  context.strokeStyle = "black";
  // lineJoin의 값
  var types = ['bevel', 'round', 'miter'];
// 세 종류의 선을 그림
  for( i=0; i<3; i++ ) {
    // 선의 좌표를 지정
    var x0 = 100 * i + 10;
    var y0 = 120;
    var x1 = x0 + 40;
    var y1 = 50;
    var x2 = x1 + 40;
    var y2 = 120;
    // 선 그리기
    context.beginPath();
    context.moveTo(x0, y0);
    context.lineTo(x1, y1);
    context.lineTo(x2, y2);
    context.lineJoin = types[i];
    context.stroke();
  }
}, false);
