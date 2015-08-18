window.addEventListener("load", function() {
  // vide 요소
  var video = document.querySelector('video');
  // 음량을 0.5로 지정
  video.volume = 0.5;
  // meter 요소에 값을 지정하는 함수 
  var update_meter = function() {
    var meter = document.querySelector('#vl');
    meter.value = (video.volume * 10).toFixed();
    meter.textContent = meter.value;
  };
  update_meter();
  // 음량 낯추기 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#dn').addEventListener("click", function() {
    var v = (video.volume  - 0.1).toFixed(1);
    if( v < 0 ) { v = 0; }
    video.volume = v;
    update_meter();
  }, false);
  // 음량 올리기 버튼에 click 이벤트 리스너를 지정
  document.querySelector('#up').addEventListener("click", function() {
    var v = (video.volume  + 0.1).toFixed(1);
if( v > 1 ) { v = 1; }
    video.volume = v;
    update_meter();
  }, false);
  // 음소거 체크 박스에 click 이벤트 리스너를 지정
  var mt = document.querySelector('#mt');
  mt.addEventListener("click", function() {
    video.muted = mt. checked ? true : false;
  }, false);
}, false);
