package Reto4.despliegue.repository;


import Reto4.despliegue.entitys.Cloud;
import Reto4.despliegue.repository.crudRepository.CloudCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CloudRepository {

    @Autowired
    private CloudCrudRepository cloudCrudRepository;

    public List<Cloud> getAll(){
        return (List<Cloud>) cloudCrudRepository.findAll();
    }

    public Optional<Cloud> getCloud(int id) {
        return cloudCrudRepository.findById(id);
    }

    public Cloud save(Cloud cloud){
        return cloudCrudRepository.save(cloud);
    }

    public void delete (Cloud cloud) {
        cloudCrudRepository.delete(cloud);
    }
}
