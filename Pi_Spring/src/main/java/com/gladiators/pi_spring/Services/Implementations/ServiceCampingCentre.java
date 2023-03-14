package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.CampingCenter;
import com.gladiators.pi_spring.Entities.Reservation;
import com.gladiators.pi_spring.Repositories.CampingCentreRepository;
import com.gladiators.pi_spring.Services.Interfaces.IServiceCampingCentre;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service

public class ServiceCampingCentre implements IServiceCampingCentre {
    @Autowired
    CampingCentreRepository campingCenterRepository ;

    @Override
    public CampingCenter ajouterCampingCentre(CampingCenter campingCenter){
        log.info("centre ajouté ");
        return campingCenterRepository.save(campingCenter);
    }

    @Override
    public List<CampingCenter> afficherCentres() {
        return campingCenterRepository.findAll();
    }

    @Override
    public void deleteCentre(Integer IdCentre) {
        campingCenterRepository.deleteById(IdCentre);
    }

    @Override
    public CampingCenter updateCentre(CampingCenter c) {
        campingCenterRepository.save(c);
        return c;
    }
    @Override
    public List<CampingCenter> recherche(String keyword) {

        if (keyword != null) {
            return campingCenterRepository.recherche (keyword);
        } else return campingCenterRepository.findAll ();
    }
}
