#!/bin/bash

echo "Starting" > out.txt

declare -A mem

mapfile -t input < input.txt

IFS=', ' read -ra PREFIXES <<< "${input[0]}"

LINES=("${input[@]:2}")

f()
{
    if [[ ${mem["$1"]+_} ]]; then
        echo "Cache hit for $1" >> out.txt
        return
    fi

    echo "Computing $1" >> out.txt
    local ret=0

    for x in "${PREFIXES[@]}"; do
        if [ "${#x}" -le "${#1}" ]; then
            local common=${1::${#x}}
            if [ "$common" == "$x" ]; then
                local size=$((${#1}-${#x}))
                if (( size == 0 )); then
                    ret=$(( ret + 1 ))
                else
                    local new=${1: -${size}}
                    f "$new"
                    ret=$(( ret + mem["$new"] ))
                fi
            fi
        fi
    done
    echo "Setting $1" >> out.txt
    mem["$1"]="$ret"
}

ans=0

for line in "${LINES[@]}"; do
    f "$line"
    ans=$(( ans+mem["$line"] ))
done

echo "$ans"
