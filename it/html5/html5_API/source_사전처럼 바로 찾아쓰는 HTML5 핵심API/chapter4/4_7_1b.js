     window.addEventListener("load", function() {
  // audio 요소 
  var audio = document.querySelector('audio');
  // 선택된 파일의 URL
  var url = audio.currentSrc;
  // audio요소와 source 요소의 리스트
  var list = document.querySelectorAll('audio,source');
  // 선택된 파일 판별
  var src = "";
  for( var i=0; i<list.length; i++ ) {
    var el = list.item(i);
    if( el.src == url ) {
      src = el.getAttribute("src");
      break;
    }
  }
  // 선택된 파일 표시
  var span = document.querySelector('#url');
  span.textContent = src;
}, false);
