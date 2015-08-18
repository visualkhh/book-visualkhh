document.addEventListener("DOMContentLoaded", function() {
  var input = document.querySelector('input[type="file"]');
  input.addEventListener("change", function(event) {
    // FileList 오브젝트
    var files = event.target.files;
    // 선택된 파일을 나타내는 File 오브젝트
    var file = files[0];
    if( ! file ) { return; }
    // 파일의 각종 정보를 표시
    document.querySelector('#name').textContent = file.name;
    document.querySelector('#size').textContent = file.size;
    document.querySelector('#type').textContent = file.type;
    document.querySelector('#mdate').textContent = file.lastModifiedDate;
  }, false);
}, false);
