package projet.repositories;

import projet.entities.Client;

public interface ClientRepository extends Repository<Client> {
    Client selectByTel(String tel);
    Client selectById(int id);

} 