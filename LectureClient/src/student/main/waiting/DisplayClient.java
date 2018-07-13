package student.main.waiting;

import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public class DisplayClient extends Thread {

    Socket s = null;
    ObjectInputStream ois = null;
    JLabel l = new JLabel();
    JWindow win = new JWindow();
    ImageIcon icon = new ImageIcon();
    JFrame fr;

    public DisplayClient() throws IOException {

        fr = new JFrame();
        s = new Socket("192.168.1.60", 2020);
        s.getOutputStream();
        ois = new ObjectInputStream(s.getInputStream());
        fr.addWindowListener(new WindowCloser());
        fr.getContentPane().add(l);
        l.setIcon(icon);
        Dimension d = fr.getToolkit().getScreenSize();
        fr.setSize(800 * d.width / d.height, 800);
        fr.setVisible(true);
        this.start();

    }

    private void switchDisplay() {
        if (fr.isVisible()) {
            fr.setVisible(false);
            fr.getContentPane().removeAll();
            win.getContentPane().removeAll();
            win.getContentPane().add(l);
            win.setSize(win.getToolkit().getScreenSize());
            win.setVisible(true);
            win.requestFocusInWindow();
        } else {
            win.setVisible(false);
            win.getContentPane().removeAll();
            fr.getContentPane().removeAll();
            fr.getContentPane().add(l);
            fr.setVisible(true);
            fr.requestFocus();
        }
    }

    public void run() {
        Dimension d = null;
        BufferedImage i = null;
        while (true) {
            try {
                d = fr.getContentPane().getSize();
                icon = (ImageIcon) ois.readObject();
                if (d == null || icon == null) {
                    continue;
                }
                if (d.width > 0 && d.height > 0 && (d.width != icon.getIconWidth() || d.height != icon.getIconHeight())) {
                    icon.setImage(icon.getImage().getScaledInstance(d.width, d.height, i.SCALE_FAST));
                }
                l.setIcon(icon);
                l.validate();
                fr.validate();
            } catch (Exception ex) {
                break;
            }
        }
    }

    class WindowCloser extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent we) {
            fr.setVisible(false);

        }
    }
    
    public JFrame getFrame(){
        return fr ;
    }

}
