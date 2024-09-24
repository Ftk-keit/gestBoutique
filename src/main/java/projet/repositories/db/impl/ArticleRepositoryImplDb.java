package projet.repositories.db.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import projet.entities.Article;
import projet.repositories.ArticleRepository;
import projet.repositories.db.RepositoryImpDb;

public class ArticleRepositoryImplDb extends RepositoryImpDb<Article> implements ArticleRepository {
    @Getter
    public static String table = "article";

    @Override
    public boolean edit(String libelle, int qteStock) {
        try {
            String query = "UPDATE article SET qteStock = ? WHERE libelle = ?";
            getConnection();
            initPrepareStatement(query);
            ps.setInt(1, qteStock);
            ps.setString(2, libelle);
            this.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERREUR: " + e.getMessage());
        } finally {
            try {
                this.closeConnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public List<Article> filter() {
        List<Article> articles = new ArrayList<>();
        try {
            String query = "SELECT * FROM article WHERE qteStock != 0";
            getConnection();
            initPrepareStatement(query);
            ResultSet rs = this.executeQuery();
            while (rs.next()) {
             
                articles.add(convert(rs));
            }
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());
        } finally {
            try {
                this.closeConnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return articles;
    }

    @Override
    public void insert(Article data) {
        try {
            String query = "INSERT INTO article (libelle, qteStock, prix) VALUES (?, ?, ?)";
            getConnection();
            initPrepareStatement(query);
            ps.setString(1, data.getLibelle());
            ps.setInt(2, data.getQteStock());
            ps.setInt(3, data.getPrix());
            this.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                data.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());

        } finally {
            try {
                this.closeConnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Article> selectAll() {
        List<Article> articles = new ArrayList<>();
        try {
            String query = "SELECT * FROM article";
            getConnection();
            initPrepareStatement(query);
            ResultSet rs = this.executeQuery();
            while (rs.next()) {
                articles.add(convert(rs));
            }
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());

        } finally {
            try {
                this.closeConnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return articles;
    }

    @Override
    public Article selectByLibelle(String libelle) {
        try {
            String query = "SELECT * FROM article WHERE libelle = ?";
            getConnection();
            initPrepareStatement(query);
            ps.setString(1, libelle);
            ResultSet rs = this.executeQuery();
            while (rs.next()) {
                return convert(rs);
            }
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());

        } finally {
            try {
                this.closeConnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Article selectById(int id) {
       
        try {
            String query = "SELECT * FROM article WHERE id = ?";
            getConnection();
            initPrepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = this.executeQuery();
            while (rs.next()) {
              return convert(rs);
            }
        } catch (Exception e) {
            System.out.println("ERREUR: " + e.getMessage());

        } finally {
            try {
                this.closeConnexion();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Article convert(ResultSet rs) throws SQLException     {
        Article article = new Article();
        article.setLibelle(rs.getString("libelle"));
        article.setQteStock(rs.getInt("qteStock"));
        article.setPrix(rs.getInt("prix"));
        return article;
    }

}










