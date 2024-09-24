package projet.vues;

import projet.entities.Dette;
import projet.services.DetteService;

public class DetteView extends View<Dette>{
    private DetteService detteService;
    public DetteView(DetteService detteService) {
        this.detteService = detteService;
    }
    @Override
    public Dette saisie() {
        // TODO Auto-generated method stub
        return null;
    }

    
    
}
