## Gerarchia di generatori
Ogni generatore deve derivare dalla classe base 'Generator' per poterli comporre im modo semplice

## Griglia e terreni
Dato che i terreni e le superfici fatte solo da cubi
sono spesso disposte in una griglia è stata creata la classe `Grid2DLayout` per gestire gli elementi e posizionarli.

## I dati dell'heightmap sono scomodi da leggere
Creata una classe HeightMap per facilitare la lettura dei dati usando la posizione (i,j) invece di un array monodimensionale.

## L'aggiornamento ad ogni frame delle texture provoca cali di fps
Risolto impostando un intervallo di tempo tra l'update delle texture e rimuovendo la luce SpotLight, sopratutto quando l'animazione viene tagliata si può vedere il delay nell'aggiornare le texture, per riprodurre il fenomeno è sufficente selezionare il `WaveDisplacementGenerator` e cliccare poi velocemente 2 volte sul `FlatDisplacementGenerator` e vedere come le texture non vengano aggiornate subito.

## Il terreno è solo 2D
Il terreno è generato usando una griglia 2D di cubi pertanto c'è un solo cubo per posizione della griglia e non vengono generati i cubi "di supporto", che possono comunque essere generati per un terreno statico prendendo i valori per il displacement di ogni generatore e generando i cubi sottostanti.  

## L'animazione viene tagliata
La transizione tra 2 generatori che agiscono sul terreno viene tagliata se si seleziona un altro generatore prima che venga completata la transizione precedente

## I valori nella HeightMap vanno da 0 a 255
Per facilitare la creazione del displacement il range (0-255) viene mappato nel range (-1,1) e poi rimappato in (0,255) per creare l'immagine dell'heightmap, questo è fatto in modo trasparente dalla classe HeightMap