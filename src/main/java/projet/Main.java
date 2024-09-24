package projet;

// import java.security.Provider.Service;
import java.util.Scanner;

import projet.core.Factory;
import projet.enums.Role;
import projet.services.ArticleService;
import projet.services.ClientService;
import projet.services.DetteService;
import projet.services.UserService;
import projet.vues.ArticleView;
import projet.vues.ClientView;
import projet.vues.DetteView;
import projet.vues.UserView;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //-----------------------------------repos-----------------------------------

                                    //Modifier avec Factory
        // ClientRepositoryImplList clientRepositoryImpl = new ClientRepositoryImplList();
        // UserRepositoryImplList userRepositoryImplList = new UserRepositoryImplList();
        // // ArticleRepositoryImplList articleRepositoryImplList = new ArticleRepositoryImplList();
        // ArticleRepositoryImplDb articleRepositoryImplDb = new ArticleRepositoryImplDb();
        // DetteRepositoryImplList detteRepositoryImplList = new DetteRepositoryImplList();
        
        // //-----------------------------------services-----------------------------------
        ClientService clientService = new ClientService(Factory.getInstanceClientRepository());
        UserService userService = new UserService(Factory.getInstanceUserRepository());
        ArticleService articleService = new ArticleService(Factory.getInstanceArticleRepository());
        DetteService detteService = new DetteService(Factory.getInstanceDetteRepository());


        //-----------------------------------vues-----------------------------------
        ClientView clientVue = new ClientView(clientService);
        UserView userView = new UserView(userService, clientService);
        ArticleView articleView = new ArticleView(articleService);
        DetteView detteView = new DetteView(detteService);
        int choice;
        do {
            choice = showMenu();
            clear();
            String info;
            boolean ok;
            int qte;
            String result;
            scanner.nextLine();
            switch (choice) {
                case 1:
                    clientService.Create(clientVue.saisie());
                    break;
                case 2:
                    clientVue.liste(clientService.show());
                    break;
                case 3:
                    System.out.println("Veuillez saisir le numéro de téléphone ");
                    info = scanner.nextLine();
                    ok = clientService.selectBy(info) != null;
                    result = ok ? clientService.selectBy(info).toString() : "Desolé !! Ce client n'existe pas";
                    System.out.println(result);
                    break;
                case 4:
                    System.out.println("Veuillez saisir le numéro de téléphone ");
                    info = scanner.nextLine();
                    ok = userView.createAccount(info);
                    result = ok ? "Compte créer avec succès" : "";
                    System.out.println(result);
                    break;
                case 5:
                    userService.Create(userView.saisie());
                    break;
                case 6:
                    System.out.println("Veuillez saisir le login");
                    info = scanner.nextLine();
                    choice = getChoice();
                    switch (choice) {
                        case 1:
                            ok = userService.edit(info, true);
                            result = ok ? "Succès" : "Ce compte n'existe pas";
                            System.out.println(result);
                            break;
                        case 2:
                            ok = userService.edit(info, false);
                            result = ok ? "Succès" : "Ce compte n'existe pas";
                            System.out.println(result);
                            break;
                        default:
                            break;
                    }
                    break;
                case 7:
                    choice = userView.filter();
                    ok = choice == 1;
                    if (ok) {
                        userView.liste(userService.filter(ok, userView.getRole()));
                    } else {
                        userView.liste(userService.filter(ok, Role.values()[0]));
                    }
                    break;
                case 8:
                    articleService.Create(articleView.saisie());
                    break;
                case 9:
                    articleView.liste(articleService.filter());
                    break;
                case 10:
                    info = "";
                    qte = 0;
                    do {
                        System.out.println("Veuillez saisir le info ");
                        info = scanner.nextLine();
                    } while (info.trim() == "");

                    do {
                        System.out.println("Veuillez saisir la qte ");
                        qte = scanner.nextInt();
                    } while (qte == 0);
                    ok = articleService.edit(info, qte);
                    result = ok ? "Succès" : "Cet article n'existe pas";
                    break;

                case 11:
                   
                    break;
                default:
                    break;
            }

        } while (choice != 12);
    }

    public static int showMenu() {
        System.out.println("1-Ajouter un client");
        System.out.println("2-Lister les clients");
        System.out.println("3-Rechercher un client");
        System.out.println("4-Créer un compte utilisateur à un client n’ayant pas de compte");
        System.out.println("5-Créer un compte utilisateur avec un rôle Boutiquier ou  Admin");
        System.out.println("6-Désactiver/Activer  un compte utilisateur ");
        System.out.println("7-Afficher les comptes utilisateurs  actifs ou par rôle. ");
        System.out.println("8-Ajouter un article ");
        System.out.println("9-Lister les articles disponibles");
        System.out.println("10-Mettre à jour la qté en stock d’un article");
        System.out.println("11-Archiver les dettes soldées");
        System.out.println("11-Archiver les dettes soldées");
        System.out.println("12-Quitter");
        return scanner.nextInt();
    }

    public static int getChoice() {
        int choice = 0;
        do {
            System.out.println("Voullez-vous");
            System.out.println("1-Activer le compte ");
            System.out.println("2-Desactiver le comte");
            choice = scanner.nextInt();
        } while (choice != 1 || choice != 2);
        return scanner.nextInt();
    }
    public static void clear() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
