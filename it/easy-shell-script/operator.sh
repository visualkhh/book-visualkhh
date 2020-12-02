#!/bin/bash

str1="hello_welcom_hello_visualkhh"
str2="hello_welcom_hello_visualkhh"
str3=5
str4=5
str5=true
str6=false

echo "string check"
if [ -z $str1 ]; then
  echo 'true'
else
  echo 'false'
fi

if [ -n $str1 ]; then
  echo 'true'
else
  echo 'false'
fi
# =, ==
if [ $str1 == $str2 ]; then
  echo 'true'
else
  echo 'false'
fi
if [ $str1 != $str2 ]; then
  echo 'true'
else
  echo 'false'
fi
#ascii코드값 비교 > < >= <=
if [[ $str1 > $str2 ]]; then
  echo 'true'
else
  echo 'false'
fi


echo "logic check"
#and -a &&, or -o ||
if [ $str3 -a $str4 ]; then
  echo 'true'
else
  echo 'false'
fi

if [ $str5 ] && [ $str6 ]; then
  echo 'true'
else
  echo 'false'
fi
if [[ $str5 && $str6 ]]; then
  echo 'true'
else
  echo 'false'
fi


echo "number check"
if [ $str3 -lt $str4 ]; then
  echo 'true'
else
  echo 'false'
fi

if [ $str3 -gt $str4 ]; then
  echo 'true'
else
  echo 'false'
fi

if [ $str3 -ge $str4 ]; then
  echo 'true'
else
  echo 'false'
fi

if [ $str3 -le $str4 ]; then
  echo 'true'
else
  echo 'false'
fi
if (( $str3 > $str4 )); then
  echo 'true'
else
  echo 'false'
fi
if (( $str3 < $str4 )); then
  echo 'true'
else
  echo 'false'
fi

if [ $str3 -eq $str4 ]; then
  echo 'true'
else
  echo 'false'
fi

if [ $str3 -ne $str4 ]; then
  echo 'true'
else
  echo 'false'
fi

echo "directory"
#-e 디랙토리안에 파일있으면은, -f 파일이면, -L 파일이고 심볼릭 링크면, -r 파일 폴더이고 읽기권한있음, -w 파일 폴더 이고 쓰기권한있음, -x 파일 폴더 이고 실행권한있으면, -s 파일 폴더이고 사이즈0이면, -O 파일 폴더이고 소유자이면 -G 파일 폴더이며 실행 그룹과 동일하면
if [ -d $HOME ]; then
  echo 'true'
else
  echo 'false'
fi
#디렉토리앙네 파일이 있으면
if [ -e $HOME/ttt ]; then
  echo 'true'
else
  echo 'false'
fi


echo "file check"
#-nt 파일이면서  변수1보다 변수2가 최신이면, -ot 파일이면서 변수1이 변수2보다 이전파일이면, 파일이면서 변수1과 변수2가 동일 파일면

#if [ $str1 -gt $str2 ]; then
#  echo 'true'
#else
#  echo 'false'
#fi

