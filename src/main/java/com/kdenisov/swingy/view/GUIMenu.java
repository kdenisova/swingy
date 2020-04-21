package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.HibernateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIMenu {
    private HibernateManager hibernateManager;
    private Renderer renderer;
    private JFrame frame;


    public void showMenu(final HibernateManager hibernateManager, Renderer renderer) {
        this.hibernateManager = hibernateManager;
        this.renderer = renderer;

        frame = new JFrame("Swingy");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                hibernateManager.tearDown();
                frame.dispose();
                System.exit(0);
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
       // panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameButtonListener());

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ContinueButtonListener());

        JButton aboutButton = new JButton("About");
        aboutButton.addActionListener(new AboutButtonListener());

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());

        panel.add(newGameButton, gbc);
        panel.add(continueButton, gbc);
        panel.add(aboutButton, gbc);
        panel.add(exitButton, gbc);

        frame.add(panel);
        frame.setBounds(50, 50,300, 300);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    class NewGameButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GUINewGame newGame = new GUINewGame();
            newGame.createHero(hibernateManager, renderer);
            frame.dispose();
        }
    }

    class ContinueButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GUIContinue aContinue = new GUIContinue();
            aContinue.uploadHeroList(hibernateManager, renderer);
            frame.dispose();
        }
    }

    class AboutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ExitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            hibernateManager.tearDown();
        }
    }
}
