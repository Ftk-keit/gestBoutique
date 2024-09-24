package projet.vues;

import projet.entities.Client;
// import projet.services.ClientService;
import projet.services.ClientService;

public class ClientView extends View<Client> {
    private ClientService clientServices;

    public ClientView (ClientService clientService ) {
        this.clientServices = clientService;
    }

    @Override
    public Client saisie() {
        Client client = new  Client();
        do {
            System.out.println("Veuillez saisir le surnom du client ");
            client.setSurnom(scanner.nextLine());
        } while (client.getSurnom() == "");
        boolean clientExist = true;
        do {
            System.out.println("Veuillez saisir le numero de telephone du client ");
            client.setTelephone(scanner.nextLine());
            clientExist = clientServices.selectBy(client.getTelephone()) != null;
            if (clientExist) {
                System.out.println("Ooups !! Ce numero exist deja");
                System.out.println("veuillez réessayer");

            }
        } while (clientExist);
        do {
            System.out.println("Veuillez saisir l'adresse du client ");
            client.setAdresse(scanner.nextLine());
        } while (client.getAdresse() == "");
        return client;
    }
 
}
