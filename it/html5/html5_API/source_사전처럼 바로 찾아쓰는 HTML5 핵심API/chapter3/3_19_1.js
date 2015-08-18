document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 평행 사변형 그리기
  draw_parallelogram(context);
  // 원 그리기
  draw_circle(context);
  // 텍스트 그리기
  draw_text(context);
}, false);
// 평행 사변형 그리기
function draw_parallelogram(context) {
  // 그리기 상태를 저장
  context.save();
  // canvas의 폭과 높이 
  var w = context.canvas.width;
  var h = context.canvas.height;
  // 평행 사변형 그리기
  context.transform(1, 0, 0.5, 1, w/2, h/2);
  context.beginPath();
  context.rect(-100, -50, 200, 100);
  context.fillStyle = "#aa0000";
  context.fill();
  context.strokeStyle = "#330000";
  context.stroke();
  // 그리기 상태를 원래대로 돌리기
  context.restore();
}
// 텍스트 그리기
function draw_text(context) {
  // 그리기 상태를 보존
  context.save();
  // 텍스트 그리기
  context.fillStyle = "white";
  context.shadowColor = "black";
  context.shadowOffsetX = 1;
  context.shadowOffsetY = 1;
  context.shadowBlur = 2;
context.font = "20px 'Arial'";
context.fillText("Text", 50, 50);
  // 그리기 상태를 원래대로 돌리기
  context.restore();
}
// 원을 그리기
function draw_circle(context) {
  // 그리기 상태를 저장
  context.save();
  // canvas의 폭과 높이
  var w = context.canvas.width;
  var h = context.canvas.height;
  // 원 그리기
  context.beginPath();
  context.arc(w/2, h/2, 45, 0, Math.PI * 2, true);
  context.fillStyle = "#007700";
  context.fill();
  context.stroke(); // 기본으로 검은색으로 그려짐
  // 그리기 상태를 원래대로 돌림
  context.restore();
}
