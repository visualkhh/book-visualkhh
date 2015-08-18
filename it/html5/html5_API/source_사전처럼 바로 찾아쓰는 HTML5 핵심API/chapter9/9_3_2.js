document.addEventListener("DOMContentLoaded", function() {
  // 현재 위치 정보 얻기
  window.navigator.geolocation.getCurrentPosition(
    show_location, // 위치 정보를 얻었을 때의 호출되는 콜백 함수 
    show_error     // 위치 정보를 얻지 못했을 때 호출되는 콜백 함수
  );
}, false);
// 위치정보를 얻었을 때의 처리 
function show_location(event) {
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

// 위치정보를 얻지 못했을 때의 처리
function show_error(event) {
  alert(event.message + "(" + event.code + ")");
}
