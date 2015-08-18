window.addEventListener("load", function() {
  // 이미지
  var img1 = document.getElementById("pic1");
  var img2 = document.getElementById("pic2");
  var img3 = document.getElementById("pic3");
  // 2D컨텍스트
  var canvas = document.createElement("canvas");
canvas.width = 300;
canvas.height = 150;
  var context = canvas.getContext("2d");
  // 입체도형의 높이 
  var wh = 100;
  //입체 도형의 정면의 왼쪽 상단 좌표
  var bx = 80;
  var by = 40;
  // 입체도형의 뒷면의 원근 감을 위한 비율
  var dx = 0.5
  var dy = 0.3
  // 정명을 그리기
  context.translate(bx, by);
  context.drawImage(img1, 0, 0, wh, wh);
  // 오른쪽 면을 그리기
  context.setTransform(dx, -dy, 0, 1, bx+wh, by);
  context.drawImage(img2, 0, 0, wh, wh);
  // 윗쪽 면을 그리기
  context.setTransform(1, 0, -dx, dy, bx, by);
  context.drawImage(img3, 0, -100, wh, wh);
  // div요소를 canvas요소로 바꾸기
  var div = document.getElementById("pic");
  div.parentNode.insertBefore(canvas, div);
  div.parentNode.removeChild(div);
}, false);
