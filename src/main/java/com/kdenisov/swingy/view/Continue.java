package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.model.Hero;
import com.kdenisov.swingy.model.HeroEntity;
import com.kdenisov.swingy.model.HeroFactory;
import com.kdenisov.swingy.model.HibernateManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Continue implements ActionListener {
    private JFrame frame;
    private String[] columnName = {"Name", "Hero Class", "Level", "Experience"};
    private JTable table;
    private JButton continueButton;
    private JButton cancelButton;
    private List<HeroEntity> heroEntities;
    private HibernateManager hibernateManager;

    public void UploadHeroList(final HibernateManager hibernateManager) {
        this.hibernateManager = hibernateManager;
        frame = new JFrame("Load the Game");
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

        heroEntities = hibernateManager.getListHeroes();

        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columnName);
        table = new JTable();
        table.setModel(model);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (e.getClickCount() == 2 && table.getSelectedRow() >= 0) {
                    Hero hero = HeroFactory.getInstance().buildHero(heroEntities.get(table.getSelectedRow()));
                    hero.setArtifacts(hibernateManager.getListArtifacts(hero.getId()));
                    System.out.println(hero.getName() + " " + hero.getHeroClass());
                    GameEngine gameEngine = new GameEngine(hibernateManager, hero);
                    gameEngine.continueGame();
                    //frame.dispose();
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < heroEntities.size(); i++) {
            model.addRow(new Object[]{heroEntities.get(i).getName(), heroEntities.get(i).getHeroClass(),
                    heroEntities.get(i).getLevel(), heroEntities.get(i).getExperience()});
        }

        frame.add(BorderLayout.CENTER, scrollPane);

        JPanel buttonPanel = new JPanel();

        continueButton = new JButton("Continue");
        continueButton.addActionListener(this);
        buttonPanel.add(continueButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);

        frame.add(BorderLayout.SOUTH, buttonPanel);

        frame.setBounds(50, 50,600, 400);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueButton) {
            if (table.getSelectedRow() >= 0) {
                Hero hero = HeroFactory.getInstance().buildHero(heroEntities.get(table.getSelectedRow()));
                hero.setArtifacts(hibernateManager.getListArtifacts(hero.getId()));
                System.out.println(hero.getName() + " " + hero.getHeroClass());
                GameEngine gameEngine = new GameEngine(hibernateManager, hero);
                gameEngine.continueGame();
                //gameEngine.play();
                //frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Please choose a hero",
                        "Choose a hero", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            frame.dispose();
        }
    }

    /*
    class ContinueButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class CancelButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }
     */
}
