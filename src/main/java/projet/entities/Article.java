package projet.entities;

import java.util.List;

import lombok.Data;


@Data
public class Article {
    private int id;
    private String libelle;
    private int qteStock;
    private int prix;
    private List<DetailDetteArticle> detailDetteArticle;
    private static int nb = 1;
    public Article(){
        id = nb++;
    }
}
