package tec.bd.gui;

import javax.swing.*;

public class AboutAppFrame extends JFrame {

    public AboutAppFrame() {

        JLabel aboutLbl = new JLabel("Weather Report Made by martinicr");

        this.setSize(250, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(aboutLbl);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
    }
}
