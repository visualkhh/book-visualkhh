(function () {  
// addEventListener() 함수가 동작하지 않으면 종료
if( ! window.addEventListener ) { return; } 
 
// 글로벌 변수를 정의
var displays = null; 
// 순위 표시용 div요소의 NodeList 
 
// window 오브젝트에 load 이벤트의 리스너를 지정 
window.addEventListener("load", function() { 
  // 순위를 표시할 div 요소의 NodeList 얻기 
  var ranking = document.getElementById("ranking"); 
  if( ! ranking ) { return; } 
  displays = ranking.getElementsByClassName("display"); 
  if( displays.length == 0 ) { return; } 
  // 순위 데이터의 URL을 커스텀 데이터 속성으로부터 얻기 
  var url = get_custom_data(ranking, "resource"); 
  // XHR로 데이터 텍스트 얻기 
  var xhr = new XMLHttpRequest(); 
  xhr.onreadystatechange = callback; 
  xhr.open("GET", url); 
  xhr.send(); 
}, false); 
 
// custom.txt를 로드한 후의 처리
function callback() { 
  if( this.readyState != 4 ) { return; } 
  if( this.status != 200 ) { return; } 
  // 텍스트 데이터를 행으로 분할하여 배열에 담기 
  var data = this.responseText; 
  var lines = data.split("\n"); 
  var list = []; 
  for( var i=0; i<lines.length; i++ ) { 
    var line = lines[i]; 
    if(line) { 
      list.push(line); 
    } 
  } 
  // 데이터를 순위 표시용 div 요소에 지정 
  for( var i=0; i<displays.length; i++ ) { 
    var div = displays.item(i); 
    // 커스텀 데이터 속성에서 오프셋 값과 리미트 값 얻기
    var offset = get_custom_data(div, "offset"); 
    var limit = get_custom_data(div, "limit"); 
    offset = parseInt(offset); 
    limit = parseInt(limit); 
    if(limit == 0) { continue; } 
    // 순위 표시용 ol 요소를 생성하여 div 요소 안에 배치 
    var ol = document.createElement("ol"); 
    ol.start = offset + 1; 
    for( var n=offset; n<offset+limit; n++ ) {
       if( n >= list.length ) { break; } 
      var li = document.createElement("li"); 
      li.appendChild(document.createTextNode(list[n])); 
      ol.appendChild(li); 
    } 
    div.innerHTML = ""; 
    div.appendChild(ol); 
  } 
} 
 
// 커스텀 데이터 속성값 얻기 
function get_custom_data(element, name) { 
  if(element.dataset) { 
    return element.dataset[name]; 
  } else { 
    return element.getAttribute("data-" + name); 
  } 
} 
 
})();
