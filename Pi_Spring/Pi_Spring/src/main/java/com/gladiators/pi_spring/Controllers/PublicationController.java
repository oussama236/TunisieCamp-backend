package com.gladiators.pi_spring.Controllers;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.PathParam;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Services.Interfaces.PubInterface;
import com.gladiators.pi_spring.Util.ImageUtility;

@RestController
@RequestMapping("/Publication")
public class PublicationController {
    @Autowired
    PubInterface pubInterface;
    @Autowired
    PublicationRepository publicationRepository;
    @PostMapping("/addPub")
    
    public Publication addPub(@RequestParam("photo") MultipartFile photo , @PathParam("contenu") String contenu) throws IOException{
    	Publication  pub =new Publication();
    	pub.setPhoto(photo.getBytes());
    	pub.setContenu(contenu);
    	return pubInterface.AddPublication(pub);
    	

    }
    @PutMapping("/updatePub")
    public Publication updatePub(@RequestBody Publication publication){
        return pubInterface.updatePublication(publication);
    }
    @DeleteMapping("/deletePub/{id_pb}")
    public void deletePub(@PathVariable Long id_pb){
        pubInterface.deletePublication(id_pb);
    }
    @GetMapping("/display")
    public List<Publication> retrieveAll(){
        return pubInterface.retrieveAllPubs();
    }
    @PostMapping( "/{id_pb}/photo")
   
    public ResponseEntity<ImageUploadResponse>  uploadPhoto(@PathVariable("id_pb") Long id_pb, @NotNull @RequestParam("file") MultipartFile file) throws Exception {

        Publication publication =publicationRepository.findById(id_pb).get();
        publication.setPhoto(ImageUtility.compressImage(file.getBytes()));
        publicationRepository.save(publication);

        return (ResponseEntity<ImageUploadResponse>) ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }
    @GetMapping("/nbrLikesDislikes")
    public void nbrLikesPosts(){
        pubInterface.nbrLikesPosts();
    }
}
