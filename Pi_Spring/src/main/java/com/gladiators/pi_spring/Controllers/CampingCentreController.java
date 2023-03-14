package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.CampingCenter;
import com.gladiators.pi_spring.Entities.Reservation;
import com.gladiators.pi_spring.Services.Interfaces.IServiceCampingCentre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/CampingCentre")

public class CampingCentreController {
    @Autowired
    IServiceCampingCentre iServiceCampingCentre ;

    @PostMapping("/ajouterCampingCentre")
    @ResponseBody
    public CampingCenter ajouterCampingCentre (@RequestBody CampingCenter campingCenter)
    {
        return iServiceCampingCentre.ajouterCampingCentre(campingCenter);
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public List<CampingCenter> getAllCampingCentres() {
        return iServiceCampingCentre.afficherCentres() ;
    }

    @DeleteMapping("/remove-centre/{id_centre}")
    public void removeClasse(@PathVariable("id_centre") Integer IdCentre) {
        iServiceCampingCentre.deleteCentre(IdCentre);
    }

    @PutMapping("/modify-centre")
    public CampingCenter modifyCentre(@RequestBody CampingCenter campingCenter) {
        return iServiceCampingCentre.updateCentre(campingCenter);
    }

    @GetMapping("recherche/{keyword}")
    public List<CampingCenter> recherche(@PathVariable("keyword") String keyword) {
        return iServiceCampingCentre.recherche(keyword);
    }

}
