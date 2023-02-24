package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Facture;
import com.gladiators.pi_spring.Repositories.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    public Facture getFactureById(Long idFacture) {
        Optional<Facture> factureOptional = factureRepository.findById(idFacture);
        return factureOptional.orElseThrow(() -> new RuntimeException("Facture non trouvée avec l'ID : " + idFacture));
    }


}
