package projet.repositories.db.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import projet.entities.Dette;
import projet.repositories.DetteRepository;
import projet.repositories.db.RepositoryImpDb;

public class DetteRepositoryImplDb extends RepositoryImpDb<Dette> implements DetteRepository {

    @Override
    public void archive() {
        //
    }

    @Override
    public List<Dette> filter() {
        List<Dette> dettes = new ArrayList<>();
        try {
            String query = "SELECT * FROM dette WHERE montantRestant = 0";
            getConnection();
            initPrepareStatement(query);
            ResultSet rs = this.executeQuery();
            while (rs.next()) {

                dettes.add(convert(rs));
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
        return dettes;
    }

    @Override
    public void insert(Dette data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public List<Dette> selectAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public Dette convert(ResultSet rs) throws SQLException {
        Dette dette = new Dette();
        dette.setMontant(rs.getInt("montant"));
        ;
        dette.setMontantVerser((rs.getInt("montantVerser")));
        dette.setArchive((rs.getBoolean("montantRestant")));
        return dette;
    }

}
