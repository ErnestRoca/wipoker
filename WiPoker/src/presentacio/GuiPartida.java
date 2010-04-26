package presentacio;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ernest
 */
public class GuiPartida {

    /** Pseudoatributs per tenir visibilitat d'atribut. */
    private JFrame jFrame;
    private JPanel jPanelGlobal;
    private JPanel jPanelCartesTaula;
    private JPanel jPanel02;
    private JLabel jlCarta01;
    private JLabel jlCarta02;
    private JLabel jLabelJPanel02;
    private JLabel jl6;
    private JFormattedTextField jftfLimInf;
    private JFormattedTextField jftfLimSup;
    private JFormattedTextField jftf1;
    private JTextArea jtaTipusE;
    private JButton jbEsborrar;
    private JButton jbSortir;
//    private Controlador c; // Referència a un objecte Controlador
    private JButton jbExecutar;
    private JCheckBox jckbVeure;

    /** Constructor. */
    private GuiPartida() {
        iniciarComponents(); // Automissatge per crear els components de la UI
        crearEscoltadors();
        crearFormatadors();
        crearVerificadors();
        crearControlador();  // Automissatge per crear l'objecte Controlador

    }

    /** Crea objectes crear els components de la UI. */
    private void iniciarComponents() {
        crearJFrame();
        crearJPanels();
        crearControls();
        iniciarJFrame();
    }

    private void crearJFrame() {
        jFrame = new JFrame();
        final int w = 1024;
        final int h = 786;
        jFrame.setSize(w, h);
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("WiPoker");
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
    }

    //Mètode per introduir una imatge de fons.
    public class jPanelGlobal extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon imatgeFons = new ImageIcon(getClass().getResource("/serveis/imatges/taula.jpg"));
            g.drawImage(imatgeFons.getImage(), -10, -100, 1000, 700, null);
            setOpaque(true);
        }
    }

    private void crearJPanels() {

        jPanelGlobal = new jPanelGlobal();
        jPanelGlobal.setBackground(Color.WHITE);
        jPanelGlobal.setLayout(null);
        jFrame.add(jPanelGlobal);

        jPanelCartesTaula = new JPanel();
        jPanelCartesTaula.setOpaque(false);
        jPanelCartesTaula.setBackground(Color.white);
        jPanelCartesTaula.setLayout(null);
        jPanelCartesTaula.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x1 = 310;
        final int y1 = 210;
        final int w1 = 375;
        final int h1 = 97;
        jPanelCartesTaula.setBounds(x1, y1, w1, h1);
        jPanelCartesTaula.setLayout(null);
        jPanelGlobal.add(jPanelCartesTaula);

    }

    private void crearControls() {
        crearControlsJPanel01();
        crearControlsJPanel02();
    }

    private void crearControlsJPanel01() {

        jlCarta01 = new JLabel();
        jlCarta01.setBackground(new Color(204, 204, 255));
        jlCarta01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/as.gif")));
        jlCarta01.setBorder(null);
        jlCarta01.setOpaque(true);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01.setBounds(x1, y1, w1, h1);
        jPanelCartesTaula.add(jlCarta01);

        jlCarta02 = new JLabel();
        jlCarta02.setBackground(new Color(204, 204, 255));
        jlCarta02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/ac.gif")));
        jlCarta02.setBorder(null);
        jlCarta02.setOpaque(true);
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = 0;
        final int w2 = 73;
        final int h2 = 97;
        jlCarta02.setBounds(x2, y2, w2, h2);
        jPanelCartesTaula.add(jlCarta02);
    }

    private void crearControlsJPanel02() {
    }

    private void crearEscoltadors() {
    }

    private void crearFormatadors() {
    }

    private void crearVerificadors() {
    }

    /** Crea l'objecte controlador del cas d'ús. */
    private void crearControlador() {
        // c = new Controlador(this); // Missatge a la classe Controlador per crear un objecte Controlador
    }

    private void iniciarJFrame() {
        jFrame.setVisible(true);
    }

    /**
     * Inicia el cas d'ús des del SO.
     * @param args No emprats
     */
    public static void main(final String[] args) {
        new GuiPartida();
    }
}
