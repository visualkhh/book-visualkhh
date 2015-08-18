document.addEventListener("DOMContentLoaded", function() {
  var input = document.querySelector('input[type="file"]');
  input.addEventListener("change", function(event) {
    // FileList 오브젝트
    var files = event.target.files;
    // 선택된 파일을 나타내는 File 오브젝트
    var file = files[0];
if( ! file ) { return; }
    // 이미지 파일의 포맷을 판단
    show_image_format(file);
  }, false);
}, false);
function show_image_format(file) {
  // FileReader 오브젝트
  var reader = new FileReader();
  // 바이너리 형식으로 파일 데이터 얻기
  reader.readAsBinaryString(file);
  // 파일 데이터 읽기가 성공했을 때의 처리
  reader.onload = function() {
    // 바이너리 데이터
    var bin = reader.result;
    // 앞부분 8바이트 얻기
    var header = bin.slice(0, 8);
    // 포맷을 판단（GIF, BMP, PNG, JPEG）
    var fmt = "";
    if( header.match(/^GIF8[79]a/) ) {
      fmt = "GIF";
    } else if( header.match(/^BM/) ) {
      fmt = "BMP";
    } else if( header.match(/^\x89PNG\x0d\x0a\x1a\x0a/) ) {
      fmt = "PNG";
    } else if( header.match(/^\off\xd8/) ) {
      fmt = "JPEG";
    }
    // 포맷 표시
    document.querySelector('#format').textContent = fmt;
  };
}
