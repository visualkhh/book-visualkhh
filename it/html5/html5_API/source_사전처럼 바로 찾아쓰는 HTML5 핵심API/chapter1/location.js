document.addEventListener("DOMContentLoaded", function() { 
  // 장 번호 
  var no = 0; 
  // document 오브젝트에 keyup 이벤트 리스너를 지정
  document.addEventListener("keyup", function(event) { 
    // 현재의 장 번호
    var m = window.location.hash.match(/^c(\d+)$/); 
    if( m ) { 
      no = parseInt(m[1]); 
    } 
    // 눌린 키의 코드
    var code = event.keyCode; 
    // 좌,우 화살표가 아니면 종료
    if( code != 37 && code != 39 ) { return; } 
    // 눌린 키에 따라 다음 장 번호를 정의
    var next = no; 
    if( code == 37 ) { 
      // 왼쪽 화살표가 눌린 경우
      next --; 
    } else if( code == 39 ) { 
      // 오른쪽 화살표가 눌린 경우 
      next ++; 
    } 
    // 다음 장 번호의 section요소
    var section = document.querySelector('#c' + next); 
    //다음 장 번호의 section요소가 있으면 플래그 식별자를 갱신 
    if( section ) { 
      window.location.hash = "#c" + next; 
      no = next; 
    } 
  }, false); 
}, false);
