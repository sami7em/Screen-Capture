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
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Border;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Haiderm
 */
public class ScreenCaptureBorderLayout {

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        JPanel container = new JPanel();
        JPanel main = new JPanel();
        JPanel status = new JPanel();
        JLabel statusBar = new JLabel("Testing.....");
        
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        main.setLayout(new BoxLayout(main, BoxLayout.LINE_AXIS));
        status.setLayout(new BoxLayout(status, BoxLayout.LINE_AXIS));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(container);
        container.add(main);
        container.add(status);

        //status.add(statusBar, BorderLayout.EAST);
        container.setBackground(Color.green);
        main.setBackground(Color.red);
        status.setBackground(Color.yellow);
        
        frame.setSize(500, 500);
        frame.setVisible(true);
        
        

    } // main

    public static void captureScreen() throws AWTException, IOException {
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture, "bmp", new File("C:\\Users\\Haiderm\\Desktop\\img.bmp"));
    }

} // class
