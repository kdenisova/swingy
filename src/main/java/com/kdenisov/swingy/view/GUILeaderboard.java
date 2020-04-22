package com.kdenisov.swingy.view;

import com.kdenisov.swingy.controller.HibernateManager;
import com.kdenisov.swingy.model.Hero;
import com.kdenisov.swingy.model.HeroEntity;
import jdk.tools.jlink.internal.Jlink;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

public class GUILeaderboard {
    private JFrame frame;
    private String[] columnName = {"Rank", "Name", "Hero Class", "Level", "Experience"};
    private JTable table;
    private List<HeroEntity> heroEntities;

    public void uploadHeroList(final HibernateManager hibernateManager) {
        heroEntities = hibernateManager.getLeaderboard();

        if (heroEntities.size() == 0) {
            showMessage();
            return;
        }

        Collections.sort(heroEntities, new Comparator<HeroEntity>() {
            @Override
            public int compare(HeroEntity o1, HeroEntity o2) {
                return Integer.compare(o2.getExperience(), o1.getExperience());
            }
        });

        frame = new JFrame("Leaderboard");
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.setColumnIdentifiers(columnName);
        table = new JTable();
        table.setModel(model);
        TableCellRenderer cellRenderer = table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel)cellRenderer;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        for (int i = 0; i < heroEntities.size(); i++) {
            model.addRow(new Object[]{i + 1, heroEntities.get(i).getName(), heroEntities.get(i).getHeroClass(),
                    heroEntities.get(i).getLevel(), heroEntities.get(i).getExperience()});
        }

        frame.add(BorderLayout.CENTER, scrollPane);

        frame.setBounds(50, 50,600, 400);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize(); //Set a window on center of screen
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setVisible(true);
    }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, "No finished games found!",
                "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
    }

//    Comparator<HeroEntity> compareByExperience = new Comparator<HeroEntity>() {
//        @Override
//        public int compare(HeroEntity o1, HeroEntity o2) {
//            return o1.getExperience().;
//        }
//    };
}
