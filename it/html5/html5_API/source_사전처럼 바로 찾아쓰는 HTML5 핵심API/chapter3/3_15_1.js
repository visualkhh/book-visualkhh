document.addEventListener("DOMContentLoaded", function() {
  // img요소를 생성
  var img = new Image();
  img.src = "pic.png";
  // img요소의 이미지가 로드 완료되면 처리 시작
  img.onload = function() {
    draw(img);
  };
}, false);
function draw(img) {
  // canvas요소
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
var w = canvas.width;
  var h = canvas.height;
  // 레이어를 위한  canvas요소를 생성
  var canvas1 = create_canvas(w, h); // 글자
  var canvas2 = create_canvas(w, h); // 글자 앞의 배경
  // 글자 레이어
  var context1 = canvas1.getContext("2d");
  context1.font = "bold normal 240px Arial";
  context1.textAlign = "center";
  context1.textBaseline = "middle";
  context1.fillText("OK", w/2, h/2);
  // 글자 앞의 뱌경
  var context2 = canvas2.getContext("2d");
  var grad = context2.createRadialGradient(w/2, h/2, 0, w/2, h/2, w/2);
  grad.addColorStop(0, '#ffffff');
  grad.addColorStop(1, '#000000');
  context2.fillStyle = grad;
  context2.fillRect(0, 0, w, h);
  // 합성
  context.drawImage(canvas1, 0, 0);
  context.globalCompositeOperation = "source-in";
  context.drawImage(img, 0, 0);
  context.globalCompositeOperation = "destination-over";
  context.drawImage(canvas2, 0, 0);
};
function create_canvas(w, h) {
  var canvas = document.createElement("canvas");
  canvas.width = w;
  canvas.height = h;
  return canvas;
}
