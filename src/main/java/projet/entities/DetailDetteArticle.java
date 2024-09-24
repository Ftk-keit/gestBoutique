package projet.entities;

import lombok.Data;

@Data
public class DetailDetteArticle {
    private int id ;
    private int qte;
    private Article article;
    private Dette dette;
    private static int nb = 1;

    public DetailDetteArticle(){
        id = nb++;
    }
}
