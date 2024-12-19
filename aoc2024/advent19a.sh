#!/bin/bash

echo "Starting" > out.txt

declare -A mem

mapfile -t input < input.txt

IFS=', ' read -ra PREFIXES <<< "${input[0]}"

LINES=("${input[@]:2}")

f()
{
    echo "Cache content: ${mem["w"]}" >> out.txt
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
                    ret=1
                    break
                fi
                local new=${1: -${size}}
                local val
                f "$new"
                if (( mem["$new"] == 1 )); then
                    ret=1
                    break
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
    if (( mem["$line"] == 1 )); then
        (( ans++ ))
    fi
done

echo "$ans"
