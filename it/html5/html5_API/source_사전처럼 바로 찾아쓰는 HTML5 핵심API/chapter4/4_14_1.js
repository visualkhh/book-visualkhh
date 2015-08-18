// 페이지 로드가 완료 되었을 때의 처리
window.addEventListener("load", function() {
  // video 요소
  var video = document.querySelector('video');
  // canvas 요소
  var cvs = document.querySelector('canvas');
  var ctx = cvs.getContext('2d');
  // canvas 요소에 click 이벤트 리스너를 지정
  cvs.addEventListener("click", function(event) {
    skip_video(event, video, ctx);
  }, false);
  // 타이머 설정 (표시 화면을 그림)
  window.setInterval( function() {
    draw_indicator(video, ctx);
  }, 1000);
}, false);
// 클릭된 위치로 재생 위치를 이동
function skip_video(event, video, ctx) {
  // 비디오의 길이를 알 수 없으면 종료
  if( ! video.duration ) { return; }
  // canvas 요소 안의 클릭좌표를 얻음
  var pos = get_mouse_position(event);
  // 비디오의 재생위치를 이동
  video.currentTime = video.duration * pos.x / ctx.canvas.width;
  // 화면 표시
  draw_indicator(video, ctx);
}
// 화면에 표시 영역을 그림
function draw_indicator(video, ctx) {
  // 비디오의 길이를 알 수 없으면 종료
  if( ! video.duration ) { return; }
  // canvas 요소
  var cvs = ctx.canvas;
  // canvas의 상태를 보존
  ctx.save();
  // 배경을 검은색으로 그리기
  ctx.fillStyle = "#000000";
  ctx.fillRect(0, 0, cvs.width, cvs.height);
  // 재생 완료 범위를 그림
  var pr = video.played;  // TimeRanges 오브젝트
  if( pr ) {
    var grad = ctx.createLinearGradient(0, 0, 0, cvs.height*3/4);
    grad.addColorStop(0.0, "#999999");
    grad.addColorStop(0.4, "#aaaaaa");
    grad.addColorStop(1.0, "#444444");
    ctx.fillStyle = grad;
    for( var i=0; i<pr.length; i++ ) {
      var s = pr.start(i); // 범위의 시작 위치
      var e = pr.end(i); // 범위의 종료 위치
      // canvasに描画
      var x = cvs.width * ( s / video.duration );
      var w = cvs.width * ( ( e - s ) / video.duration );
      ctx.fillRect(x, 0, w, cvs.height*3/4);
    }
  }
  // 버퍼링 완료 영역을 그림
  var br = video.buffered; // TimeRanges 오브젝트
  if( br ) {
    ctx.fillStyle = "#aaaaaa";
    for( var i=0; i<br.length; i++ ) {
      var s = br.start(i); // 범위의 시작 위치
      var e = br.end(i); // 범위의 종료 위치
      // canvas에 그리기
      var x = cvs.width * ( s / video.duration );
      var w = cvs.width * ( ( e - s ) / video.duration );
      ctx.fillRect(x, cvs.height*3/4, w, cvs.height/4);
    }
  }
// 현재 재생중인 위치를 그림
var cx = cvs.width * video.currentTime / video.duration;
  ctx.fillStyle = "#ffffff";
  ctx.shadowOffsetX = 1;
  ctx.shadowColor = "#000000";
  ctx.shadowBlur = 1;
  //
  ctx.beginPath();
  ctx.moveTo(cx-3, 0);
  ctx.lineTo(cx+3, 0);
  ctx.lineTo(cx+0.5, 3);
  ctx.lineTo(cx+0.5, cvs.height-3);
  ctx.lineTo(cx+3, cvs.height);
  ctx.lineTo(cx-3, cvs.height);
  ctx.lineTo(cx-0.5, cvs.height-3);
  ctx.lineTo(cx-0.5, 3);
  ctx.closePath();
  ctx.fill();
  // canvas의 상태를 원래대로
  ctx.restore();
}
// 마우스 포인터의 좌표
function get_mouse_position(event) {
  var rect = event.target.getBoundingClientRect() ;
  return {
    x: event.clientX - rect.left,
    y: event.clientY - rect.top
  };
}
