document.addEventListener("DOMContentLoaded", function() { 
  // button 요소
  var button = document.querySelector('button'); 
  // button 요소에 click 이벤트의 리스너를 지정
  button.addEventListener("click", function() { 
    // 사용자 명을 얻음
    var span = document.querySelector('#user'); 
    // 모달 다이얼로그를 표시
    var opt = "dialogwidth=400px; dialogheight=100px;" 
    var new_user = window.showModalDialog("dialog.html", span.textContent, opt); 
    // 모달 다이얼로그에서 입력된 사용자 명을 표시
    if( new_user ) { 
      span.textContent = new_user; 
    } 
  }, false); 
}, false);
