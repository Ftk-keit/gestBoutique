package projet.repositories.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet.entities.Client;
import projet.repositories.ClientRepository;
import projet.repositories.db.RepositoryImpDb;

public class ClientRepositoryImplDb extends RepositoryImpDb<Client> implements ClientRepository {
    private static ClientRepositoryImplDb instance;
    
    @Override
    public Client selectByTel(String tel) {
        try {
            String query = "SELECT * FROM client WHERE telephone = ?";
            getConnection();
            initPrepareStatement(query);
            ps.setString(1, tel);
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
    public Client selectById(int id) {
        try {
            String query = "SELECT * FROM client WHERE id = ?";
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
    public void insert(Client data) {
        try {
            String query = "INSERT INTO client (telephone, surnom, adresse, userId) VALUES (?, ?, ?, ?)";
            getConnection();
            initPrepareStatement(query);
            ps.setString(1, data.getTelephone());
            ps.setString(2, data.getSurnom());
            ps.setString(3, data.getAdresse());
            if (data.getUser() != null && data.getUser() != null) {
                ps.setInt(4, data.getUser().getId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }

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
    public List<Client> selectAll() {
       List<Client> clients = new ArrayList<>();
        try {
            String query = "SELECT * FROM client";
            getConnection();
            initPrepareStatement(query);
            ResultSet rs = this.executeQuery();
            while (rs.next()) {
                clients.add(convert(rs));
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

        return clients;
    }

    @Override
    public Client convert(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setTelephone(rs.getString("telephone"));
        client.setSurnom(rs.getString("surnom"));
        client.setAdresse(rs.getString("adresse"));
        return client;
    }
    
    

    
    public static ClientRepositoryImplDb getInstance() {
        if (instance == null) {
            instance = new ClientRepositoryImplDb();
        }
        return instance;
    }

}
