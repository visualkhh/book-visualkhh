window.addEventListener("load", function() {
  // video 요소
  var video = document.querySelector('video');
  // span 요소
  var span = document.querySelector('#time');
  // 5초 앞으로 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#ff').addEventListener("click", function() {
    if( video.currentTime + 5 > video.duration ) {
      video.currentTime = video.duration;
    } else {
      video.currentTime += 5;
    }
    span.textContent = video.currentTime.toFixed(3);
  }, false);
  // 처음으로 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#bk').addEventListener("click", function() {
    var stime;
    if( typeof( video.initialTime ) == "number" ) {
      stime = video.initialTime;
    } else if( typeof( video.startTime ) == "number" ) {
      stime = video.startTime;
    } else {
      stime = 0;
    }
    video.currentTime = stime;
    span.textContent = stime.toFixed(3);
  }, false);
  // 5초 뒤로 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#fr').addEventListener("click", function() {
    if( video.currentTime - 5 < 0 ) {
      video.currentTime = 0;
    } else {
      video.currentTime -= 5;
    }
    span.textContent = video.currentTime.toFixed(3);
  }, false);
  // video 요소에 timeupdate 이벤트 리스너를 지정
  video.addEventListener("timeupdate", function() {
    span.textContent = video.currentTime.toFixed(3);
  }, false);
}, false);
