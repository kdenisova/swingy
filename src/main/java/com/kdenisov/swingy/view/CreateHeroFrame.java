package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Artefact;
import com.kdenisov.swingy.model.HeroClass;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class CreateHeroFrame {
    JFrame frame;

    public void createHero() {
        frame = new JFrame("Create a new Hero");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JButton saveButton = new JButton("Save");
        //saveButton.addActionListener(new GUIRenderer.NewGameButtonListener());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setSize(100, 30);
        nameLabel.setLocation(50, 50);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setSize(190, 30);
        nameField.setLocation(150, 50);
        frame.add(nameField);

        JLabel classLabel = new JLabel("Hero Class");
        classLabel.setSize(100, 30);
        classLabel.setLocation(50, 100);
        frame.add(classLabel);

        JComboBox classBox = new JComboBox(HeroClass.values());
        classBox.setSize(190, 30);
        classBox.setLocation(150, 100);
        frame.add(classBox);

        JLabel artifactLabel = new JLabel("Artefact");
        artifactLabel.setSize(100, 30);
        artifactLabel.setLocation(50, 150);
        frame.add(artifactLabel);

        JComboBox artefactBox = new JComboBox(Artefact.values());
        artefactBox.setSize(190, 30);
        artefactBox.setLocation(150, 150);
        frame.add(artefactBox);

        ImageIcon heroIcon = new ImageIcon("/Users/angrynimfa/Downloads/elf11.jpg");
        JLabel iconLabel = new JLabel(heroIcon);
        iconLabel.setSize(200, 200);
        iconLabel.setLocation(370, 30);
        frame.add(iconLabel);

        JLabel attackLabel = new JLabel("Attack");
        attackLabel.setSize(100, 30);
        attackLabel.setLocation(50, 250);
        frame.add(attackLabel);

        JTextField attackField = new JTextField("0");
        attackField.setSize(50, 30);
        attackField.setLocation(100, 250);
        attackField.setEnabled(false);
        frame.add(attackField);

        JLabel defenseLabel = new JLabel("Defense");
        defenseLabel.setSize(100, 30);
        defenseLabel.setLocation(170, 250);
        frame.add(defenseLabel);

        JTextField defenseField = new JTextField("0");
        defenseField.setSize(50, 30);
        defenseField.setLocation(230, 250);
        defenseField.setEnabled(false);
        frame.add(defenseField);

        JLabel hitLabel = new JLabel("Hit Points");
        hitLabel.setSize(100, 30);
        hitLabel.setLocation(300, 250);
        frame.add(hitLabel);

        JTextField hitField = new JTextField("0");
        hitField.setSize(50, 30);
        hitField.setLocation(370, 250);
        hitField.setEnabled(false);
        frame.add(hitField);

        JPanel panel = new JPanel();
        //panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       // gbc.gridwidth = GridBagConstraints.BOTH;
        //gbc.fill = GridBagConstraints.SOUTH;
       // gbc.anchor = GridBagConstraints.PAGE_END;
        panel.add(saveButton);
        panel.add(cancelButton);

        frame.add(panel, BorderLayout.SOUTH);
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
}
