window.addEventListener("load", function() {
  // audio 요소를 생성
  var audio = document.createElement("audio");
  audio.preload = "auto";
  audio.src = "audio.mp3";
  // 에러를 표시를 위한 span요소
  var span = document.querySelector('#error');
  // audio 요소에 error이벤트 리스너를 지정
  audio.addEventListener("error", function() {
    // 에러 코드 얻기
    var c = audio.error.code;
    // 에러 메시지 표시
    if( c == audio.error.MEDIA_ERR_NETWORK ) {
      span.textContent = "네트워크 오류가 발생했습니다.";
    } else if( c == audio.error.MEDIA_ERR_DECODE ) {
      span.textContent = "디코딩 실패";
    } else if( c == audio.error.MEDIA_ERR_SRC_NOT_SUPPORTED ) {
      span.textContent = "지정한 오디오가 적절하지 않습니다.";
    }
  }, false);
  // 버튼에 click 이벤트 리스너를 지정
  var button = document.querySelector('#play');
  button.addEventListener("click", function() {
    if( audio.paused == true ) {
      audio.play();
      button.innerHTML = "&#x25ae;&#x25ae;";
      button.title = "정지 ";
    } else {
      audio.pause();
      button.innerHTML = "&#x25ba;";
      button.title = "재생";
    }
  }, false);
}, false);
