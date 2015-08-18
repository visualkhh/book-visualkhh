window.addEventListener("load", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 패턴을 그린 img요소
  var img = document.querySelector("#pattern");
  // 패턴을 생성하여  fillStyle에 지정
  var pattern = context.createPattern(img, "repeat");
  context.fillStyle = pattern;
// 사각형의 패스를 만들고 속을 채우고 윤곽선을 그림
context.beginPath();
  context.rect(50, 25, 200, 100);
  context.fill();
  context.stroke();
}, false);
