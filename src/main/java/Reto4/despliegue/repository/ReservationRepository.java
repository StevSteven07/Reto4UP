package Reto4.despliegue.repository;


import Reto4.despliegue.entitys.Client;
import Reto4.despliegue.entitys.Reservation;
import Reto4.despliegue.entitys.DTOs.CompletedAndCancelled;
import Reto4.despliegue.entitys.DTOs.TotalAndClient;
import Reto4.despliegue.repository.crudRepository.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrud;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrud.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrud.findById(id);
    }

    public Reservation save(Reservation reservation){
        return reservationCrud.save(reservation);
    }

    public void delete (Reservation reservation) {
        reservationCrud.delete(reservation);
    }
    
   
    
    public List<Reservation> getReservationInPeriod(Date a, Date b){
        return reservationCrud.findAllByStartDateAfterAndDevolutionDateBefore(a, b);
    }
    
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrud.findAllByStatus(status);
    }
    
    public List<TotalAndClient> getTopClients(){
        List<TotalAndClient> respuesta= new ArrayList<>();
        List<Object[]> reporte= reservationCrud.countTotalReservationsByClient();
        
        for(int i=0; i<reporte.size(); i++){
            respuesta.add(new TotalAndClient((Long)reporte.get(i)[1], (Client) reporte.get(i)[0]));
        }
        return respuesta;
    }
}
