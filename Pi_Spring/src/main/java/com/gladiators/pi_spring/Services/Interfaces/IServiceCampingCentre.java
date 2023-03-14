package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.CampingCenter;
import com.gladiators.pi_spring.Entities.Reservation;

import java.util.List;

public interface IServiceCampingCentre {
    public CampingCenter ajouterCampingCentre(CampingCenter campingCenter);

    List<CampingCenter> afficherCentres();

    void deleteCentre(Integer IdCentre);

    CampingCenter updateCentre(CampingCenter c);

    public List<CampingCenter> recherche(String keyword);

}
