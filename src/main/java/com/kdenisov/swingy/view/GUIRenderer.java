package com.kdenisov.swingy.view;

import javax.swing.*;

public class GUIRenderer implements Renderer {
    JFrame frame;

    @Override
    public void renderMap() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    @Override
    public void renderHero() {

    }

    @Override
    public void renderVillain() {

    }
}
