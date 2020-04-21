
;;;Esercizio 2
if (test $# -lt 1)
then
	echo 'usage square.sh <number>'
	exit 1
fi

if (test $1 -lt 2) || (test $1 -gt 15)
then
	echo 'number must be between 2 and 15'
	exit 1
fi

selseq=`seq 1 $1 | sort -n -r`
for row in $selseq
do
	for column in $selseq
	do
		char="X"
		if ( test $row -eq 1 || test $row -eq $1 )
		then
			char="-"
		fi
		if ( test $column -eq 1 || test $column -eq $1 ) 
		then
			char="|"
		fi
		if ((test $row -eq 1) || (test $row -eq $1)) && ((test $column -eq 1) || (test $column -eq $1)) 
		then
			char="+"
		fi
		printf "%s" $char
	done
	echo ''
done

exit 0


;;;Esercizio 3
if test $# -lt 1; then
	echo "usage irm <dir>"
	exit 0
fi

files=`ls -R -l | grep '^-' | tr -s ' ' ' ' | cut -d " " -f9`
for file in files; do
	vfilename=`basename $file`
	if echo "$vfilename" | grep -q "*core*"; then
		rm file
	fi
done


