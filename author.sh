#!/usr/bin/env bash
mkdir -p ./target
grep -r --exclude="author.sh" --exclude="CHANGELOG.*" --exclude="target" "@author" .
if [ $? -gt 0 ]; then
  exit 0
else
  exit -1
fi
