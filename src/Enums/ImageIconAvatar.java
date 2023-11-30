package Enums;

import java.util.ArrayList;
import java.util.List;

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


    public static List<ImageIconAvatar> getAll(){
        List<ImageIconAvatar> imageIconAvatarList = new ArrayList<>();
        imageIconAvatarList.add(MONKEY);
        imageIconAvatarList.add(CRAB);
        imageIconAvatarList.add(ELEPHANT);
        imageIconAvatarList.add(PIG);
        imageIconAvatarList.add(BUNNY);
        imageIconAvatarList.add(COW);
        imageIconAvatarList.add(LOBSTER);
        imageIconAvatarList.add(SNAKE);
        imageIconAvatarList.add(PANDA);
        imageIconAvatarList.add(SQUID);
        imageIconAvatarList.add(TIGER);

        return imageIconAvatarList;
    }
}
