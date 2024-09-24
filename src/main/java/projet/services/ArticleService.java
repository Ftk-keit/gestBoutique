package projet.services;

import java.util.List;

import projet.entities.Article;
import projet.repositories.ArticleRepository;

public class ArticleService {
    private ArticleRepository articleRepository;
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

   
    public void Create(Article data) {
        articleRepository.insert(data);
    }

   
    public Article selectBy(String libelle) {
        return  articleRepository.selectByLibelle(libelle);
    }

   
    public List<Article> show() {
        return articleRepository.selectAll();
    }

    public List<Article> filter(){
        return articleRepository.filter();
    }

    public boolean edit(String libelle, int qteStock){
       return articleRepository.edit(libelle, qteStock);
    }
}
