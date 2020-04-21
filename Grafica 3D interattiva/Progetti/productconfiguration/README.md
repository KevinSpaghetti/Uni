# Chair configurator
 
# Descrizione

Il progetto è un componente Vue.js per visualizzare un modello e configurarlo, è stato creato in modo da essere più generale possibile 
e permettere il cambio di scelte di materiali in modo rapido senza cambiare alcun codice.

Il componente dispone di un parametro(configuration) che permette, passando un oggetto js, di definire il modello da caricare,
i suoi componenti e per ognuno di questi le scelte di materiali possibili.

è inoltre possibile mostrare le dimensioni dell'oggetto attivando il parametro showObjectDimensions (passando la prop showObjectDimensions=true).

# Risultato

Configuratore  
![Pagina principale configuratore](/showcase/main_page.png)

Indicatori delle dimensioni dell'oggetto  
![Length_dimension](/showcase/length_dimension.png)

Mesh usata per disporre le luci attorno al modello  
![Studio_lights](/showcase/studio_lights.png)


# Processo

## Classi
Il progetto utilizza principalmente le classi:

- **AssetLoader.js**: Prende l'oggetto configuration e scarica gli asset corrispondenti (trasforma ad esempio il semplice nome del modello nell'effettivo oggetto threejs).
- **LengthDimension.js**: Estende Object3D per permettere di piazzare nella scena un indicatore che mostra del testo e la lunghezza in unità di threejs, utilizzato per mostrare le dimensioni dell'oggetto 
- **PBRMaterial.js**: Un wrapper per la classe ShaderMaterial che permette di memorizzare oltre allo shader anche tutte le texture comunemente richieste 
in uno shader PBR, esegue anche il merge con gli uniform di threejs prmettendo di usare le luci built-in con uno shader custom.
- **StudioLights.js**: Estende Object3D per piazzare nella scena un set di luci, prende come parametro una geometria i cui vertici verranno usati per posizionare le luci.

## Planning
Già dall'inizio dello sviluppo il configuratore è stato pensato per essere facilmente usato e riutilizzato in altri progetti,
per questo è stato inserito in un componente Vue.js che permette la veloce configurazione (attraverso le props) e il semplice riuso.

Il componente ProductViewer contiene:
- Una parte che serve per mostrare l'interfaccia, scegliere i vari materiali e modificare colore e intensità delle luci
- il componente Renderer che si occupa del rendering vero e proprio

ProductViewer ha una prop chiamata configuration che serve per impostare il modello da caricare e inoltre permette per ogni mesh appartenente
al modello di definire i materiali che è possibile scegliere.

# Credits 
[Sedia](https://www.cgtrader.com/free-3d-models/furniture/chair/mid-century-modern-dining-chair)  
Importata in maya, orientata nella giusta direzione e scalata in Autodesk Maya.  
[Materiali](https://cc0textures.com/)  
Scaricati in substance player ed esportati nelle diverse texture richieste.
