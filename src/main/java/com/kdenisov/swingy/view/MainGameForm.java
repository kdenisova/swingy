package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGameForm implements KeyListener {
    private Hero hero;
    private JLabel heroLabel;

    private int iconSize;
    private int mapSize;
    private JFrame frame;
    private JLabel[][] iconLabels;
    private JLabel experienceLabel;
    private Image heroImage;
    private HibernateManager hibernateManager;


    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    public void Start(final HibernateManager hibernateManager, Hero hero) {
        this.hibernateManager = hibernateManager;
        this.hero = hero;

        BufferedImage bufferedHeroImage = null;
        try {
            bufferedHeroImage = ImageIO.read(new File(hero.getHeroImage()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setIconSize(40);
        heroImage = bufferedHeroImage.getScaledInstance(getIconSize(), getIconSize() + 10, Image.SCALE_SMOOTH);
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

        mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

        GridLayout grid = new GridLayout(mapSize, mapSize);

        JPanel mapPanel = new JPanel(grid);

        iconLabels = new JLabel[mapSize][mapSize];
        BufferedImage bufferedMapImage = null;

        try {
            bufferedMapImage = ImageIO.read(new File("/Users/angrynimfa/projects/swingy/src/main/resources/grass.png"));
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

        hero.setX(mapSize / 2);
        hero.setY(mapSize / 2);
        iconLabels[hero.getY()][hero.getX()].add(heroLabel);
        iconLabels[hero.getY()][hero.getX()].setFocusable(true);
        iconLabels[hero.getY()][hero.getX()].addKeyListener(this);
        frame.add(BorderLayout.WEST, mapPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel("Name: " + hero.getName());
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel classLabel = new JLabel("Hero Class: " + hero.getHeroClass());
        classLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        experienceLabel = new JLabel();
        //nameLabel.setFont(new Font("Serif", Font.BOLD, 22));
        infoPanel.add(nameLabel);
        infoPanel.add(classLabel);
        infoPanel.add(experienceLabel);

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

        System.out.println(hero.getName() + " " + hero.getHeroClass());
        System.out.println("Map size = " + mapSize);
    }

    public void placeHero(int x, int y) {
        iconLabels[hero.getY()][hero.getX()].remove(heroLabel);

        frame.revalidate();
        frame.repaint();

        iconLabels[hero.getY()][hero.getX()].revalidate();
        iconLabels[hero.getY()][hero.getX()].repaint();

        hero.setX(x);
        hero.setY(y);

        heroLabel = new JLabel(new ImageIcon(heroImage));
        iconLabels[hero.getY()][hero.getX()].add(heroLabel);
        System.out.println(String.format("I'm here [%d; %d]", x, y));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN && hero.getY() < mapSize - 1) {
            placeHero(hero.getX(), hero.getY() + 1);
        } else if (e.getKeyCode() == KeyEvent.VK_UP && hero.getY() != 0) {
            placeHero(hero.getX(), hero.getY() - 1);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT && hero.getX() != 0) {
            placeHero(hero.getX() - 1, hero.getY());
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && hero.getX() < mapSize - 1) {
            placeHero(hero.getX() + 1, hero.getY());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           hibernateManager.updateHero(hero);
        }
    }

    class BackButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }
    }

}
