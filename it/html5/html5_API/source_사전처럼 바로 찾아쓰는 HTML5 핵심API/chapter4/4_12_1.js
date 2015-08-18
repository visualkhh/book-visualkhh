window.addEventListener("load", function() {
  // video 요소 
  var video = document.querySelector('video');
  // 재생 속도를 변경하는 함수
  var change_rate = function(rate) {
    // 재생 시작
    video.play();
    // 재생 속도를 변경
    video.playbackRate = rate;
    // 재생 속도를 표시
var pb = document.querySelector('#pb');
    if( video.playbackRate >= 0 ) {
      pb.textContent = "> " + video.playbackRate + "x";
    } else {
      pb.textContent = "< " + video.playbackRate + "x";
    }
  };
  // + 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#ff').addEventListener("click", function() {
    change_rate( video.playbackRate + 1 );
  }, false);
  // 재생버튼에 click 이벤트 리스너를 지정
  document.querySelector('#pb').addEventListener("click", function() {
    change_rate(video.defaultPlaybackRate);
  }, false);
  // - 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#fr').addEventListener("click", function() {
    change_rate( video.playbackRate - 1 );
  }, false);
}, false);
