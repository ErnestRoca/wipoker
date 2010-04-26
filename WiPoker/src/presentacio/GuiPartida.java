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
    private JLabel jlCarta03;
    private JLabel jlCarta04;
    private JLabel jlCarta05;
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
    private JPanel jPanelFitxes;

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
            ImageIcon imatgeFons = new ImageIcon(getClass().getResource("/serveis/imatges/taula.png"));
            g.drawImage(imatgeFons.getImage(), 100, 70, 800, 450, null);
            setOpaque(true);
        }
    }

    private void crearJPanels() {

        jPanelGlobal = new jPanelGlobal();
        jPanelGlobal.setBackground(Color.BLACK);
        jPanelGlobal.setLayout(null);
        jFrame.add(jPanelGlobal);

        jPanelCartesTaula = new JPanel();
        jPanelCartesTaula.setOpaque(false);
        jPanelCartesTaula.setBackground(Color.white);
        jPanelCartesTaula.setLayout(null);
        jPanelCartesTaula.setBorder(null);
        final int x1 = 310;
        final int y1 = 250;
        final int w1 = 375;
        final int h1 = 97;
        jPanelCartesTaula.setBounds(x1, y1, w1, h1);
        jPanelCartesTaula.setLayout(null);
        jPanelGlobal.add(jPanelCartesTaula);

        jPanelFitxes = new JPanel();
        jPanelFitxes.setOpaque(false);
        jPanelFitxes.setBackground(Color.white);
        jPanelFitxes.setLayout(null);
        jPanelFitxes.setBorder(null);
        final int x2 = 310;
        final int y2 = 250;
        final int w2 = 375;
        final int h2 = 97;
        jPanelFitxes.setBounds(x1, y1, w1, h1);
        jPanelFitxes.setLayout(null);
        jPanelGlobal.add(jPanelFitxes);

    }

    private void crearControls() {
        crearControlsJPanel01();
        crearControlsJPanel02();
    }

    private void crearControlsJPanel01() {

        jlCarta01 = new JLabel();
        jlCarta01.setBackground(new Color(204, 204, 255));
        jlCarta01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/as.gif")) );
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
        jlCarta02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/2h.gif")));
        jlCarta02.setBorder(null);
        jlCarta02.setOpaque(true);
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02.setBounds(x2, y2, w2, h2);
        jPanelCartesTaula.add(jlCarta02);

        jlCarta03 = new JLabel();
        jlCarta03.setBackground(new Color(204, 204, 255));
        jlCarta03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/3c.gif")));
        jlCarta03.setBorder(null);
        jlCarta03.setOpaque(true);
        final int sh23 = sh12;
        final int x3 = x2 + w2 + sh23;
        final int y3 = y1;
        final int w3 = w1;
        final int h3 = h1;
        jlCarta03.setBounds(x3, y3, w3, h3);
        jPanelCartesTaula.add(jlCarta03);

        jlCarta04 = new JLabel();
        jlCarta04.setBackground(new Color(204, 204, 255));
        jlCarta04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/kc.gif")));
        jlCarta04.setBorder(null);
        jlCarta04.setOpaque(true);
        final int sh34 = sh12;
        final int x4 = x3 + w3 + sh34;
        final int y4 = y1;
        final int w4 = w1;
        final int h4 = h1;
        jlCarta04.setBounds(x4, y4, w4, h4);
        jPanelCartesTaula.add(jlCarta04);

        jlCarta05 = new JLabel();
        jlCarta05.setBackground(new Color(204, 204, 255));
        jlCarta05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/ac.gif")));
        jlCarta05.setBorder(null);
        jlCarta05.setOpaque(true);
        final int sh45 = sh12;
        final int x5 = x4 + w4 + sh45;
        final int y5 = y1;
        final int w5 = w1;
        final int h5 = h1;
        jlCarta05.setBounds(x5, y5, w5, h5);
        jPanelCartesTaula.add(jlCarta05);
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
