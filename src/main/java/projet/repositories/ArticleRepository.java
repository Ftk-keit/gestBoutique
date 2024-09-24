package projet.repositories;

import projet.entities.Article;

import java.util.List;


public interface ArticleRepository extends Repository<Article>{
    List<Article> filter();
    boolean edit(String libelle, int qteStock);
    Article selectByLibelle(String libelle);
   
} 
