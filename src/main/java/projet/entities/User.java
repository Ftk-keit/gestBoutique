package projet.entities;

import lombok.Data;
import projet.enums.Role;

@Data
public class User {
    private int id;
    private String login;
    private String password;
    private static int nb = 1;
    private Client client;
    private Role rôle;
    private Boolean actif = true;
    public User() {
        id = nb++;
    }
}
