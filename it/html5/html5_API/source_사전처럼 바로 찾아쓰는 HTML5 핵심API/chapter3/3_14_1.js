window.addEventListener("load", function() {
  // img요소
  var img = document.getElementById("pic");
  // button요소
  var btn = document.getElementById("shuffle");
  // button요소에  click이벤트 리스너 지정
  btn.addEventListener("click", function(event){
    event.target.disabled = true;
    shuffle(img);
  }, false);
}, false);

// 이미지 섞기
function shuffle(img) {

  // 이미지의 폭과 높이
var w = parseInt(img.width);
var h = parseInt(img.height);

  // canvas요소를 생성
  var canvas = document.createElement("canvas");
  canvas.width = w;
  canvas.height = h;
  var context = canvas.getContext("2d");
  
  // img요소를 canavs요소에 지정
  img.parentNode.insertBefore(canvas, img);
  img.parentNode.removeChild(img);
  
  // 분할 할 조각 수 
  var n1 = 4; // 가로 
  var n2 = 3; //세로
  
  // 한 조각의 크기
  var pw = w / n1;
  var ph = h / n2
  
  // 각 조각의 왼쪽 위의 좌표를 배열에 넣기
  var pp = [];
  for( var y=0; y<h; y+=ph ) {
    for( var x=0; x<w; x+=pw ) {
      pp.push([x, y]);
    }
  }
  
  // 각 조각의 순서를 무작위로 섞음
  var rpp = [];
  while( pp.length > 0 ) {
    var el = pp.splice( Math.floor(Math.random() * pp.length), 1 );
    rpp.push(el[0]);
  }
  
  // 조각 들을 Canvas에 그림
  for( var y=0; y<h; y+=ph ) {
    for( var x=0; x<w; x+=pw ) {
      // 조각 들을 그림
      var p = rpp.shift();
      context.drawImage(img, p[0], p[1], pw, ph, x, y, pw, ph);
      
      // 조각에 그림자를 추가
      draw_shadow(context, x, y, pw, ph);
    }
  }
}

// 조각에 그림자 추가
function draw_shadow(context, x, y, w, h) {
  // 그림자의 폭
var sw = 1;
  context.lineWidth = sw;
  var s = sw / 2;
  // 왼쪽과 위쪽 그리기
  context.beginPath();
  context.moveTo(x+s, y+h-s);
  context.lineTo(x+s, y+s);
  context.lineTo(x+w-s, y+s);
  context.strokeStyle = "rgba(255, 255, 255, 0.3)";
  context.stroke();
  // 오른쪽과 아래쪽 그리기
  context.beginPath();
  context.moveTo(x+w-s, y+s);
  context.lineTo(x+w-s, y+h-s);
  context.lineTo(x+s, y+h-s);
  context.strokeStyle = "rgba(0, 0, 0, 0.3)";
  context.stroke();
}
