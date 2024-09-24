package projet.entities;

import lombok.Data;
import java.util.List;
import java.util.Date;


@Data
public class Dette {
    private int id;
    private static int nb = 1;
    private Date date;
    private int montant;
    private int montantVerser;
    private int montantRestant;
    private List<DetailDetteArticle> detailDetteArticle;
    private List<Paiement> paiement;
    private boolean archive = false;
    public Dette(){
        id = nb++;
    }
}
