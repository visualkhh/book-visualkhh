#!/bin/bash
#sh parameter.sh 1 2 3 4 5 6 7 8 9 10 11 "12 13 14"
echo "=========the start"
echo "welcome $0 $1 $2 $3 $4 $5 $5 $6 $7 $8 $9 ${10}"
echo "full asterisk $*"
echo "full at $@"
echo "full cnt $#"

for at in "$*"
do
  echo "for asterisk -> $at"
done

for at in "$@"
do
  echo "for at -> $at"
done


echo "run script PID $$"
echo "최근에 실행된 명령어, 함수 스크립트의 종료상태 $?"
echo "최근에 실행한 백그라운드 (비동기)명령의 PID $!"
echo "현재 옵션 플래그 $~"

language="korea"
echo "say ${language} ho~"
echo "say ${company-"omnicns"} ho~"
echo "say ${company:-"omnicns"} ho~"
echo "say ${company="omnicns"} ho~"
echo "say ${company} ho~"
echo "say ${company:4:1} ho~"
echo "say ${company:(-2):1} ho~"
echo "say ${company#*i} ho~"
unset company
echo "=========the end"
