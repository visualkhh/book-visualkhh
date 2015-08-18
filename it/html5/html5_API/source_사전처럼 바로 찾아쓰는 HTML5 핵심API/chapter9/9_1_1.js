document.addEventListener("DOMContentLoaded", function() {
  // 현 위치 정보 얻기
  window.navigator.geolocation.getCurrentPosition(show_location);
}, false);
// 위치정보를 얻은 후의 처리
function show_location(event) {
  // 위도
  var latitude = event.coords.latitude;
  document.querySelector('#latitude').textContent = latitude;
  // 경도
  var longitude = event.coords.longitude;
  document.querySelector('#longitude').textContent = longitude;
}
