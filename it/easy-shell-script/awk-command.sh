#!/bin/bash
# $NF number of Field 필드의 갸ㅐ수 즉 마지막 필드의 문자열을 출력하겠다.
#ls -al | awk '$2 == 1 { print $NF }'
# -f 명령어를 가지고  불러서 사용한다.


#print 대상 파일 내용을 그대로 출력함
#print 필드리스트 대상 파일의 필드 인덱스($0 ~ $n), 자체 변수(NF, FNR 등), 문자열 등을조합하여 명시한 대로 출력함
#print 필드리스트 > 파일 대상 파일의 필드 인덱스($0 ~ $n), 자체 변수(NF, FNR 등), 문자열 등을조합하여 명시한 대로 파일에 출력함
#printf 포맷, 필드리스트 명시한 대상 파일의 필드 인덱스를 명시한 포맷에 맞추어 출력함
#printf 포맷, 필드리스트 > 파일 명시한 대상 파일의 필드 인덱스를 명시한 포맷에 맞추어 파일에 출력함
#getline 대상 파일의 짝수 라인과 마지막 라인을 읽어들임. 단독으로 사용할 수 없으며,
#print 등과 함께 사용해야 함
#getline var 대상 파일의 홀수 라인을 읽어들여 단독으로 사용할 수 없으며, print 등과 함께 사용해야 함
#getline < 파일 명시한 파일의 값을 읽어들임. 단독으로 사용할 수 없으며, print 등과 함께 사용해야 함
#getline var < 파일 명시한 파일의 값을 읽어 var에 저장함. print 등과 함께 사용해야 하며 파일에명시된 숫자에 해당하는 필드를 출력함

# FNR 파일의 레코드 번호를 의미지하는다
#ls -al | awk '{ print FNR $1, $6 }'
#ls -al | awk '{ print FNR $0 }'


# > 액션
#awk '{print $1, $8}'>awk-result.txt awk-sample1.txt

#포맷
ls -al | awk '{printf "%-10s %s\n", $8, $6}'
#awk '{printf "%-10s %s\n", $8, $6}' > awk-res.txt awk-sample1.txt

# 패턴 BEGIN, END BEGINFILE, ENDFILE...

# 관계식 $2 == 2 { print $NF }


#표준옵션
-f 파일 / --file 파일 Awk 프로그램(패턴 {액션})을 파일에 저장하고, 해당 파일을 이용하여

필요한 필드 및 레코드를 추출함

-F 구분 기호
--field-separator 구분 기호

필드구분 기호를 변경할 수 있음. Awk의 기본 필드구분 기호는 스페이
스이지만, -F 옵션을 통해 필드구분 기호를 변경할 수 있음

-v 변수=값
--assign 변수=값

필드 및 레코드를 출력할 때 -v 옵션을 통해 변수의 값을 함께 출력할 수
있음

#확장옵션
-b / --characters-as-bytes 입력되는 문자열을 바이트로 처리하며, 문자열 길이를 구하는 length( ) 같

은 함수의 결과값에 영향을 줌
-C / --copyright GNU 라이센스 정보를 보여줌
-d파일명
--dump-variables=파일명

Awk 내장 변수와 값을 명시한 파일에 저장하여 확인할 수 있음

-h / --help Awk에 대한 사용법 및 옵션을 보여줌
-L ‘fatal’ / --lint=‘fatal’ 구문 오류에 대한 에러 메시지를 자세하게 보여줌
-p파일명
--profile=파일명 Awk 프로그램(패턴 {액션})을 awkprog.out 또는 명시한 파일에 파싱하

여 저장함

-S / --sandbox system( ) 함수, getline, 프린트 함수를 이용한 redirection, 확장 모듈

사용을 할 수 없음
-V / --version Awk에 대한 버전 정보를 보여줌
