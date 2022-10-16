package Reto4.despliegue.services;


import Reto4.despliegue.entitys.Cloud;
import Reto4.despliegue.repository.CloudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudServices {

    @Autowired
    private CloudRepository cloudRepository;

    public List<Cloud> getAll(){
        return cloudRepository.getAll();
    }

    public Optional<Cloud> getCloud(int id){
        return cloudRepository.getCloud(id);
    }

    public Cloud save (Cloud cloud){
         if(validarCampos(cloud)) {
             if (cloud.getId() == null) {
                 return cloudRepository.save(cloud);
             } else {
                 Optional<Cloud> c = cloudRepository.getCloud(cloud.getId());
                 if (c.isPresent()) {
                     return cloud;
                 } else {
                     return cloudRepository.save(cloud);
                 }
             }
         } return cloud;
    }

    public Cloud update(Cloud cloud){
        if(validarCampos(cloud)) {
            if (cloud.getId() != null) {
                Optional<Cloud> c = cloudRepository.getCloud(cloud.getId());
                if (c.isPresent()) {
                    if (cloud.getName() != null) {
                        c.get().setName(cloud.getName());
                    }
                    if (cloud.getBrand() != null) {
                        c.get().setBrand(cloud.getBrand());
                    }
                    if (cloud.getYear() != null) {
                        c.get().setYear(cloud.getYear());
                    }
                    if (cloud.getDescription() != null) {
                        c.get().setDescription(cloud.getDescription());
                    }
                    cloudRepository.save(c.get());
                    return c.get();
                } else {
                    return cloud;
                }
            } else {
                return cloud;
            }
        } return cloud;
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Cloud> cloud = cloudRepository.getCloud(id);
        if (cloud.isPresent()){
            cloudRepository.delete(cloud.get());
            flag=true;
        }
        return flag;
    }

    public boolean validarCampos (Cloud cloud){
        return (cloud.getBrand().length()<=45 && cloud.getName().length()<=45 &&
                String.valueOf(cloud.getYear()).length()==4 && cloud.getDescription().length()<=250);
    }
}
