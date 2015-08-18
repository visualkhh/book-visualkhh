document.addEventListener("DOMContentLoaded", function() {
  var input = document.querySelector('input[type="file"]');
  input.addEventListener("change", function(event) {
    // FileList 오브젝트
    var files = event.target.files;
    // 선택된 파일을 나타내는 File 오브젝트
    var file = files[0];
    if( ! file ) { return; }
    // 파일의 URI를 생성
    var uri = window.webkitURL.createObjectURL(file);
    // img 요소에 지정
    var img = document.createElement("img");
    img.src = uri;
    document.body.appendChild(img);
  }, false);
}, false);
