document.addEventListener("DOMContentLoaded", function() {
  // 감시 식별 ID
  var watch_id = 0;
  // 버튼에 click 이벤트 리스너 지정
  var button = document.querySelector('button');
  button.addEventListener("click", function() {
    if( watch_id > 0 ) {
      // 실시간 감시를 정지
      window.navigator.geolocation.clearWatch(watch_id);
      // 감시 식별 ID에 0을 지정
      watch_id = 0;
      // 버튼 표기를 변경
      button.textContent = "시작 ";
    } else {
      // 실시간 감시 시작
      watch_id = window.navigator.geolocation.watchPosition(monitor);
      // 버튼 표기를 변경
button.textContent = "정지 ";
    }
  }, false);
}, false);

// 실시간 감시
function monitor(event) {
  // 위도
  var latitude = event.coords.latitude;
  document.querySelector('#latitude').textContent = latitude;
  // 경도
  var longitude = event.coords.longitude;
  document.querySelector('#longitude').textContent = longitude;
}
