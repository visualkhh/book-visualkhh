// 페이지에서 메시지가 왔을 때의 처리
self.onmessage = function(event) {
  // 페이지에서 받은 메시지
  var message = event.data;
  // 처리를 시작
  for( var y=message.y; y<message.y + message.trim_height; y++ ) {
    var pixels = [];
    var p = 0;
    for( var x=0; x<message.w; x++ ) {
      // 픽셀의 변환 처리
      var rgb = convert_pixel(message, x, y);
      // 변환후의 결과 보존
      pixels[p*4 + 0] = rgb.r;
      pixels[p*4 + 1] = rgb.g;
      pixels[p*4 + 2] = rgb.b;
      pixels[p*4 + 3] = 255;
      //
      p ++;
    }
    // 페이지에 보낼 메시지
    var response = {
      y: y,
      trim_height: 1,
pixels: pixels
    };
    // 페이지에 메시지 보내기
    self.postMessage(response);
  }
};
// 픽셀 변환 처리
function convert_pixel(message, x, y) {
  var w = message.w;
  var h = message.h;
  var range = message.range;
  var pixels = message.pixels;
  // rgb 의 레벨을 정의 ( 8단계 )
  var levels = [16, 48, 80, 112, 144, 176, 208, 240];
  // 근접 픽셀의 정보 얻기
  var left = ( x - range < 0 ) ? 0 : x - range;
  var top = ( y - range < 0 ) ? 0 : y - range;
  var right = ( x + range > w ) ? w : x + range;
  var bottom = ( y + range > h ) ? h : y + range;
  var histogram = { r:{}, g:{}, b:{} };
  var num = 0;
  for( var ny=top; ny<=bottom; ny++ ) {
    for( var nx=left; nx<=right; nx++ ) {
      var idx = ( ny * w + nx ) * 4;
      // RGB의 색을 균일화( 8단계 )
      var r = levels[ parseInt( pixels[idx + 0] / 32 ) ];
      var g = levels[ parseInt( pixels[idx + 1] / 32 ) ];
      var b = levels[ parseInt( pixels[idx + 2] / 32 ) ];
      // 히스토그램에 추가
      if( histogram.r[r] === undefined ) { histogram.r[r] = 0; }
      histogram.r[r] ++;
      if( histogram.g[g] === undefined ) { histogram.g[g] = 0; }
      histogram.g[g] ++;
      if( histogram.b[b] === undefined ) { histogram.b[b] = 0; }
      histogram.b[b] ++;
      //
      num ++;
    }
  }
  // 히스토그램에서 가장 빈도가 많은 레벨을 해당 픽셀에 적용
  var rgb = {
    r: get_max(histogram.r),
g: get_max(histogram.g),
    b: get_max(histogram.b)
  };
  // rgb정보 반환
  return rgb;
}
function get_max(hash) {
  var max_value = 0;
  var max_index = 0;
  for( var k in hash ) {
    if( hash[k] > max_value ) {
      max_value = hash[k];
      max_index = k;
    }
  }
  return max_index;
}
