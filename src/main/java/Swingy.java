import com.kdenisov.swingy.model.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Swingy {
    public static void main(String[] args) throws SwingyException {
        HeroClass heroClass;
        String rawHeroClass = "Elf";
        String name;

        try {
            heroClass = HeroClass.valueOf(rawHeroClass.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SwingyException("Unknown hero class: " + rawHeroClass);
        }

        Hero hero = null;

        switch (heroClass) {
            case ELF:
                ElfBuilder eb = new ElfBuilder();
                Director.constructElf(eb, "Legolas");
                hero = eb.getResult();
                break;
            case DWARF:
                DwarfBuilder db = new DwarfBuilder();
                Director.constructDwarf(db, "Gimli");
                hero = db.getResult();
                break;
            case HOBBIT:
                HobbitBuilder hb = new HobbitBuilder();
                Director.constructHobbit(hb, "Frodo");
                hero = hb.getResult();
                break;
        }

        System.out.println("Hero " + hero.getHeroClass() + " " + hero.getName() + " created");

        Swingy s = new Swingy();
        s.connectToMySQL();
    }

    public void connectToMySQL() {
        System.out.println("Trying to connect...");
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swingy",
                "root", "nimfa")) {
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
