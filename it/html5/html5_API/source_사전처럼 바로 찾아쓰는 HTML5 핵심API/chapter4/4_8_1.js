window.addEventListener("load", function() {
  // video 요소
  var video = document.querySelector('video');
  // a 요소 리스트
  var alist = document.querySelectorAll('#dn a');
  // a 요소에 click 이벤트 리스너를 지정
  for( var i=0; i<alist.length; i++ ) {
    alist.item(i).addEventListener("click", function(event) {
      // 기본 액션을 취소
      event.preventDefault();
      // networkState 프로퍼티의 값을 검사
      if( video.networkState == video.NETWORK_LOADING ) {
        // NETWORK_LOADING 상태라면 경고 창 표시
        alert("잠시후 다시 시도해 주세요");
      } else {
        // NETWORK_LOADING 상태가 아니라면 다운로드 
        window.open( event.currentTarget.href, "download" );
      }
    }, false);
}
}, false);
