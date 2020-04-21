#!/bin/sh

#Rimuove senza chiedere conferma
alias rm='yes | rm'
alias rm='echo Y | rm'

#Rimuove chiedendo conferma
alias rmi='rm -i'

#Mostra il numero di processi (tail -n +2 ignora la prima linea)
ps | tail -n +2 | wc -l

#Salva il contenuto di ls nel file last_ls
!ls | tail -n +2 > last_ls

#Mostra il numero di comandi nella history
history | wc -l

#Mostra i primi 15 elementi della history
history | head -n 15

#Comandi nel sistema che iniziano con lo
lo [TAB] [TAB]

#Lista tutti i file che iniziano con al nella home directory
ls ~/ | grep ^al.
ls -d ~/al*

# 1 ls -R || (echo file non accessibili > tmp)
# 2 (who | grep rossi) && cd ~rossi
# 3 (cd / ; pwd ; ls | wc -l)


# 1 Mostra ricorsivamente il contenuto delle directory, se questa operazione
#   fallisce tampa la stringa "File non accessibili" nel file tmp
# 2 Con il comando who ottiene la lista di utenti attivi e
#   cerca se l'utente 'rossi' è attivo, poi entra nella directory home
#   dell'utente rossi
# 3 Dopo essersi spostato nella root (cd /) stampa il percorso corrente,
#   poi stampa il numero di elementi presenti nella cartella (linee)
