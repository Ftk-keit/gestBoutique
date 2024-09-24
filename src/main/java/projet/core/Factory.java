package projet.core;

import projet.repositories.ArticleRepository;
import projet.repositories.ClientRepository;
import projet.repositories.DetteRepository;
import projet.repositories.UserRepository;
import projet.repositories.db.impl.ArticleRepositoryImplDb;
import projet.repositories.db.impl.ClientRepositoryImplDb;
import projet.repositories.db.impl.DetteRepositoryImplDb;
import projet.repositories.db.impl.UserRepositoryImplDb;

public class Factory {

    public static ClientRepository getInstanceClientRepository(){
        return  ClientRepositoryImplDb.getInstance();
    }
    
    public static ArticleRepository getInstanceArticleRepository(){
        return new ArticleRepositoryImplDb();
    }

    // public DetailDetteArticle getInstanceDetailDetteArticle(){
    //     return new DetailDetteArticleImplDb();
    // }

    public static DetteRepository getInstanceDetteRepository(){
        return new DetteRepositoryImplDb();
    }

    public static UserRepository getInstanceUserRepository(){
        return new UserRepositoryImplDb();
    }
    
}
