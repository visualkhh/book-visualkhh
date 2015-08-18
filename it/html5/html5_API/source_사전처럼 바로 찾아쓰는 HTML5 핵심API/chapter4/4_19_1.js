document.addEventListener("DOMContentLoaded", function() {
  // audio 요소
  var audio = document.querySelector('#player audio,video');
  
  // 상태를 나타내는 span 요소
  var span_st = document.querySelector('#player span.st');
  
  // 현재 위치를 나타내는 canvas 요소 준비
  var canvas = document.querySelector('#player canvas');
  
  // canvas 요소에 click 이벤트 리스너 지정
  canvas.addEventListener("click", function(event) {
   
   // 클릭 위치에 따라 오디오를 탐색
    click_seek(event, audio, canvas);
  
  // 재생 위치 정보를 갱신
    update_timeline(audio, canvas);
  }, false);
  
  // audio 요소에 timeupdate이벤트 리스너 지정
  audio.addEventListener("timeupdate", function() {
  
  // 재생 위치 정보를 갱신
    update_timeline(audio, canvas);
  }, false);
  
  // audio 요소에 error이벤트 리스너 지정
  audio.addEventListener("error", function() {
  
  // 에러 표시
    show_error(audio, span_st);
  }, false);
  
  // audio 요소에기타 이벤트 리스너 지정
  var events = [
    "loadstart", "loadedmetadata", "play", "pause",
    "waiting", "stalled", "canplaythrough",
    "seeking", "seeked", "ended"
  ];
  
  for( var i=0; i<events.length; i++ ) {
    audio.addEventListener(events[i], function(event) {
  
  // 이벤트에 따라 상태 표시
      show_event_status(event, span_st);
  
  // 재생 위치 정보를 갱신
      update_timeline(audio, canvas);
    }, false);
  }
  
  // 재생 버튼에 click이벤트 리스너 지정
  var pl = document.querySelector('#player button.pl');
  pl.addEventListener("click", function(event) {
  
  // 재생 버튼이 눌렸을때의 처리
    click_play_button(event, audio);
  }, false);
  
  // 리셋 버튼에 click이벤트 리스너 지정
  var rt = document.querySelector('#player button.rt');
  rt.addEventListener("click", function() { audio.load(); }, false);
}, false);


// 재생 버튼이 눌렸을때의 처리
function click_play_button(event, audio) {
  var pl = event.currentTarget;
  if( audio.paused == true ) {
    // 재생 시작
    audio.play();
    // 표기를 변경
    pl.innerHTML = "&#x25ae;&#x25ae;";
    pl.title = "정지 ";
  } else {
    // 재생 중지
    audio.pause();
    // 표기를 변경
    pl.innerHTML = "&#x25ba;";
    pl.title = "재생 ";
  }
}


// 이벤트에 따라 상태 변경
function show_event_status(event, span_st) {
  if( event.type == "loadstart" ) {
    span_st.textContent = "준비중 ";
  } else if( event.type == "loadedmetadata" ) {
    span_st.textContent = "메타정보 얻는중";
  } else if( event.type == "play" ) {
    span_st.textContent = "재생 시작 ";
  } else if( event.type == "pause" ) {
    span_st.textContent = "일시정지중 ";
  } else if( event.type.match(/^(waiting|stalled)$/) ) {
    span_st.textContent = "버퍼링중...";
  } else if( event.type == "canplaythrough" ) {
    span_st.textContent = "버퍼링 완료 ";
  } else if( event.type == "seeking" ) {
    span_st.textContent = "탐색중";
  } else if( event.type == "seeked" ) {
    span_st.textContent = "탐색 완료";
  } else if( event.type == "ended" ) {
    span_st.textContent = "종료 ";
  }
}


// 에러 표시
function show_error(audio, span_st) {
  if( audio.error.code == audio.error.MEDIA_ERR_NETWORK ) {
span_st.textContent = "네트워크 오류";
  } else if( audio.error.code == audio.error.MEDIA_ERR_DECODE ) {
    span_st.textContent = "디코드 실패";
  } else if( audio.error.code == audio.error.MEDIA_ERR_SRC_NOT_SUPPORTED ) {
    span_st.textContent = "부적절한 파일";
  }
}


// 클릭위치에 따라 오디오 탐색
function click_seek(event, audio, canvas) {
  
  // canvas 요소 내에서 클릭좌표 얻기
  var pos = get_mouse_position(event);
  
  // 클릭된 위치의 초 구하기
  var tm = audio.duration * pos.x / canvas.width;
  
  // 재생위치 지정
  audio.currentTime = tm;
}


// 재생위치 정보 갱신
function update_timeline(audio, canvas) {
 
 // canvas의 2D컨텍스트
  var ctx = canvas.getContext('2d');
 
 // canvas의 사애를 보존
  ctx.save();
 
 // 배경을 검은색으로
  ctx.fillStyle = "#444444";
  ctx.fillRect(0, 0, canvas.width, canvas.height);
 
 // 현재 재생위치와 전체 길이 
  var c = audio.currentTime.toFixed(1);
  var d = audio.duration.toFixed(1);
 
 // canvas 요소 다시 그리기
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

// canvas를 원래 대로
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
