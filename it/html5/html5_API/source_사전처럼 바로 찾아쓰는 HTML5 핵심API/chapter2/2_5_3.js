// progress 요소 
var progress = null; 
// button 요소 
var button = null; 
// 로드할 파일의 전체 크기
var total = undefined; 
// 로드가 끝난 파일의 데이터 크기
var loaded = undefined; 
 
// document 오브젝트에 DOMContentLoaded 이벤트의 리스너를 지정
document.addEventListener("DOMContentLoaded", function() { 
  progress = document.querySelector('progress'); 
  button = document.querySelector('button'); 
  button.addEventListener("click", data_load, false); 
}, false); 
 
// 로드 시작
function data_load() { 
  // 버튼을 무효화 하고 표시를 변경
  button.disabled = true; 
  button.textContent = '로드중'; 
  // XmlHttpRequest 
  var xhr = new XMLHttpRequest(); 
  // progress 이벤트의 핸들러를 지정 
  xhr.onprogress = progress_handler; 
  // load 이벤트의 핸들러를 지정
  xhr.onload = load_handler; 
  // 파일을 로드
  xhr.open("GET", "aaa.zip"); 
  xhr.send(); 
} 
 
// progress 의 핸들러
function progress_handler(event) { 
  // 로드할 파일의 전체 크기
  total = event.total;   
  // 로드가 완료된 파일의 전체 크기
  loaded = event.loaded; 
  // 프로그레스바를 업데이트
  update_progress(); 
} 
 
// load 이벤트의 핸들러
function load_handler(event) { 
  // 로드가 완료된 크기를 파일의 전테 크기에 맞춘다
  loaded = total; 
  // 프로그래스바를 업데이트
  update_progress(); 
  // 버튼의 표시를 변경
  button.textContent = '로드 완료'; 
} 
 
// 프로그레스바를 업데이트
function update_progress() { 
  if( isNaN(total) || isNaN(loaded) ) { return; } 
  if( ! progress ) { return; } 
  // progress 요소의 max 와 value 속성의 값을 지정
  progress.max = total; 
  progress.value = loaded; 
  // 진행의 비율 구하기
  rate = progress.position; 
  // progress 요소를 지원하지 않는 브라우저용
  if( ! rate && total > 0 ) { 
    rate = loaded / total; 
  } 
  progress.textContent = parseInt( rate * 100 ) + '% (' + loaded + '/' + total + ')'; 
}
