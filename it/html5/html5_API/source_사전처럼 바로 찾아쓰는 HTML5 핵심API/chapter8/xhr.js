// 처리 시작
req();
// 정기적으로 서버에서 데이터 가져오기 
function req(i) {
// XMLHttpRequest 객체 생성
var xhr = new XMLHttpRequest();
// 메시지 수신시의 처리 정의
xhr.onreadystatechange = function() {
if( this.readyState != 4 ) { return; }
if( this.status != 200 ) { return; } // 수신한 텍스트를 송신 
self.postMessage(this.responseText);
};
// XHR Request
var url = "xhr.php?t=" + (new Date()).getTime();
xhr.open("GET", url);
xhr.send();
// 이스크립트를 1초후에다시호출
self.setTimeout(arguments.callee, 1000, i);
}