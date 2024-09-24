package projet.services;

import java.util.List;

import projet.entities.Client;
import projet.repositories.ClientRepository;


public class ClientService {
    private ClientRepository clientRepository;
    
   
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void Create(Client data) {
        clientRepository.insert(data);
    }

   
    public Client selectBy(String tel) {
        return clientRepository.selectByTel(tel);
    }

   
    public List<Client> show() {
        return clientRepository.selectAll();
    }
}
