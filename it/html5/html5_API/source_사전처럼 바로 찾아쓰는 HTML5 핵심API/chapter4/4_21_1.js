window.addEventListener("load", function() {
  // video요소
  var video = document.querySelector('video');
// 커스텀 데이터 속성 data-track-src 에서 자막 파일의 URL 얻기
 var srt_url = video.getAttribute("data-track-src");
  // 자막 파일을 XMLHttpRequest를 사용하여 얻기
  var srt = null;
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if( this.readyState != 4 ) { return; }
    if( this.status != 200 ) { return; }
    srt = parse_srt(this.responseText);
  };
  xhr.open("GET", srt_url);
  xhr.send();
  // 자막을 표시하기 위한 div요소를 생성
  var div = document.createElement("div");
  div.className = "srt";
  video.parentNode.insertBefore(div, video);
  div.style.width = video.width + "px";
  div.style.left = video.offsetLeft + "px";
  div.style.top = (video.offsetTop + parseInt(video.height) - 70) + "px";
  // video요소에 timeupdate이벤트 리스너를 지정
  video.addEventListener("timeupdate", function() {
    // 재생위치에서 자막 텍스트 얻기
    var text = get_cue_text(srt, video.currentTime);
    // 자막 텍스트 그리기
    if( text == "" ) {
      div.style.display = "none";
    } else {
      div.style.display = "block";
      div.textContent = text;
    }
  }, false);
}, false);
// 자막 파일 분석
function parse_srt(txt) {
  if( ! txt ) { return; }
  // 개행（CRLF, CR）을 LF로 통일
  txt = txt.replace(/(\r\n|\r)/, "\n");
// 공백행（\n이 2개 이상 연속 ）으로 큐 별로 텍스트로 분할
var cues = txt.split(/\n{2,}/);
  // 각 큐의 타이밍과 텍스트를 추출
  var srt = [];
  var cue_num = cues.length;
  for( var i=0; i<cue_num; i++ ) {
    var cue = cues[i];
    var lines = cue.split(/\n/);
    //
    var id = lines.shift(); // 큐식별자 (사용하지 않음)
    var timing = lines.shift(); // 큐 타이밍
    var text = lines.join(); // 큐 텍스트
    if(text == "") { continue; }
    // 큐 타이밍을 분석 (시작 초를 산출)
    var m1 = timing.match(/^(\d{2,})\:(\d{2})\:(\d{2})(\.\d{3}) \-\-\> /);
    if( !m1 ) { continue; }
    var sh = convert_string_to_number( m1[1] );
    var sm = convert_string_to_number( m1[2] );
    var ss = convert_string_to_number( m1[3] );
    var sf = parseFloat( m1[4] );
    var s = (sh * 3600) + (sm * 60) + ss + sf;
    // 큐 타이밍을 분석(종료 초를 산출)
    var m2 = timing.match(/ \-\-\> (\d{2,})\:(\d{2})\:(\d{2})(\.\d{3})/);
    if( !m2 ) { continue; }
    var eh = convert_string_to_number( m2[1] );
    var em = convert_string_to_number( m2[2] );
    var es = convert_string_to_number( m2[3] );
    var ef = parseFloat( m2[4] );
    var e = (eh * 3600) + (em * 60) + es + ef;
    //
    srt.push([s, e, text]);
  }
  //
  return srt;
}
// 숫자 문자열을 수치로 변환
function convert_string_to_number(string) {
  string = string.replace(/^0+/, "");
  if( string == "" ) {
    return 0;
  } else {
    return parseInt(string);
  }
}
// 재생 위치의 자막 텍스트 얻기
function get_cue_text(srt, sec) {
  // srt 파일이 분석되지 않았으면 종료
  if( srt == null ) { return ""; }
  // 현재 재생 위치(초)에서 해당 자막 텍스트를 검색
 for( var i=0; i<srt.length; i++ ) {
    var s = srt[i][0];
    var e = srt[i][1];
    var text = srt[i][2];
    // 타이밍이 일치하는지 확인
    if( sec >= s && sec <= e ) {
      return text;
    }
  }
  return "";
}
