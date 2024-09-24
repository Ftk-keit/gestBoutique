package projet.repositories.list.impl;

import java.util.List;
import java.util.stream.Collectors;

import projet.entities.Dette;
import projet.repositories.DetteRepository;
import projet.repositories.list.RepositoryImpList;

public class DetteRepositoryImplList extends RepositoryImpList<Dette> implements DetteRepository  {

    @Override
    public void archive() {
        List<Dette> dettes = this.filter();
        dettes.stream().forEach(dette -> dette.setArchive(true));
        list = dettes;
    }

    @Override
    public List<Dette> filter() {
      return list.stream()
        .filter(dette -> dette.getMontantRestant() ==0)
        .collect(Collectors.toList());
    }
    
}
