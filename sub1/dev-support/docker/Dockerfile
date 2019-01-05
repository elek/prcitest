# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
FROM alpine
RUN apk add --update --no-cache bash alpine-sdk maven grep openjdk8 py-pip rsync procps autoconf automake libtool

#Install real glibc
RUN apk --no-cache add ca-certificates wget && \
    wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub && \
    wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.28-r0/glibc-2.28-r0.apk && \
    apk add glibc-2.28-r0.apk

#Install protobuf
RUN mkdir -p /usr/local/src/ && \
    cd /usr/local/src/ && \
    wget https://github.com/google/protobuf/releases/download/v2.5.0/protobuf-2.5.0.tar.gz && \
    tar xvf protobuf-2.5.0.tar.gz && \
    cd protobuf-2.5.0 && \
    ./autogen.sh && \
    ./configure --prefix=/usr && \
    make && \
    make install && \
    protoc --version

#Findbug install
RUN mkdir -p /opt && \
    curl -sL https://sourceforge.net/projects/findbugs/files/findbugs/3.0.1/findbugs-3.0.1.tar.gz/download | tar -xz  && \
     mv findbugs-* /opt/findbugs

#Install apache-ant
RUN mkdir -p /opt && \
    curl -sL 'https://www.apache.org/dyn/mirrors/mirrors.cgi?action=download&filename=/ant/binaries/apache-ant-1.10.5-bin.tar.gz' | tar -xz  && \
       mv apache-ant* /opt/ant

#Install docker-compose
RUN pip install docker-compose

ENV PATH=$PATH:/opt/findbugs/bin
