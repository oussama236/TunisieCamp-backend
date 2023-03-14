package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Outils;
import com.gladiators.pi_spring.Repositories.OutilRepository;
import com.gladiators.pi_spring.Services.OutilService;
import com.gladiators.pi_spring.Services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@RestController
    @RequestMapping("/outils")
    public class OutilController {
    @Autowired
    private StorageService storageService;
        @Autowired
        private OutilService outilService;
    @Autowired
    private OutilRepository outilRepository;

    @GetMapping("/{IdOutil}")
        public ResponseEntity<Outils> getOutilById(@PathVariable long id) {
            Outils outil = outilService.getOutilById(id);
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
        public ResponseEntity<Outils> updateOutil(@PathVariable long IdOutils, @RequestBody Outils outils) {
            Outils updatedOutil = outilService.updateOutil(IdOutils, outils);
            return ResponseEntity.ok(updatedOutil);
        }

        @DeleteMapping("/Delete/{id}")
        public ResponseEntity<?> deleteOutil(@PathVariable long id) {
            outilService.deleteOutil(id);
            return ResponseEntity.ok().build();
        }

    @GetMapping("/outils/top-visited")
    public List<Outils> getTopVisitedOutils(@RequestParam int limit) {
        return OutilService.getTopVisitedOutils(limit);
    }
/*
    @PostMapping("/uploadimg")
public Outils save(@RequestParam MultipartFile file , Outils outils){
            String fileName= storageService.CreateNameImage(file);
            storageService.store(file,fileName);
            outils.setImage(fileName);
            return outilRepository.save(outils);
*/



    @GetMapping("Files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\""+file.getFilename()+"\"").body(file);

    }


    // ********************************************************************


    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        String uploadImage = outilService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData=outilService.downloadImage(fileName);
        System.err.println("*****1******* "+ fileName);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }


    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/sendMail/{toEmail}/{subject}/{body}")
    public void sendSimpleEmail(@PathVariable String toEmail,@PathVariable String subject,@PathVariable String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fromemail@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Send...");
    }






    }

