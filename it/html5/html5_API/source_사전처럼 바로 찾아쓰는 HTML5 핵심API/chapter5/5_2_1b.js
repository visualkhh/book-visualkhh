// document오브젝트에 mouseup이벤트 리스너 지정
document.addEventListener("mouseup", function() {
  // Selection오브젝트
  var selection = window.getSelection();
  // 선택된 것이 없다면 종료
  if( selection.isCollapsed == true ) { return; }
  // 선택 영역의 시작위치를 포함하는 텍스트 노드
  var anchor_node = selection.anchorNode;
  // 선택영역의 시작 위치
  var anchor_offset = selection.anchorOffset;
  // 선택 영역의 종료 위치를 포함하는 텍스트 노드
  var focus_node = selection.focusNode;
  // 선택영역의 종료 위치
  var focus_offset = selection.focusOffset;
  // 선택 영역의 결과를 표시
  var msg = anchor_node.nodeValue + "(" + anchor_offset + ")\n";
  msg += focus_node.nodeValue + "(" + focus_offset + ")";
  alert(msg);
}, false);
