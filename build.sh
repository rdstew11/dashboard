#!/bin/bash
parent_path=$( cd "$S(dirname "${BASH_SOURCE[0]}")" ; pwd -P)

cd "$parent_path"
f_flag=false
b_flag=false

while getopts 'fb' OPTION; do
    case "$OPTION" in
        f) f_flag=true ;;
        b) b_flag=true ;;
    esac
done

echo $f_flag
echo $b_flag

if $f_flag ; then
    echo "frontend"
    # cd "./frontend/app"
    # ng serve -o 
elif $b_flag ; then
    echo "backend"
    # cd "./backend/"
    # mvn spring-boot:run
fi

