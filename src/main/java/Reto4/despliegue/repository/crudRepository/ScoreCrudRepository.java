package Reto4.despliegue.repository.crudRepository;


import Reto4.despliegue.entitys.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreCrudRepository extends CrudRepository<Score, Integer> {
}