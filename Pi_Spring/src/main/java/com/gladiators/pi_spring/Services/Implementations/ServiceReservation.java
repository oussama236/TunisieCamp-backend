package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.CampingCenter;
import com.gladiators.pi_spring.Entities.CentreZone;
import com.gladiators.pi_spring.Entities.Reservation;
import com.gladiators.pi_spring.Repositories.CampingCentreRepository;
import com.gladiators.pi_spring.Repositories.ReservationRepository;
import com.gladiators.pi_spring.Services.Interfaces.IServiceReservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class ServiceReservation implements IServiceReservation {
    @Autowired
    CampingCentreRepository campingCenterRepository ;
    @Autowired
    ReservationRepository reservationRepository ;


    @Override
    public Reservation ajouterReservation(Reservation reservation, Integer idCentre) {
        // Récupération du camping center correspondant à l'id fourni
        CampingCenter centre = campingCenterRepository.findById(idCentre)
                .orElseThrow(() -> new NoSuchElementException("Le camping center n'existe pas"));
        // Association du commentaire avec le camping center
        reservation.setCamping_centre(centre);
        // Enregistrement du commentaire en base de données
        reservation = reservationRepository.save(reservation);

        return reservation;
    }
    public Integer nbResParZone(CentreZone centreZone)
    {
        return reservationRepository.nbResParZone(centreZone);
    }

    @Override
    @Transactional
    public Reservation updateReservation(Integer idReservation, Reservation newReservation) {
        Reservation reservation = reservationRepository.findById(idReservation).orElse(null);
        if (reservation == null) {
            return null;
        }
        reservation.setNomCentre(newReservation.getNomCentre());
        reservation.setReservation_price(newReservation.getReservation_price());
        reservation.setDateReservation(newReservation.getDateReservation());
        return reservationRepository.save(reservation);
    }

    @Override
    public void supprimerReservationParId(Integer idReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);
        if (optionalReservation.isPresent()) {
            reservationRepository.delete(optionalReservation.get());
        } else {
            throw new RuntimeException("Le reservation avec l'ID " + idReservation + " n'existe pas");
        }
    }
    public List<Reservation> afficheReservation(Integer id_centre) {
        return reservationRepository.findAll();
    }
    @Override
    public List<Reservation> retrieveAllReservation() {
        List<Reservation> ListActivty = reservationRepository.findAll ();
        return ListActivty;
    }


}
