document.addEventListener("DOMContentLoaded", function() { // 2D 컨텍스트
var canvas = document.querySelector("canvas");
var context = canvas.getContext("2d");
// 텍스트를 지정
var text = "Canvas\n로 글자 그리기 "; 
// 폰트를 지정
context.font = "italic bold 24px 'Dotum'"; 
// 텍스트를 파랑색으로 채워서 그리기 
context.fillStyle = "blue"; 
context.fillText(text, 20, 50);
// 텍스트를 녹색 윤곽선으로 그리기 
context.strokeStyle = "green"; 
context.strokeText(text, 20, 100);
}, false);