window.addEventListener("load", function() {
  // video 요소
  var video = document.querySelector('video');
  // video 요소를 클릭했을 때 실행 할 함수
  var playback_toggle = function() {
    if( video.paused == true ) {
      video.play();
    } else {
      video.pause();
    }
  };
  // click 이벤트의 리스너를 지정
  video.addEventListener("click", playback_toggle, false);
  // touchstart 이벤트의 리스너를 지정 (아이패드 용)
  video.addEventListener("touchend", playback_toggle, false);
  // select 요소에 change 이벤트 리스너를 지정
  var select = document.querySelector('select[name="list"]');
  select.addEventListener("change", set_video, false);
}, false);
function set_video() {
  // video 요소 
  var video = document.querySelector('video');
  // select 요소
  var select = document.querySelector('select[name="list"]');
  // 선택된 비디오 이름
  var name = select.value;
  if( ! name.match(/^video[1-3]$/) ) { return; }
  // 재생 가능한 형식을 판별하여 video 요소의 src 속성을 지정
  if( video.canPlayType('video/mp4') ) {
    video.src = name + ".mp4";
  } else if( video.canPlayType('video/webm') ) {
    video.src = name + ".webm";
  } else if( video.canPlayType('video/ogg') ) {
    video.src = name + ".ogv";
  } else {
    return;
  }
  // video 요소를 리셋
  video.load();
}
