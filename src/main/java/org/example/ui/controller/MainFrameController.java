package org.example.ui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextArea;

import org.example.ui.view.MainFrame;

public class MainFrameController {

    private MainFrame mainFrame;
    private JButton welcomeBtn;
    private JTextArea welcomeTA;

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public JButton getWelcomeBtn() {
        return welcomeBtn;
    }

    public JTextArea getWelcomeTA() {
        return welcomeTA;
    }

    public MainFrameController() {
        initComponents();
        initListeners();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        welcomeBtn = mainFrame.getWelcomeBtn();
        welcomeTA = mainFrame.getWelcomeTA();
    }

    private void initListeners() {
        welcomeBtn.addActionListener(new WelcomeBtnLister());
    }

    private class WelcomeBtnLister implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            welcomeTA.append("Welcome IntelliJ IDEA Swing Creator\n");
        }
    }
}
