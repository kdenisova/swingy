package com.kdenisov.swingy.view;

import javax.swing.*;
import java.awt.*;

public class GUIRenderer implements Renderer {
    JFrame frame;

    @Override
    public void renderMap() {
        frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
       // panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        JButton newGame = new JButton("New Game");
        JButton savedGame = new JButton("Continue");
        JButton about = new JButton("About");
        JButton exit = new JButton("Exit");

        panel.add(newGame, gbc);
        panel.add(savedGame, gbc);
        panel.add(about, gbc);
        panel.add(exit, gbc);

        frame.add(panel);
        frame.setBounds(50, 50,300, 300);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    @Override
    public void renderHero() {

    }

    @Override
    public void renderVillain() {

    }
}
