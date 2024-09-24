package projet.repositories.list.impl;

import java.util.List;
import java.util.stream.Collectors;

import projet.entities.User;
import projet.enums.Role;
import projet.repositories.UserRepository;
import projet.repositories.list.RepositoryImpList;

public class UserRepositoryImplList extends RepositoryImpList<User> implements UserRepository{

    @Override
    public List<User> filter(boolean actif, Role role) {
        return actif ? this.list.stream()
                .filter(user -> user.getActif())
                .collect(Collectors.toList())
                : this.list.stream()
                        .filter(user -> user.getRôle().equals(role))
                        .collect(Collectors.toList());
    }

    @Override
    public User selectByLogin(String login) {
        return !list.isEmpty()
                ? list.stream()
                        .filter(user -> user.getLogin().equals(login))
                        .findFirst()
                        .orElse(null)
                : null;
    }

    @Override
    public boolean updateUser(String login, Boolean statut) {
        User user = new User();
        boolean exist = this.selectByLogin(login) != null;
        if (!exist) {
            return false;
        }
        user = this.selectByLogin(login);
        user.setActif(statut);
        return true;
    }
    
}
