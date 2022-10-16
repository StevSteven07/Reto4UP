package Reto4.despliegue.repository.crudRepository;


import Reto4.despliegue.entitys.Reservation;
import org.springframework.data.repository.CrudRepository;
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
}