document.addEventListener("DOMContentLoaded", function() {
  // video 요소 오브젝트
  var video = document.querySelector('video');
  // button 요소 오브젝트
  var button = document.querySelector('button');
  // button 요소에 click이벤트 리스너를 지정
  button.addEventListener("click", function() {
    // loop 프로퍼티 의 값을 반전
    video.loop = video.loop ? false : true;
    // button 요소의 텍스트를 변경
    if( video.loop == true ) {
      button.textContent = " 반복 재생 ";
    } else {
      button.textContent = " 반복 재생 해제 상태 ";
    }
  }, false);
}, false);
