import com.kdenisov.swingy.model.*;

public class Swingy {
    public static void main(String[] args) throws SwingyException {
        Director director = new Director();

        HeroClass heroClass;
        String rawHeroClass = "Elf";
        String name;

        try {
            heroClass = HeroClass.valueOf(rawHeroClass.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SwingyException("Unknown hero class: " + rawHeroClass);
        }

        ElfBuilder builder = new ElfBuilder(); //always used ElfBuilder

        switch (heroClass) {
            case ELF:
                director.constructElf(builder, "Legolas");
                break;
            case DWARF:
                director.constructDwarf(builder, "Gimli");
                break;
            case HOBBIT:
                director.constructHobbit(builder, "Frodo");
                break;
        }

//        switch (heroClass) {
//            case ELF: {
//                ElfBuilder builder = new ElfBuilder();
//                director.constructElf(builder, "Legolas");
//            }
//            case DWARF: {
//                DwarfBuilder builder = new DwarfBuilder();
//                director.constructDwarf(builder, "Gimli");
//            }
//            case HOBBIT: {
//                HobbitBuilder builder = new HobbitBuilder();
//                director.constructHobbit(builder, "Frodo");
//            }
//        }

        Hero hero = builder.getResult();

        System.out.println("Hero " + hero.getHeroClass() + " " + hero.getName() + " created");
    }
}
