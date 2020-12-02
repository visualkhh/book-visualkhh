#!/bin/bash

numbers="5 6 7 8 9"
#for num in 1 2 3 4
for num in $numbers
do
  echo $num
done

for file in $HOME/*
do
  echo $file
done
echo  $HOME/*

for it in {5..16}
do
  echo $it
done

arrays=('a' 'b' 'c')
for it in ${arrays[@]}
do
  echo $it
done

for (( i = 0; i < 10; i++ ))
do
  echo $i
done

echo "while======"
num=0
while [ $num -lt 3 ]; do
  echo $num
  num=$((num+1));
done
