package projet.repositories;

import java.util.List;

import projet.entities.Dette;

public interface DetteRepository extends Repository<Dette> {
    List<Dette> filter();
    void archive();
}