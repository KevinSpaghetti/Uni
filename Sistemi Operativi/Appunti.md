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
