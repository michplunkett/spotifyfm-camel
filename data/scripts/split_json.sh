#!/bin/bash

# This script was used because the LastFM track listing originally came in a JSON
# array, meaning all tracks would be submitted through Camel in one large
# group. Concisely, this script breaks the entire array of 150k+ tracks into
# individual files.

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
