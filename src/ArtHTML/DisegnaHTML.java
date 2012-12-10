/**
 * La classe disegna l'immagine tramite una tabella HTML
 * 
 * Autore: Antonio Bianco
 */
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
public class DisegnaHTML
{
    // Istanza di variabili
    private String immagine = "";
    private BufferedImage immagine_sorgente;
    private final String virgolette = ((char)((byte)34))+"";
    private boolean gui = false;
    private Finestra finestra;

    /**
     * Costruttore dell'oggetto
     */
    public DisegnaHTML()
    {
        super();
    }
    
    /**
     * Costruttore dell'oggetto
     */
    public DisegnaHTML(boolean gui)
    {
        super();
        this.gui = gui;
    }
    
    public void apri(String path_immagine) throws Exception
    {
        this.immagine = path_immagine;
        immagine_sorgente = ImageIO.read(new File(path_immagine));
    }
    
    /**
     * Converte l'immagine in una tabella HTML
     */
    public void converti() throws Exception
    {
        // Legge le dimensioni dell'immagine
        String documento = immagine + ".html"; //Documento senza i tag <html> e <body>: pronto per essere cop-incollato in un altro html
        int larghezza=immagine_sorgente.getWidth(null);
        int altezza=immagine_sorgente.getHeight(null);
        int pixel;
        
        // Crea la finestra con la barra di avanzamento
        if (gui)
        {
            finestra = new Finestra("Leggo il file...");
            finestra.setMax(altezza);
        }
        
        // Creazione del file
        FileWriter f = new FileWriter(documento);
        PrintWriter out = new PrintWriter(f);
        
        // Crea la tabella HTML
        out.print("<table cellpadding=0 cellspacing=0>\n");
        
        // Crea la prima riga e imposta le larghezze delle colonne
        if (gui) finestra.setValore(1, "Conversione riga 1 / " + (altezza+1));
        out.print("<tr height=1>");
        for(int x=0; x<larghezza; x++)
        {
            pixel = immagine_sorgente.getRGB(x, 0);
            int alpha = (pixel >> 24) & 0x000000FF;
            int red = (pixel >> 16) & 0x000000FF;
            int green = (pixel >>8 ) & 0x000000FF;
            int blue = (pixel) & 0x000000FF;
            BigDecimal value = new BigDecimal((100*alpha)/255);
            value = value.setScale(0, RoundingMode.HALF_UP);
            double alphaVal = value.doubleValue()/100;
            out.print("<td  width=1 style="+virgolette+"background-color:rgba("+red+","+green+","+blue+","+alphaVal+");"+virgolette+"></td>");
        }
        out.print("</tr>\n");
        
        /*
         * Crea tutte le altre righe
         * Non hanno bisogno di larghezza
         */
        for(int y=1; y<altezza; y++)
        {
            if (gui) finestra.setValore((y+1), "Conversione riga " + (y+1) + " / " + (altezza+1));
            else System.out.println("Riga " + (y+1) + " / " + (altezza+1));
            out.print("<tr height=1>");
            for(int x=0; x<larghezza; x++)
            {
                pixel = immagine_sorgente.getRGB(x, y);
                int alpha = (pixel >> 24) & 0x000000FF;
                int red = (pixel >> 16) & 0x000000FF;
                int green = (pixel >>8 ) & 0x000000FF;
                int blue = (pixel) & 0x000000FF;
                BigDecimal value = new BigDecimal((100*alpha)/255);
                value = value.setScale(0, RoundingMode.HALF_UP);
                double alphaVal = value.doubleValue()/100;
                out.print("<td  width=1 style="+virgolette+"background-color:rgba("+red+","+green+","+blue+","+alphaVal+");"+virgolette+"></td>");
            }
            out.print("</tr>\n");
        }
        out.print("</table>");
        
        // Chiudo il file
        out.close();
        if (gui) finestra.chiudi();
        else System.out.println("La tabella è pronta");
    }
}
