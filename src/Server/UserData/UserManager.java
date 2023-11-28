package Server.UserData;

import java.io.*;
import java.util.List;

public class UserManager implements Serializable {
    private List<User> users;

    public UserManager() {
        readUsersFromFile();
    }
    public void readUsersFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/Server/UserData/users.ser"))) {
          User user;
          while((user = (User) in.readObject()) != null){
              users.add(user);
          }
        } catch (IOException e) {
            System.out.println("Could not read users from file");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find class");
        }
    }

    public void writeUsersToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/Server/UserData/users.ser"))) {
            for(User user : users){
                out.writeObject(user);
            }
        } catch (IOException e) {
            System.out.println("Could not write users to file");
        }
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
