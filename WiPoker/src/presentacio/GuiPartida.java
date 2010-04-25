package presentacio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
    private JPanel jPanel01;
    private JPanel jPanel02;
    private JPanel jPanel03;
    private JPanel jPanel04;
    private JLabel jLabelJPanel01;
    private JLabel jLabelJPanel02;
    private JLabel jLabelJPanel03;
    private JLabel jLabelJPanel04;
    private JLabel jlLimInf;
    private JLabel jlLimSup;
    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;
    private JLabel jl5;
    private JLabel jl6;
    private JFormattedTextField jftfLimInf;
    private JFormattedTextField jftfLimSup;
    private JFormattedTextField jftf1;
    private JFormattedTextField jftf2;
    private JFormattedTextField jftf3;
    private JFormattedTextField jftf4;
    private JFormattedTextField jftf5;
    private JFormattedTextField jftf6;
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
        jFrame.setResizable(true);
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

        jPanel01 = new JPanel();
        jPanel01.setOpaque(false);
        jPanel01.setBackground(Color.white);
        jPanel01.setLayout(null);
        jPanel01.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x1 = 310;
        final int y1 = 210;
        final int w1 = 375;
        final int h1 = 97;
        jPanel01.setBounds(x1, y1, w1, h1);
        jPanel01.setLayout(null);
        jPanelGlobal.add(jPanel01);

    }

    private void crearControls() {
        crearControlsJPanel01();
        crearControlsJPanel02();
    }

    private void crearControlsJPanel01() {

        jLabelJPanel01 = new JLabel();
        jLabelJPanel01.setBackground(new Color(204, 204, 255));
        jLabelJPanel01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/as.gif")));
        jLabelJPanel01.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jLabelJPanel01.setOpaque(true);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jLabelJPanel01.setBounds(x1, y1, w1, h1);
        jPanel01.add(jLabelJPanel01);
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
