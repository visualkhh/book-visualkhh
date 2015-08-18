var context = null;
document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  context = canvas.getContext("2d");
  // 그리기
  draw(false);
  // canvas에 mousemove이벤트의 리스너를 지정
  canvas.addEventListener("mousemove", function(event) {
    // 마우스 포인터가 버튼위에 있는지 여부 판별
    var flag = is_point_in_button(event);
    // 다시 그리기
    draw(flag);
  }, false);
  // canvas에 click이벤트 리스너를 지정
  canvas.addEventListener("click", function(event) {
    // 마우스 포인터가 버튼 위에 있는지 여부를 판별
    var flag = is_point_in_button(event);
    if( flag != true ) { return; }
    // 클릭에 후에 대한 처리
    alert("Started!");
  }, false);
}, false);
// 마우스 포인터가 버튼 위에 있는지 여부를 판별
function is_point_in_button(event) {
  // 마우스 포인터의 좌표
  var pos = get_mouse_position(event);
  // 버튼 클릭영역의 패스를 생성
  set_button_path(context);
  // 마우스 포인터가 버튼 위에 있는지 여부를 판별
var flag = context.isPointInPath(pos.x, pos.y);
  return flag;
}
// 마우스 포인터의 좌표
function get_mouse_position(event) {
  var rect = event.target.getBoundingClientRect() ;
  return {
    x: event.clientX - rect.left,
    y: event.clientY - rect.top
  };
}
// 버튼 그리기
function draw(is_point_in_button) {
  // canvas의 상태를 보존
  context.save();
  // 배경을 진한 회색으로 칠하기
  context.fillStyle = "#222222";
  context.fillRect(0, 0, 300, 150);
  // 둥근 버튼의 바깥쪽 그리기
  context.beginPath();
  context.arc(150, 75, 70, 0, Math.PI * 2, true);
  context.closePath();
  var grad = context.createRadialGradient(150, 75, 60, 150, 75, 70);
  grad.addColorStop(0, "#666666");
  grad.addColorStop(0.5, "#ffffff");
  grad.addColorStop(1, "#666666");
  context.fillStyle = grad;
  context.fill();
  // 둥근 버튼의 안쪽 칠하기
  set_button_path(context);
  var grad = context.createRadialGradient(150, 75, 0, 150, 75, 65);
  var grad_colors = ["#555555", "#444444", "#333333", "#222222"];
  if(is_point_in_button == true) {
    grad_colors = ["#666666", "#555555", "#444444", "#333333"];
  }
  grad.addColorStop(0, grad_colors[0]);
  grad.addColorStop(0.5, grad_colors[1]);
  grad.addColorStop(0.9, grad_colors[2]);
  grad.addColorStop(1, grad_colors[3]);
  context.fillStyle = grad;
  context.fill();
// 버튼 텍스트 그리기
  if(is_point_in_button == true) {
    context.fillStyle = "#ffffff";
  } else {
    context.fillStyle = "#888888";
  }
  context.shadowColor = "black";
  context.shadowOffsetX = -1;
  context.shadowOffsetY = -1;
  context.font = "26px Arial";
  context.textAlign = "center";
  context.textBaseline = "middle";
  context.fillText("START", 150, 75);
  // canvas의 상태를 원래대로 돌리기
  context.restore();
}
// 버튼 클릭 영역의 패스를 생성
function set_button_path() {
  context.beginPath();
  context.arc(150, 75, 60, 0, Math.PI * 2, true);
  context.closePath();
}
