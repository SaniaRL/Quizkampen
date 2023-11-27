package Enums;

public enum ImageIconAvatar {
    MONKEY("Icons/apa.png"),
    CRAB("Icons/crab.png"),
    ELEPHANT("Icons/elefant.png"),
    PIG("Icons/gris.png"),
    BUNNY("Icons/kanin.png"),
    COW("Icons/ko.png"),
    LOBSTER("Icons/lobster.png"),
    SNAKE("Icons/orm.png"),
    PANDA("Icons/panda.png"),
    SQUID("Icons/squid.png"),
    TIGER("Icons/tiger.png");

    public final String iconPath;

    ImageIconAvatar(String iconPath){
        this.iconPath = iconPath;
    }

    }
