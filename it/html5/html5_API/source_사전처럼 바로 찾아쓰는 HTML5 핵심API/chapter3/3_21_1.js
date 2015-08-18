document.addEventListener("DOMContentLoaded", function() {
  // canvas요소
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 선 스타일 지정
  context.lineWidth = 5;
  context.lineCap = "round";
  context.fillStyle = "black";
  context.strokeStyle = "black";
  // 그리는 중인지에 대한 플래그
  var drawing = false;
  // canvas요소에 각종 이벤트 리스너를 지정
  canvas.addEventListener("mousedown", function(event) {
    event.preventDefault();
    drawing = true;
    var p = get_mouse_position(event);
    context.beginPath();
    context.arc(p.x, p.y, context.lineWidth / 2, 0, Math.PI*2, true);
    context.fill();
    context.beginPath();
    context.moveTo(p.x, p.y);
  }, false);
  canvas.addEventListener("mousemove", function(event) {
    event.preventDefault();
    if(drawing == true) {
      draw(event, context);
    }
  }, false);
  canvas.addEventListener("mouseup", function(event) {
    event.preventDefault();
    if(drawing == true) {
      draw(event, context);
      drawing = false;
    }
  }, false);
  canvas.addEventListener("mouseout", function(event) {
    event.preventDefault();
    if(drawing == true) {
      draw(event, context);
      drawing = false;
    }
}, false);
// 저장 버튼에 click이벤트 리스너를 지정
  var save_btn = document.getElementById("save");
  save_btn.addEventListener("click", function() {
    // data URL을 생성
    var data_url = canvas.toDataURL();
    // 새로운 윈도우에 표시
    window.open(data_url, "new image");
  }, false);
  // 지우기 버튼에 click이벤트 리스너를 지정
  var clear_btn = document.getElementById("clear");
  clear_btn.addEventListener("click", function() {
    context.clearRect(0, 0, canvas.width, canvas.height);
  }, false);
}, false);
// canvas에 이미지 그리기
function draw(event, context) {
  var p = get_mouse_position(event);
  context.lineTo(p.x, p.y);
  context.stroke();
  context.beginPath();
  context.moveTo(p.x, p.y);
}
// 마우스 포인터 좌표
function get_mouse_position(event) {
  var rect = event.target.getBoundingClientRect() ;
  return {
    x: event.clientX - rect.left,
    y: event.clientY - rect.top
  };
}
