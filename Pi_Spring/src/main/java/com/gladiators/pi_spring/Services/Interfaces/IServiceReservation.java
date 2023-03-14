package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.CentreZone;
import com.gladiators.pi_spring.Entities.Likes;
import com.gladiators.pi_spring.Entities.Reservation;


import java.util.List;

public interface IServiceReservation {
    //public Reservation addResAndAssignToCenter(Reservation reservation, Integer IdCentre);
    public Integer nbResParZone(CentreZone r);
    public Reservation updateReservation(Integer idReservation, Reservation newReservation);

    Reservation ajouterReservation(Reservation reservation, Integer idCentre);
    public void supprimerReservationParId(Integer idReservation);
    //public Reservation updateReservation(Integer idReservation, Reservation reservation) ;
    public List<Reservation> afficheReservation(Integer id_centre);
    public List<Reservation> retrieveAllReservation();
}
