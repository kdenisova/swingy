package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CreateHeroForm {
    private int attack, defense, hitPoints;
    private JFrame frame;
    private JComboBox heroClassBox;
    private JComboBox artefactBox;
    private JTextField nameField;
    private JTextField attackField;
    private JTextField defenseField;
    private JTextField hitField;
    private JLabel iconLabel;
    private HibernateManager hibernateManager;

    public void createHero(final HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;

        frame = new JFrame("Create a new Hero");
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

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 50, 100, 30);
        mainPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 50, 190, 30);
        mainPanel.add(nameField);

        JLabel heroClassLabel = new JLabel("Hero Class");
        heroClassLabel.setBounds(50, 100, 100, 30);
        mainPanel.add(heroClassLabel);

        heroClassBox = new JComboBox(HeroClass.values());
        heroClassBox.setBounds(150, 100, 190, 30);
        heroClassBox.setSelectedIndex(-1);
        heroClassBox.addActionListener(new HeroClassBoxListener());
        mainPanel.add(heroClassBox);

        JLabel artifactLabel = new JLabel("Artefact");
        artifactLabel.setBounds(50, 150, 100, 30);
        mainPanel.add(artifactLabel);

        artefactBox = new JComboBox(Artifact.values());
        artefactBox.setBounds(150, 150, 190, 30);
        artefactBox.setSelectedIndex(-1);
        artefactBox.addActionListener(new ArtefactBoxListener());
        mainPanel.add(artefactBox);

        iconLabel = new JLabel();
        mainPanel.add(iconLabel);

        JLabel attackLabel = new JLabel("Attack");
        attackLabel.setBounds(50, 200, 100, 30);
        mainPanel.add(attackLabel);

        attackField = new JTextField("0");
        attackField.setBounds(100, 200, 50, 30);
        attackField.setEnabled(false);
        mainPanel.add(attackField);

        JLabel defenseLabel = new JLabel("Defense");
        defenseLabel.setBounds(170, 200, 100, 30);
        mainPanel.add(defenseLabel);

        defenseField = new JTextField("0");
        defenseField.setBounds(230, 200, 50, 30);
        defenseField.setEnabled(false);
        mainPanel.add(defenseField);

        JLabel hitLabel = new JLabel("Hit Points");
        hitLabel.setBounds(300, 200, 100, 30);
        mainPanel.add(hitLabel);

        hitField = new JTextField("0");
        hitField.setBounds(370, 200, 50, 30);
        hitField.setEnabled(false);
        mainPanel.add(hitField);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 250, 100, 30);
        saveButton.addActionListener(new SaveButtonListener());
        buttonPanel.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 250, 100, 30);
        cancelButton.addActionListener(new CancelButtonListener());
        buttonPanel.add(cancelButton);

        frame.add(BorderLayout.CENTER, mainPanel);
        frame.add(BorderLayout.SOUTH, buttonPanel);
        frame.setBounds(50, 50,600, 400);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (nameField.getText().isEmpty() || heroClassBox.getSelectedIndex() < 0
                    || artefactBox.getSelectedIndex() < 0)
                JOptionPane.showMessageDialog(null, "Please complete all required fields",
                        "Error", JOptionPane.ERROR_MESSAGE);
            else {
                //HibernateManager hibernateManager = new HibernateManager();
                //hibernateManager.setUp();
                HeroClass heroClass = HeroClass.valueOf(heroClassBox.getSelectedItem().toString());
                Artifact artifact = Artifact.valueOf(artefactBox.getSelectedItem().toString());

                hibernateManager.saveHero(nameField.getText(), heroClass, artifact, Integer.parseInt(attackField.getText()),
                        Integer.parseInt(defenseField.getText()), Integer.parseInt(hitField.getText()));

                Hero hero = HeroFactory.getInstance().buildHero(hibernateManager.getNewHero());

                MainGameForm mainGameForm = new MainGameForm();
                mainGameForm.Start(hibernateManager, hero);

                frame.dispose();
            }

        }
    }

    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }

    class HeroClassBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BufferedImage img = null;
            String imageName = null;

            if (artefactBox.getSelectedIndex() >= 0)
                artefactBox.setSelectedIndex(-1);
            if (heroClassBox.getSelectedItem().equals(HeroClass.ELF)) {
                imageName = "/Users/angrynimfa/projects/swingy/src/main/resources/heroes/elf.png";
                attack = 100;
                defense = 50;
                hitPoints = 15;
                attackField.setText(String.valueOf(attack));
                defenseField.setText(String.valueOf(defense));
                hitField.setText(String.valueOf(hitPoints));
            } else if (heroClassBox.getSelectedItem().equals(HeroClass.DWARF)) {
                imageName = "/Users/angrynimfa/projects/swingy/src/main/resources/heroes/dwarf.png";
                attack = 110;
                defense = 60;
                hitPoints = 12;
                attackField.setText(String.valueOf(attack));
                defenseField.setText(String.valueOf(defense));
                hitField.setText(String.valueOf(hitPoints));
            }
            else {
                imageName = "/Users/angrynimfa/projects/swingy/src/main/resources/heroes/wizard.png";
                attack = 90;
                defense = 30;
                hitPoints = 10;
                attackField.setText(String.valueOf(attack));
                defenseField.setText(String.valueOf(defense));
                hitField.setText(String.valueOf(hitPoints));
            }

            try {
                assert imageName != null;
                img = ImageIO.read(new File(imageName));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            iconLabel.setBounds(400, 40, 150, 150);
            Image dimg = img.getScaledInstance(iconLabel.getWidth(), iconLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            iconLabel.setIcon(imageIcon);
        }
    }

    class ArtefactBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int value;

            if (artefactBox.getSelectedIndex() >= 0 && heroClassBox.getSelectedIndex() >= 0) {
                if (artefactBox.getSelectedItem().equals(Artifact.WEAPON)) {
                    value = attack + 10;
                    attackField.setText(String.valueOf(value));
                    defenseField.setText(String.valueOf(defense));
                    hitField.setText(String.valueOf(hitPoints));
                } else if (artefactBox.getSelectedItem().equals(Artifact.ARMOR)) {
                    value = defense + 10;
                    attackField.setText(String.valueOf(attack));
                    defenseField.setText(String.valueOf(value));
                    hitField.setText(String.valueOf(hitPoints));
                } else {
                    value = hitPoints + 10;
                    attackField.setText(String.valueOf(attack));
                    defenseField.setText(String.valueOf(defense));
                    hitField.setText(String.valueOf(value));
                }
            }
        }
    }
}
