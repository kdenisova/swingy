import com.kdenisov.swingy.model.*;
import com.kdenisov.swingy.view.GUIRenderer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Swingy {

    public static void main(String[] args) throws SwingyException {
        HeroClass heroClass;
        String rawHeroClass = "Elf";
        String name;


        if (args.length == 0) {
            System.out.println("usage: java -jar swingy.jar [console/gui]");
            System.exit(1);
        }
        HibernateManager hibernateManager = HibernateManager.getHibernateManager();

        if (args[0].equals("gui")) {
            GUIRenderer gui = new GUIRenderer();
            gui.renderMenu(hibernateManager);
        }

        try {
            heroClass = HeroClass.valueOf(rawHeroClass.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SwingyException("Unknown hero class: " + rawHeroClass);
        }
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

            String sql = "CREATE TABLE HERO " +
                    "(ID INTEGER not NULL, " +
                    " Name VARCHAR(255), " +
                    " Class VARCHAR(255), " +
                    " Level INTEGER, " +
                    " Experience INTEGER, " +
                    " Attack INTEGER, " +
                    " Defense INTEGER, " +
                    " HitPoints INTEGER, " +
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
