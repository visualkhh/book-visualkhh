#!/bin/bash

echo $HOME    # 사용자의 홈 디렉터리
echo $PATH    # 실행 파일을 찾을 디렉터리 경로
echo $FUNCNAME    # 현재 함수 이름
echo $LANG    # 프로그램 사용 시 기본으로 지원되는 언어
echo $PWD   # 사용자의 현재 작업 중인 디렉터리
echo $TERM    # 로그인 터미널 타입
echo $SHELL   # 로그인해서 사용하는 셸
echo $USER    # 사용자 이름
echo $USERNAME    # 사용자 이름
echo $GROUP   # 사용자 그룹 (/etc/passwd 값을 출력)
echo $DISPLAY   # X 디스플레이 이름
echo $COLUMNS   # 현재 터미널이나 윈도우 터미널의 컬럼 수
echo $LINES   # 터미널의 라인 수
echo $PS1   # 기본 프롬프트 변수
echo $PS2   # 보조 프롬프트 변수 (기본값: >), 명령을 \ 를 사용하여 명령 행 연장 시 사용됨
echo $PS3   # 셸 스크립트에서 select 사용 시 프롬프트 변수
echo $PS4   # 셸 스크립트 디버깅 모드의 프롬프트 변수(기본값: +)
echo $BASH    # BASH 실행 파일 경로
echo $BASH_VERSION    # 설치된 BASH 버전
echo $BASH_ENV    # 스크립트 실행 시 BASH 시작 파일을 읽을 위치 변수
echo $HISTFILE    # history 파일 경로
echo $HISTFILESIZE    # history 파일 크기
echo $HISTSIZE    # history 저장되는 개수
echo $HOSTNAME    # 호스트 이름
echo $HOSTTYPE    # 시스템 하드웨어 종류
echo $MACHTYPE    # 머신 종류(HOSTTYPE과 같은 정보지만 조금 더 상세하게 표시됨)
echo $MAIL    # 메일 보관 경로
echo $LOGNAME   # 로그인 이름
echo $TMOUT   # 0이면 제한이 없으며 time 시간 지정 시 지정한 시간 이후 로그아웃
echo $SECONDS   # 스크립트가 실행된 초 단위 시간
echo $UID   # 사용자 UID
echo $OSTYPE    # 운영체제 종류
