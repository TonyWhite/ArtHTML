/**
 * La classe ArtHTML genera una tabella in codice HTML con celle di dimensioni 1x1 pixel in modo da riprodurre un'immagine
 *
 * Autore Bianco Antonio
 * Versione 0.0 alpha
 */
public class ArtHTML
{
    /**
     * Costruttore della classe ArtHTML
     */
    public static void Main(String args[])
    {
        if (args.length==1)
        {
            String errore = "";
            DisegnaHTML html = new DisegnaHTML();
            try
            {
                errore = "L'immagine " + args[0] + " non esiste.";
                html.apri(args[0]);
                errore = "Impossibile convertire l'immagine " + args[0] + ".";
                html.converti();
            }
            catch(Exception e)
            {
                System.out.println(errore);
                System.exit(1);
            }
        }
        else
        {
            System.out.println("Devi passare come parametro il percorso del file da convertire in tabella HTML");
            System.exit(1);
        }
        System.exit(0); // Il programma termina correttamente
    }
}