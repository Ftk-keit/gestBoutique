package projet.vues;


import projet.core.PasswordHashing;
import projet.entities.Client;
import projet.entities.User;
import projet.enums.Role;
import projet.services.ClientService;
import projet.services.UserService;

public class UserView extends View<User> {
    private UserService userServices;
    private ClientService clientServices;

    public UserView(UserService userServices, ClientService clientServices) {
        this.userServices = userServices;
        this.clientServices = clientServices;
    }

    @Override
    public User saisie() {
        User user = new User();
        boolean userExist = true;
        do {
            System.out.println("Veuillez saisir le login ");
            user.setLogin(scanner.nextLine());
            userExist = userServices.selectBy(user.getLogin()) != null;
            if (userExist) {
                System.out.println("Ooups !! Ce login exist deja");
                System.out.println("veuillez réessayer");
            }
        } while (userExist);
        user.setRôle(getRole());
        do {
            System.out.println("Veuillez saisir le password");
            user.setPassword(PasswordHashing.hashPassword(scanner.nextLine()));
        } while (user.getPassword().trim() == "");
        return user;
    }

    public boolean createAccount(String tel) {
        User user = new User();
        Client client = new Client();
        boolean exist;
        exist = this.clientServices.selectBy(tel) != null;
        if (!exist) {
            System.out.println("Ce client n'existe pas");
            return false;
        }
        client = this.clientServices.selectBy(tel);
        if (client.getUser() != null) {
            System.out.println("Ce client possède déjà un compte : " + client.getUser().toString());
            return false;
        }
        user = this.saisie();
        client.setUser(user);
        user.setClient(client);
        return true;
    }

    public Role getRole() {
        int choice = 0;
        do {
            System.out.println("Veuillez choisir le rôle");
            System.out.println("1-Boutiquier");
            System.out.println("2-Admin");
            choice = scanner.nextInt();
        } while (choice != 1 || choice != 2);
        return Role.values()[choice - 1];
    }

   public int filter(){
    if (userServices.show().size() == 0)
    {
        System.out.println("Veuillez enregistrer des comptes");
        return 0;
    }
     
    int choice = 0;
    do {
        System.out.println("Veuillez choisir le filtre");
        System.out.println("1-Role");
        System.out.println("2-Compte actif");
        choice = scanner.nextInt();
    } while (choice != 1 || choice != 2);
    return choice;
   }
   
}
