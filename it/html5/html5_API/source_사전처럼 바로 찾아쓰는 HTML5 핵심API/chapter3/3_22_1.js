document.addEventListener("DOMContentLoaded", function() {
  // canvas요소
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  var w = canvas.width;
  var h = canvas.height;
  
  // 입자의 수
  var pnum = 1000;
  
  // 입자의 정보를 가지고있는 배열
  var particles = init_particles(pnum, w, h);
  
  // 애니메이션
  window.setInterval( function() {
    draw(context, w, h, particles);
  }, 42);
}, false);

// 모든 입자의 정보를 초기화
function init_particles(n, w, h) {
  var particles = new Array();
  for( var i=0; i<n; i++ ) {
    var state = generate_particle_state(w, h);
    particles.push(state);
  }
return particles;
}

// 입자의 정보를 생성
function generate_particle_state(w, h) {
  var colors = ["#ff0000", "#ffa500", "#ffff00", "#008000", "#0000ff", 
"#4b0082", "#800080"];
  var state = {
    x: (w - 40) * Math.random() + 20, // 입자의 중심의 x좌표
    y: (h - 40) * Math.random() + 20, // 입자의 중심의 y좌표
    r: 5 + parseInt(16 * Math.random()), // 입자의 반경
    c: colors[ parseInt( colors.length * Math.random() ) ], // 색
    s: 3 + parseInt(8 * Math.random()), // 이동 속도 (3~10)
    a: Math.PI * 2 * Math.random() // 입자가 이동하는 각도
  };
  return state;
}

// 전체를 그리기
function draw(context, w, h, particles) {
  context.save();
  
  // 배경을 검은색으로 칠하기
  context.fillStyle = "#000000";
  context.fillRect(0, 0, w, h);
  
  // 입자 그리기
  var pn = particles.length;
  for( var i=0; i<pn; i++ ) {
    draw_particle(context, w, h, particles[i]);
  }
  
  //
  context.restore();
};

// 입자 그리기
function draw_particle(context, w, h, p) {
  context.save();
  
  // 입자의 다음 좌표를 계산
  update_particle(p, w, h);
  
  // 원을 그리기
  context.beginPath();
  context.arc(p.x, p.y, p.r, 0, Math.PI*2, true);
  context.fillStyle = p.c;
  context.fill();
  
  // 그림자 그리기
var offset = p.r * 0.4
  var gx = p.x - offset;
  var gy = p.y - offset;
  var gr = p.r + offset;
  var grad = context.createRadialGradient(gx, gy, 0, gx, gy, gr);
  grad.addColorStop(0,'rgba(255, 255, 255, 0.5)');
  grad.addColorStop(1,'rgba(0, 0, 0, 0.7)');
  context.fillStyle = grad;
  context.fill();
  
  //
  context.restore();
}
// 입자의 다음 좌표를 계산
function update_particle(p, w, h) {
  // 다음 좌표를 계산
  p.x += p.s * Math.cos(p.a);
  p.y += p.s * Math.sin(p.a);
  
  // Canvas영역을 벗어나면 좌표를 리셋
  if( p.x < -p.r || p.x > w + p.r || p.y < -p.r || p.y > h + p.r ) {
    p.x = w * Math.random();
    p.y = h * Math.random();
  }
}
