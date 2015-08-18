document.addEventListener("DOMContentLoaded", function() {
  var input = document.querySelector('input[type="file"]');
  input.addEventListener("change", function(event) {
    // FileList 오브젝트
    var files = event.target.files;
    // 선택된 파일을 나타내는 File 오브젝트
    var file = files[0];
    if( ! file ) { return; }
    // FileReader 오브젝트
    var reader = new FileReader();
    // 로드 버튼에 click 이벤트 리스너 지정
    document.querySelector('#load').addEventListener("click", function() {
      // error 이벤트 핸들러 지정
      reader.onerror = function() {
        show_error(reader);
      };
      // Data URL 형식으로 파일 데이터 얻기
      reader.readAsDataURL(file);
    }, false);
    // 중지 버튼에 click 이벤트 리스너 지정
    document.querySelector('#abort').addEventListener("click", function() {
      abort_load(reader);
    }, false);
  }, false);
}, false);
// 오류 표시
function show_error(reader) {
    var code = reader.error.code;
    if( code == reader.error.NOT_FOUND_ERR ) {
      alert("파일을 발견할 수 없었습니다.");
    } else if( code == reader.error.SECURITY_ERR ) {
      alert("보안 오류가 발생 했습니다.");
    } else if( code == reader.error.ABORT_ERR ) {
alert("파일 읽기가 중지 되었습니다.");
    } else if( code == reader.error.NOT_READABLE_ERR ) {
      alert("파일 읽기가 막혀 있습니다.");
    } else if( code == reader.error.ENCODING_ERR ) {
      alert("파일 용량이 너무 큽니다.");
    }
}
// 로드 중지
function abort_load(reader) {
  //  로드가 시작되지 않았으면 종료
  if( ! reader ) { return; }
  // 로드 중이 아니라면 종료
  if( reader.readyState == reader.LOADING ) { return; }
  // 로드 중지
  reader.abort();
}
