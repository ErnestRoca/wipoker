package presentacio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JOptionPane;

/**
 *
 * @author wida53312560
 */
public class SortirListener implements WindowListener, ActionListener {

    private GuiMenu menu;

    public SortirListener(GuiMenu menu) {
        this.menu = menu;
    }

    @Override
    public void windowOpened(WindowEvent e) {

        System.out.println("window opened");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("window closing");
        ActionEvent a0 = new ActionEvent(e.getSource(), 0, "");
        actionPerformed(a0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

        System.out.println("window closed");
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (JOptionPane.showConfirmDialog(menu.getjFrame(),
                "Esta segur que vol sortir?", "Sortir",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            menu.getjFrame().dispose();
        }
    }
}
