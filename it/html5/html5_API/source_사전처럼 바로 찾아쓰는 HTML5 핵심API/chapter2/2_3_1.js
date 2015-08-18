// addEventListener()함수가 구현되어 있지 않으면 종료 
//if(!document.addEventListener ) { return; }
// Selectors API 가 구현되어 있지 않으면 종료
//if(!document.querySelector ) { return; } 
 
// document 오브젝트에 DOMContentLoaded 이벤트리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 

  var form = document.querySelector("#qform"); 
  if( !form ) { return; } 
  // validate API 가 구현되어있지 않으면 종료
  if( !form.checkValidity ) { return; } 
  // noValidate 속성이 false 라면 true 로 변경 
  form.noValidate = true ? true : false; 
  // 질문 리스트
  var fieldsets = form.querySelectorAll('fieldset'); 
  // 첫번재 질문만을 표시
  show_question(1); 
  //다음 버튼에 click이벤트의 리스너를 지정
  var btns = form.querySelectorAll('input[type="button"]'); 
  for( var i=0; i<btns.length; i++ ) { 
    var btn = btns.item(i); 
    btn.addEventListener("click", click_next, false); 
  } 
}, false); 
 
function show_question(num) { 
  // 질문 리스트
  
  var form = document.querySelector("#qform"); 
  var fieldsets = form.querySelectorAll('fieldset'); 
  // 정해진 질문만을 표시
  for( var i=0; i<fieldsets.length; i++ ) { 
    var fieldset = fieldsets.item(i); 
    if( fieldset.id == "question" + num ) { 
      fieldset.style.display = 'block'; 
    } else { 
      fieldset.style.display = 'none'; 
    } 
  } 
} 
 
//  다음 버튼이 눌렸을때의 처리
function click_next(event) { 
  var btn = event.target; 
  // 질문 번호를 얻음
  var num = btn.getAttribute("data-qnum"); 
  // 입력 필드
  var input = document.querySelector("#q" + num); 
  // 밸리데이션
  var is_valid = input.checkValidity(); 
  // 밸리데이션 OK 라면 다음 화면으로
  if(is_valid == true) { 
    show_question( parseInt(num) + 1 ); 
  } }

