package screen.capture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ScreenCapture {

    public static String desktop = System.getProperty("user.home") + "\\Desktop\\";
    public static String filename = "";
    
    public static void main(String args[]) {
        Frame f = new Frame();
        FlowLayout layout = new FlowLayout();
        Button capture = new Button("Capture");
        Button open = new Button("Open in Paint");
        Label file = new Label("Filename: ");
        TextField tfFilename = new TextField("Image1");
        Label status = new Label("Press capture");
        

        f.setLayout(layout);
        f.setSize(200, 200);
        f.add(capture);
        f.add(open);
        open.setEnabled(false);
        f.add(file);
        f.add(tfFilename);
        f.add(status);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        capture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    filename = tfFilename.getText() + ".bmp";
                    System.out.println("Button Pressed");
                    f.setState(Frame.ICONIFIED);
                    captureScreen();
                    f.setState(Frame.NORMAL);
                    status.setText("  Capture successful");
                    open.setEnabled(true);
                } catch (AWTException ex) {
                    Logger.getLogger(ScreenCapture.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ScreenCapture.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Process p = Runtime.getRuntime().exec("C:\\Windows\\System32\\mspaint.exe " + desktop + filename);
                    status.setText("");
                    
                } catch (IOException ex) {
                    Logger.getLogger(ScreenCapture.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
        
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
        
        tfFilename.addFocusListener(new java.awt.event.FocusAdapter() {
    public void focusGained(java.awt.event.FocusEvent evt) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                tfFilename.selectAll();
            }
        });
    }
});

    } // main

    public static void captureScreen() throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "bmp", new File(desktop + filename));
    }
}
