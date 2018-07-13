/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturemanagementdoctor.doctor;

//displayserver.DisplayServer.java
import java.net.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import javax.imageio.ImageIO;

public class DisplayServer extends Thread {

    public static boolean running;
    ServerSocket ss = null;
    ServerSocket admin = null;
    Vector<ClientThread> clientList = new Vector<ClientThread>();
    long sleepInterval = 125;

    public DisplayServer(long interval) throws Exception {
        ss = new ServerSocket(2020);
        admin = new ServerSocket(2021);
        sleepInterval = interval;
        this.setPriority(MIN_PRIORITY);
        this.start();
        startServer();
    }

    private void startServer() {
        Socket client = null;
        System.err.println("Starts listening for clients");
        ClientThread ct = null;
        while (true) {
            try {
                client = ss.accept();
                ct = new ClientThread(client);
                clientList.addElement(ct);
                ct.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void run() {
        String localHost = "192.168.1.60";
        try {
            localHost = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
        }
        while (true) {
            try {
                Socket ad = admin.accept();
                if (ad.getInetAddress().getHostAddress().equals(localHost)
                        && ad.getInputStream().read() == 'X') {
                    ss.close();
                    for (int i = 0, n = clientList.size(); i < n; i++) {
                        clientList.get(i).client.close();
                    }
                    System.exit(0);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void removeMe(Socket s) {
        clientList.removeElement(s);
    }

    class ClientThread extends Thread {

        Socket client = null;
        ObjectOutputStream os = null;
        InputStream is = null;

        public ClientThread(Socket s) {
            client = s;
            try {
                os = new ObjectOutputStream(s.getOutputStream());
                is = s.getInputStream();
                System.out.println("Client from " + s.getInetAddress().getHostAddress() + " connected");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        public void run() {
            java.awt.image.BufferedImage img = null;
            Robot r = null;
            try {
                r = new Robot();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rect = new Rectangle(0, 0, size.width, size.height);
            javax.swing.ImageIcon icon = null;

            while (true) {

                try {
                    System.gc();
                    if (!running) {
                         File file = new File("src/res/stuck.png");
                         System.out.println(file.getAbsolutePath());
                        img = ImageIO.read(new FileInputStream(file));
                    } else {
                        img = r.createScreenCapture(rect);
                    }
                    icon = new javax.swing.ImageIcon(img);

                    os.writeObject(icon);
                    os.flush();
                    icon = null;
                    System.gc();
                    try {
                        Thread.currentThread().sleep(sleepInterval);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    closeAll();
                    break;
                }
            }
        }

        private void closeAll() {
            DisplayServer.this.removeMe(client);
            try {
                os.close();
                is.close();
                client.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String arg[]) throws Exception {
        long interval = 80;

        new DisplayServer(interval);
    }
}
