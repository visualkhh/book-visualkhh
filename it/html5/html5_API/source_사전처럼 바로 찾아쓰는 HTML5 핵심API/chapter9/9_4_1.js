document.addEventListener("DOMContentLoaded", function() {
// 옵션 인자 값을 지정
  var position_options = {
enableHighAccuracy: true, // 정확한 위치 요구
    timeout: 60000,           // 최대 대기 시간(밀리초)
    maximumAge: 0             // 캐시 유효기간(밀리초)
  };
  // 현재 위치 얻기
 window.navigator.geolocation.watchPosition(monitor, null, position_options);
}, false);
// 위치정보를 얻었을 때의 처리
function monitor (event) {
  // 위도
  var latitude = event.coords.latitude;
  if(latitude == null)  	latitude = "-";
  
  document.querySelector('#latitude').textContent = latitude;
  // 경도
  var longitude = event.coords.longitude;
     if(longitude == null)  	longitude = "-";
  
  document.querySelector('#longitude').textContent = longitude;
 
  // 위도와 경도의 정밀도
  var accuracy = event.coords.accuracy;
     if(accuracy == null)  	accuracy = "-";
  
  document.querySelector('#accuracy').textContent = accuracy;
  // GPS고도
  var altitude = event.coords.altitude;
     if(altitude == null)  	altitude = "-";
  
  document.querySelector('#altitude').textContent = altitude;
  // GPS고도의 정밀도
  var altitudeAccuracy = event.coords.altitudeAccuracy;
     if(altitudeAccuracy == null)  	altitudeAccuracy = "-";
  
  document.querySelector('#altitudeAccuracy').textContent = altitudeAccuracy;
  // 이동 방향
  var heading = event.coords.heading;
     if(heading == null)  	heading = "-";
  
  document.querySelector('#heading').textContent = heading;
  // 이동 속도
  var speed = event.coords.speed;
     if(speed == null)  	speed = "-";
  
  document.querySelector('#speed').textContent = speed;
  // 시간
  var date = event.timestamp;
  
  if( typeof(date) == "number" ) {
    date = new Date(date);
  }
  document.querySelector('#timestamp').textContent = date.toString();
}
