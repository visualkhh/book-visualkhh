document.addEventListener("DOMContentLoaded", function() {
  // video요소
  var video = document.querySelector('#player video');
  // 재생위치를 나타내는 canvas요소 
  var canvas = document.querySelector('#player canvas.timeline');
  // 썸네일 표시를 위한 canvas요소의 생성
  var canvas2 = document.createElement("canvas");
canvas2.id = "thumbnail";
canvas2.width = 100;
  canvas2.height = parseInt(canvas2.width * video.height / video.width);
  document.body.appendChild(canvas2);
  // 썸네일을 담을 배열을 준비
  var thumbs = new Array(10);
  //
  // 재생위치를 표시할 canvas요소에 click 이벤트 리스너를 지정
  canvas.addEventListener("click", function(event) {
    // 클릭 위치에 따라 비디오를 탐색 
    click_seek(event, video, canvas);
    // 재생 위치정보를 갱신
    update_timeline(video, canvas);
  }, false);
  // 재생 버튼에 click 이벤트 리스너를 지정
  var pl = document.querySelector('#player button.pl');
  pl.addEventListener("click", function(event) {
    // 재생 버튼이 눌렸을 때의 처리
    click_play_button(event, video);
  }, false);
  // video 요소에 timeupdate 이벤트 리스너를 지정
  video.addEventListener("timeupdate", function() {
    // 재생위치정보를 갱신
    update_timeline(video, canvas);
    // 썹네일 얻기
    get_thumbnail(video, thumbs, canvas2.width, canvas2.height);
  }, false);
  // 재생위치를 표시할 canvas요소에 mousemove 이벤트 리스너를 지정
  canvas.addEventListener("mousemove", function(event) {
    // 썸네일 표시
    show_thumbnail(event, video, canvas, canvas2, thumbs);
  }, false);
  // 재생위치를 표시할 canvas요소에 mouseout 이벤트 리스너를 지정
  canvas.addEventListener("mouseout", function(event) {
    // 썸네일 숨기기
    canvas2.className = "hide";
  }, false);
}, false);
// 썸네일 얻기
function get_thumbnail(video, thumbs, w, h) {
  // 썸네일의 인덱스 번호
  var index = parseInt( video.currentTime * 10 / video.duration );
  // 이미 썸네일을 얻었다면 종료
if( thumbs[index] ) { return; }
  // 작업을 위한 canvas요소를 생성
  var cvs = document.createElement("canvas");
  cvs.width = w;
  cvs.height = h;
  // 썸네일 얻기
  var ctx = cvs.getContext('2d');
  ctx.drawImage(video, 0, 0, video.width, video.height, 0, 0, w, h);
  // 배열에 썸네일 canvas요소 담기
  thumbs[index] = cvs;
}
// 썸네일을 표시
function show_thumbnail(event, video, canvas, canvas2, thumbs) {
  // canvas 요소 안에 마우스 포인터좌표 구하기
  var pos = get_mouse_position(event);
  // 마우스 포인터의 위치 시간을 구하기
  var tm = video.duration * pos.x / canvas.width;
  // 썸네일인덱스 번호
  var index = parseInt( tm * 10 / video.duration );
  // 썸네일이 생성되어 있지 않다면 숨기고 종료
  if( ! thumbs[index] ) {
    canvas2.className = "";
    return;
  }
  // 썸네일을 canvas요소에 표시
  var ctx2 = canvas2.getContext('2d');
  ctx2.drawImage(thumbs[index], 0, 0);
  // 썸네일의 좌표를 변경
  canvas2.style.left = parseInt(event.pageX - canvas2.width/2)+ "px";
  canvas2.style.top = parseInt(event.pageY - canvas2.height - 10) + "px";
  // 썸네을 표시
  canvas2.className = "show";
}
// 재생 버튼이 눌렸을때의 처리
function click_play_button(event, video) {
  // 눌린 button 요소
  var pl = event.currentTarget;
  if( video.paused == true ) {
    // 재생 시작
    video.play();
    // 표기를 변경
pl.innerHTML = "&#x25ae;&#x25ae;";
    pl.title = "정지 ";
  } else {
    // 재생 중지
    video.pause();
    // 표기를 변경
    pl.innerHTML = "&#x25ba;";
    pl.title = "재생 ";
  }
}
// 클릭 위치에 따라 비디오를 탐색
function click_seek(event, video, canvas) {
  // canvas요소안에서의 클릭 좌표 구하기
  var pos = get_mouse_position(event);
  //  클릭된 위치의 초 구하기 
  var tm = video.duration * pos.x / canvas.width;
  // 재생 위치 지정
  video.currentTime = tm;
}
// 재생 위치 정보를 갱신
function update_timeline(video, canvas) {
  // canvas의 2D컨텍스트
  var ctx = canvas.getContext('2d');
  // canvas의 상태 저장
  ctx.save();
  //배경을 검은 색으로 채우기
  ctx.fillStyle = "#444444";
  ctx.fillRect(0, 0, canvas.width, canvas.height);
  // 현재의 재생 위치와 길이
  var c = video.currentTime.toFixed(1);
  var d = video.duration.toFixed(1);
  // canvas요소를 다시 그림
  ctx.fillStyle = "#777777";
  ctx.fillRect(0, 0, canvas.width * c / d, canvas.height);
  ctx.shadowOffsetX = 1;
  ctx.shadowOffsetY = 1;
  ctx.shadowBlur = 1;
  ctx.shadowColor = "#000000";
  ctx.textAlign = "center";
  ctx.textBaseline = "middle";
  ctx.font = "12px Arial";
ctx.fillStyle = "#ffffff";
  var text = c + "s / " + d + "s";
  ctx.fillText(text, canvas.width/2, canvas.height/2);
  // canvas를 원래대로
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
