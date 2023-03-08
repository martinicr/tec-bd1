package tec.bd.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame  {

    AboutAppAction aboutAppAction = new AboutAppAction();

    public MainFrame() {

//        JMenuItem toastWheat = new JMenuItem("Wheat");
//        toastWheat.setMnemonic(KeyEvent.VK_W);
//        toastWheat.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
//        toastWheat.addActionListener( e -> System.out.println("wheat toast") );
//
//        JMenuItem toastWhite = new JMenuItem("White");
//        toastWhite.setMnemonic(KeyEvent.VK_I);
//        toastWhite.setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.SHIFT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK));
//        toastWhite.addActionListener( e -> System.out.println("white toast") );

//        JMenu toastMenu = new JMenu("Toast");
//        toastMenu.setMnemonic(KeyEvent.VK_T);
//        toastMenu.add(toastWheat);
//        toastMenu.add(toastWhite);


        JMenuItem byZipCode = new JMenuItem("By Zip Code");
        JMenuItem byCity = new JMenuItem("By City");

        JMenu weatherReportMenu = new JMenu("Weather Report");
        weatherReportMenu.add(byZipCode);
        weatherReportMenu.add(byCity);
//        weatherReportMenu.addSeparator();
//        weatherReportMenu.add(biscuit);
//        weatherReportMenu.add(toastMenu);

        JMenuItem aboutApp = new JMenuItem("About Weather Report");
        aboutApp.addActionListener(aboutAppAction);

        JMenu about = new JMenu("About");
        about.add(aboutApp);


        JMenuBar menubar = new JMenuBar();
        menubar.add(weatherReportMenu);
        menubar.add(about);

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        mainPanel.add(new JLabel("Weather Report"));


        this.setTitle("Weather Report");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(420, 420);
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setResizable(true);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        this.setJMenuBar(menubar);
    }

    class AboutAppAction extends AbstractAction {

        public AboutAppAction() {

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            new AboutAppFrame();
        }
    }
}
