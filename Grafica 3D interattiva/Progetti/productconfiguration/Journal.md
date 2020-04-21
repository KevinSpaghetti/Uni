# Shading con la envmap
La sedia ha una componente diffusiva e speculare, per cui è necessario creare una 
BRDF che usi la environment map per calcolare l'illuminazione e le riflessioni nel caso del
componente speculare e solo l'illuminazione nel caso diffusivo.

# Le luci built-in di threejs
Quando si esegue il merge tra uniform builtin e uniform custom le texture non vengono
copiate correttamente, quindi è necessario prima fare il merge poi impostare il valore
delle texture (Si veda la classe PBRMaterial.js)

# Non si possono usare spazi 
Nei nomi dei materiali dato che devono essere caricati da remoto secondo il loro nome
per cui non possono essere usati spazi

# Lo shader BRDF 
Lo shader BRDF deve essere adattato alle texture esportate con Substance Player che
hanno un formato diverso da quelle usate di solito.

# Ombre
è possibile abilitare anche le ombre, tuttavia queste sono troppo pesanti per dispositivi
mobile

#Responsive
Su mobile l'interfaccia per scegliere i materiali è stata spostata sotto, ha problemi
con lo scrolling dato che il canvas di threejs inibisce l'evento scroll.