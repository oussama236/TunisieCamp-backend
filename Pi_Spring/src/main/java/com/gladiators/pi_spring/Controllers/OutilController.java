package com.gladiators.pi_spring.Controllers;


import com.gladiators.pi_spring.Entities.Outils;
import com.gladiators.pi_spring.Services.OutilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
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

    @PostMapping("/upload")
    public String handleFileUpload(HttpServletRequest request, MultipartResolver multipartResolver) throws IOException {
        MultipartHttpServletRequest multipartRequest = multipartResolver .resolveMultipart(request);
        Iterator<String> fileNames = multipartRequest.getFileNames();
        while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile file = multipartRequest.getFile(fileName);
            // traiter le fichier ici
        }
        return "Upload réussi";
    }
    }

