package Server.UserData;

import javax.swing.*;
import java.io.Serializable;

public class User implements Serializable {
    String name;
    ImageIcon avatar;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, ImageIcon avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }
}
