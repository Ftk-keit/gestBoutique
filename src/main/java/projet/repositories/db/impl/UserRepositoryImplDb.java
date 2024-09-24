package projet.repositories.db.impl;
import projet.core.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet.entities.User;
import projet.enums.Role;
import projet.repositories.ClientRepository;
import projet.repositories.UserRepository;
import projet.repositories.db.RepositoryImpDb;

public class UserRepositoryImplDb extends RepositoryImpDb<User> implements UserRepository {

    @Override
    public List<User> filter(boolean actif, Role role) {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM User WHERE actif = ? AND roleId = ?";
            getConnection();
            initPrepareStatement(query);
            ps.setBoolean(1, actif);
            ps.setInt(2, 1 + role.ordinal());
            ResultSet rs = this.executeQuery();
            while (rs.next()) {
                users.add(convert(rs));
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
        return users;
    }

    @Override
    public User selectByLogin(String login) {
        try {
            String query = "SELECT * FROM user WHERE login = ?";
            getConnection();
            initPrepareStatement(query);
            ps.setString(1, login);
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
    public boolean updateUser(String login, Boolean statut) {
        return true;
    }

    @Override
    public void insert(User data) {
        ClientRepositoryImplDb clientRepositoryImplDb = new ClientRepositoryImplDb();
        try {
            String query = "INSERT INTO user (login, password, clientId, roleId, actif) VALUES (?, ?, ?, ?, ?)";
            getConnection();
            initPrepareStatement(query);
            ps.setString(1, data.getLogin());
            ps.setString(2, data.getPassword());
            ps.setInt(3, (clientRepositoryImplDb.selectByTel(data.getClient().getTelephone()).getId()));
            ps.setInt(4, data.getRôle().ordinal() + 1);
            ps.setBoolean(5, data.getActif());
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
    public List<User> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public User convert(ResultSet rs) throws SQLException {
        User user = new User();
        ClientRepository clientRepository = Factory.getInstanceClientRepository();
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setClient(clientRepository.selectById(rs.getInt("clientId")));
        return user;
    }

}
