window.addEventListener("load", function() {
 
 // img요소
  var img = document.getElementById("pic");
  
  // 이미지의 사이즈
  var iw = parseInt(img.width);
  var ih = parseInt(img.height);

  // 히스토그램을 그리기위한 canvas요소
  var canvas1 = document.querySelector("canvas");
  var context1 = canvas1.getContext("2d");
  
  // 이미지 데이터를 저장하기 위한 작업용 canvas요소
  var canvas2 = document.createElement("canvas");
  canvas2.width = iw;
  canvas2.height = ih;
  var context2 = canvas2.getContext("2d");
 
 // 이미지를 작업용 canvas에 그리기
  context2.drawImage(img, 0, 0, iw, ih);
  
  // 픽셀별로 색의 레벨을 계산하여 분포를 산출
  var imagedata = context2.getImageData(0, 0, iw, ih);
  var data = imagedata.data;
  var data_num = data.length;
  
  var histogram = {
    r: new Array(256),
    g: new Array(256),
    b: new Array(256),
    rgb: new Array(256)
  };
  
  for( var i=0; i<256; i++ ) {
    histogram.r[i] = 0;
    histogram.g[i] = 0;
    histogram.b[i] = 0;
  }
 
 for( var i=0; i<data_num; i+=4 ) {
    var r = data[i];
    var g = data[i+1];
    var b = data[i+2];
    histogram.r[r] ++;
    histogram.g[g] ++;
    histogram.b[b] ++;
  }
  
  // R, G, B의 분포의 평균값 구하기
  for( var i=0; i<256; i++ ) {
    var avg = ( histogram.r[i] + histogram.g[i] + histogram.b[i] ) / 3;
    histogram.rgb[i] = Math.round(avg);
  }
 
 // 히스토그램에 그릴 최대 값을 정의
 
 var max = Math.round( iw * ih / 50 );
 
 // 히스토그램 그리기
  var cw = canvas1.width;
  var ch = canvas1.height;
  context1.clearRect(0, 0, cw, ch); // canvas 지우기
  context1.fillStyle = "black"; // 그래프의 색 
  var barw = cw / 256; // 막대 그래프 하나의 두께
  var types = ["rgb", "r", "g", "b"];
  var colors = { r:"red", g:"green", b:"blue", rgb:"black" };
  for( var t=0; t<4; t++ ) {
    var type = types[t];
    context1.beginPath();
    context1.moveTo(0, ch);
   
   	for( var i=0; i<256; i++ ) {
      var h = ( histogram[type][i] / max ) * ch;
      context1.lineTo(barw/2 + barw*i, ch-h);
   	 }
   
    context1.lineTo(cw, ch);
    context1.strokeStyle = colors[type];
    if(type == "rgb") { context1.fill(); }
    context1.stroke();
  }
}, false);
