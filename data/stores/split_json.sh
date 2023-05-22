#!/bin/bash

# Split a JSON into multiple files. Uses jq.

# Usage
# ./split_json.sh /path/to/json/file

file="$1"

jq -cr 'keys[] as $k | "\($k)\t\(.[$k])"' "$file"  | awk -F\\t '{ file=$1".json"; print $2 > file; close(file); }'

del=1
for fspec in *.* ; do
    if [[ ${del} -eq 1 ]] ; then
        del=0
        echo rm ${fspec}
    else
        echo ok ${fspec}
        del=1
    fi
done
