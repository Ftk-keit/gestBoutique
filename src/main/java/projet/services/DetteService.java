package projet.services;

import java.util.List;

import projet.entities.Dette;
import projet.repositories.DetteRepository;




public class DetteService  {
    private DetteRepository detteRepository;


   
    public DetteService(DetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }


    public void Create(Dette data) {
        detteRepository.insert(data);
    }

   
    public List<Dette> show() {
        return  detteRepository.selectAll();
    }

    public List<Dette> filter(){
        return detteRepository.filter();
    }

    public void archive(){
        detteRepository.archive();
    }
    // public Dette selectBy(String data) {
    //     // TODO Auto-generated method stub
    //     return null;
    // }
}
