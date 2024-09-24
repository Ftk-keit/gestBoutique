package projet.repositories.list;

import java.util.ArrayList;
import java.util.List;

import projet.repositories.Repository;

public class RepositoryImpList<T> implements Repository<T> {
    protected List<T> list = new ArrayList<>();

    @Override
    public void insert(T data) {
        list.add(data);
    }

    @Override
    public List<T> selectAll() {
        return list;
    }
    
}
