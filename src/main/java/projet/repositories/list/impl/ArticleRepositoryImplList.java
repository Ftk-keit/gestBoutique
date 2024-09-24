package projet.repositories.list.impl;

import java.util.List;
import java.util.stream.Collectors;

import projet.entities.Article;
import projet.repositories.ArticleRepository;
import projet.repositories.list.RepositoryImpList;

public class ArticleRepositoryImplList extends RepositoryImpList<Article> implements ArticleRepository  {

    @Override
    public boolean edit(String libelle, int qteStock) {
        Article article = new Article();
        boolean exist = this.selectByLibelle(libelle) != null;
        if (!exist) {
            return false;
        }
        article = this.selectByLibelle(libelle);
        article.setQteStock(qteStock);
        return true;
    }

    @Override
    public List<Article> filter() {
     return this.list.stream()
        .filter(article -> article.getQteStock() !=0)
        .collect(Collectors.toList());
    }

    @Override
    public void insert(Article data) {
        list.add(data);
    }

    @Override
    public List<Article> selectAll() {
        return list;
    }

    @Override
    public Article selectByLibelle(String libelle) {
        return !list.isEmpty()
        ? list.stream()
                .filter(article -> article.getLibelle().equals(libelle))
                .findFirst()
                .orElse(null)
        : null;
    }
    
}
