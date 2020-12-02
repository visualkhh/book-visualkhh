#!/bin/bash

tail="korea hello"
function print() {
  local localVar="localVarValue"
  echo "loval:$localVar input:$1, tail:$tail"
}
print "aaa bbb"

# local 변수 출력안됨
echo "$localVar $tail"
