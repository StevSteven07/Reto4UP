package Reto4.despliegue.repository.crudRepository;


import Reto4.despliegue.entitys.Message;
import org.springframework.data.repository.CrudRepository;
public interface MessageCrudRepository extends CrudRepository<Message, Integer> {
}