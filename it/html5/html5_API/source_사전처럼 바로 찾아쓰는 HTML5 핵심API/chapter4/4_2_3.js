(function () {
// video 요소
var video = null;
// 페이지가 로드 되었을 때의 처리
window.addEventListener("load", function() {
  // video 요소 오브젝트
  video = document.querySelector('video');
  // video 요소가 클릭되었을 때의 처리
  window.addEventListener("click", toggle_size, false);
  // 창의 크기가 변경 되었을 때의 처리
  window.addEventListener("resize", centering, false);
  // video 요소를 한가운데로 이동
  centering();
  video.style.display = "block";
}, false);
// video 요소가 클릭되었을 때의 처리
function toggle_size() {
  if( video.height == video.videoHeight ) {
    // 창의 크기에 맞춤
    video.width = document.documentElement.clientWidth;
    video.height = document.documentElement.clientHeight;
    // video요소의 위치를 왼쪽 상단으로
    video.style.left = "0px";
    video.style.top = "0px";
  } else {
    // 원래 크기로 돌리기
    video.width = video.videoWidth;
    video.height = video.videoHeight;
    // video요소를 한가운데로 이동
    centering();
  }
}
// video 요소를 한가운데로 이동
function centering() {
  // 창 크기
  var sw = document.documentElement.clientWidth;
  var sh = document.documentElement.clientHeight;
  // video 요소의 크기
  var vw = video.width;
  var vh = video.height;
  // video 요소를 가운데로 이동
  video.style.left = ( (sw - vw) / 2 ) + "px";
  video.style.top = ( (sh - vh) / 2 ) + "px";
}
})();
