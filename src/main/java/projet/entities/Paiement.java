package projet.entities;

import lombok.Data;
import java.util.Date;

@Data
public class Paiement {
    private int id;
    private static int nb = 1;
    private Date date;
    private int montant;
    private Dette dette;    
    public Paiement(){
        id = nb++;
    }
}
