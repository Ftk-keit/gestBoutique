package projet.core;

import java.util.List;

public interface Services<T> {
    void Create(T data);
    public T selectBy(String data);
    public List<T> show();
}
