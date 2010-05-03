package presentacio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import sun.font.Font2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ernest
 */
public class GuiTaulell {

    /** Pseudoatributs per tenir visibilitat d'atribut. */
    private JFrame jFrame;
    private JPanel jPanelGlobal;
    private JPanel jPanelCartesTaula;
    private JPanel jPanelFitxes01;
    private JPanel jPanelFitxes02;
    private JPanel jPanelFitxes03;
    private JPanel jPanelFitxes04;
    private JPanel jPanelFitxes05;
    private JPanel jPanelFitxes06;
    private JPanel jPanelFitxes07;
    private JPanel jPanelFitxes08;
    private JPanel jPanelFitxes09;
    private JPanel jPanelCrupier;
    private JLabel jlCarta01;
    private JLabel jlCarta02;
    private JLabel jlCarta03;
    private JLabel jlCarta04;
    private JLabel jlCarta05;
//    private Controlador c; // Referència a un objecte Controlador
    private JLabel jlFitxes01;
    private JLabel jlFitxes02;
    private JLabel jlFitxes03;
    private JLabel jlFitxes04;
    private JLabel jlFitxes05;
    private JLabel jlFitxes06;
    private JLabel jlFitxes07;
    private JLabel jlFitxes08;
    private JLabel jlFitxes09;
    private JLabel jlCroupier;
    private JLabel jlNomCroupier;
    private JLabel jlAvatar01;
    private JLabel jlAvatar02;
    private JLabel jlAvatar03;
    private JLabel jlAvatar04;
    private JLabel jlAvatar05;
    private JLabel jlAvatar06;
    private JLabel jlAvatar07;
    private JLabel jlAvatar08;
    private JLabel jlAvatar09;

    /** Constructor. */
    private GuiTaulell() {
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
            g.drawImage(imatgeFons.getImage(), 50, 70, 900, 450, null);
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
        final int xCartes = 310;
        final int yCartes = 250;
        final int wCartes = 375;
        final int hCartes = 97;
        jPanelCartesTaula.setBounds(xCartes, yCartes, wCartes, hCartes);
        jPanelCartesTaula.setLayout(null);
        jPanelGlobal.add(jPanelCartesTaula);

        jPanelFitxes01 = new JPanel();
        jPanelFitxes01.setOpaque(false);
        jPanelFitxes01.setBackground(Color.white);
        jPanelFitxes01.setLayout(null);
        jPanelFitxes01.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x1 = 620;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 200;
        jPanelFitxes01.setBounds(x1, y1, w1, h1);
        jPanelFitxes01.setLayout(null);
        jPanelGlobal.add(jPanelFitxes01);

        jPanelFitxes02 = new JPanel();
        jPanelFitxes02.setOpaque(false);
        jPanelFitxes02.setBackground(Color.white);
        jPanelFitxes02.setLayout(null);
        jPanelFitxes02.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x2 = 750;
        final int y2 = 50;
        final int w2 = 200;
        final int h2 = 200;
        jPanelFitxes02.setBounds(x2, y2, w2, h2);
        jPanelFitxes02.setLayout(null);
        jPanelGlobal.add(jPanelFitxes02);

        jPanelFitxes03 = new JPanel();
        jPanelFitxes03.setOpaque(false);
        jPanelFitxes03.setBackground(Color.white);
        jPanelFitxes03.setLayout(null);
        jPanelFitxes03.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x3 = 750;
        final int y3 = 300;
        final int w3 = 200;
        final int h3 = 200;
        jPanelFitxes03.setBounds(x3, y3, w3, h3);
        jPanelFitxes03.setLayout(null);
        jPanelGlobal.add(jPanelFitxes03);

        jPanelFitxes04 = new JPanel();
        jPanelFitxes04.setOpaque(false);
        jPanelFitxes04.setBackground(Color.white);
        jPanelFitxes04.setLayout(null);
        jPanelFitxes04.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x4 = 620;
        final int y4 = 400;
        final int w4 = w1;
        final int h4 = h1;
        jPanelFitxes04.setBounds(x4, y4, w4, h4);
        jPanelFitxes04.setLayout(null);
        jPanelGlobal.add(jPanelFitxes04);

        jPanelFitxes05 = new JPanel();
        jPanelFitxes05.setOpaque(false);
        jPanelFitxes05.setBackground(Color.white);
        jPanelFitxes05.setLayout(null);
        jPanelFitxes05.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x5 = 460;
        final int y5 = 400;
        final int w5 = w1;
        final int h5 = h1;
        jPanelFitxes05.setBounds(x5, y5, w5, h5);
        jPanelFitxes05.setLayout(null);
        jPanelGlobal.add(jPanelFitxes05);

        jPanelFitxes06 = new JPanel();
        jPanelFitxes06.setOpaque(false);
        jPanelFitxes06.setBackground(Color.white);
        jPanelFitxes06.setLayout(null);
        jPanelFitxes06.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x6 = 310;
        final int y6 = 400;
        final int w6 = w1;
        final int h6 = h1;
        jPanelFitxes06.setBounds(x6, y6, w6, h6);
        jPanelFitxes06.setLayout(null);
        jPanelGlobal.add(jPanelFitxes06);

        jPanelFitxes07 = new JPanel();
        jPanelFitxes07.setOpaque(false);
        jPanelFitxes07.setBackground(Color.white);
        jPanelFitxes07.setLayout(null);
        jPanelFitxes07.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x7 = 100;
        final int y7 = 350;
        final int w7 = 200;
        final int h7 = h1;
        jPanelFitxes07.setBounds(x7, y7, w7, h7);
        jPanelFitxes07.setLayout(null);
        jPanelGlobal.add(jPanelFitxes07);

        jPanelFitxes08 = new JPanel();
        jPanelFitxes08.setOpaque(false);
        jPanelFitxes08.setBackground(Color.white);
        jPanelFitxes08.setLayout(null);
        jPanelFitxes08.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x8 = 100;
        final int y8 = 100;
        final int w8 = 200;
        final int h8 = h1;
        jPanelFitxes08.setBounds(x8, y8, w8, h8);
        jPanelFitxes08.setLayout(null);
        jPanelGlobal.add(jPanelFitxes08);

        jPanelFitxes09 = new JPanel();
        jPanelFitxes09.setOpaque(false);
        jPanelFitxes09.setBackground(Color.white);
        jPanelFitxes09.setLayout(null);
        jPanelFitxes09.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x9 = 310;
        final int y9 = 0;
        final int w9 = w1;
        final int h9 = h1;
        jPanelFitxes09.setBounds(x9, y9, w9, h9);
        jPanelFitxes09.setLayout(null);
        jPanelGlobal.add(jPanelFitxes09);

        jPanelCrupier = new JPanel();
        jPanelCrupier.setOpaque(false);
        jPanelCrupier.setBackground(Color.white);
        jPanelCrupier.setLayout(null);
        final int x10 = 450;
        final int y10 = 0;
        final int w10 = w1;
        final int h10 = h1;
        jPanelCrupier.setBounds(x10, y10, w10, h10);
        jPanelCrupier.setLayout(null);
        jPanelGlobal.add(jPanelCrupier);

    }

    private void crearControls() {
        crearControlsJPanelCartesTaula();
        crearControlsJPanelFitxes01();
        crearControlsJPanelFitxes02();
        crearControlsJPanelFitxes03();
        crearControlsJPanelFitxes04();
        crearControlsJPanelFitxes05();
        crearControlsJPanelFitxes06();
        crearControlsJPanelFitxes07();
        crearControlsJPanelFitxes08();
        crearControlsJPanelFitxes09();
        crearControlsJPanelCrupier();
    }

    private void crearControlsJPanelCartesTaula() {

        jlCarta01 = new JLabel();
        jlCarta01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/as.gif")));
        jlCarta01.setOpaque(true);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01.setBounds(x1, y1, w1, h1);
        jPanelCartesTaula.add(jlCarta01);

        jlCarta02 = new JLabel();
        jlCarta02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/2h.gif")));
        jlCarta02.setOpaque(true);
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02.setBounds(x2, y2, w2, h2);
        jPanelCartesTaula.add(jlCarta02);

        jlCarta03 = new JLabel();
        jlCarta03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/3c.gif")));
        jlCarta03.setOpaque(true);
        final int sh23 = sh12;
        final int x3 = x2 + w2 + sh23;
        final int y3 = y1;
        final int w3 = w1;
        final int h3 = h1;
        jlCarta03.setBounds(x3, y3, w3, h3);
        jPanelCartesTaula.add(jlCarta03);

        jlCarta04 = new JLabel();
        jlCarta04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/kc.gif")));
        jlCarta04.setOpaque(true);
        final int sh34 = sh12;
        final int x4 = x3 + w3 + sh34;
        final int y4 = y1;
        final int w4 = w1;
        final int h4 = h1;
        jlCarta04.setBounds(x4, y4, w4, h4);
        jPanelCartesTaula.add(jlCarta04);

        jlCarta05 = new JLabel();
        jlCarta05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/ac.gif")));
        jlCarta05.setOpaque(true);
        final int sh45 = sh12;
        final int x5 = x4 + w4 + sh45;
        final int y5 = y1;
        final int w5 = w1;
        final int h5 = h1;
        jlCarta05.setBounds(x5, y5, w5, h5);
        jPanelCartesTaula.add(jlCarta05);
    }

    private void crearControlsJPanelFitxes01() {
        jlAvatar01 = new JLabel();
        jlAvatar01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar01.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 100;
        final int h1 = 100;
        jlAvatar01.setBounds(x1, y1, w1, h1);
        jPanelFitxes01.add(jlAvatar01);

        jlFitxes01 = new JLabel();
        jlFitxes01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 2 + ".png")));
        jlFitxes01.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x2 = 0;
        final int y2 = jPanelFitxes01.getHeight() - 80;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes01.setBounds(x2, y2, w2, h2);
        jPanelFitxes01.add(jlFitxes01);
    }

    private void crearControlsJPanelFitxes02() {
        jlAvatar02 = new JLabel();
        jlAvatar02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar02.setOpaque(false);
        final int x1 = 100;
        final int y1 = 0;
        final int w1 = 100;
        final int h1 = 100;
        jlAvatar02.setBounds(x1, y1, w1, h1);
        jPanelFitxes02.add(jlAvatar02);

        jlFitxes02 = new JLabel();
        jlFitxes02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes02.setOpaque(false);
        final int x2 = 0;
        final int y2 = jPanelFitxes01.getHeight() - 80;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes02.setBounds(x2, y2, w2, h2);
        jPanelFitxes02.add(jlFitxes02);
    }

    private void crearControlsJPanelFitxes03() {
        jlAvatar03 = new JLabel();
        jlAvatar03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar03.setOpaque(false);
        final int x1 = 100;
        final int y1 = 100;
        final int w1 = 100;
        final int h1 = 100;
        jlAvatar03.setBounds(x1, y1, w1, h1);
        jPanelFitxes03.add(jlAvatar03);

        jlFitxes03 = new JLabel();
        jlFitxes03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 2 + ".png")));
        jlFitxes03.setOpaque(false);
        final int x2 = 0;
        final int y2 = 0;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes03.setBounds(x2, y2, w2, h2);
        jPanelFitxes03.add(jlFitxes03);
    }

    private void crearControlsJPanelFitxes04() {
        jlFitxes04 = new JLabel();
        jlFitxes04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));
        jlFitxes04.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes04.setBounds(x1, y1, w1, h1);
        jPanelFitxes04.add(jlFitxes04);

        jlAvatar04 = new JLabel();
        jlAvatar04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar04.setOpaque(false);
        final int x2 = 0;
        final int y2 = 100;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar04.setBounds(x2, y2, w2, h2);
        jPanelFitxes04.add(jlAvatar04);
    }

    private void crearControlsJPanelFitxes05() {
        jlFitxes05 = new JLabel();
        jlFitxes05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));
        jlFitxes05.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes05.setBounds(x1, y1, w1, h1);
        jPanelFitxes05.add(jlFitxes05);

        jlAvatar05 = new JLabel();
        jlAvatar05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar05.setOpaque(false);
        final int x2 = 0;
        final int y2 = 100;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar05.setBounds(x2, y2, w2, h2);
        jPanelFitxes05.add(jlAvatar05);
    }

    private void crearControlsJPanelFitxes06() {
        jlFitxes06 = new JLabel();
        jlFitxes06.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));
        jlFitxes06.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes06.setBounds(x1, y1, w1, h1);
        jPanelFitxes06.add(jlFitxes06);

        jlAvatar06 = new JLabel();
        jlAvatar06.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar06.setOpaque(false);
        final int x2 = 0;
        final int y2 = 100;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar06.setBounds(x2, y2, w2, h2);
        jPanelFitxes06.add(jlAvatar06);
    }

    private void crearControlsJPanelFitxes07() {
        jlFitxes07 = new JLabel();
        jlFitxes07.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));
        jlFitxes07.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes07.setBounds(x1, y1, w1, h1);
        jPanelFitxes07.add(jlFitxes07);

        jlAvatar07 = new JLabel();
        jlAvatar07.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar07.setOpaque(false);
        final int x2 = 0;
        final int y2 = 100;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar07.setBounds(x2, y2, w2, h2);
        jPanelFitxes07.add(jlAvatar07);
    }

    private void crearControlsJPanelFitxes08() {
        jlFitxes08 = new JLabel();
        jlFitxes08.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));
        jlFitxes08.setOpaque(false);
        final int x1 = 100;
        final int y1 = 100;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes08.setBounds(x1, y1, w1, h1);
        jPanelFitxes08.add(jlFitxes08);

        jlAvatar08 = new JLabel();
        jlAvatar08.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar08.setOpaque(false);
        final int x2 = 0;
        final int y2 = 0;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar08.setBounds(x2, y2, w2, h2);
        jPanelFitxes08.add(jlAvatar08);
    }

    private void crearControlsJPanelFitxes09() {
        jlAvatar09 = new JLabel();
        jlAvatar09.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar09.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 100;
        final int h1 = 100;
        jlAvatar09.setBounds(x1, y1, w1, h1);
        jPanelFitxes09.add(jlAvatar09);

        jlFitxes09 = new JLabel();
        jlFitxes09.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 2 + ".png")));
        jlFitxes09.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x2 = 0;
        final int y2 = jPanelFitxes09.getHeight() - 80;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes09.setBounds(x2, y2, w2, h2);
        jPanelFitxes09.add(jlFitxes09);
    }

    private void crearControlsJPanelCrupier() {
        jlNomCroupier = new JLabel("Crooupiera");
        jlNomCroupier.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNomCroupier.setForeground(Color.WHITE);
        jlNomCroupier.setBounds(20, 100, 100, 10);
        jPanelCrupier.add(jlNomCroupier);

        jlCroupier = new JLabel();
        jlCroupier.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/jugadora_poker.png")));
        jlCroupier.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 100;
        final int h1 = 100;
        jlCroupier.setBounds(x1, y1, w1, h1);
        jPanelCrupier.add(jlCroupier);
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
        new GuiTaulell();
    }
}
