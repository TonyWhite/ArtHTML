# ![ArtHTML icon](https://raw.githubusercontent.com/TonyWhite/ArtHTML/master/src/ArtHTML/img/64/ArtHTML.png) ArtHTML


### DOWNLOAD ULTIMA VERSIONE
ArtHTML (v1.0)

### REQUISITI
Java 6

### COSA FA
Questa è una inutility.<br>
Converte immagini statiche in tabelle HTML.<br>
È possibile usarlo sia con l’interfaccia grafica, che da terminale.<br>
Avviandolo normalmente appare la finestra di scelta del file, e dopo averlo selezionato parte la conversione.

Avviandolo da terminale con:
`java -jar ArtHTML.jar ~/Immagini/Icone/Java.png`

Verrà creato il file
`~/Immagini/Icone/Java.png.html`

### CARATTERISTICHE E LIMITAZIONI
- Può convertire immagini bmp, jpg, png, gif, tiff;
- Può convertire immagini con trasparenza;
- Non può convertire gif animate;
- È sconsigliabile convertire immagini superiori a 512×512 pixel;
- La grafica è integrata col sistema. Un ringraziamento speciale a gli sviluppatori di gtkjfilechooser per aver migliorato l’integrazione del JFileChooser con il Look&Feel GTK+.

### ATTENZIONE
Convertendo immagini di grandi dimensioni si creeranno tabelle abbastanza pesanti da bloccare il browser.

### INFO
- Data la pesantezza delle tabelle risultanti, questo progetto è rivolto principalmente ad un uso didattico.

### NOVITÀ E BUGFIX
**1 settembre 2012**
- È possibile convertire anche le immagini con trasparenze;
- Carica automaticamente il tema grafico predefinito di sistema;
- Aggiunta di una finestra con barra di avanzamento e visualizzazione dello stato;
- Uso del package gtkjfilechooser per correggere il bug grafico delle librerie ufficiali di Java;
- Anteprima delle immagini compatibili durante la selezione del file;

**Periodo buio, lungo e nero**
- Aggiunta di un JFileChooser;

**23 settembre 2008**
- Implementata la modalità CLI;

**22 settembre 2008**
- Prime bozze alpha;
- È possibile convertire immagini statiche senza trasparenze.
