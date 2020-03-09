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

        JLabel[][] iconLabels = new JLabel[mapSize][mapSize];
        BufferedImage img = null;
        BufferedImage heroImg = null;

        try {
            img = ImageIO.read(new File("/Users/angrynimfa/projects/swingy/src/main/resources/grass.png"));
            heroImg = ImageIO.read(new File("/Users/angrynimfa/projects/swingy/src/main/resources/elf.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Image dimg = img.getScaledInstance(getIconSize(), getIconSize() + 10, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);

        Image dimgH = heroImg.getScaledInstance(getIconSize(), getIconSize() + 10, Image.SCALE_SMOOTH);
        ImageIcon imageIconH = new ImageIcon(dimgH);

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                iconLabels[y][x] = new JLabel();
                iconLabels[y][x].setSize(getIconSize(),getIconSize());
                iconLabels[y][x].setIcon(imageIcon);
                mapPanel.add(iconLabels[y][x]);
            }
        }

        iconLabels[mapSize / 2][mapSize / 2].setIcon(imageIconH);
        mapPanel.add(iconLabels[mapSize / 2][mapSize / 2]);

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

    class ImageRenderer extends DefaultTableCellRenderer {
        JLabel label = new JLabel();

        //ImageIcon icon = new ImageIcon("/Users/angrynimfa/projects/swingy/src/main/resources/elf.jpg");

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            label.setIcon((ImageIcon)value);
            return label;
        }
    }
}
