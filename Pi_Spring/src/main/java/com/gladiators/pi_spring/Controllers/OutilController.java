package com.gladiators.pi_spring.Controllers;


import com.gladiators.pi_spring.Entities.Outils;
import com.gladiators.pi_spring.Services.OutilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/outil")
    public class OutilController {
        @Autowired
        private OutilService outilService;

        @GetMapping("/{IdOutil}")
        public ResponseEntity<Outils> getOutilById(@PathVariable Long IdOutils) {
            Outils outil = outilService.getOutilById(IdOutils);
            return ResponseEntity.ok(outil);
        }

        @GetMapping("/List")
        public ResponseEntity<List<Outils>> getAllOutils() {
            List<Outils> outils = outilService.getAllOutils();
            return ResponseEntity.ok(outils);
        }

        @PostMapping("/Post")
        public ResponseEntity<Outils> addOutil(@RequestBody Outils outils) {
            Outils newOutil = outilService.addOutil(outils);
            return ResponseEntity.ok(newOutil);
        }

        @PutMapping("/Update/{IdOutils}")
        public ResponseEntity<Outils> updateOutil(@PathVariable Long IdOutils, @RequestBody Outils outils) {
            Outils updatedOutil = outilService.updateOutil(IdOutils, outils);
            return ResponseEntity.ok(updatedOutil);
        }

        @DeleteMapping("/Delete/{id}")
        public ResponseEntity<?> deleteOutil(@PathVariable Long id) {
            outilService.deleteOutil(id);
            return ResponseEntity.ok().build();
        }

    @GetMapping("/outils/top-visited")
    public List<Outils> getTopVisitedOutils(@RequestParam int limit) {
        return OutilService.getTopVisitedOutils(limit);
    }
    }

