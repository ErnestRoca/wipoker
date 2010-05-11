/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import presentacio.dades.GuiMenuDades;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import presentacio.jabber.GuiMenuJabber;
import presentacio.partida.GuiNovaPartida;

/**
 *
 * @author wida45787385
 */
public class GuiMenu {

    private JFrame jFrame;
    private JPanel jpFons;
    private JLabel jlTitol;
    private JLabel jlImatgeFons;
    private JButton jbPartida;
    private JButton jbDades;
    private JButton jbSortir;
    private JButton jbJabber;
    private JButton jbTornar;
    private GuiMenuJabber jabber;
    private GuiMenuDades dades;
    private GuiNovaPartida partida;

    public GuiMenu() throws InterruptedException {
        iniciarComponents();
    }

    public void iniciarComponents() throws InterruptedException {
        jFrame = new JFrame();
        jFrame.setSize(new Dimension(338, 629));
        jFrame.setLocationRelativeTo(null);
        jFrame.setTitle("WiPoker");
        jFrame.setLayout(null);
        jFrame.setBackground(Color.WHITE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jpFons = new JPanel();
        jpFons.setLayout(null);
        jpFons.setOpaque(false);
        jpFons.setBounds(0, 0, 340, 950);
        jFrame.add(jpFons);

        jlTitol = new JLabel();
        jlTitol.setBounds(0, 0, 340, 104);
        jlTitol.setLayout(null);
        jlTitol.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/WiPokerLogo2.gif")));
        jpFons.add(jlTitol);

        jlImatgeFons = new JLabel();
        jlImatgeFons.setBounds(0, 104, 340, 499);
        jlImatgeFons.setLayout(null);
        jlImatgeFons.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/Wipokerbackground.jpg")));
        jlImatgeFons.setOpaque(false);

        final Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        jbJabber = new JButton("Jabber");
        jbJabber.setCursor(cursor);
        jbJabber.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbJabber.setBorder(null);
        jbJabber.setLayout(null);
        jbJabber.setBounds(40, 135, 260, 40);
        jbJabber.setIconTextGap(-260);
        jbJabber.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbJabber.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbJabber.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbJabber);

        jbPartida = new JButton("Jugar");
        jbPartida.setCursor(cursor);
        jbPartida.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbPartida.setBorder(null);
        jbPartida.setLayout(null);
        jbPartida.setBounds(40, 245, 260, 40);
        jbPartida.setIconTextGap(-260);
        jbPartida.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbPartida.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbPartida.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbPartida);

        jbDades = new JButton("Dades jugador");
        jbDades.setCursor(cursor);
        jbDades.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbDades.setBorder(null);
        jbDades.setLayout(null);
        jbDades.setBounds(40, 355, 260, 40);
        jbDades.setIconTextGap(-260);
        jbDades.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbDades.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbDades.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbDades);

        jbSortir = new JButton("Sortir");
        jbSortir.setCursor(cursor);
        jbSortir.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        jbSortir.setBorder(null);
        jbSortir.setLayout(null);
        jbSortir.setBounds(40, 465, 260, 40);
        jbSortir.setIconTextGap(-260);
        jbSortir.setIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto1.gif")));
        jbSortir.setRolloverIcon(new ImageIcon(getClass().getResource("/serveis/imatges/boto2.gif")));
        jbSortir.setHorizontalTextPosition(SwingConstants.CENTER);
        jpFons.add(jbSortir);

        jpFons.add(jlImatgeFons);

        jFrame.setVisible(true);

        jbJabber.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    jabber = new GuiMenuJabber();
                    jabber.getjFrame().setLocation(jFrame.getLocation());
                    jabber.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


        jbPartida.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    partida = new GuiNovaPartida();
                    partida.getjFrame().setLocation(jFrame.getLocation());
                    partida.getjFrame().setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });


        jbDades.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                try {
                    jFrame.setVisible(false);
                    dades = new GuiMenuDades();
                    dades.getjFrame().setLocation(jFrame.getLocation());
                    dades.getjFrame().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenuDades.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        jbSortir.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public static void main(final String[] args) {
        /** Triem el tipus de Look&Feel. */
        final int TIPUSLF = 6;
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                // Missatge inicarAction la classe Gui per crear un objecte Gui
                try {
                    String plaf = ""; // plaf = Pluggable Look&Feel
                    // Missatge inicarAction la classe Gui per crear un objecte Gui
                    final GuiMenu gui = new GuiMenu();
                    try {
                        // Triem el Look&Feel
                        switch (TIPUSLF) {
                            case 1:
                                // Especifiquem el Java Look & Feel (Conegut com Metal). Es pot emprar en totes les plataformes.
                                plaf = "javax.swing.plaf.metal.MetalLookAndFeel";
                                UIManager.setLookAndFeel(plaf);
                                break;
                            case 2:
                                // Es pot emprar en totes les plataformes.
                                plaf = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                                UIManager.setLookAndFeel(plaf);
                                break;
                            case 3:
                                // Nomes funciona en sistemes Win32.
                                plaf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                                UIManager.setLookAndFeel(plaf);
                                break;
                            case 4:
                                // Nomes funciona en sistemes Mac OS.
                                plaf = "javax.swing.plaf.mac.MacLookAndFeel";
                                UIManager.setLookAndFeel(plaf);
                                break;
                            case 5:
                                // Especifiquem el Java Look & Feel (Conegut com Metal) Es pot emprar en totes les plataformes.
                                plaf = UIManager.getCrossPlatformLookAndFeelClassName();
                                UIManager.setLookAndFeel(plaf);
                                break;
                            case 6:
                                /* Especifiquem el Java Look & Feel de la plataforma actual
                                 * En Win32, es el Windows Look & Feel.
                                 * En Mac es el Mac OS Look & Feel.
                                 * En Sun es el CDE/Motif Look & Feel.
                                 * Es pot emprar en totes les plataformes.
                                 */
                                plaf = UIManager.getSystemLookAndFeelClassName();
                                UIManager.setLookAndFeel(plaf);
                                break;
                            default:
                        }
                        //Actualitzem l'objecte jFrame amb el Look&Feel triat i tots els demes components ho faran en cascada
                        SwingUtilities.updateComponentTreeUI(gui.jFrame);
                    } catch (final Exception exception) {
                        final String missatge = "No s'ha pogut carregar el Look&Feel desitjat\nEs carrega el Look&Feel per defecte (Java Look & Feel)";
                        final String titol = "S'ha produit una excepció";
                        JOptionPane.showMessageDialog(gui.jFrame, missatge, titol, JOptionPane.ERROR_MESSAGE);
                    } finally {
                        gui.jFrame.setVisible(true);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(GuiMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
