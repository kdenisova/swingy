package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Hero;
import com.kdenisov.swingy.model.HeroEntity;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainGameForm {
    private int iconSize;
    private int mapSize;
    private JFrame frame;
    private JLabel[][] iconLabels;

    public int getIconSize() {
        return iconSize;
    }

    public void setIconSize(int iconSize) {
        this.iconSize = iconSize;
    }

    public void Start(Hero hero) {
        setIconSize(40);
        frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);


        GridLayout grid = new GridLayout(mapSize, mapSize);

        JPanel mapPanel = new JPanel(grid);

        iconLabels = new JLabel[mapSize][mapSize];
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(new File("/Users/angrynimfa/projects/swingy/src/main/resources/grass.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Image image = bufferedImage.getScaledInstance(getIconSize(), getIconSize() + 10, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(image);

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                iconLabels[y][x] = new JLabel();
                iconLabels[y][x].setSize(getIconSize(),getIconSize());
                iconLabels[y][x].setLayout(new BorderLayout());
                iconLabels[y][x].setIcon(imageIcon);
                mapPanel.add(iconLabels[y][x]);
            }
        }
        //JLabel heroLabel = new JLabel(imageIconH);
        //iconLabels[mapSize / 2][mapSize / 2].setLayout(new BorderLayout());
        //iconLabels[mapSize / 2][mapSize / 2].add(heroLabel);//.setIcon(imageIconH);
        //mapPanel.add(iconLabels[mapSize / 2][mapSize / 2]);
        placeHero(hero);
        frame.add(BorderLayout.WEST, mapPanel);

        frame.setBounds(50, 50, mapSize * getIconSize() * 2, mapSize * getIconSize() + 50);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        System.out.println(hero.getName() + " " + hero.getHeroClass());
        System.out.println("Map size = " + mapSize);
    }

    public void placeHero(Hero hero) {
        BufferedImage bufferedImage = null;

        try {
            bufferedImage = ImageIO.read(new File(hero.getHeroImage()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Image image = bufferedImage.getScaledInstance(getIconSize(), getIconSize() + 10, Image.SCALE_SMOOTH);
        JLabel heroLabel = new JLabel(new ImageIcon(image));
        iconLabels[mapSize / 2][mapSize / 2].add(heroLabel);
    }

}
