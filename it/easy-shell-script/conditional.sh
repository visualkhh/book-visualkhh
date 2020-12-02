#!/bin/bash
#if
val1=''
val2=2
if [ $val1 = $val2 ]
then
  echo '--'
else
  echo 'no'
fi
if [ -z $val1 ]
then
  echo 'length 0'
else
  echo 'no length 0'
fi


if [ $val2 -gt 0 ] && [ $val2 -lt 10 ]
then
  echo 'good'
else
  echo 'no'
fi

case $val2 in
  1)
    echo '1'
  ;;
  2)
    echo '2'
  ;;
  *)
    echo 'other'
  ;;
esac
#switch-case
