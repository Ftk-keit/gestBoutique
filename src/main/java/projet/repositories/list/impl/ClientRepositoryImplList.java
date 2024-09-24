package projet.repositories.list.impl;

import projet.entities.Client;
import projet.repositories.ClientRepository;
import projet.repositories.list.RepositoryImpList;

public class ClientRepositoryImplList  extends RepositoryImpList<Client> implements ClientRepository{
    @Override
    public Client selectByTel(String tel) {
        return !list.isEmpty()
        ? list.stream()
                .filter(client -> client.getTelephone().equals(tel))
                .findFirst()
                .orElse(null)
        : null;
    }

    @Override
    public Client selectById(int id) {
        return !list.isEmpty()
        ? list.stream()
                .filter(client -> client.getId() == id)
                .findFirst()
                .orElse(null)
        : null;
    }
    
}
