package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Outils;
import com.gladiators.pi_spring.Repositories.OutilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class OutilService {
    private static OutilRepository outilRepository;
    @Autowired
    private OutilRepository outilsRepository;

    public Outils getOutilById(Long id) {
        return outilRepository.findById(id).orElseThrow(() -> new RuntimeException("Outil introuvable"));
    }

    public List<Outils> getAllOutils() {
        return outilRepository.findAll();
    }

    public Outils addOutil(Outils outils) {
        return outilRepository.save(outils);
    }

    public Outils updateOutil(Long IdOutils, Outils outils) {
        Outils existingOutil = getOutilById(IdOutils);
        existingOutil.setName(outils.getName());
        existingOutil.setPoids(outils.getPoids());
        existingOutil.setPrice(outils.getPrice());
        existingOutil.setCathegorie(outils.getCathegorie());
        return outilRepository.save(existingOutil);
    }

    public void deleteOutil(Long IdOutils) {
        outilRepository.deleteById(IdOutils);
    }

    public static List<Outils> getTopVisitedOutils(int limit) {
        Pageable topN = (Pageable) PageRequest.of(0, limit, Sort.by("nombreVisites").descending());
        Page<Outils> topOutils = outilRepository.findAll(topN);
        return topOutils.getContent();
    }
}