#!/usr/bin/env bash
mkdir -p /opt
curl -sL 'https://www.apache.org/dyn/mirrors/mirrors.cgi?action=download&filename=/ant/binaries/apache-ant-1.10.5-bin.tar.gz' | tar -xz  && \
   mv apache-ant* /opt/ant
