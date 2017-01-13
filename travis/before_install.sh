#!/bin/bash
#
# $Id$
#
# Copyright 2016-2017 Quantcast Corporation. All rights reserved.
#
# This file is part of Quantcast File System.
#
# Licensed under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License. You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
# implied. See the License for the specific language governing
# permissions and limitations under the License.

################################################################################
# The following is executed on .travis.yml's before_install section
################################################################################

set -ex

if [[ "$TRAVIS_OS_NAME" == "osx" ]]; then
    brew update || true
    brew install git || true
    brew install cmake || true
    brew install maven || true
    brew install boost || true
fi

# use docker to build on linux; pull the corresponding docker image
if [[ "$TRAVIS_OS_NAME" == "linux" ]]; then
    docker pull $DISTRO:$VER
fi
