#!/bin/sh
# Checks for trailing whitespace

git grep --cached -I -n --no-color -P '[ \t]+$' -- ':/' |
awk '
    BEGIN {
        FS = ":"
        OFS = ":"
        ret = 0
    }
    {
        severity = "ERROR"
        ret = 1
        print severity, $1, $2, " trailing whitespace."
    }
    END {
        exit ret
    }
'
