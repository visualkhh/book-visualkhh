document.addEventListener("DOMContentLoaded", function() { 
  while( true ) { 
    // 입력 다이얼 로그를 표시
    var user = window.prompt("사용자명을 입력해 주세요"); 
    // 입력 상태에 따라 처리를 분기
    if( user == null ) { 
      // 취소가 눌리면 종료
      break; 
    } else if( user == "" ) { 
      // 아무 입력이 없는데 OK가 눌리면 경고
      window.alert("값을 입력하지 않았습니다."); 
    } else { 
      // 값을 입력하고OK를 누르면 확인 다이얼로그 표시 
      var message = "「" + user + "」을 지정해도 좋습니까?"; 
      var res = window.confirm(message); 
      // 확인 다이얼로그에서 OK 가 눌리면 값을 span요소에 지정하고 종료
      if( res == true ) { 
        document.querySelector('#user').textContent = user; 
        break; 
      } 
    } 
  } 
}, false);
