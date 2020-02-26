package com.kdenisov.swingy.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIRenderer implements Renderer {
    JFrame frame;

    @Override
    public void renderMenu() {
        frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

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
            CreateHeroForm createHeroForm = new CreateHeroForm();
            createHeroForm.createHero();
        }
    }

    class ContinueButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

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
        }
    }

    @Override
    public void renderHero() {

    }

    @Override
    public void renderVillain() {

    }
}
