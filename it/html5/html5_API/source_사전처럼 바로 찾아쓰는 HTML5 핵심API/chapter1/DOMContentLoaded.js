(function () { 
 
var start = Date.now() 
 
// DOMContentLoaded 이벤트가 발생할때의 처리
document.addEventListener("DOMContentLoaded", function() { 
  // 이벤트 발생시간
  var msg = (Date.now() - start) + "ms 후 : " 
  // h2 요소 
  var h2 = document.querySelector("#target"); 
  // h2 요소의 위치 좌표 
  msg += "좌표 (" + h2.offsetLeft + ", " + h2.offsetTop + ")"; 
  document.querySelector("#pos1").innerHTML = msg; 
}, false); 
 
// load 이벤트가 발생할 때의 처리 
window.addEventListener("load", function() { 
  // 이벤트 발생 시간 
  var msg = (Date.now() - start) + "ms 후 : " 
  // h2 요소 
  var h2 = document.querySelector("#target"); 
  // h2 요소의 위치 좌표 
  msg += "좌표 (" + h2.offsetLeft + ", " + h2.offsetTop + ")"; 
  document.querySelector("#pos2").innerHTML = msg; 
}, false); 
 })(); 
