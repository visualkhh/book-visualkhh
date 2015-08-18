document.addEventListener("DOMContentLoaded", function() { 
  // 다이얼로그를 호출한 페이지로부터 전달된 값을 얻음
  var user = window.dialogArguments; 
  // 텍스트 필드의 값으로 지정
  document.querySelector('#user').value = user; 
  // 확인 버튼에 click이벤트 리스너를 지정
  document.querySelector('#ok').addEventListener("click", function() { 
    // 다이얼로그를 호출한 페이지에 반환 할 값을 지정 
    window.returnValue = document.querySelector('#user').value; 
    // 모달 다이얼로그 닫기
    window.close(); 
  }, false); 
  // 취소 버튼에 click이벤트 리스너를 지정
  document.querySelector('#cancel').addEventListener("click", function() { 
    // 다이얼로그를 호출한 페이지에 반환 할 값을 지정
    window.returnValue = ""; 
    // 모달 다이얼로그 닫기
    window.close(); 
  }, false); 
}, false);
