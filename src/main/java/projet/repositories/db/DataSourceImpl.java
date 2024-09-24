package projet.repositories.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import projet.core.DataSource;

public class DataSourceImpl<T> implements DataSource<T> {

    protected Connection conn;
    private String url = "jdbc:postgresql://localhost:5432/gestBoutique";
    private String user = "postgres";
    private String password = "tima04";
    protected PreparedStatement ps ;
    @Override
    public void getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, user, password);
    }

    @Override
    public void closeConnexion() throws SQLException {
        if ( conn != null && !conn.isClosed() ) {
            conn.close();
        }
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return ps.executeQuery();
        
    }

    @Override
    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

    @Override
    public String GenerateSql() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GenerateSql'");
    }

    @Override
    public void initPrepareStatement(String sql) throws SQLException {
        this.getConnection();
        if (sql.toUpperCase().trim().startsWith("INSERT")){
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            ps = conn.prepareStatement(sql);

        }
    }

}
