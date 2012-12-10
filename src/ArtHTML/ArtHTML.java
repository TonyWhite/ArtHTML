/**
 * La classe ArtHTML converte in'immagine in una tabella HTML
 *
 * Autore Bianco Antonio
 * Versione 1.0
 */
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import eu.kostia.gtkjfilechooser.ui.ImagePreviewer;
public class ArtHTML
{
    /**
     * Costruttore della classe ArtHTML
     */
    public static void main(String args[])
    {
        if (args.length==0) // Se non gli passi alcun parametro carica la GUI
        {
            setTema();
            apriMessaggio();
        }
        else if (args.length==1) // Se gli passi come parametro il percorso del file da convertire fa tutto senza GUI
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
        else    // Se gli hai passato più di 1 parametro
        {
            System.out.println("Devi passare solo 1 parametro: il percorso del file da convertire.");
        }
        System.exit(0); // Il programma termina correttamente
    }
    
    private static void apriMessaggio()
    {
        JFileChooser fc = new JFileChooser();
        
        // Attiva l'anteprima delle immagini
        ImagePreviewer anteprima = new ImagePreviewer(fc);
        JComponent accessory = fc.getAccessory();
        fc.setAccessory(anteprima);
        if (accessory != null) ((ImagePreviewer)accessory).loadImage(null);
        
        // Visualizza il JFileChooser
        int returnVal = fc.showOpenDialog(null);
        switch (returnVal)
        {
            case JFileChooser.APPROVE_OPTION:
                String errore = "";
                DisegnaHTML html = new DisegnaHTML(true);
                String nomeFile = fc.getSelectedFile().getAbsolutePath();
                try
                {
                    errore = "L'immagine " + nomeFile + " non esiste.";
                    html.apri(nomeFile);
                    errore = "Impossibile convertire l'immagine " + nomeFile + ".";
                    html.converti();
                    JOptionPane.showMessageDialog(null, "Il file è stato creato con successo", "ArtHTML", JOptionPane.INFORMATION_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Apertura annullata", "ArtHTML", JOptionPane.INFORMATION_MESSAGE);System.exit(0);
                break;
            
            case JFileChooser.ERROR_OPTION:
                // Errore
                JOptionPane.showMessageDialog(null, "Errore su JFileChooser", "ArtHTML", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                break;
            default:
                // Errore sconosciuto
                JOptionPane.showMessageDialog(null, "Errore interno: condizione non gestita.\nJFileChooser.showOpenDialog() ha ritornato " + returnVal + ".", "ArtHTML", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
                break;
        }
    }
    
    /**
     * Carica il tema predefinito.
     */
    private static void setTema()
    {
        try
        {
            String classeTemaPredefinito = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(classeTemaPredefinito);
            // Correggi il FileChooser se il tema è GTK+
            if ("GTK look and feel".equals(UIManager.getLookAndFeel().getName())) UIManager.put("FileChooserUI", "eu.kostia.gtkjfilechooser.ui.GtkFileChooserUI");
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}