#!/usr/bin/env bash
mkdir -p /opt && \
curl -sL https://sourceforge.net/projects/findbugs/files/findbugs/3.0.1/findbugs-3.0.1.tar.gz/download | tar -xz  && \
   mv findbugs-* /opt/findbugs
