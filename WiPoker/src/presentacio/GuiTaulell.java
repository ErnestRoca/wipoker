package presentacio;

import controladora.ControladoraGui;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author ernest
 */
public class GuiTaulell {

    ArrayList<JPanel> panellsJugadors = new ArrayList<JPanel>();
    ArrayList<JLabel> nomsJugadors = new ArrayList<JLabel>();
    ArrayList<JLabel> avatars = new ArrayList<JLabel>();
    ArrayList<JLabel> fitxesJugadors = new ArrayList<JLabel>();
    ArrayList<JLabel> numFitxesJugadors = new ArrayList<JLabel>();
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
    private JPanel jPanelMissatges;
    //
    private JTextArea jtaMissatge;
    private JLabel jlMissatge2;
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
    private JScrollBar jsbEntradaFitxes;
    private JButton jbRise;
    private JButton jbCallCheck;
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
    private JLabel jlMinScrollBar;

    /** Constructor. */
    public GuiTaulell() {
        iniciarComponents(); // Automissatge per crear els components de la UI
        crearEscoltadors();
    }

    public GuiTaulell(ControladoraGui gui) {
        try {
            this.gui = gui;
            iniciarComponents(); // Automissatge per crear els components de la UI
            crearEscoltadors();
            gui.setTaulell(this);
            gui.iniciarPartida();
        } catch (InterruptedException ex) {
            Logger.getLogger(GuiTaulell.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            ImageIcon imatgeFons = new ImageIcon(getClass().getResource("/serveis/imatges/fons.png"));
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
        jPanelGlobal.setBackground(Color.GREEN);
        jPanelGlobal.setLayout(null);
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
        panellsJugadors.add(jPanelJugador01);
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
        panellsJugadors.add(jPanelJugador02);
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
        panellsJugadors.add(jPanelJugador03);
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
        panellsJugadors.add(jPanelJugador04);
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
        panellsJugadors.add(jPanelJugador05);
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
        panellsJugadors.add(jPanelJugador06);
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
        panellsJugadors.add(jPanelJugador07);
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
        panellsJugadors.add(jPanelJugador08);
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
        panellsJugadors.add(jPanelJugador09);
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

        jPanelMissatges = new JPanel();
        jPanelMissatges.setOpaque(true);
        jPanelMissatges.setLayout(null);
        jPanelMissatges.setBackground(Color.BLACK);
        jPanelMissatges.setBounds(390, 250, 220, 70);
        jPanelMissatges.setLayout(null);
        //jPanelMissatges.setVisible(false);
        jPanelGlobal.add(jPanelMissatges);

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
        crearControlsJPanelMissatges();
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
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01Usuari.setBounds(x1, y1, w1, h1);
        jPanelUsuari.add(jlCarta01Usuari);

        jlCarta02Usuari = new JLabel();
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02Usuari.setBounds(x2, y2, w2, h2);
        jPanelUsuari.add(jlCarta02Usuari);
    }

    private void crearControlsJPanelCartesTaula() {
        jlCarta01 = new JLabel();
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 73;
        final int h1 = 97;
        jlCarta01.setBounds(x1, y1, w1, h1);
        jPanelCartesTaula.add(jlCarta01);

        jlCarta02 = new JLabel();
        final int sh12 = 2;
        final int x2 = x1 + w1 + sh12;
        final int y2 = y1;
        final int w2 = w1;
        final int h2 = h1;
        jlCarta02.setBounds(x2, y2, w2, h2);
        jPanelCartesTaula.add(jlCarta02);

        jlCarta03 = new JLabel();
        final int sh23 = sh12;
        final int x3 = x2 + w2 + sh23;
        final int y3 = y1;
        final int w3 = w1;
        final int h3 = h1;
        jlCarta03.setBounds(x3, y3, w3, h3);
        jPanelCartesTaula.add(jlCarta03);

        jlCarta04 = new JLabel();
        final int sh34 = sh12;
        final int x4 = x3 + w3 + sh34;
        final int y4 = y1;
        final int w4 = w1;
        final int h4 = h1;
        jlCarta04.setBounds(x4, y4, w4, h4);
        jPanelCartesTaula.add(jlCarta04);

        jlCarta05 = new JLabel();
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
        jlAvatar01.setOpaque(false);
        final int x1 = 0;
        final int y1 = 0;
        final int w1 = 100;
        final int h1 = 100;
        jlAvatar01.setBounds(x1, y1, w1, h1);
        jPanelJugador01.add(jlAvatar01);
        avatars.add(jlAvatar01);

        jlFitxes01 = new JLabel();
        final int x2 = 0;
        final int y2 = jPanelJugador01.getHeight() - 93;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes01.setBounds(x2, y2, w2, h2);
        jPanelJugador01.add(jlFitxes01);
        fitxesJugadors.add(jlFitxes01);

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
        nomsJugadors.add(jlNom01);

        jlNumFitxes01 = new JLabel();
        jlNumFitxes01.setText("1000 f.");
        jlNumFitxes01.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes01.setBounds(x4, y4, w4, h4);
        jPanelJugador01.add(jlNumFitxes01);
        numFitxesJugadors.add(jlNumFitxes01);
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
        avatars.add(jlAvatar02);

        jlFitxes02 = new JLabel();
        jlFitxes02.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes02.setOpaque(false);
        final int x2 = 0;
        final int y2 = jPanelJugador02.getHeight() - 93;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes02.setBounds(x2, y2, w2, h2);
        jPanelJugador02.add(jlFitxes02);
        fitxesJugadors.add(jlFitxes02);

        jlNom02 = new JLabel();
        jlNom02.setText("Jugador02");
        jlNom02.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x1 + 20;
        final int y3 = h1;
        final int w3 = 100;
        final int h3 = 13;
        jlNom02.setBounds(x3, y3, w3, h3);
        jPanelJugador02.add(jlNom02);
        nomsJugadors.add(jlNom02);

        jlNumFitxes02 = new JLabel();
        jlNumFitxes02.setText("1000 f.");
        jlNumFitxes02.setForeground(Color.WHITE);
        final int x4 = x2 + 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes02.setBounds(x4, y4, w4, h4);
        jPanelJugador02.add(jlNumFitxes02);
        numFitxesJugadors.add(jlNumFitxes02);
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
        avatars.add(jlAvatar03);

        jlFitxes03 = new JLabel();
        jlFitxes03.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        jlFitxes03.setOpaque(false);
        final int x2 = 0;
        final int y2 = 0;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes03.setBounds(x2, y2, w2, h2);
        jPanelJugador03.add(jlFitxes03);
        fitxesJugadors.add(jlFitxes03);

        jlNom03 = new JLabel();
        jlNom03.setText("Jugador03");
        jlNom03.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x1 + 20;
        final int y3 = y1 + h1;
        final int w3 = 100;
        final int h3 = 13;
        jlNom03.setBounds(x3, y3, w3, h3);
        jPanelJugador03.add(jlNom03);
        nomsJugadors.add(jlNom03);

        jlNumFitxes03 = new JLabel();
        jlNumFitxes03.setText("1000 f.");
        jlNumFitxes03.setForeground(Color.WHITE);
        final int x4 = x2 + 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes03.setBounds(x4, y4, w4, h4);
        jPanelJugador03.add(jlNumFitxes03);
        numFitxesJugadors.add(jlNumFitxes03);
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
        fitxesJugadors.add(jlFitxes04);

        jlAvatar04 = new JLabel();
        jlAvatar04.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar04.setOpaque(false);
        final int x2 = 0;
        final int y2 = 137;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar04.setBounds(x2, y2, w2, h2);
        jPanelJugador04.add(jlAvatar04);
        avatars.add(jlAvatar04);

        jlNom04 = new JLabel();
        jlNom04.setText("Jugador04");
        jlNom04.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom04.setBounds(x3, y3, w3, h3);
        jPanelJugador04.add(jlNom04);
        nomsJugadors.add(jlNom04);

        jlNumFitxes04 = new JLabel();
        jlNumFitxes04.setText("1000 f.");
        jlNumFitxes04.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = h1 + 10;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes04.setBounds(x4, y4, w4, h4);
        jPanelJugador04.add(jlNumFitxes04);
        numFitxesJugadors.add(jlNumFitxes04);
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
        fitxesJugadors.add(jlFitxes05);

        jlAvatar05 = new JLabel();
        jlAvatar05.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar05.setOpaque(false);
        final int x2 = 0;
        final int y2 = 137;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar05.setBounds(x2, y2, w2, h2);
        jPanelJugador05.add(jlAvatar05);
        avatars.add(jlAvatar05);

        jlNom05 = new JLabel();
        jlNom05.setText("Jugador05");
        jlNom05.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom05.setBounds(x3, y3, w3, h3);
        jPanelJugador05.add(jlNom05);
        nomsJugadors.add(jlNom05);

        jlNumFitxes05 = new JLabel();
        jlNumFitxes05.setText("1000 f.");
        jlNumFitxes05.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = h1 + 10;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes05.setBounds(x4, y4, w4, h4);
        jPanelJugador05.add(jlNumFitxes05);
        numFitxesJugadors.add(jlNumFitxes05);
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
        fitxesJugadors.add(jlFitxes06);

        jlAvatar06 = new JLabel();
        jlAvatar06.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar06.setOpaque(false);
        final int x2 = 0;
        final int y2 = 137;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar06.setBounds(x2, y2, w2, h2);
        jPanelJugador06.add(jlAvatar06);
        avatars.add(jlAvatar06);

        jlNom06 = new JLabel();
        jlNom06.setText("Jugador06");
        jlNom06.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom06.setBounds(x3, y3, w3, h3);
        jPanelJugador06.add(jlNom06);
        nomsJugadors.add(jlNom06);

        jlNumFitxes06 = new JLabel();
        jlNumFitxes06.setText("1000 f.");
        jlNumFitxes06.setForeground(Color.WHITE);
        final int x4 = 20;
        final int y4 = h1 + 10;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes06.setBounds(x4, y4, w4, h4);
        jPanelJugador06.add(jlNumFitxes06);
        numFitxesJugadors.add(jlNumFitxes06);
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
        fitxesJugadors.add(jlFitxes07);

        jlAvatar07 = new JLabel();
        jlAvatar07.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar07.setOpaque(false);
        final int x2 = 0;
        final int y2 = 100;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar07.setBounds(x2, y2, w2, h2);
        jPanelJugador07.add(jlAvatar07);
        avatars.add(jlAvatar07);

        jlNom07 = new JLabel();
        jlNom07.setText("Jugador07");
        jlNom07.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        final int x3 = 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom07.setBounds(x3, y3, w3, h3);
        jPanelJugador07.add(jlNom07);
        nomsJugadors.add(jlNom07);

        jlNumFitxes07 = new JLabel();
        jlNumFitxes07.setText("1000 f.");
        jlNumFitxes07.setForeground(Color.WHITE);
        final int x4 = x1 + 20;
        final int y4 = y1 + h1;
        final int w4 = 100;
        final int h4 = 13;
        jlNumFitxes07.setBounds(x4, y4, w4, h4);
        jPanelJugador07.add(jlNumFitxes07);
        numFitxesJugadors.add(jlNumFitxes07);
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
        fitxesJugadors.add(jlFitxes08);

        jlAvatar08 = new JLabel();
        jlAvatar08.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/avatar.png")));
        jlAvatar08.setOpaque(false);
        final int x2 = 0;
        final int y2 = 0;
        final int w2 = 100;
        final int h2 = 100;
        jlAvatar08.setBounds(x2, y2, w2, h2);
        jPanelJugador08.add(jlAvatar08);
        avatars.add(jlAvatar08);

        jlNom08 = new JLabel();
        jlNom08.setText("Jugador08");
        jlNom08.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        jlNom08.setForeground(Color.GRAY);
        final int x3 = x2 + 20;
        final int y3 = y2 + h2;
        final int w3 = 100;
        final int h3 = 13;
        jlNom08.setBounds(x3, y3, w3, h3);
        jPanelJugador08.add(jlNom08);
        nomsJugadors.add(jlNom08);

        jlNumFitxes08 = new JLabel();
        jlNumFitxes08.setText("1000 f.");
        jlNumFitxes08.setForeground(Color.WHITE);
        final int x4 = x1 + 20;
        final int y4 = y1 + h1;
        final int w4 = 100;
        final int h4 = 12;
        jlNumFitxes08.setBounds(x4, y4, w4, h4);
        jPanelJugador08.add(jlNumFitxes08);
        numFitxesJugadors.add(jlNumFitxes08);
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
        avatars.add(jlAvatar09);

        jlFitxes09 = new JLabel();
        jlFitxes09.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/fitxes" + 4 + ".png")));
        final int x2 = 0;
        final int y2 = jPanelJugador09.getHeight() - 93;
        final int w2 = 107;
        final int h2 = 80;
        jlFitxes09.setBounds(x2, y2, w2, h2);
        jPanelJugador09.add(jlFitxes09);
        fitxesJugadors.add(jlFitxes09);

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
        nomsJugadors.add(jlNom09);

        jlNumFitxes09 = new JLabel();
        jlNumFitxes09.setText("1000 f.");
        jlNumFitxes09.setForeground(Color.WHITE);
        final int x4 = x2 + 20;
        final int y4 = y2 + h2;
        final int w4 = 100;
        final int h4 = 12;
        jlNumFitxes09.setBounds(x4, y4, w4, h4);
        jPanelJugador09.add(jlNumFitxes09);
        numFitxesJugadors.add(jlNumFitxes09);
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
        jlMinScrollBar = new JLabel();


        jsbEntradaFitxes = new JScrollBar(SwingConstants.HORIZONTAL, 0, 0, 0, 100);
        jsbEntradaFitxes.setOpaque(false);
        final int sv = 2;
        final int x1 = 6;
        final int y1 = 20;
        final int w1 = 220;
        final int h1 = 20;
        jsbEntradaFitxes.setBounds(x1, y1, w1, h1);
        jPanelBotons.add(jsbEntradaFitxes);

        jbRise = new JButton();
        jbRise.setIconTextGap(-180);
        jbRise.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbRise.setMnemonic('R');
        jbRise.setText("Rise");
        jbRise.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbRise.setCursor(cursor);
        jbRise.setToolTipText("Apujar l'aposta");
        final int x2 = 0;
        final int y2 = 0 + 40 + sv;
        final int w2 = 236;
        final int h2 = 40;
        jbRise.setBounds(x2, y2, w2, h2);
        jPanelBotons.add(jbRise);

        jbCallCheck = new JButton();
        jbCallCheck.setIconTextGap(-180);
        jbCallCheck.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbCallCheck.setMnemonic('C');
        jbCallCheck.setText("Call");
        jbCallCheck.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbCallCheck.setCursor(cursor);
        jbCallCheck.setToolTipText("Igualar aposta");
        final int x3 = x2;
        final int y3 = y2 + h2 + sv;
        final int w3 = w2;
        final int h3 = h2;
        jbCallCheck.setBounds(x3, y3, w3, h3);
        jPanelBotons.add(jbCallCheck);

        jbFold = new JButton();
        jbFold.setIconTextGap(-180);
        jbFold.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbFold.setMnemonic('F');
        jbFold.setText("Fold");
        jbFold.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbFold.setCursor(cursor);
        jbFold.setToolTipText("No jugar ronda actual");
        final int x4 = x2;
        final int y4 = y3 + h2 + sv;
        final int w4 = w2;
        final int h4 = h2;
        jbFold.setBounds(x4, y4, w4, h4);
        jPanelBotons.add(jbFold);
    }

    private void crearControlsJPanelMissatges() {
        jtaMissatge = new JTextArea();
        jtaMissatge.setRows(5);
        jtaMissatge.setText("aaaaaa\n jaaaaaaa\naaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaa");
        jtaMissatge.setForeground(Color.BLACK);
        jtaMissatge.setBounds(5, 10, 210, 20);
        jPanelMissatges.add(jtaMissatge);

        jlMissatge2 = new JLabel();
        jlMissatge2.setText("aaaaaa\n jaaaaaaa\naaaaaaaaaaaaaaaa\naaaaaaaaaaaaaaaaaaaaaaa");
        jlMissatge2.setForeground(Color.BLACK);
        jlMissatge2.setBounds(5, 35, 210, 20);
        jPanelMissatges.add(jlMissatge2);
    }

    private void crearEscoltadors() {
        //pendiente crear control que recoja la cantidad apostada
        jmiSortir.addActionListener(new ActionListener() {

            private GuiMenu menu;

            @Override
            public void actionPerformed(final ActionEvent evt) {
                try {
                    //gui.getCp().setFi(true);
                    jFrame.dispose();
                    menu = new GuiMenu(gui);
                    menu.getjFrame().setVisible(true);
                } catch (Throwable ex) {
                    Logger.getLogger(GuiTaulell.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        jbCallCheck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.accioCheckCall();
                gui.getTornActual().resume();

            }
        });

        jbRise.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.doRise(30);
                gui.getTornActual().resume();
            }
        });

        jbFold.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                gui.doFold();
                gui.getTornActual().resume();

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

    public JButton getJbCallCheck() {
        return jbCallCheck;
    }

    public void setJbCallCheck(JButton jbCallCheck) {
        this.jbCallCheck = jbCallCheck;
    }

    public JScrollBar getJsbEntradaFitxes() {
        return jsbEntradaFitxes;
    }

    public void setJsbEntradaFitxes(JScrollBar jsbEntradaFitxes) {
        this.jsbEntradaFitxes = jsbEntradaFitxes;
    }

    public JButton getJbFold() {
        return jbFold;
    }

    public void setJbFold(JButton jbFold) {
        this.jbFold = jbFold;
    }

    public JButton getJbRise() {
        return jbRise;
    }

    public void setJbRise(JButton jbRise) {
        this.jbRise = jbRise;
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

    public JLabel getJlCarta01Usuari() {
        return jlCarta01Usuari;
    }

    public void setJlCarta01Usuari(JLabel jlCarta01Usuari) {
        this.jlCarta01Usuari = jlCarta01Usuari;
    }

    public JLabel getJlCarta02() {
        return jlCarta02;
    }

    public void setJlCarta02(JLabel jlCarta02) {
        this.jlCarta02 = jlCarta02;
    }

    public JLabel getJlCarta02Usuari() {
        return jlCarta02Usuari;
    }

    public void setJlCarta02Usuari(JLabel jlCarta02Usuari) {
        this.jlCarta02Usuari = jlCarta02Usuari;
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

    public JLabel getJlNom01() {
        return jlNom01;
    }

    public void setJlNom01(JLabel jlNom01) {
        this.jlNom01 = jlNom01;
    }

    public JLabel getJlNom02() {
        return jlNom02;
    }

    public void setJlNom02(JLabel jlNom02) {
        this.jlNom02 = jlNom02;
    }

    public JLabel getJlNom03() {
        return jlNom03;
    }

    public void setJlNom03(JLabel jlNom03) {
        this.jlNom03 = jlNom03;
    }

    public JLabel getJlNom04() {
        return jlNom04;
    }

    public void setJlNom04(JLabel jlNom04) {
        this.jlNom04 = jlNom04;
    }

    public JLabel getJlNom05() {
        return jlNom05;
    }

    public void setJlNom05(JLabel jlNom05) {
        this.jlNom05 = jlNom05;
    }

    public JLabel getJlNom06() {
        return jlNom06;
    }

    public void setJlNom06(JLabel jlNom06) {
        this.jlNom06 = jlNom06;
    }

    public JLabel getJlNom07() {
        return jlNom07;
    }

    public void setJlNom07(JLabel jlNom07) {
        this.jlNom07 = jlNom07;
    }

    public JLabel getJlNom08() {
        return jlNom08;
    }

    public void setJlNom08(JLabel jlNom08) {
        this.jlNom08 = jlNom08;
    }

    public JLabel getJlNom09() {
        return jlNom09;
    }

    public void setJlNom09(JLabel jlNom09) {
        this.jlNom09 = jlNom09;
    }

    public JLabel getJlNomCroupier() {
        return jlNomCroupier;
    }

    public void setJlNomCroupier(JLabel jlNomCroupier) {
        this.jlNomCroupier = jlNomCroupier;
    }

    public JLabel getJlNumFitxes01() {
        return jlNumFitxes01;
    }

    public void setJlNumFitxes01(JLabel jlNumFitxes01) {
        this.jlNumFitxes01 = jlNumFitxes01;
    }

    public JLabel getJlNumFitxes02() {
        return jlNumFitxes02;
    }

    public void setJlNumFitxes02(JLabel jlNumFitxes02) {
        this.jlNumFitxes02 = jlNumFitxes02;
    }

    public JLabel getJlNumFitxes03() {
        return jlNumFitxes03;
    }

    public void setJlNumFitxes03(JLabel jlNumFitxes03) {
        this.jlNumFitxes03 = jlNumFitxes03;
    }

    public JLabel getJlNumFitxes04() {
        return jlNumFitxes04;
    }

    public void setJlNumFitxes04(JLabel jlNumFitxes04) {
        this.jlNumFitxes04 = jlNumFitxes04;
    }

    public JLabel getJlNumFitxes05() {
        return jlNumFitxes05;
    }

    public void setJlNumFitxes05(JLabel jlNumFitxes05) {
        this.jlNumFitxes05 = jlNumFitxes05;
    }

    public JLabel getJlNumFitxes06() {
        return jlNumFitxes06;
    }

    public void setJlNumFitxes06(JLabel jlNumFitxes06) {
        this.jlNumFitxes06 = jlNumFitxes06;
    }

    public JLabel getJlNumFitxes07() {
        return jlNumFitxes07;
    }

    public void setJlNumFitxes07(JLabel jlNumFitxes07) {
        this.jlNumFitxes07 = jlNumFitxes07;
    }

    public JLabel getJlNumFitxes08() {
        return jlNumFitxes08;
    }

    public void setJlNumFitxes08(JLabel jlNumFitxes08) {
        this.jlNumFitxes08 = jlNumFitxes08;
    }

    public JLabel getJlNumFitxes09() {
        return jlNumFitxes09;
    }

    public void setJlNumFitxes09(JLabel jlNumFitxes09) {
        this.jlNumFitxes09 = jlNumFitxes09;
    }

    public JPanel getjPanelMissatges() {
        return jPanelMissatges;
    }

    public void setjPanelMissatges(JPanel jPanelMissatges) {
        this.jPanelMissatges = jPanelMissatges;
    }

    public JTextArea getJtaMissatge() {
        return jtaMissatge;
    }

    public void setJtaMissatge(JTextArea jtaMissatge) {
        this.jtaMissatge = jtaMissatge;
    }

    public JLabel getJlMissatge2() {
        return jlMissatge2;
    }

    public void setJlMissatge2(JLabel jlMissatge2) {
        this.jlMissatge2 = jlMissatge2;
    }

    public ArrayList<JLabel> getAvatars() {
        return avatars;
    }

    public void setAvatars(ArrayList<JLabel> Avatars) {
        this.avatars = Avatars;
    }

    public ArrayList<JPanel> getPanellsJugadors() {
        return panellsJugadors;
    }

    public void setPanellsJugadors(ArrayList<JPanel> panellsJugadors) {
        this.panellsJugadors = panellsJugadors;
    }

    public ArrayList<JLabel> getFitxesJugadors() {
        return fitxesJugadors;
    }

    public void setFitxesJugadors(ArrayList<JLabel> fitxesJugadors) {
        this.fitxesJugadors = fitxesJugadors;
    }

    public ArrayList<JLabel> getNomsJugadors() {
        return nomsJugadors;
    }

    public void setNomsJugadors(ArrayList<JLabel> nomsJugadors) {
        this.nomsJugadors = nomsJugadors;
    }

    public ArrayList<JLabel> getNumFitxesJugadors() {
        return numFitxesJugadors;
    }

    public void setNumFitxesJugadors(ArrayList<JLabel> numFitxesJugadors) {
        this.numFitxesJugadors = numFitxesJugadors;
    }

    /**
     * Inicia el cas d'ús des del SO.
     * @param args No emprats
     */
    public static void main(final String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new GuiTaulell();
            }
        });
    }
}
