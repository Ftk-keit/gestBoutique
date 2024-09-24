package projet.vues;


import java.util.List;
import java.util.Scanner;

public abstract class View<T> implements ViewInt<T> {
    protected Scanner scanner = new Scanner(System.in);

    @Override
    public void liste(List<T> datas) {
        for (T t : datas) {
            System.out.println(t);
        }
    }
}
