import com.kdenisov.swingy.model.Director;
import com.kdenisov.swingy.model.Elf;
import com.kdenisov.swingy.model.ElfBuilder;

public class Swingy {
    public static void main(String[] args) {
        Director director = new Director();

        ElfBuilder builder = new ElfBuilder();
        director.constructElf(builder);

        Elf elf = builder.getResult();

        System.out.println("Elf " + elf.getName() + " created");
    }
}
