document.addEventListener("DOMContentLoaded", function() {
  var input = document.querySelector('input[type="file"]');
  input.addEventListener("change", function(event) {
    // FileList 오브젝트
    var files = event.target.files;
    // 선택된 파일을 나타내는 File 오브젝트
    var file = files[0];
    if( ! file ) { return; }
    if( ! file.type.match(/^video\/mp4$/) ) {
      alert("MP4 비디오 파일을 지정해 주세요");
    }
    // 비디오 파일 읽기 시작
    load_file(file);
  }, false);
}, false);
function load_file(file) {
  // FileReader 오브젝트
  var reader = new FileReader();
  // Data URL 형식으로 파일 데이터 얻기
  reader.readAsDataURL(file);
  // 파일 읽기의 진행률을 표시
  var span = document.querySelector('#state');
  var handler = window.setInterval( function() {
    if( reader.readyState == reader.EMPTY ) {
      span.textContent = "로드중 ";
    } else if( reader.readyState == reader.LOADING ) {
      span.textContent = "로드중 ";
    } else if( reader.readyState == reader.DONE ) {
      span.textContent = "완료 ";
      // video 요소의 src 속성을 지정
      var video = document.querySelector('video');
      video.src = reader.result;
      video.load();
      // 타이머 해제
      window.clearInterval(handler);
    }
}, 100);
}
