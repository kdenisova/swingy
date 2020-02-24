package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Artefact;
import com.kdenisov.swingy.model.HeroClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.parser.Parser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class CreateHeroFrame {
    private int attack, defense, hitPoints;
    private JFrame frame;
    private JComboBox heroClassBox;
    private JComboBox artefactBox;
    private JTextField attackField;
    private JTextField defenseField;
    private JTextField hitField;

    public void createHero() {
        frame = new JFrame("Create a new Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 50, 100, 30);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 190, 30);
        frame.add(nameField);

        JLabel heroClassLabel = new JLabel("Hero Class");
        heroClassLabel.setBounds(50, 100, 100, 30);
        frame.add(heroClassLabel);

        heroClassBox = new JComboBox(HeroClass.values());
        heroClassBox.setBounds(150, 100, 190, 30);
        heroClassBox.setSelectedIndex(-1);
        heroClassBox.addActionListener(new HeroClassBoxListener());
        frame.add(heroClassBox);

        JLabel artifactLabel = new JLabel("Artefact");
        artifactLabel.setBounds(50, 150, 100, 30);
        frame.add(artifactLabel);

        artefactBox = new JComboBox(Artefact.values());
        artefactBox.setBounds(150, 150, 190, 30);
        artefactBox.setSelectedIndex(-1);
        artefactBox.addActionListener(new ArtefactBoxListener());
        frame.add(artefactBox);

        ImageIcon heroIcon = new ImageIcon("/Users/angrynimfa/projects/swingy/src/main/resources/elf4.jpg");//"/Users/angrynimfa/Downloads/elf11.jpg");
        JLabel iconLabel = new JLabel(heroIcon);
        iconLabel.setBounds(400, 45, 150, 150);
        frame.add(iconLabel);

        JLabel attackLabel = new JLabel("Attack");
        attackLabel.setBounds(50, 200, 100, 30);
        frame.add(attackLabel);

        attackField = new JTextField("0");
        attackField.setBounds(100, 200, 50, 30);
        attackField.setEnabled(false);
        frame.add(attackField);

        JLabel defenseLabel = new JLabel("Defense");
        defenseLabel.setBounds(170, 200, 100, 30);
        frame.add(defenseLabel);

        defenseField = new JTextField("0");
        defenseField.setBounds(230, 200, 50, 30);
        defenseField.setEnabled(false);
        frame.add(defenseField);

        JLabel hitLabel = new JLabel("Hit Points");
        hitLabel.setBounds(300, 200, 100, 30);
        frame.add(hitLabel);

        hitField = new JTextField("0");
        hitField.setBounds(370, 200, 50, 30);
        hitField.setEnabled(false);
        frame.add(hitField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 250, 100, 30);
        frame.add(saveButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(250, 250, 100, 30);
        cancelButton.addActionListener(new CancelButtonListener());
        frame.add(cancelButton);

//        JPanel panel = new JPanel();
//        //panel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//       // gbc.gridwidth = GridBagConstraints.BOTH;
//        //gbc.fill = GridBagConstraints.SOUTH;
//       // gbc.anchor = GridBagConstraints.PAGE_END;
//        panel.add(saveButton);
//        panel.add(cancelButton);
//
//        frame.add(panel, BorderLayout.SOUTH);
        frame.setBounds(50, 50,600, 400);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
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
            if (artefactBox.getSelectedIndex() >= 0)
                artefactBox.setSelectedIndex(-1);
            if (heroClassBox.getSelectedItem().equals(HeroClass.ELF)) {
                attack = 100;
                defense = 50;
                hitPoints = 15;
                attackField.setText(String.valueOf(attack));
                defenseField.setText(String.valueOf(defense));
                hitField.setText(String.valueOf(hitPoints));
            } else if (heroClassBox.getSelectedItem().equals(HeroClass.DWARF)) {
                attack = 110;
                defense = 60;
                hitPoints = 12;
                attackField.setText(String.valueOf(attack));
                defenseField.setText(String.valueOf(defense));
                hitField.setText(String.valueOf(hitPoints));
            }
            else {
                attack = 90;
                defense = 30;
                hitPoints = 10;
                attackField.setText(String.valueOf(attack));
                defenseField.setText(String.valueOf(defense));
                hitField.setText(String.valueOf(hitPoints));
            }
        }
    }

    class ArtefactBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int value;

            if (artefactBox.getSelectedIndex() >= 0) {
                if (artefactBox.getSelectedItem().equals(Artefact.WEAPON)) {
                    value = attack + 10;
                    attackField.setText(String.valueOf(value));
                    defenseField.setText(String.valueOf(defense));
                    hitField.setText(String.valueOf(hitPoints));
                } else if (artefactBox.getSelectedItem().equals(Artefact.ARMOR)) {
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
