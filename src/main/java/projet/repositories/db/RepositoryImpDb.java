package projet.repositories.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import projet.repositories.Repository;

public abstract class RepositoryImpDb<T> extends DataSourceImpl<T> implements Repository<T> {
    public abstract T convert(ResultSet rs) throws SQLException;

}
