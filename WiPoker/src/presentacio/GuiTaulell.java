package presentacio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

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
    private JPanel jPanelJugador01;
    private JPanel jPanelJugador02;
    private JPanel jPanelJugador03;
    private JPanel jPanelJugador04;
    private JPanel jPanelJugador05;
    private JPanel jPanelJugador06;
    private JPanel jPanelJugador07;
    private JPanel jPanelJugador08;
    private JPanel jPanelJugador09;
    private JPanel jPanelCrupier;
    private JLabel jlCarta01;
    private JLabel jlCarta02;
    private JLabel jlCarta03;
    private JLabel jlCarta04;
    private JLabel jlCarta05;
//  private Controlador c; // Referència a un objecte Controlador
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
    private JPanel jPanelBotons;
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
    private JButton jbCheck;

    /** Constructor. */
    public GuiTaulell() {
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

        jPanelJugador01 = new JPanel();
        jPanelJugador01.setOpaque(false);
        jPanelJugador01.setBackground(Color.white);
        jPanelJugador01.setLayout(null);
        jPanelJugador01.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x1 = 620;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 200;
        jPanelJugador01.setBounds(x1, y1, w1, h1);
        jPanelJugador01.setLayout(null);
        jPanelGlobal.add(jPanelJugador01);

        jPanelJugador02 = new JPanel();
        jPanelJugador02.setOpaque(false);
        jPanelJugador02.setBackground(Color.white);
        jPanelJugador02.setLayout(null);
        jPanelJugador02.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x2 = 750;
        final int y2 = 50;
        final int w2 = 200;
        final int h2 = 200;
        jPanelJugador02.setBounds(x2, y2, w2, h2);
        jPanelJugador02.setLayout(null);
        jPanelGlobal.add(jPanelJugador02);

        jPanelJugador03 = new JPanel();
        jPanelJugador03.setOpaque(false);
        jPanelJugador03.setBackground(Color.white);
        jPanelJugador03.setLayout(null);
        jPanelJugador03.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x3 = 750;
        final int y3 = 300;
        final int w3 = 200;
        final int h3 = 200;
        jPanelJugador03.setBounds(x3, y3, w3, h3);
        jPanelJugador03.setLayout(null);
        jPanelGlobal.add(jPanelJugador03);

        jPanelJugador04 = new JPanel();
        jPanelJugador04.setOpaque(false);
        jPanelJugador04.setBackground(Color.white);
        jPanelJugador04.setLayout(null);
        jPanelJugador04.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x4 = 620;
        final int y4 = 350;
        final int w4 = w1;
        final int h4 = 250;
        jPanelJugador04.setBounds(x4, y4, w4, h4);
        jPanelJugador04.setLayout(null);
        jPanelGlobal.add(jPanelJugador04);

        jPanelJugador05 = new JPanel();
        jPanelJugador05.setOpaque(false);
        jPanelJugador05.setBackground(Color.white);
        jPanelJugador05.setLayout(null);
        jPanelJugador05.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x5 = 460;
        final int y5 = 350;
        final int w5 = w1;
        final int h5 = 250;
        jPanelJugador05.setBounds(x5, y5, w5, h5);
        jPanelJugador05.setLayout(null);
        jPanelGlobal.add(jPanelJugador05);

        jPanelJugador06 = new JPanel();
        jPanelJugador06.setOpaque(false);
        jPanelJugador06.setBackground(Color.white);
        jPanelJugador06.setLayout(null);
        jPanelJugador06.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x6 = 310;
        final int y6 = 350;
        final int w6 = w1;
        final int h6 = 250;
        jPanelJugador06.setBounds(x6, y6, w6, h6);
        jPanelJugador06.setLayout(null);
        jPanelGlobal.add(jPanelJugador06);

        jPanelJugador07 = new JPanel();
        jPanelJugador07.setOpaque(false);
        jPanelJugador07.setBackground(Color.white);
        jPanelJugador07.setLayout(null);
        jPanelJugador07.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x7 = 60;
        final int y7 = 300;
        final int w7 = 200;
        final int h7 = h1;
        jPanelJugador07.setBounds(x7, y7, w7, h7);
        jPanelJugador07.setLayout(null);
        jPanelGlobal.add(jPanelJugador07);

        jPanelJugador08 = new JPanel();
        jPanelJugador08.setOpaque(false);
        jPanelJugador08.setBackground(Color.white);
        jPanelJugador08.setLayout(null);
        jPanelJugador08.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x8 = 80;
        final int y8 = 50;
        final int w8 = 200;
        final int h8 = h1;
        jPanelJugador08.setBounds(x8, y8, w8, h8);
        jPanelJugador08.setLayout(null);
        jPanelGlobal.add(jPanelJugador08);

        jPanelJugador09 = new JPanel();
        jPanelJugador09.setOpaque(false);
        jPanelJugador09.setBackground(Color.white);
        jPanelJugador09.setLayout(null);
        jPanelJugador09.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x9 = 310;
        final int y9 = 0;
        final int w9 = w1;
        final int h9 = h1;
        jPanelJugador09.setBounds(x9, y9, w9, h9);
        jPanelJugador09.setLayout(null);
        jPanelGlobal.add(jPanelJugador09);

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

        jPanelBotons = new JPanel();
        jPanelBotons.setOpaque(false);
        jPanelBotons.setBackground(Color.white);
        jPanelBotons.setLayout(null);
        jPanelBotons.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        jPanelBotons.setBounds(0, 550, 200, 200);
        jPanelBotons.setLayout(null);
        jPanelGlobal.add(jPanelBotons);

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
        crearControlsJPanelBotons();
    }

    private void crearControlsJPanelCartesTaula() {

        jlCarta01 = new JLabel();
        jlCarta01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/as.gif")));
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01.setBounds(x1, y1, w1, h1);
        jPanelCartesTaula.add(jlCarta01);

        jlCarta02 = new JLabel();
        jlCarta02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/2h.gif")));
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02.setBounds(x2, y2, w2, h2);
        jPanelCartesTaula.add(jlCarta02);

        jlCarta03 = new JLabel();
        jlCarta03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/3c.gif")));
        final int sh23 = sh12;
        final int x3 = x2 + w2 + sh23;
        final int y3 = y1;
        final int w3 = w1;
        final int h3 = h1;
        jlCarta03.setBounds(x3, y3, w3, h3);
        jPanelCartesTaula.add(jlCarta03);

        jlCarta04 = new JLabel();
        jlCarta04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/kc.gif")));
        final int sh34 = sh12;
        final int x4 = x3 + w3 + sh34;
        final int y4 = y1;
        final int w4 = w1;
        final int h4 = h1;
        jlCarta04.setBounds(x4, y4, w4, h4);
        jPanelCartesTaula.add(jlCarta04);

        jlCarta05 = new JLabel();
        jlCarta05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/ac.gif")));
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
        jPanelJugador01.add(jlAvatar01);

        jlFitxes01 = new JLabel();
        jlFitxes01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 1 + ".png")));
        jlFitxes01.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x2 = 0;
        final int y2 = jPanelJugador01.getHeight() - 80;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes01.setBounds(x2, y2, w2, h2);
        jPanelJugador01.add(jlFitxes01);
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
        jPanelJugador02.add(jlAvatar02);

        jlFitxes02 = new JLabel();
        jlFitxes02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes02.setOpaque(false);
        final int x2 = 0;
        final int y2 = jPanelJugador02.getHeight() - 80;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes02.setBounds(x2, y2, w2, h2);
        jPanelJugador02.add(jlFitxes02);
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
        jPanelJugador03.add(jlAvatar03);

        jlFitxes03 = new JLabel();
        jlFitxes03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes03.setOpaque(false);
        final int x2 = 0;
        final int y2 = 0;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes03.setBounds(x2, y2, w2, h2);
        jPanelJugador03.add(jlFitxes03);
    }

    private void crearControlsJPanelFitxes04() {
        jlFitxes04 = new JLabel();
        jlFitxes04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes04.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes04.setBounds(x1, y1, w1, h1);
        jPanelJugador04.add(jlFitxes04);

        jlAvatar04 = new JLabel();
        jlAvatar04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar04.setOpaque(false);
        final int x2 = 0;
        final int y2 = 150;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar04.setBounds(x2, y2, w2, h2);
        jPanelJugador04.add(jlAvatar04);
    }

    private void crearControlsJPanelFitxes05() {
        jlFitxes05 = new JLabel();
        jlFitxes05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes05.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes05.setBounds(x1, y1, w1, h1);
        jPanelJugador05.add(jlFitxes05);

        jlAvatar05 = new JLabel();
        jlAvatar05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar05.setOpaque(false);
        final int x2 = 0;
        final int y2 = 150;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar05.setBounds(x2, y2, w2, h2);
        jPanelJugador05.add(jlAvatar05);
    }

    private void crearControlsJPanelFitxes06() {
        jlFitxes06 = new JLabel();
        jlFitxes06.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes06.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;

        jlFitxes06.setBounds(x1, y1, w1, h1);
        jPanelJugador06.add(jlFitxes06);

        jlAvatar06 = new JLabel();
        jlAvatar06.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar06.setOpaque(false);
        final int x2 = 0;
        final int y2 = 150;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar06.setBounds(x2, y2, w2, h2);
        jPanelJugador06.add(jlAvatar06);
    }

    private void crearControlsJPanelFitxes07() {
        jlFitxes07 = new JLabel();
        jlFitxes07.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes07.setOpaque(false);
        final int x1 = 100;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes07.setBounds(x1, y1, w1, h1);
        jPanelJugador07.add(jlFitxes07);

        jlAvatar07 = new JLabel();
        jlAvatar07.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar07.setOpaque(false);
        final int x2 = 0;
        final int y2 = 100;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar07.setBounds(x2, y2, w2, h2);
        jPanelJugador07.add(jlAvatar07);
    }

    private void crearControlsJPanelFitxes08() {
        jlFitxes08 = new JLabel();
        jlFitxes08.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes08.setOpaque(false);
        final int x1 = 100;
        final int y1 = 120;
        final int w1 = 107;
        final int h1 = 80;
        jlFitxes08.setBounds(x1, y1, w1, h1);
        jPanelJugador08.add(jlFitxes08);

        jlAvatar08 = new JLabel();
        jlAvatar08.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar08.setOpaque(false);
        final int x2 = 0;
        final int y2 = 0;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar08.setBounds(x2, y2, w2, h2);
        jPanelJugador08.add(jlAvatar08);
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
        jPanelJugador09.add(jlAvatar09);

        jlFitxes09 = new JLabel();
        jlFitxes09.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes09.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        final int x2 = 0;
        final int y2 = jPanelJugador09.getHeight() - 80;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes09.setBounds(x2, y2, w2, h2);
        jPanelJugador09.add(jlFitxes09);
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

    private void crearControlsJPanelBotons() {
        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        jbCheck = new JButton();
        jbCheck.setIconTextGap(-180);
        jbCheck.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCheck.setMnemonic('C');
        jbCheck.setText("Check");
        jbCheck.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCheck.setCursor(cursor);
        jbCheck.setToolTipText("Fa chek :)");
        final int x1 = 0;
        final int y1 = 2;
        final int w1 = 200;
        final int h1 = 40;
        jbCheck.setBounds(x1, y1, w1, h1);
        jPanelBotons.add(jbCheck);

        jbCheck = new JButton();
        jbCheck.setIconTextGap(-180);
        jbCheck.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCheck.setMnemonic('R');
        jbCheck.setText("Rise");
        jbCheck.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCheck.setCursor(cursor);
        jbCheck.setToolTipText("Fa rise :)");
        final int x2 = 0;
        final int y2 = 2;
        final int w2 = 200;
        final int h2 = 40;
        jbCheck.setBounds(x2, y2, w2, h2);
        jPanelBotons.add(jbCheck);

        jbCheck = new JButton();
        jbCheck.setIconTextGap(-180);
        jbCheck.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCheck.setMnemonic('C');
        jbCheck.setText("Check");
        jbCheck.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCheck.setCursor(cursor);
        jbCheck.setToolTipText("Fa chek :)");
        final int x3 = 0;
        final int y3 = 2;
        final int w3 = 200;
        final int h3 = 40;
        jbCheck.setBounds(x3, y3, w3, h3);
        jPanelBotons.add(jbCheck);

        jbCheck = new JButton();
        jbCheck.setIconTextGap(-180);
        jbCheck.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCheck.setMnemonic('C');
        jbCheck.setText("Check");
        jbCheck.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCheck.setCursor(cursor);
        jbCheck.setToolTipText("Fa chek :)");
        final int x4 = 0;
        final int y4 = 2;
        final int w4 = 200;
        final int h4 = 40;
        jbCheck.setBounds(x4, y4, w4, h4);
        jPanelBotons.add(jbCheck);
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

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    public JButton getJbCheck() {
        return jbCheck;
    }

    public void setJbCheck(JButton jbCheck) {
        this.jbCheck = jbCheck;
    }

    public JLabel getJlAvatar01() {
        return jlAvatar01;
    }

    public void setJlAvatar01(JLabel jlAvatar01) {
        this.jlAvatar01 = jlAvatar01;
    }

    public JLabel getJlAvatar02() {
        return jlAvatar02;
    }

    public void setJlAvatar02(JLabel jlAvatar02) {
        this.jlAvatar02 = jlAvatar02;
    }

    public JLabel getJlAvatar03() {
        return jlAvatar03;
    }

    public void setJlAvatar03(JLabel jlAvatar03) {
        this.jlAvatar03 = jlAvatar03;
    }

    public JLabel getJlAvatar04() {
        return jlAvatar04;
    }

    public void setJlAvatar04(JLabel jlAvatar04) {
        this.jlAvatar04 = jlAvatar04;
    }

    public JLabel getJlAvatar05() {
        return jlAvatar05;
    }

    public void setJlAvatar05(JLabel jlAvatar05) {
        this.jlAvatar05 = jlAvatar05;
    }

    public JLabel getJlAvatar06() {
        return jlAvatar06;
    }

    public void setJlAvatar06(JLabel jlAvatar06) {
        this.jlAvatar06 = jlAvatar06;
    }

    public JLabel getJlAvatar07() {
        return jlAvatar07;
    }

    public void setJlAvatar07(JLabel jlAvatar07) {
        this.jlAvatar07 = jlAvatar07;
    }

    public JLabel getJlAvatar08() {
        return jlAvatar08;
    }

    public void setJlAvatar08(JLabel jlAvatar08) {
        this.jlAvatar08 = jlAvatar08;
    }

    public JLabel getJlAvatar09() {
        return jlAvatar09;
    }

    public void setJlAvatar09(JLabel jlAvatar09) {
        this.jlAvatar09 = jlAvatar09;
    }

    public JLabel getJlCarta01() {
        return jlCarta01;
    }

    public void setJlCarta01(JLabel jlCarta01) {
        this.jlCarta01 = jlCarta01;
    }

    public JLabel getJlCarta02() {
        return jlCarta02;
    }

    public void setJlCarta02(JLabel jlCarta02) {
        this.jlCarta02 = jlCarta02;
    }

    public JLabel getJlCarta03() {
        return jlCarta03;
    }

    public void setJlCarta03(JLabel jlCarta03) {
        this.jlCarta03 = jlCarta03;
    }

    public JLabel getJlCarta04() {
        return jlCarta04;
    }

    public void setJlCarta04(JLabel jlCarta04) {
        this.jlCarta04 = jlCarta04;
    }

    public JLabel getJlCarta05() {
        return jlCarta05;
    }

    public void setJlCarta05(JLabel jlCarta05) {
        this.jlCarta05 = jlCarta05;
    }

    public JLabel getJlCroupier() {
        return jlCroupier;
    }

    public void setJlCroupier(JLabel jlCroupier) {
        this.jlCroupier = jlCroupier;
    }

    public JLabel getJlFitxes01() {
        return jlFitxes01;
    }

    public void setJlFitxes01(JLabel jlFitxes01) {
        this.jlFitxes01 = jlFitxes01;
    }

    public JLabel getJlFitxes02() {
        return jlFitxes02;
    }

    public void setJlFitxes02(JLabel jlFitxes02) {
        this.jlFitxes02 = jlFitxes02;
    }

    public JLabel getJlFitxes03() {
        return jlFitxes03;
    }

    public void setJlFitxes03(JLabel jlFitxes03) {
        this.jlFitxes03 = jlFitxes03;
    }

    public JLabel getJlFitxes04() {
        return jlFitxes04;
    }

    public void setJlFitxes04(JLabel jlFitxes04) {
        this.jlFitxes04 = jlFitxes04;
    }

    public JLabel getJlFitxes05() {
        return jlFitxes05;
    }

    public void setJlFitxes05(JLabel jlFitxes05) {
        this.jlFitxes05 = jlFitxes05;
    }

    public JLabel getJlFitxes06() {
        return jlFitxes06;
    }

    public void setJlFitxes06(JLabel jlFitxes06) {
        this.jlFitxes06 = jlFitxes06;
    }

    public JLabel getJlFitxes07() {
        return jlFitxes07;
    }

    public void setJlFitxes07(JLabel jlFitxes07) {
        this.jlFitxes07 = jlFitxes07;
    }

    public JLabel getJlFitxes08() {
        return jlFitxes08;
    }

    public void setJlFitxes08(JLabel jlFitxes08) {
        this.jlFitxes08 = jlFitxes08;
    }

    public JLabel getJlFitxes09() {
        return jlFitxes09;
    }

    public void setJlFitxes09(JLabel jlFitxes09) {
        this.jlFitxes09 = jlFitxes09;
    }

    public JLabel getJlNomCroupier() {
        return jlNomCroupier;
    }

    public void setJlNomCroupier(JLabel jlNomCroupier) {
        this.jlNomCroupier = jlNomCroupier;
    }

    /**
     * Inicia el cas d'ús des del SO.
     * @param args No emprats
     */
    public static void main(final String[] args) {
        new GuiTaulell();
    }
}
