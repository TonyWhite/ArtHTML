/**
 * La classe mostra la barra di avanzamento per far vedere che il programma non si è ancora bloccato.
 * 
 * Autore: Antonio Bianco
 * Finestra con una progressBar: talmente innovativa, minimalista figa e magica... che quasi quasi ci faccio un brevetto e querelo Samsung.
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
public class Finestra extends JFrame implements WindowListener
{
    // Istanza di variabili
    private JProgressBar progress;
    private JLabel infoStato;
    /**
     * Costruttore dell'oggetto
     */
    public Finestra(String stato)
    {
        // Inizializzazione delle variabili
        super("ArtHTML");
        this.impostaIcona("img/256/ArtHTML.png");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        infoStato = new JLabel(stato, JLabel.CENTER);
        add(infoStato, BorderLayout.CENTER);
        progress = new JProgressBar(JProgressBar.HORIZONTAL);
        add(progress, BorderLayout.SOUTH);
        
        Dimension dimensioni = Toolkit.getDefaultToolkit().getScreenSize();
        pack();
        setSize(400, 100);
        setLocation((int)(dimensioni.getWidth()/2-getWidth()/2), (int)(dimensioni.getHeight()/2-getHeight()/2));
        this.setVisible(true);
        this.addWindowListener(this);
    }
    
    /** Imposta il valore massimo MASSIMO MASSIMO EHHHH!!! */
    public void setMax(int massimo) {progress.setMaximum(massimo);}
    
    /** Imposta il valore corrente */
    public void setValore(int valore) {progress.setValue(valore);}
    
    /** Imposta il valore corrente ed aggiorna lo stato */
    public void setValore(int valore, String stato)
    {
        progress.setValue(valore);
        infoStato.setText(stato);
    }
    
    /**
     * Imposta l'icona per la finestra corrente
     */
    private void impostaIcona(String path)
    {
        URL imgURL = Finestra.class.getResource(path);
        if (imgURL != null)
        {
            setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
        }
        else
        {
            System.err.println("Impossibile trovare " + imgURL.getPath() + "\nFile: " + imgURL.getFile());
        }
    }
    
    /** Visualizza lo stato corrente */
    public void setStato(String stato) {infoStato.setText(stato);}
    
    /** Esce dall'applicazione con delicatezza, cazzo. */
    public void chiudi()
    {
        if (progress.getMaximum()==progress.getValue())
        {
            this.setVisible(false);
            this.dispose();
        }
        else if (JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(this,"Vuoi chiudere l'applicazione?", this.getTitle(), JOptionPane.YES_NO_OPTION))
        {
            this.setVisible(false);
            this.dispose();
        }
    }
    
    /**
     * Ascoltatore della finestra
     */
    public void windowActivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
    public void windowClosing(WindowEvent e) {chiudi();}
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
}
