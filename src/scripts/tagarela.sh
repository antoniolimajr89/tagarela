#!/bin/bash

export GOOGLE_APPLICATION_CREDENTIALS="$(pwd)/CHAVE.json"

java -cp "libs/*" br.com.caelum.Main "$@"