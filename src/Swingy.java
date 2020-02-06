import com.kdenisov.swingy.model.*;

public class Swingy {
    public static void main(String[] args) throws SwingyException {
        Director director = new Director();

        String heroClass = "DWARF";

        ElfBuilder builder = new ElfBuilder();

        if (heroClass.equals(HeroClass.ELF.toString())) {
            director.constructElf(builder, "Legolas");
        } else if (heroClass.equals((HeroClass.DWARF.toString()))) {
            director.constructDwarf(builder, "Gimli");
        } else if (heroClass.equals(HeroClass.HOBBIT.toString())) {
            director.constructHobbit(builder, "Frodo");
        }
       else {
           throw new SwingyException("Unknown hero class");
        }

        Hero hero = builder.getResult();

        System.out.println("Hero " + hero.getName() + " created");
    }
}
