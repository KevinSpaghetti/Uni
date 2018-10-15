#!/usr/bin/env bash

df ./ | tail -n +2
du -s | tr -s ' ' ' ' | cut -d ' ' -f 1
#Quanto spazio occupa la home directory

df ./ | tail -n +2 | tr -s ' ' ' '  | cut -d ' ' -f 2
#Solo con il numero di blocchi, prima è da comprimere gli spazi che possono essre multipli

#Il contenuto del file viene eliminato

# str1 > str2 Le parti mancanti di str1 vengono convertite nell'ultimo carattere disponibile in str2
# str1 < str2 Le parti mancanti di str1 non vengono convertite

tr -s a-zA-Z0-9 '\t'
#Sostituisce tutti i caratteri alfanumerici in caratteri tab

date | tr -s ' ' ' ' | cut -d ' ' -f 5 | cut -d ',' -f 1 | cut -d '.' -f 2
#Estrae soltanto i minuti dal comando date

sort file | uniq -D
#Scopre se ci sono linee ripetute in un file

ps -el | tail -n +2 |  tr -s ' ' ' ' | cut -d ' ' -f 3 | sort | uniq -u
#visualizza su stdout, senza ripetizioni, gli ID di tutti gli utenti con almeno un processo attivo
#nel sistemo
