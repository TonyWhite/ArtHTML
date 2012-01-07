/**
 * La classe ArtHTML genera una tabella in codice HTML con celle di dimensioni 1x1 pixel in modo da riprodurre un'immagine
 *
 * Autore Bianco Antonio
 * Versione 0.0 alpha
 */
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
public class ArtHTML
{
    /**
     * Costruttore della classe ArtHTML
     */
    public static void main(String args[])
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
        else if (args.length==0)
        {
            apriMessaggio();
        }
        else
        {
            System.out.println("Devi passare come parametro il percorso del file da convertire in tabella HTML");
            System.exit(1);
        }
        System.exit(0); // Il programma termina correttamente
    }
    
    private static void apriMessaggio()
    {
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        switch (returnVal)
        {
            case JFileChooser.APPROVE_OPTION:
                String errore = "";
                DisegnaHTML html = new DisegnaHTML();
                String nomeFile = fc.getSelectedFile().getAbsolutePath();
                try
                {
                    errore = "L'immagine " + nomeFile + " non esiste.";
                    html.apri(nomeFile);
                    errore = "Impossibile convertire l'immagine " + nomeFile + ".";
                    html.converti();
                }
                catch(Exception e)
                {
                    // Errore di apertura
                    JOptionPane.showMessageDialog(null, errore, "ArtHTML", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }
                break;
            
            case JFileChooser.CANCEL_OPTION:
                // Apertura annullata
                System.exit(0);
                break;
            
            case JFileChooser.ERROR_OPTION:
                // Errore
                JOptionPane.showMessageDialog(null, "Errore su JFileChooser", "ArtHTML", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Errore interno: condizione non gestita", "ArtHTML", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                break;
        }
    }
}