# Appunti Shell

## Permessi
A gruppi di 3
- [u] owner 000
- [g] group 000
- [o] other 000

'chmod 744 file'

'chmod u=rwx go=r file'

'chmod g+r'
file Aggiunge read(r) al gruppo(g)

## Inode e Link

### Inode
Strutture speciali che contengono i metadati di un file, come
il proprietario o dove si trova il file su disco

### Directory
Le directory associano nomi di file a numeri di Inode.
Ogni entry di una directory è un link che và dal file
all'entry nella tabella degli Inode (Hard Link)

### Creazione di un link

'ln sorgente hard_link' crea un file che punta allo stesso Inode a cui punta la sorgente, in questo modo non si può attraversare in un altro filesystem, poichè ogni filesystem ha il proprio array degli Inode
'ln -s sorgente symbolic_link' crea un link simbolico che al momento dell'uso rimanderà il comando al file originale, in questo modo posso fare riferimento a diversi filesystem (hard disk e chiavetta)

Ogni comando a symbolic_link e hard_link verrà eseguito sul file originale

C'è un limite al numero di link che possono essere messi in catena per evitare di generare cicli nel filesystem e rompere le utility che lo esplorano

## Concatena caratteri
 - | Pipe redireziona l'output del primo nell'input del secondo (Non è detto che il secondo venga
     eseguito dopo il primo, solo che il secondo aspetta in input l'output del primo)
 - ; Esegue i comandi in sequenza
 - && Esegue i comandi in sequenza se il precedente termina con successo
 - (..) Raggruppa i comandi

## Controllo di processo

Ogni processo ha in **PID**, un codice che lo identifica univocamente nella
macchina, il primo processo ha PID 1 ed è il primo processo che parte quando si
avvia il computer

 - ps Per osservare tutti i processi avviati dall'utente corrente nel terminale corrente
 - tty stampa il terminale corrente

### Usi di ps

 - ps -a Tutti i processi associati ad un terminale
 - ps -el Tutti i processi i sistema

### Terminare il processo
 - kill PID Termina il programma
 - kill -9 PID Forza la terminazione di un programma
 - kill -s kill PID Forza il programma a terminare inviandogli un segnale di SIGKILL

kill -l per elencare tutti i segnalis


## Jobs

I job sono comandi che stanno venendo eseguiti a seguito di un comando della shell,
ogni job può dare il via a più processi, una sequenza dà il via a più processi

# Filesystem

## Informazioni sul filesystem
**mount**

**df -h**

stampa i dati dei volumi montati sulla root

# File

## Differenza tra file

**cmp file1 file2**

Non stampa nulla se file1 e file2 sono uguali, altrimenti stampa
il numero di linea

**diff file1 file2**

2c
sinistra ci sono le linee del primo file, a destra quelle del secondo file
2: 2 linea
c: cambia
2: con la seconda linea del 2° file
\< def
\-\-\-
\> fgh

Stampa le modifiche da fare al file1 per renderlo uguale al file2

## find percorsi espressione

trova i file nei percorsi specificati specificando
 - opzione
 - condizione
 - espressione

**find . -name '\*.c' -print -exec rm {} \;**
  - {} Il modo in cui il find rappresenta il match corrente
  - il \; deve essere quotato perchè non vogliamo che la shell
    lo interpreti come delimitatore di una sequenza di comandi
  - ls è differente da print perchè oltre al nome del file ne stampa
    anche gli attributi

Non si vuole che sia la shell a espandere l'\*, quindi si usa
un meccanismo di quoting per servire la stringa a find così com'è
senza che la shell espanda nulla

**find /etc -type d -print**
 - Stampa il nome solo delle directory nella cartella

# Comandi filtro

Comandi per filtrare lo stdin nello stdout dopo aver filtrato lo stream del testo

**uniq**

elimina le righe duplicate nello stdin

**grep**

Cerca nel testo fornitogli dallo stdin e cerca un match con un espressione regolare

**fgrep**

Cerca solo la stringa esatta, senza espressioni regolari

**egrep**

Except grep, prende le linee che non contengono il pattern specificato

### Espressioni regolari

Le espressioni regolari permettono di definire dei caratteri usati
per identificare una classe di caratteri

 - **.***  può identificare anche una stringa vuota
 - **.+**   ci deve essere almeno un carattere nella stringa(+ fallisce)
 - **[ab]***  match con stringhe aaaaa oppure abababbaab, equivalente
   all'espressione [ab][ab][ab]...
 - **[0-4]{3,6}**  stringhe di lunghezza compresa tra 3 e 6 composte da
   numeri da 0 a 4

**sort**

Prende in input delle linee, le ordina e le ritorna ordinate allo stdout
Di default ordina le linee in ordine alfabetico
 - -t : :Imposta i 2 punti(:) come limitatore per dividere i campi
 - -n :Esegue un ordinamento numerico
 - -r :Esegue un ordinamento in ordine decrescente
 - -k s1,s2 :Le chiavi usate nell'ordinamento, da s1 a s2 (dal 2° campo al 5°)


**tr str1 str2**

Prende la prima stringa e converte le occorrenze del 1° carattere nello std
vengono rimpiazzate da occorrenze del 1° carattere della stringa 2
 - -c :Il not dell'espressione
 - -s :comprimi tutti gli spazi in uno solo
 - -d :cancella i caratteri che dovrebbero essere invece sostituiti

**cut -d: -f1**

Copia del testo preso dal 1° campo (**-f1**) dopo il limitatore (**:**)
*cut* come in Taglio verticale dei database, non come taglia e copia, cut non elimina nulla

**paste**

Prende la prima linea del primo file e ci concatena la prima linea del secondo inserendo a metà un
delimitatore
