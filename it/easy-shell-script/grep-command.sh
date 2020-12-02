#!/bin/bash
#-i 대소문자 구분하지 않음 (-y도동일), -e 하나이상일때, -f 패턴파일을 참조해서 할수있다.,
#grep -i 'D' expression.txt
#grep -i -e 'D' -e '010' expression.txt
#grep -i -f ./resource/pattern1.txt ./resource/expression.txt

# -E 확장정규식 egrep , -F 여러줄 사용 fgrep, -G 기본옵션, -P 펄에서 사용되는 ..
#echo '===normal'
#grep 'q[[:lower:]]*\??' ./resource/expression.txt
#echo '===extanstion'
#grep -E 'q[[:lower:]]*\??' ./resource/expression.txt
#echo '===multi line'
#grep -F '# Date
# Author' ./resource/expression.txt
#echo '===matching'
#grep -e 'Author' --regex 'q[[:lower:]]*\?' ./resource/expression.txt
#grep 'Author' ./resource/expression.txt

# -v 해당부분을 제외한 모든 부분을 보여준다.
#grep -v 'Date' ./resource/expression.txt
#grep -v '^#' ./resource/expression.txt

# -w 정확하게 해당되는 라인만(해당줄 출력)
#grep 'Autho' ./resource/expression.txt
#grep -w 'Autho' ./resource/expression.txt

# -x 라인전체가 정확해얒 보여줌
#grep -x '# Help s' ./resource/expression.txt
#grep -x '# Help' ./resource/expression.txt

# -c 출력된 개수
#grep -c 'D' ./resource/expression.txt

#--color, GREP_COLOR="1;32" 컬러변경
#grep --color 'D' ./resource/expression.txt

# -L 제외한 파일들만 리스팅, -l 있는 파일들만 리스팅
#grep -L 'D' ./resource/*
#grep -l 'D' ./resource/*

#-m 몇라인까지만 출력
#grep -m 2 'D' ./resource/expression.txt

#-o 패턴과 일치된 단어만
#grep -o 'express[[:lower:]]*' ./resource/expression.txt

#-q 결과안보여줌
#grep -q 'D' ./resource/expression.txt

# -s 시스템 메시지를 출력하지 않습니다.  (없는 디렉토리경우 grep: ./r11esource/expression.txt: No such file or directory 메시지나옴)
#grep -s 'D' ./r11esource/expression.txt

# -b 바이트수를 앞에 표시함
#grep -b 'express' ./resource/expression.txt

# -H 파일이름을 앞에 표시함, -h 파일명 표시안함.
#grep -H 'express' ./resource/expression.txt

# -n 라이눗를 앞에 표시함
#grep -n 'express' ./resource/expression.txt

#--label=LABEL
#ls -l | grep --label=file -H sh
#ls -l | grep --label=file -H sh

# -T 탭으로 앞부분 구분을 해라.
#grep --initial-tab -n 'question' ./resource/expression.txt
#grep -T -n 'question' ./resource/expression.txt

# -u -b 유닉스 byte
#grep -u -b 'question' ./resource/expression.txt
#grep -b 'question' ./resource/expression.txt

# -Z  목록을 한줄로 처리
#grep -Z -l '010' ./resource/*

# -A 검색된 라인의 다음 라인수, -B 검색된 라인의 이전 라인수
#grep -A 2 '010' ./resource/expression.txt

# --group-separator='====' 그룹 구분 기호
# --no-group-separator 반대로 그룹 구분 기호를 표시하지 않습니다.
# -a 바이너리 파일 내의 해당 패턴이 있는지 검색합니다.
# --binary-file=binary, --binary-file=text
# -D read, -D skip  오류발생시 스킵  예를들어 권한문제발생시
