document.addEventListener("DOMContentLoaded", function() {
  var input = document.querySelector('input[type="file"]');
  input.addEventListener("change", function(event) {
    // FileList 오브젝ㅌ,
    var files = event.target.files;
    // 선택된 파일을 나타내는 File 오브젝트
    var file = files[0];
    if( ! file ) { return; }
    // FileReader 오브젝트
    var reader = new FileReader();
    // progress 이벤트 핸들러 지정
    reader.onprogress = show_progress;
    // load 이벤트 핸들러 지정
    reader.onloadend = show_progress;
    // 바이너리 형식으로 파일 데이터 얻기
    reader.readAsBinaryString(file);
  }, false);
}, false);
// 파일 로드의 진행 률 표시
function show_progress(event) {
  document.querySelector('#total').textContent = event.total;
  document.querySelector('#loaded').textContent = event.loaded;
var rate = ( event.loaded * 100 / event.total ).toFixed(1);
document.querySelector('#rate').textContent = rate;
  document.querySelector('progress').value = rate;
}
