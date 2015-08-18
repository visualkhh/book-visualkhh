document.addEventListener("DOMContentLoaded", function() {
  // img요소의 생성
  var img = new Image();
  img.src = "pic.png";
  // img요소의 이미지가 로드되면 처리 시작
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
  // 배경 
  var grad = context.createRadialGradient(w/2, h/2, 0, w/2, h/2, w/2);
  grad.addColorStop(0, '#ffffff');
  grad.addColorStop(1, '#000000');
  context.fillStyle = grad;
  context.fillRect(0, 0, w, h);
  // 클리핑 영역의 패스를 작성
  context.beginPath();
  context.arc(w/2, h/2, Math.min(w, h) * 0.8 / 2, 0, Math.PI * 2, true);
  // 글자 부분을 클리핑 
  context.clip();
  // 사진을 그리기
  context.drawImage(img, 0, 0);
};
