package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Hero;
import com.kdenisov.swingy.model.HibernateSetUp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoadGameForm {
    private JFrame frame;
    private String[] columnName = {"Name", "Hero Class", "Level", "Experience"};
    private JTable table;
    private List<Hero> heroes;

    public void UploadHeroList () {
        frame = new JFrame("Load the Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        HibernateSetUp hibernateSetUp = new HibernateSetUp();
        hibernateSetUp.setUp();
        heroes = hibernateSetUp.uploadHeroes();

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        table = new JTable();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        //table.setBounds(50, 50, 200, 300);
        for (int i = 0; i < heroes.size(); i++) {
            model.addRow(new Object[]{heroes.get(i).getName(), heroes.get(i).getHeroClass(),
            heroes.get(i).getLevel(), heroes.get(i).getExperience()});
        }

        frame.add(BorderLayout.CENTER, scrollPane);

        JPanel buttonPanel = new JPanel();

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ContinueButtonListener());
        buttonPanel.add(continueButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonListener());
        buttonPanel.add(cancelButton);

        frame.add(BorderLayout.SOUTH, buttonPanel);

        frame.setBounds(50, 50,600, 400);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

    }

    class ContinueButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (table.getSelectedRow() >= 0) {
                MainGameForm mainGameForm = new MainGameForm();
                mainGameForm.Start(heroes.get(table.getSelectedRow()));
                frame.dispose();
            }
            else {
                JOptionPane.showMessageDialog(null, "Please choose a hero",
                        "Choose a hero", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
}
