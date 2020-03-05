package com.kdenisov.swingy.view;

import com.kdenisov.swingy.model.Hero;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainGameForm {
    private int mapSize;

    private JFrame frame;

    public void Start(Hero hero) {
        frame = new JFrame("Swingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        mapSize = (hero.getLevel() - 1) * 5 + 10 - (hero.getLevel() % 2);

        DefaultTableModel model = new DefaultTableModel();
        JTable map = new JTable(mapSize, mapSize);
        map.setModel(model);
        map.setOpaque(false);
        map.setBorder(BorderFactory.createCompoundBorder());
        map.setForeground(Color.BLACK);
        //map.getColumnModel().getColumn(0).setCellRenderer(new ImageRenderer());
        //map.setGridColor(Color.BLACK);
        map.setShowGrid(true);
        JScrollPane scrollPane = new JScrollPane(map);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        final ImageIcon icon = new ImageIcon("/Users/angrynimfa/projects/swingy/src/main/resources/grass1.jpg");
        JPanel background = new JPanel(new BorderLayout()) {
          @Override
          protected void paintComponent(Graphics g) {
              super.paintComponent(g);
              g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
          }
        };
        background.add(scrollPane);
        //scrollPane.getViewport().setBackground(Color.getHSBColor(107, 142, 35));
        //scrollPane.setBackground(Color.BLACK);
        frame.add(BorderLayout.WEST, background);

        frame.setBounds(50, 50,1000, 500);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);

        System.out.println(hero.getName() + " " + hero.getHeroClass());
        System.out.println("Map size = " + mapSize);
    }

//    class ImageRenderer extends DefaultTableCellRenderer {
//        JLabel label = new JLabel();
//
//        ImageIcon icon = new ImageIcon("/Users/angrynimfa/projects/swingy/src/main/resources/elf.jpg");
//
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            label.setIcon(icon);
//            return label;
//        }
//    }
}
