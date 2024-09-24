package projet.core;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataSource<T> {
    void getConnection() throws SQLException;

    void closeConnexion() throws SQLException;

    ResultSet executeQuery() throws SQLException;

    int executeUpdate() throws SQLException;

    String GenerateSql() throws SQLException;

    void initPrepareStatement(String sql) throws SQLException;
    
}
