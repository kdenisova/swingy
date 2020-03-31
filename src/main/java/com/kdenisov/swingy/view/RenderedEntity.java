package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Villain;

import javax.swing.*;
import java.awt.*;

public class RenderedEntity {
    private Villain villain;
    private JLabel label;
    private Image image;

    public RenderedEntity(Villain villain, JLabel label, Image image) {
        this.villain = villain;
        this.label = label;
        this.image = image;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public Image getImage() {
        return image;
    }

    public Villain getVillain() {
        return villain;
    }

    public void setVillain(Villain villain) {
        this.villain = villain;
    }
}
