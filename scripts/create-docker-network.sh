#!/bin/bash


red=$(tput setaf 1)
green=$(tput setaf 2)
blue=$(tput setaf 4)
reset=$(tput sgr0)

docker network create alex-cour-net || { echo "${red}Error${reset} creating 'alex-cour-net' network"; return 1; }
