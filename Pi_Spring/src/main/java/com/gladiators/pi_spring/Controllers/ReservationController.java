package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.CentreZone;
import com.gladiators.pi_spring.Entities.Reservation;
import com.gladiators.pi_spring.Repositories.ReservationRepository;
import com.gladiators.pi_spring.Services.Interfaces.IServiceReservation;
import com.gladiators.pi_spring.utils.ExportPdf;
import com.gladiators.pi_spring.utils.QRCode;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    IServiceReservation reservationService ;
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/ajouterReservation/{idCentre}")
    @ResponseBody
    public ResponseEntity<Reservation> ajouterReservation(@PathVariable Integer idCentre, @RequestBody Reservation reservation) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("mahmoud.lakhal@esprit.tn");
        mail.setSubject("Nouveau commentaire sur votre post");
        javaMailSender.send(mail);


        Reservation nouveauReservation = reservationService.ajouterReservation(reservation, idCentre);
        return ResponseEntity.ok(nouveauReservation);
    }

    @GetMapping("/nb-reservation-par-zone/{centre_zone}")
    public Integer nbResParZone(@PathVariable("centre_zone") CentreZone centreZone) {
        return reservationService.nbResParZone(centreZone);
    }

    @PutMapping("/reservations/{id}")
    public Reservation updateReservation(@PathVariable("id") Integer idReservation, @RequestBody Reservation newReservation) {
        return reservationService.updateReservation(idReservation, newReservation);
    }
    @DeleteMapping("/{idReservation}")
    public ResponseEntity<String> supprimerReservationParId(@PathVariable("idReservation") Integer idReservation) {
        try {
            reservationService.supprimerReservationParId(idReservation);
            return new ResponseEntity<>("Le reservation avec l'ID " + idReservation  + " a été supprimé avec succès", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/modifierReservation/{idReservation}")
    @ResponseBody
    public Reservation modifierReservation(@PathVariable("idReservation") Integer idReservation, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(idReservation, reservation);
    }


    @GetMapping(value = "/list/{id_centre}")
    @ResponseBody
    public List<Reservation> getAllReservation(@PathVariable("id_centre") Integer id_centre) {
        return reservationService.afficheReservation(id_centre) ;
    }



   @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
   public ResponseEntity<InputStreamResource> ActivityReport(HttpServletResponse response) throws IOException {

       List<Reservation> allActivtys = reservationRepository.findAll ();

       ByteArrayInputStream bis = ExportPdf.ReservationReport (allActivtys);

       HttpHeaders headers = new HttpHeaders ();

       headers.add ("Content-Disposition", "attachment;filename=Activty.pdf");

       return ResponseEntity.ok ().headers (headers).contentType (MediaType.APPLICATION_PDF)
               .body (new InputStreamResource (bis));
   }


    @ApiOperation(value = "Récupérer la liste des reservation")
    @GetMapping("/retrieve-all-reservation")
    public List<Reservation> getReservation() throws IOException, WriterException {
        List<Reservation> listClasses = reservationService.retrieveAllReservation ();
        if (listClasses.size () != 0) {
            for (Reservation activity : listClasses) {
                QRCode.generateQRCode (activity);
            }
        }
        return listClasses;
    }

}
