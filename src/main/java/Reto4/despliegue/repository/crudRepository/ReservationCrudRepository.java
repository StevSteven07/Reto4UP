package Reto4.despliegue.repository.crudRepository;


import Reto4.despliegue.entitys.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer> {
    
    public List<Reservation> findAllByStatus(String status);
    
    public List<Reservation> findAllByStartDateAfterAndDevolutionDateBefore(Date dateOne, Date dateTwo);
    
    @Query("SELECT c.client, COUNT(c.client) FROM Reservation AS c GROUP BY c.client ORDER BY COUNT(c.client) DESC")
    
    public List<Object[]> countTotalReservationsByClient();
    
    //balance de reservas 
    
    
}