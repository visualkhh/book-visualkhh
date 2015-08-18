window.addEventListener("load", function() {
  // audio 요소
  var audio = document.querySelector('audio');
  // 선택된 파일의 URL
  var url = audio.currentSrc;
  // 선택된 파일 표시
  var span = document.querySelector('#url');
  span.textContent = url;
}, false);
