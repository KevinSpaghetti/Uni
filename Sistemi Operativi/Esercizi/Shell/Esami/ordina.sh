
if test $# -ne 1; then
    echo "Numero sbagliato di parametri";
    exit 1;
fi

if test -d $1 -a -r $1; then
    ls $1 | sort -t. -k2
else
    echo "Non è una directory o non leggibile";
    exit 1;
fi

exit 0;
