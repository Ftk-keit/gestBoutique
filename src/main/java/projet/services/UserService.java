package projet.services;

import java.util.List;

import projet.entities.User;
import projet.enums.Role;
import projet.repositories.UserRepository;


public class UserService{
    private UserRepository userRepository;

  
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   
    public void Create(User data) {
        userRepository.insert(data);
    }

   
    public User selectBy(String login) {
        return userRepository.selectByLogin(login);
    }

   
    public List<User> show() {
        return userRepository.selectAll();
    }

    public boolean edit(String login, Boolean statut) {
        return userRepository.updateUser(login, statut);
    }

    public List<User> filter(boolean actif, Role role) {
         return userRepository.filter(actif, role);
    }
}
