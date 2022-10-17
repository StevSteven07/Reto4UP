
package Reto4.despliegue.controller;

import Reto4.despliegue.entitys.Reservation;
import Reto4.despliegue.entitys.DTOs.CompletedAndCancelled;
import Reto4.despliegue.entitys.DTOs.TotalAndClient;
import Reto4.despliegue.services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Reservation")
public class ReservationController {

    @Autowired
    private ReservationServices reservationServices;

    @GetMapping("/all")
    public List<Reservation> getAll(){
        return reservationServices.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservation(@PathVariable("id") int id) {
        return reservationServices.getReservation(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation save(@RequestBody Reservation reservation){
        return reservationServices.save(reservation);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation update(@RequestBody Reservation reservation){
        return reservationServices.update(reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id) {
        return reservationServices.delete(id);
    }
    
    @GetMapping("/report-clients")
    public List<TotalAndClient> getTopClientsReport(){
        return reservationServices.getTopClientsReport();
    }
    
    @GetMapping("/report-status")
    public CompletedAndCancelled getReservationStatusReport(){
        return reservationServices.getReservationStatusReport();
    }
    
     @GetMapping("/report-dates/{fecha1}/{fecha2}")
     public List<Reservation> getReservationsInPeriodReport(@PathVariable("fecha1") String fecha1, @PathVariable("fecha2") String fecha2){
         return reservationServices.getReservationInPeriod(fecha1, fecha2);
     }
}