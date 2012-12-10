/**
 * Write a description of class DisegnaHTML here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.imageio.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
public class DisegnaHTML
{
    // Istanza di variabili
    private String immagine;
    private BufferedImage immagine_sorgente;

    /**
     * Constructor for objects of class DisegnaHTML
     */
    public DisegnaHTML()
    {
        super();
        immagine = "";
    }
    
    public void apri(String path_immagine) throws Exception
    {
        this.immagine = path_immagine;
        immagine_sorgente=ImageIO.read(new File(path_immagine));
    }
    
    /**
     * Converte un numero intero decimale in esadecimale
     */
    private String converti_Hex_in_Dec(int intero)
    {
        String numero = "" + intero;
        int i = Integer.parseInt(numero);
        String hex = Integer.toHexString(i);
        if (hex.length()<2)
        {
            hex = "0" + hex;
        }
        return hex;
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
        Color pixel;
        
        // creazione del file
        FileWriter f = new FileWriter(documento);
        PrintWriter out = new PrintWriter(f);
        
        // Crea la tabella HTML
        out.print("<table cellpadding=0 cellspacing=0>\n");
        
        // Crea la prima riga e imposta le larghezze delle colonne
        out.print("<tr height=1>");
        for(int x=0; x<larghezza; x++)
        {
            pixel = new Color(immagine_sorgente.getRGB(x,0));
            String coloreHTML = "" + converti_Hex_in_Dec(pixel.getRed()) + converti_Hex_in_Dec(pixel.getGreen()) + converti_Hex_in_Dec(pixel.getBlue());
            out.print("<td bgcolor=#");
            out.print(coloreHTML);
            out.print(" width=1></td>");
        }
        out.print("</tr>\n");
        
        /*
         * Crea tutte le altre righe
         * Non hanno bisogno di larghezza
         */
        for(int y=1; y<altezza; y++)
        {
            out.print("<tr height=1>");
            for(int x=0; x<larghezza; x++)
            {
                pixel = new Color(immagine_sorgente.getRGB(x,y));
                //pixel = new Color(immagine_sorgente.getRGB(x,y), true); //Immagini con trasparenze
                String coloreHTML = "" + converti_Hex_in_Dec(pixel.getRed()) + converti_Hex_in_Dec(pixel.getGreen()) + converti_Hex_in_Dec(pixel.getBlue());
                out.print("<td bgcolor=#");
                out.print(coloreHTML);
                out.print("></td>");
            }
            out.print("</tr>\n");
            System.out.println("Riga " + y);
        }
        out.print("</table>");
        
        // Chiudo il file
        out.close();
        System.out.println("La tabella è pronta");
    }
}
