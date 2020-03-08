package com.kdenisov.swingy.model;

public final class HeroFactory {
    private static final HeroFactory _instance = new HeroFactory();

    public static HeroFactory getInstance() {
        return _instance;
    }

    public Hero buildHero(HeroEntity heroEntity) {
        Hero hero = null;

        switch (heroEntity.getHeroClass()) {
            case ELF:
                ElfBuilder eb = new ElfBuilder();
                Director.constructElf(eb, heroEntity);
                hero = eb.getResult();
                break;
            case DWARF:
                DwarfBuilder db = new DwarfBuilder();
                Director.constructDwarf(db, heroEntity);
                hero = db.getResult();
                break;
            case WIZARD:
                WizardBuilder hb = new WizardBuilder();
                Director.constructWizard(hb, heroEntity);
                hero = hb.getResult();
                break;
        }

        //System.out.println("Hero " + hero.getHeroClass() + " " + hero.getName() + " created");

        return hero;
    }
}
