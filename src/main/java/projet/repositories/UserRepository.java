package projet.repositories;

import java.util.List;

import projet.entities.User;
import projet.enums.Role;

public interface UserRepository extends Repository<User>{
    User selectByLogin(String login);
    boolean updateUser(String login, Boolean statut);
    List<User> filter(boolean actif, Role role);
} 