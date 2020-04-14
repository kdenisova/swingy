package com.kdenisov.swingy;

import com.kdenisov.swingy.controller.HibernateManager;
import com.kdenisov.swingy.view.GUIRenderer;
import com.kdenisov.swingy.view.Renderer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Swingy {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("usage: java -jar swingy.jar [console/gui]");
            System.exit(1);
        }

//        com.kdenisov.swingy.Swingy s = new com.kdenisov.swingy.Swingy();
//        s.connectToMySQL();
        HibernateManager hibernateManager = HibernateManager.getHibernateManager();
        Renderer renderer = null;
        if (args[0].equals("gui")) {
            renderer = new GUIRenderer();
            //GUIRenderer gui = new GUIRenderer();
            //gui.renderMenu(hibernateManager);
        } else if (args[0].equals("console")) {
            //console mode
        }
        else {
            System.out.println("usage: java -jar swingy.jar [console/gui]");
            System.exit(1);
        }

        renderer.renderMenu(hibernateManager);
    }

    public void connectToMySQL() {
        System.out.println("Trying to connect...");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swingy",
                    "root", "nimfa");
            if (connection != null) {
                System.out.println("Connection Successful");
            }
            else {
                System.out.println("Connection Failed");
            }

            Statement statement = null;
            statement = connection.createStatement();

            String sql = "CREATE TABLE VILLAIN " +
                    "(ID INTEGER not NULL, " +
                    " Name VARCHAR(255), " +
                    " Level INTEGER, " +
                    " Attack INTEGER, " +
                    " Defense INTEGER, " +
                    " HitPoints INTEGER, " +
                    " Picture VARCHAR(255), " +
                    " PRIMARY KEY ( ID ))";

            statement.executeUpdate(sql);
            System.out.println("Table created");

//            boolean isValid = connection.isValid(0);
//            assert(isValid);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
