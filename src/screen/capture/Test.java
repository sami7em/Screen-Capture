/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen.capture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Haiderm
 */
public class Test {

    public static void main(String args[]) {
        Frame f = new Frame();
        BoxLayout layout = new BoxLayout(f, BoxLayout.Y_AXIS);
        final JFileChooser fc = new JFileChooser();
        File file = new File("C:\\Users\\Haiderm\\Desktop\\img.bmp");

        JButton capture = new JButton("Capture");
        capture.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel status = new JLabel("Press capture");
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel save = new JLabel("Save to: ");
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea saveLocation = new JTextArea("C:\\Users\\Haiderm\\Desktop");
        saveLocation.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton browse = new JButton ("Browse");
        browse.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        f.setLayout(layout);
        f.setSize(500, 500);
        f.add(capture);
        f.add(status);
        f.add(save);
        f.add(saveLocation);
        f.add(browse);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        capture.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    System.out.println("Button Pressed");
                    f.setState(Frame.ICONIFIED);
                    captureScreen(file);
                    f.setState(Frame.NORMAL);
                    status.setText("  Capture successful");
                } catch (AWTException ex) {
                    Logger.getLogger(ScreenCapture.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ScreenCapture.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }); // capture listener
        
        browse.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

        int returnVal = fc.showOpenDialog(f);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //file = fc.getSelectedFile();
            }
            }}); // browse button listener
        
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        }); // window listener

    } // main

    public static void captureScreen(File file) throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "bmp", file);
    } // captureScreen

} // class
