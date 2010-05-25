package presentacio;

import controladora.ControladoraGui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author ernest
 */
public class GuiTaulell {

    private JFrame jFrame;
    private JPanel jPanelGlobal;
    private JPanel jPanelBotons;
    private JPanel jPanelUsuari;
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
    //
    private JLabel jlCarta01Usuari;
    private JLabel jlCarta02Usuari;
    //
    private JLabel jlCarta01;
    private JLabel jlCarta02;
    private JLabel jlCarta03;
    private JLabel jlCarta04;
    private JLabel jlCarta05;
    //
    private JLabel jlNomCroupier;
    private JLabel jlNom01;
    private JLabel jlNom02;
    private JLabel jlNom03;
    private JLabel jlNom04;
    private JLabel jlNom05;
    private JLabel jlNom06;
    private JLabel jlNom07;
    private JLabel jlNom08;
    private JLabel jlNom09;
    //
    private JLabel jlCroupier;
    private JLabel jlAvatar01;
    private JLabel jlAvatar02;
    private JLabel jlAvatar03;
    private JLabel jlAvatar04;
    private JLabel jlAvatar05;
    private JLabel jlAvatar06;
    private JLabel jlAvatar07;
    private JLabel jlAvatar08;
    private JLabel jlAvatar09;
    //
    private JLabel jlFitxes01;
    private JLabel jlFitxes02;
    private JLabel jlFitxes03;
    private JLabel jlFitxes04;
    private JLabel jlFitxes05;
    private JLabel jlFitxes06;
    private JLabel jlFitxes07;
    private JLabel jlFitxes08;
    private JLabel jlFitxes09;
    //
    private JLabel jlNumFitxes01;
    private JLabel jlNumFitxes03;
    private JLabel jlNumFitxes02;
    private JLabel jlNumFitxes04;
    private JLabel jlNumFitxes05;
    private JLabel jlNumFitxes06;
    private JLabel jlNumFitxes07;
    private JLabel jlNumFitxes08;
    private JLabel jlNumFitxes09;
    //
    private JButton jbCheck;
    private JButton jbRise;
    private JButton jbBet;
    private JButton jbFold;
    //
    private JMenuBar jMenuBar;
    private JMenuItem jmiSortir;
    private JMenuItem jmiAjuda;
    private JMenu jmMenuJoc;
    private JMenu jmMenuAjuda;
    private JMenuItem jmiQuantA;
    private JSeparator jseSeparador;
    //
    private ControladoraGui gui;

    /** Constructor. */
    public GuiTaulell() {
        iniciarComponents(); // Automissatge per crear els components de la UI
        crearEscoltadors();


    }

    public GuiTaulell(ControladoraGui gui) {
        this.gui = gui;
        iniciarComponents(); // Automissatge per crear els components de la UI
        crearEscoltadors();

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
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setLayout(null);
    }

    //Mètode per introduir una imatge de fons.
    public class JPanelGlobal extends javax.swing.JPanel {

        @Override
        public void paintComponent(Graphics g) {
            ImageIcon imatgeFons = new ImageIcon(getClass().getResource("/serveis/imatges/taulaMenu.gif"));
            g.drawImage(imatgeFons.getImage(), 0, 0, 1016, 733, null);
            setOpaque(true);
        }
    }

    private void crearJPanels() {
        jMenuBar = new JMenuBar();
        jMenuBar.setToolTipText("Barra de menús");
        jMenuBar.setBounds(0, 0, 1024, 25);
        jFrame.add(jMenuBar);

        jPanelGlobal = new JPanelGlobal();
        jPanelGlobal.setBackground(Color.WHITE);
        jPanelGlobal.setLayout(null);
        jPanelGlobal.setBorder(null);
        jPanelGlobal.setBounds(0, 25, 1024, 761);
        jFrame.add(jPanelGlobal);

        jPanelUsuari = new JPanel();
        jPanelUsuari.setOpaque(false);
        jPanelUsuari.setLayout(null);
        final int xjPanelUsari = 420;
        final int yjPanelUsari = 610;
        final int wjPanelUsari = 148;
        final int hjPanelUsari = 97;
        jPanelUsuari.setBounds(xjPanelUsari, yjPanelUsari, wjPanelUsari, hjPanelUsari);
        jPanelUsuari.setLayout(null);
        jPanelGlobal.add(jPanelUsuari);

        jPanelCartesTaula = new JPanel();
        jPanelCartesTaula.setOpaque(false);
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
        jPanelJugador01.setLayout(null);
        final int x1 = 620;
        final int y1 = 0;
        final int w1 = 107;
        final int h1 = 213;
        jPanelJugador01.setBounds(x1, y1, w1, h1);
        jPanelJugador01.setLayout(null);
        jPanelGlobal.add(jPanelJugador01);

        jPanelJugador02 = new JPanel();
        jPanelJugador02.setOpaque(false);
        jPanelJugador02.setLayout(null);
        final int x2 = 750;
        final int y2 = 50;
        final int w2 = 200;
        final int h2 = 200;
        jPanelJugador02.setBounds(x2, y2, w2, h2);
        jPanelJugador02.setLayout(null);
        jPanelGlobal.add(jPanelJugador02);

        jPanelJugador03 = new JPanel();
        jPanelJugador03.setOpaque(false);
        jPanelJugador03.setLayout(null);
        final int x3 = 750;
        final int y3 = 300;
        final int w3 = 200;
        final int h3 = 200;
        jPanelJugador03.setBounds(x3, y3, w3, h3);
        jPanelJugador03.setLayout(null);
        jPanelGlobal.add(jPanelJugador03);

        jPanelJugador04 = new JPanel();
        jPanelJugador04.setOpaque(false);
        jPanelJugador04.setLayout(null);
        final int x4 = 620;
        final int y4 = 350;
        final int w4 = w1;
        final int h4 = 250;
        jPanelJugador04.setBounds(x4, y4, w4, h4);
        jPanelJugador04.setLayout(null);
        jPanelGlobal.add(jPanelJugador04);

        jPanelJugador05 = new JPanel();
        jPanelJugador05.setOpaque(false);
        jPanelJugador05.setLayout(null);
        final int x5 = 460;
        final int y5 = 350;
        final int w5 = w1;
        final int h5 = 250;
        jPanelJugador05.setBounds(x5, y5, w5, h5);
        jPanelJugador05.setLayout(null);
        jPanelGlobal.add(jPanelJugador05);

        jPanelJugador06 = new JPanel();
        jPanelJugador06.setOpaque(false);
        jPanelJugador06.setLayout(null);
        final int x6 = 310;
        final int y6 = 350;
        final int w6 = w1;
        final int h6 = 250;
        jPanelJugador06.setBounds(x6, y6, w6, h6);
        jPanelJugador06.setLayout(null);
        jPanelGlobal.add(jPanelJugador06);

        jPanelJugador07 = new JPanel();
        jPanelJugador07.setOpaque(false);
        jPanelJugador07.setLayout(null);
        final int x7 = 60;
        final int y7 = 300;
        final int w7 = 200;
        final int h7 = h1;
        jPanelJugador07.setBounds(x7, y7, w7, h7);
        jPanelJugador07.setLayout(null);
        jPanelGlobal.add(jPanelJugador07);

        jPanelJugador08 = new JPanel();
        jPanelJugador08.setOpaque(false);
        jPanelJugador08.setLayout(null);
        final int x8 = 80;
        final int y8 = 50;
        final int w8 = 200;
        final int h8 = h1;
        jPanelJugador08.setBounds(x8, y8, w8, h8);
        jPanelJugador08.setLayout(null);
        jPanelGlobal.add(jPanelJugador08);

        jPanelJugador09 = new JPanel();
        jPanelJugador09.setOpaque(false);
        jPanelJugador09.setLayout(null);
        final int x9 = 310;
        final int y9 = 0;
        final int w9 = w1;
        final int h9 = h1;
        jPanelJugador09.setBounds(x9, y9, w9, h9);
        jPanelJugador09.setLayout(null);
        jPanelGlobal.add(jPanelJugador09);

        jPanelCrupier = new JPanel();
        jPanelCrupier.setOpaque(false);
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
        jPanelBotons.setLayout(null);
        jPanelBotons.setBounds(5, 557, 236, 166);
        jPanelBotons.setLayout(null);
        jPanelGlobal.add(jPanelBotons);

    }

    private void crearControls() {
        crearControlsJMenuBar();
        crearControlsJPanelUsuari();
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

    private void crearControlsJMenuBar() {
        jmMenuJoc = new JMenu("Joc");
        jmMenuJoc.setMnemonic('J');
        jMenuBar.add(jmMenuJoc);

        jmiSortir = new JMenuItem("Sortir");
        jmiSortir.setMnemonic('S');
        jmMenuJoc.add(jmiSortir);

        jmMenuAjuda = new JMenu("Ajuda");
        jmMenuAjuda.setMnemonic('A');
        jMenuBar.add(jmMenuAjuda);

        jmiAjuda = new JMenuItem("Contingut de l'ajuda F1");
        jmiAjuda.setMnemonic(KeyEvent.VK_F1);
        jmMenuAjuda.add(jmiAjuda);

        jseSeparador = new JSeparator(SwingConstants.HORIZONTAL);
        jmMenuAjuda.add(jseSeparador);

        jmiQuantA = new JMenuItem("Quant a WiPoker");
        jmiQuantA.setMnemonic('Q');
        jmMenuAjuda.add(jmiQuantA);
    }

    private void crearControlsJPanelUsuari() {
        jlCarta01Usuari = new JLabel();
        jlCarta01Usuari.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/14s.gif")));
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01Usuari.setBounds(x1, y1, w1, h1);
        jlCarta01Usuari.setVisible(false);
        jPanelUsuari.add(jlCarta01Usuari);

        jlCarta02Usuari = new JLabel();
        jlCarta02Usuari.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/2h.gif")));
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02Usuari.setBounds(x2, y2, w2, h2);
        jlCarta02Usuari.setVisible(false);
        jPanelUsuari.add(jlCarta02Usuari);
    }

    private void crearControlsJPanelCartesTaula() {

        jlCarta01 = new JLabel();
        jlCarta01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/14s.gif")));
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01.setBounds(x1, y1, w1, h1);
        jlCarta01.setVisible(false);
        jPanelCartesTaula.add(jlCarta01);

        jlCarta02 = new JLabel();
        jlCarta02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/2h.gif")));
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02.setBounds(x2, y2, w2, h2);
        jlCarta02.setVisible(false);
        jPanelCartesTaula.add(jlCarta02);

        jlCarta03 = new JLabel();
        jlCarta03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/3c.gif")));
        final int sh23 = sh12;
        final int x3 = x2 + w2 + sh23;
        final int y3 = y1;
        final int w3 = w1;
        final int h3 = h1;
        jlCarta03.setBounds(x3, y3, w3, h3);
        jlCarta03.setVisible(false);
        jPanelCartesTaula.add(jlCarta03);

        jlCarta04 = new JLabel();
        jlCarta04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/" + "13" + "c" + ".gif")));
        final int sh34 = sh12;
        final int x4 = x3 + w3 + sh34;
        final int y4 = y1;
        final int w4 = w1;
        final int h4 = h1;
        jlCarta04.setBounds(x4, y4, w4, h4);
        jlCarta04.setVisible(false);
        jPanelCartesTaula.add(jlCarta04);

        jlCarta05 = new JLabel();
        jlCarta05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/cards/14c.gif")));
        final int sh45 = sh12;
        final int x5 = x4 + w4 + sh45;
        final int y5 = y1;
        final int w5 = w1;
        final int h5 = h1;
        jlCarta05.setBounds(x5, y5, w5, h5);
        jlCarta05.setVisible(false);
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
        jlFitxes01.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        final int x2 = 0;
        final int y2 = jPanelJugador01.getHeight() - 93;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes01.setBounds(x2, y2, w2, h2);
        jPanelJugador01.add(jlFitxes01);

        jlNom01 = new JLabel();
        jlNom01.setText("Jugador01");
        jlNom01.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNom01.setForeground(Color.WHITE);
        final int x3 = 20;
        final int y3 = h1;
        final int w3 = 100;
        final int h3 = 13;
        jlNom01.setBounds(x3, y3, w3, h3);
        jPanelJugador01.add(jlNom01);

        jlNumFitxes01 = new JLabel();
        jlNumFitxes01.setText("1000 f.");
        jlNumFitxes01.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes01.setBounds(x4, y4, w4, h4);
        jPanelJugador01.add(jlNumFitxes01);
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
        final int y2 = jPanelJugador02.getHeight() - 93;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes02.setBounds(x2, y2, w2, h2);
        jPanelJugador02.add(jlFitxes02);

        jlNom02 = new JLabel();
        jlNom02.setText("Jugador02");
        jlNom02.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x1 + 20;
        final int y3 = h1;
        final int w3 = 100;
        final int h3 = 13;
        jlNom02.setBounds(x3, y3, w3, h3);
        jPanelJugador02.add(jlNom02);

        jlNumFitxes02 = new JLabel();
        jlNumFitxes02.setText("1000 f.");
        jlNumFitxes02.setForeground(Color.WHITE);
        final int x4 = x2 + 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes02.setBounds(x4, y4, w4, h4);
        jPanelJugador02.add(jlNumFitxes02);
    }

    private void crearControlsJPanelFitxes03() {
        jlAvatar03 = new JLabel();
        jlAvatar03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar03.setOpaque(false);
        final int x1 = 100;
        final int y1 = 87;
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

        jlNom03 = new JLabel();
        jlNom03.setText("Jugador03");
        jlNom03.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x1 + 20;
        final int y3 = y1 + h1;
        final int w3 = 100;
        final int h3 = 13;
        jlNom03.setBounds(x3, y3, w3, h3);
        jPanelJugador03.add(jlNom03);

        jlNumFitxes03 = new JLabel();
        jlNumFitxes03.setText("1000 f.");
        jlNumFitxes03.setForeground(Color.WHITE);
        final int x4 = x2 + 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes03.setBounds(x4, y4, w4, h4);
        jPanelJugador03.add(jlNumFitxes03);
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
        final int y2 = 137;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar04.setBounds(x2, y2, w2, h2);
        jPanelJugador04.add(jlAvatar04);

        jlNom04 = new JLabel();
        jlNom04.setText("Jugador04");
        jlNom04.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom04.setBounds(x3, y3, w3, h3);
        jPanelJugador04.add(jlNom04);

        jlNumFitxes04 = new JLabel();
        jlNumFitxes04.setText("1000 f.");
        jlNumFitxes04.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = h1 + 10;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes04.setBounds(x4, y4, w4, h4);
        jPanelJugador04.add(jlNumFitxes04);
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
        final int y2 = 137;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar05.setBounds(x2, y2, w2, h2);
        jPanelJugador05.add(jlAvatar05);

        jlNom05 = new JLabel();
        jlNom05.setText("Jugador05");
        jlNom05.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom05.setBounds(x3, y3, w3, h3);
        jPanelJugador05.add(jlNom05);

        jlNumFitxes05 = new JLabel();
        jlNumFitxes05.setText("1000 f.");
        jlNumFitxes05.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = h1 + 10;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes05.setBounds(x4, y4, w4, h4);
        jPanelJugador05.add(jlNumFitxes05);
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
        final int y2 = 137;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar06.setBounds(x2, y2, w2, h2);
        jPanelJugador06.add(jlAvatar06);

        jlNom06 = new JLabel();
        jlNom06.setText("Jugador06");
        jlNom06.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom06.setBounds(x3, y3, w3, h3);
        jPanelJugador06.add(jlNom06);

        jlNumFitxes06 = new JLabel();
        jlNumFitxes06.setText("1000 f.");
        jlNumFitxes06.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = h1 + 10;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes06.setBounds(x4, y4, w4, h4);
        jPanelJugador06.add(jlNumFitxes06);
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

        jlNom07 = new JLabel();
        jlNom07.setText("Jugador07");
        jlNom07.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom07.setBounds(x3, y3, w3, h3);
        jPanelJugador07.add(jlNom07);

        jlNumFitxes07 = new JLabel();
        jlNumFitxes07.setText("1000 f.");
        jlNumFitxes07.setForeground(Color.WHITE);
        final int x4 = x1 + 20;
        final int y4 = y1 + h1;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes07.setBounds(x4, y4, w4, h4);
        jPanelJugador07.add(jlNumFitxes07);
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

        jlNom08 = new JLabel();
        jlNom08.setText("Jugador08");
        jlNom08.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNom08.setForeground(Color.GRAY);
        final int x3 = x2+20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom08.setBounds(x3, y3, w3, h3);
        jPanelJugador08.add(jlNom08);

        jlNumFitxes08 = new JLabel();
        jlNumFitxes08.setText("1000 f.");
        jlNumFitxes08.setForeground(Color.WHITE);
        final int x4 = x1+20;
        final int y4 = y1 + h1;
        final int w4 = 100;
        final int h4 = 12;
        jlNumFitxes08.setBounds(x4, y4, w4, h4);
        jPanelJugador08.add(jlNumFitxes08);
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
        final int x2 = 0;
        final int y2 = jPanelJugador09.getHeight() - 93;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes09.setBounds(x2, y2, w2, h2);
        jPanelJugador09.add(jlFitxes09);

        jlNom09 = new JLabel();
        jlNom09.setText("Jugador09");
        jlNom09.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNom09.setForeground(Color.WHITE);
        final int x3 = 20;
        final int y3 = h1;
        final int w3 = 100;
        final int h3 = 13;
        jlNom09.setBounds(x3, y3, w3, h3);
        jPanelJugador09.add(jlNom09);

        jlNumFitxes09 = new JLabel();
        jlNumFitxes09.setText("1000 f.");
        jlNumFitxes09.setForeground(Color.WHITE);
        final int x4 = x2+20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 12;
        jlNumFitxes09.setBounds(x4, y4, w4, h4);
        jPanelJugador09.add(jlNumFitxes09);
    }

    private void crearControlsJPanelCrupier() {
        jlNomCroupier = new JLabel("Crooupiera");
        jlNomCroupier.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNomCroupier.setForeground(Color.WHITE);
        jlNomCroupier.setBounds(20, 100, 100, 13);
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
        final int sv = 2;
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 236;
        final int h1 = 40;
        jbCheck.setBounds(x1, y1, w1, h1);
        jPanelBotons.add(jbCheck);

        jbRise = new JButton();
        jbRise.setIconTextGap(-180);
        jbRise.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbRise.setMnemonic('R');
        jbRise.setText("Rise");
        jbRise.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbRise.setCursor(cursor);
        jbRise.setToolTipText("Fa rise :)");
        final int x2 = x1;
        final int y2 = y1 + h1 + sv;
        final int w2 = w1;
        final int h2 = h1;
        jbRise.setBounds(x2, y2, w2, h2);
        jPanelBotons.add(jbRise);

        jbBet = new JButton();
        jbBet.setIconTextGap(-180);
        jbBet.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbBet.setMnemonic('B');
        jbBet.setText("Bet");
        jbBet.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbBet.setCursor(cursor);
        jbBet.setToolTipText("Fa bet :)");
        final int x3 = x1;
        final int y3 = y2 + h2 + sv;
        final int w3 = w1;
        final int h3 = h1;
        jbBet.setBounds(x3, y3, w3, h3);
        jPanelBotons.add(jbBet);

        jbFold = new JButton();
        jbFold.setIconTextGap(-180);
        jbFold.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbFold.setMnemonic('F');
        jbFold.setText("Fold");
        jbFold.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbFold.setCursor(cursor);
        jbFold.setToolTipText("Fa fold :)");
        final int x4 = x1;
        final int y4 = y3 + h2 + sv;
        final int w4 = w1;
        final int h4 = h1;
        jbFold.setBounds(x4, y4, w4, h4);
        jPanelBotons.add(jbFold);
    }

    private void crearEscoltadors() {
        //pendiente crear control que recoja la cantidad apostada
        jmiSortir.addActionListener(new ActionListener() {

            private GuiMenu menu;

            public void actionPerformed(final ActionEvent evt) {
                try {
                    jFrame.dispose();
                    menu = new GuiMenu(gui);
                    menu.getjFrame().setVisible(true);
                } catch (Throwable ex) {
                    Logger.getLogger(GuiTaulell.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jbCheck.addActionListener(new ActionListener() {

            public void actionPerformed(final ActionEvent evt) {
                //crear jlabel con dinero
                gui.doCheck(0);
            }
        });
        jbBet.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.doBet(0);
            }
        });

        jbRise.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.doRise(0);
            }
        });
        jbFold.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                gui.doFold();
            }
        });
    }

    /** Crea l'objecte controlador del cas d'ús. */
    private void iniciarJFrame() {
        jFrame.setVisible(true);
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    /**
     * Inicia el cas d'ús des del SO.
     * @param args No emprats
     */
    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GuiTaulell();
            }
        });
    }
}
