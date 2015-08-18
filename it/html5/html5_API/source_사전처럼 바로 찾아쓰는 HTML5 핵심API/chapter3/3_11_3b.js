document.addEventListener("DOMContentLoaded", function() {
  // 2D컨텍스트
  var canvas = document.querySelector("canvas");
  var context = canvas.getContext("2d");
  // 텍스트를 지정
  context.font = "24px 'Dotum'";
 
 context.textAlign = "start";
  var text = "start";
  context.fillText(text, 20, 20);
  text = "end";
  context.fillText(text, 20, 50);
  text = "left";
  context.fillText(text, 20, 80);
  text = "right";
  context.fillText(text, 20, 110);
  text = "center";
  context.fillText(text, 20, 140);
  
  text = "مرحبا الويب";
  context.textAlign = "start";
  context.fillText(text, 200, 20);
  context.textAlign = "end";
  context.fillText(text, 200, 50);
  context.textAlign = "left";
  context.fillText(text, 200, 80);
  context.textAlign = "right";
  context.fillText(text, 200, 110);
  context.textAlign = "center";
  context.fillText(text, 200, 140);
  
  
  
  
}, false);
