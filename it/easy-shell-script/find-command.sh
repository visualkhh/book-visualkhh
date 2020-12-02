#!/bin/bash
find ./ -name operator.sh

# -L 심볼릭 링크의 원본 파일 속성을 검사하도록 하는것.
# -perm 은 권한을 의미함.
#find -L /etc -perm 644 -name 'rc.*'

# -amin n 현재 시각을 기준으로 N분 전 엑세스된 파일
# -atime n 현재 시각을 기준으로 N*24시간 전 엑세스된 파일
# -cmin n 현재 시각을 기준으로 N분 전 이름변경된 파일
# -ctime n 현재 시각을 기준으로 N*24시간 전 이름변경된 파일
# -mmin n 현재 시각을 기준으로 N분 전 변경된 파일
# -mtime n 현재 시각을 기준으로 N*24시간 전 변경된 파일
# -anewer 명시한 파일보다 최근에 접근한 파일찾아줌
# -cnewer 명시한 파일보다 최근에 변경된 파일찾아줌
# -newer 명시한 파일보다 최근에 수정된 파일찾아줌
# -newerXY 명시한 파일보다 최근에 수정된 파일찾아줌 XY로표시.
# -perm mode 명시된 파일 권한과 동일한 파일을 검색함
# -perm -mode 명시된 파일 권한이 포함된 파일을 검색함
# -perm /mode 명시된 파일 권한을 소유자, 그룹, 기타로 구분하여 세 개의 권한 중 하나라도 동일한 파일 을 검색함
# -perm +mode 더이상 사용되지 않으며, 대신 -perm /mode가 +mode를 대신함
# -readable 로그인한 사용자가 읽을 수 있는 파일을 검색함
# -writable 로그인한 사용자가 쓸 수 있는 파일을 검색함
# -executable 실행 권한이 있는 파일만 검색함

# -gid n 그룹ID가 명시한 그룹ID n과 동일한 파일 검색
# -group gname 그룹명이 명시한 그룹명 gname과 동일한 파일 검색
# -nogroup 존재하지 않은 그룹ID를 가지고 있는 파일 검색
# -nouser 존재하지 않는 사용자ID를 가지고 있는 파일 검색
# -uid n 사용자ID가 명시한 사용자ID n과 동일한 파일 검색
# -user uname 사용자명이 명시한 사용자명 uname과 동일한 파일 검색

# -iname pattern 대소문자 구분없이 패턴과 일치하는 파일 검색
# -inum n 파일의 Inode 번호 n을 갖는 파일 검색
# -lname pattern 패턴과 일치하는 심볼릭 링크 검색
# -name pattern 패턴과 일치하는 파일 검색
# -regex pattern 패턴과 일치하는 경로 검색. Emacs 정규 표현식이 기본값이며, -regextype 옵션을 사 용하여 변경할 수 있음
# -iregex pattern 대소문자 구분없이 패턴과 일치하는 경로 검색
# -samefile name 파일명과 동일한 파일 및 심볼릭 링크 검색. 심볼릭 링크 검색을 위해서는 -L 옵션을 함께

# -ipath pattern 대소문자를 구분하지 않고 패턴과 일치하는 경로를 검색함
# -iwholename pattern -ipath 테스트와 동일하며, -ipath 테스트보다 이식성이 떨어짐
# -links n N개의 링크를 가지고 있는 경로를 검색함
# -ipath pattern 대소문자를 구분하지 않고 패턴과 일치하는 경로를 검색함
# -iwholename pattern -ipath 테스트와 동일하며, -ipath 테스트보다 이식성이 떨어짐
# -links n N개의 링크를 가지고 있는 경로를 검색함

-fstype type BSD 계열의 운영체제에서 지원되며, -type 테스트와 유사한 기능을 제공함
-type c 명시한 파일 타입과 동일한 파일을 검색함. 사용 가능한 파일 타입은 다음과 같음 b: 블록 c: 문자 d: 디렉터리 p: 명명된 파이프 f: 일반 파일 l: 심볼릭 링크 s: 소켓 D: door (Solaris)
-xtype c -type 테스트와 동일하며, 심볼릭 링크를 검색할 경우 -L 옵션과 함께 사용해야 함
-context pattern 패턴과 일치되는 보안 컨텍스트를 가진 파일을 검색함. SELinux가 있는 Fedora 계열 리눅스에서만 사용 가능함 사용해야 함

-true 항상 true를 리턴함
-false 항상 false를 리턴함
-empty 파일 사이즈가 0인 파일을 검색함
-size n[cwbkMG] 명시한 파일 사이즈에 해당하는 파일을 검색. 사이즈 단위는 다음과 같음 ‘b’ 512바이트 블록 ‘c’ 바이트 ‘w’ 2바이트 ‘k’ 킬로바이트 ‘M’ 메가바이트 ‘G’ 기가바이트
-used n 파일이 변경된 후 n일에 마지막으로 액세스한 파일을 검색함

( expr ) 우선순위나 표현식을 그룹핑할 경우 사용되며 \ 백슬래시와 함께 사용되어야 함
! expr 표현식의 반대 결과를 리턴함
-not expr ! expr과 같이 반대 결과를 리턴함
expr1 expr2 AND 연산을 수행하며, expr1이 false이면 expr2는 평가되지 않음
expr1 -a expr2 expr1 expr2와 동일함
expr1 -and expr2 expr1 expr2와 동일하지만 POSIX 호환은 안 됨
expr1 -o expr2 OR 연산을 수행하며, expr1이 true이면 expr2는 평가되지 않음
expr1 -or expr2 expr1 -o expr2와 동일하지만 POSIX 호환은 안 됨
expr1 , expr2 expr1, expr2를 각각 수행되며, 결과는 expr2에 해당하는 것만 출력됨. 함께 출력을 하기 위해서는 -printf나 -fprintf를 함께 사용해야 함
find ./ \( -name 'exp*' \)




-delete 표현식에 의해 검색된 파일을 삭제함
-exec command { } ; 표현식에 의해 검색된 파일을 인수로 받아 -exec 다음의 명령어를 수행함. 인수로 받을 결과값은 중괄호{ }로 표현되며 세미콜론;은 역슬래시\와 함께 사용해야 함
-exec command { } + -exec와 동일하나, 결과값을 연 이어서 보여줌
-execdir command { } ; -exec와 유사하나, 서브 디렉터리부터 검색하기 때문에 결과값은 파일명만출력됨
-execdir command { } + -execdir과 동일하나, 결과값을 연 이어서 보여줌
-ok command { } ; -exec와 유사하지만 사용자에게 실행 여부를 확인 후 실행함
-okdir command { } ; -execdir과 유사하지만 -ok와 같은 방식으로 사용자에게 실행 여부를 확인후 실행함
-prune 검색한 패턴이 디렉터리인 경우, 하위 디렉터리의 파일은 검색하지 않음
-quit -quit 앞에 만난 표현식에 해당하는 파일이 검색되면 검색을 종료함


-delete 표현식에 의해 검색된 파일을 삭제함
-exec command { } ; 표현식에 의해 검색된 파일을 인수로 받아 -exec 다음의 명령어를 수행함. 인수로 받을 결과값은 중괄호{ }로 표현되며 세미콜론;은 역슬래시\와 함께 사용해야 함
-exec command { } + -exec와 동일하나, 결과값을 연 이어서 보여줌
-execdir command { } ; -exec와 유사하나, 서브 디렉터리부터 검색하기 때문에 결과값은 파일명만출력됨
-execdir command { } + -execdir과 동일하나, 결과값을 연 이어서 보여줌
-ok command { } ; -exec와 유사하지만 사용자에게 실행 여부를 확인 후 실행함
-okdir command { } ; -execdir과 유사하지만 -ok와 같은 방식으로 사용자에게 실행 여부를 확인후 실행함
-prune 검색한 패턴이 디렉터리인 경우, 하위 디렉터리의 파일은 검색하지 않음
-quit -quit 앞에 만난 표현식에 해당하는 파일이 검색되면 검색을 종료함

-d FreeBSD, NetBSD, MacOS X 및 OpenBSD와의 호환성을 위한 -depth의 동의어
-depth 서브 디렉터리의 파일을 먼저 검색함
-daystart 24시간이 아닌 해당일을 기준으로 파일 검색. -amin, -atime, -cmin, -ctime,
-mmin 및 -mtime과 함께 사용해야 함
-regextype type -regex나 -iregex의 정규식 구문을 변경함. 기본 유형은 emacs이며, posix-awk,posix-basic, posix-egrep 및 posix-extended가 있음
-maxdepth levels 명시한 level만큼 서브 디렉터리의 파일까지 검색함
-mindepth levels 명시한 level의 서브 디렉터리부터 파일을 검색함
-mount USB나 CD-ROM과 같은 시스템의 파일을 검색하지 않음
-warn, -nowarn 경고 메시지를 켜거나 끔. 경고는 명령줄 사용법에만 적용되며 디렉터리를 검색할 때 발견되는 조건에는 적용되지 않으며, 표준 입력이 tty이면 -warn, 그렇지 않으면 -nown에 해당함
-help, --help find 사용법을 보여줌
-version, --version find 버전을 보여줌

-P 파일을 검사할 때 파일이 심볼릭 링크인 경우, 심볼릭 링크 자체의 속성을 검사하며, find의 기본 옵션임
따라서 옵션을 생략하면 -P 옵션으로 적용됨
-L 파일을 검사할 때 파일이 심볼릭 링크인 경우, 심볼릭 링크에 연결된 파일의 속성을 검사하며, 검사되는 모
든 파일 목록을 보여줌
-H 파일을 검사할 때 파일이 심볼릭 링크인 경우, 심볼릭 링크 자체의 속성을 검사하나, 명령 행에 지정된 파
일이 심볼릭 링크인 경우, 심볼릭 링크에 연결된 파일의 속성을 검사함



-D help 디버깅 옵션을 설명함
-D tree 표현식 트리를 원래의 최적화된 형태로 보여줌
-D search 디렉터리 트리를 자세하게 탐색함
-D stat stat나 lstat와 같은 시스템 호출이 필요한 파일을 검사할 때 메시지를 보여줌
-D rates 표현식이 얼마나 성공했는지를 요약해서 보여줌
-D opt 표현식 tree 최적화와 관련된 진단 정보를 보여줌. 최적화와 관련된 -O 옵션을 참조하여 사용할 수 있음

-O0 최적화 수준 1과 같음
-O1 기본적 최적화 수준으로, 파일 이름(예 : -name 및 -regex)을 기반으로 하는 테스트가 먼저 수행되도
록 식 순서가 바뀜
-O2 -type이나 -xtype과 함께 사용할 때 -name 테스트한 후 -type 테스트를 수행함
-O3 전체 비용 기반 쿼리 최적화 프로그램이 활성화됨. -o의 경우 성공할 수 있는 표현식이 더 빨리 평가되고
-a의 경우 실패할 수 있는 표현식이 더 빨리 평가됨

# find ./ -mmin 44
