document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  
  // 텍스트를 지정
  var text = "The canvas element provides scripts with a resolution-dependent bitmap canvas, which can be used for rendering graphs, game graphics, or other visual images on the fly.";
 
 // 폰트를 지정
  context.font = "16px 'Arial'";
  
  // 단어로 분할
  var words = text.split(" ");
  // 줄의 길이를 확인하며 그리기
  var line = "";    // 줄의 텍스트
  var line_y = 25;  // 줄의 y좌표
  var line_max_width = canvas.width - 20; // 줄의 넓이 
  var w;
  while( w = words.shift() ) {
  
  // 줄의 길이를 측정
    var metrix = context.measureText(line + " " + w);
    var line_width = metrix.width;
    // 다음 단어를 더해서 줄의 길이를 넘으면 그리기
    if( line_width > line_max_width ) {
      context.fillText(line, 10, line_y);
      line = w;
      line_y += 25;
    } else {
      line += " " + w;
    }
  }
  // 남은 줄 그리기
  if( line != "" ) {
    context.fillText(line, 10, line_y);
  }
}, false);
