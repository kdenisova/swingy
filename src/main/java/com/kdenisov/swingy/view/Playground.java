package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.GameEngine;
import com.kdenisov.swingy.controller.HeroMove;
import com.kdenisov.swingy.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class Playground implements KeyListener {
    private JLabel heroLabel;

    private int iconSize;
    private int mapSize;
    private GameEngine game;
    private JFrame frame;
    private JLabel[][] iconLabels;
    private JLabel experienceLabel;
    private Image heroImage;
    private HibernateManager hibernateManager;
    private List<RenderedEntity> renderedEntities;

    public Playground(HibernateManager hibernateManager, int mapSize, GameEngine game) {
        this.hibernateManager = hibernateManager;
        this.mapSize = mapSize;
        this.game = game;
    }

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }


    public void render() {
        renderedEntities = new ArrayList<>();

        BufferedImage bufferedHeroImage = null;
        try {
            bufferedHeroImage = ImageIO.read(getClass().getResource("/heroes/" + game.getHero().getHeroClass() + ".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setIconSize(40);
        heroImage = bufferedHeroImage.getScaledInstance(getIconSize(), getIconSize(), Image.SCALE_SMOOTH);
        heroLabel = new JLabel(new ImageIcon(heroImage));

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

        GridLayout grid = new GridLayout(mapSize, mapSize);

        JPanel mapPanel = new JPanel(grid);

        iconLabels = new JLabel[mapSize][mapSize];
        BufferedImage bufferedMapImage = null;

        try {
            bufferedMapImage = ImageIO.read(getClass().getResource("/grass.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Image mapImage = bufferedMapImage.getScaledInstance(getIconSize(), getIconSize() + 10, Image.SCALE_SMOOTH);
        ImageIcon mapIcon = new ImageIcon(mapImage);

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                iconLabels[y][x] = new JLabel();
                iconLabels[y][x].setSize(getIconSize(),getIconSize());
                iconLabels[y][x].setLayout(new BorderLayout());
                iconLabels[y][x].setIcon(mapIcon);
                mapPanel.add(iconLabels[y][x]);
            }
        }

        iconLabels[game.getHero().getY()][game.getHero().getX()].add(heroLabel);
        iconLabels[game.getHero().getY()][game.getHero().getX()].setFocusable(true);
        iconLabels[game.getHero().getY()][game.getHero().getX()].addKeyListener(this);

        renderVillians();

        frame.add(BorderLayout.WEST, mapPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name: " + game.getHero().getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel classLabel = new JLabel("Hero Class: " + game.getHero().getHeroClass());
        classLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel levelLabel = new JLabel("Level: " + game.getHero().getLevel());
        levelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        experienceLabel = new JLabel("Experience: " + game.getHero().getExperience());
        experienceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel attackLabel = new JLabel("Attack: " + game.getHero().getAttack());
        attackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel defenseLabel = new JLabel("Defense: " + game.getHero().getDefense());
        defenseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel hitLabel = new JLabel("Hit Points: " + game.getHero().getHitPoints());
        hitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //nameLabel.setFont(new Font("Serif", Font.BOLD, 22));
        infoPanel.add(nameLabel);
        infoPanel.add(classLabel);
        infoPanel.add(experienceLabel);
        infoPanel.add(attackLabel);
        infoPanel.add(defenseLabel);
        infoPanel.add(hitLabel);

        frame.add(BorderLayout.CENTER, infoPanel);

        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        saveButton.setFocusable(false);
        saveButton.addActionListener(new SaveButtonListener());
        JButton backButton = new JButton("Back");
        backButton.setFocusable(false);
        backButton.addActionListener(new BackButtonListener());

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        frame.add(BorderLayout.SOUTH, buttonPanel);

        frame.setBounds(50, 50, mapSize * getIconSize() * 2, mapSize * getIconSize() + 50);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        System.out.println(game.getHero().getName() + " " + game.getHero().getHeroClass());
        System.out.println("Map size = " + mapSize);
    }

    public int chooseAction(int y, int x) {
        RenderedEntity entity = null;

        for (int i = 0; i < renderedEntities.size(); i++) {
            if (y == renderedEntities.get(i).getVillain().getY() && x == renderedEntities.get(i).getVillain().getX()) {
                entity = renderedEntities.get(i);
                break;
            }
        }
        ImageIcon icon = new ImageIcon(entity.getImage());

        Object[] options = {"Run", "Fight"};
        int result = JOptionPane.showOptionDialog(null,
                entity.getVillain().getName() + "\n" + "Level " + entity.getVillain().getLevel(),
                "Fight or run?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                options,
                options[0]);
        return result;
    }

    public void showMessageDialog() {
        System.out.println("Showing combat dialog");
        String[] buttons = {"Fight", "Run"};
        JOptionPane.showMessageDialog(
                null,
                "Villain are here", "Fight or run?"
                ,
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void renderHero(int oldX, int oldY, int newX, int newY) {
        iconLabels[oldY][oldX].remove(heroLabel);

        frame.revalidate();
        frame.repaint();

        iconLabels[oldY][oldX].revalidate();
        iconLabels[oldY][oldX].repaint();

        heroLabel = new JLabel(new ImageIcon(heroImage));
        iconLabels[newY][newX].add(heroLabel);
        //System.out.println(String.format("I'm here [%d; %d]", newX, newY));
    }

    public void renderVillians() {
        BufferedImage bufferedImage = null;
        Image villainImage;
        JLabel villainLabel;

        for (int i = 0; i < game.getVillains().size(); i++) {

            try {
                bufferedImage = ImageIO.read(getClass().getResource("/villains/" +
                        game.getVillains().get(i).getName().toLowerCase() + ".png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            villainImage = bufferedImage.getScaledInstance(getIconSize(), getIconSize(), Image.SCALE_SMOOTH);
            villainLabel = new JLabel(new ImageIcon(villainImage));

            iconLabels[game.getVillains().get(i).getY()][game.getVillains().get(i).getX()].add(villainLabel);

            renderedEntities.add(new RenderedEntity(game.getVillains().get(i), villainLabel, villainImage));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (game.isStatus()) {
            if (e.getKeyCode() == KeyEvent.VK_DOWN && game.getHero().getY() < mapSize - 1) {
                game.heroMoved(HeroMove.DOWN);
            } else if (e.getKeyCode() == KeyEvent.VK_UP && game.getHero().getY() != 0) {
                game.heroMoved(HeroMove.UP);
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT && game.getHero().getX() != 0) {
                game.heroMoved(HeroMove.LEFT);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && game.getHero().getX() < mapSize - 1) {
                game.heroMoved(HeroMove.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           hibernateManager.updateHero(game.getHero());
        }
    }

    class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }

}
