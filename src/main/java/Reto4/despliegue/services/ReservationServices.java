package Reto4.despliegue.services;


import Reto4.despliegue.entitys.Reservation;
import Reto4.despliegue.entitys.DTOs.CompletedAndCancelled;
import Reto4.despliegue.entitys.DTOs.TotalAndClient;
import Reto4.despliegue.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServices {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }

    public Reservation save (Reservation reservation){
        if(reservation.getIdReservation()==null){
            reservation.setStatus("created");
            return reservationRepository.save(reservation);
        }else {
            Optional <Reservation> e = reservationRepository.getReservation(reservation.getIdReservation());
            if(e.isPresent()){
                return reservation;
            }else{
                return reservationRepository.save(reservation);
            }
        }
    }

    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation()!=null){
            Optional<Reservation> r = reservationRepository.getReservation(reservation.getIdReservation());
            if(r.isPresent()){
                if(reservation.getStartDate()!=null){
                    r.get().setStartDate(reservation.getStartDate());
                }
                if(reservation.getDevolutionDate()!=null){
                    r.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if(reservation.getStatus()!=null){
                    r.get().setStatus(reservation.getStatus());
                }/*
                if(reservation.getClient()!=null){
                    r.get().setClient(reservation.getClient());
                }
                if(reservation.getCloud()!=null){
                    r.get().setCloud(reservation.getCloud());
                }*/
                reservationRepository.save(r.get());
                return r.get();
            }else {
                return reservation;
            }
        }else {
            return reservation;
        }
    }

    public boolean delete(int id){
        boolean flag=false;
        Optional<Reservation> reservation = reservationRepository.getReservation(id);
        if (reservation.isPresent()){
            reservationRepository.delete(reservation.get());
            flag=true;
        }
        return flag;
    }
    
    public List<Reservation> getReservationInPeriod(String dateA, String dateB){
        SimpleDateFormat parser= new SimpleDateFormat("yyyy-MM-dd");
        Date a= new Date();
        Date b= new Date();
        try{
            a= parser.parse(dateA);
            b= parser.parse(dateB);
        }catch(ParseException e){
            e.printStackTrace();
        }
        if(a.before(b)){
            return reservationRepository.getReservationInPeriod(a, b);
        }else{
            return new ArrayList<>();
        }
    }
    
    public CompletedAndCancelled getReservationStatusReport(){
        List<Reservation> completed= reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled= reservationRepository.getReservationByStatus("cancelled");
        
        return new CompletedAndCancelled ((long)completed.size(),(long) cancelled.size());       
        
    }
    
    public List<TotalAndClient> getTopClientsReport(){
        return reservationRepository.getTopClients();
    }
    
    
    
}
