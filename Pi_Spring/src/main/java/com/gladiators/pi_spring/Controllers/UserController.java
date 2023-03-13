package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Export.ExportPdf;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Implementations.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController

public class UserController {

    @Autowired
    UserImp userImp;

    @PostMapping("/signup/{idRole}")
    @ResponseBody
    public User signup( User user , @RequestParam("file") MultipartFile file , @PathVariable("idRole") Long idRole ) {
        return  userImp.AddUserAndAffectRole(user , idRole, file);
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        return userImp.deleteUserById(id);

    }

    @PostMapping("/update")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        userImp.updateUser(user);
        return user;
    }

    @GetMapping("/list")
    public List<User> showUsers() {
        return userImp.retrieveAllUser();

    }

    @GetMapping("recherche/{keyword}")
    public List<User> recherche(@PathVariable("keyword") String keyword) {
        return userImp.recherche (keyword);
    }
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ActivityReport(HttpServletResponse response) throws IOException {

        List<User> allActivtys = userRepository.findAll ();

        ByteArrayInputStream bis = ExportPdf.ActivityReport (allActivtys);

        HttpHeaders headers = new HttpHeaders ();

        headers.add ("Content-Disposition", "attachment;filename=User.pdf");

        return ResponseEntity.ok ().headers (headers).contentType (MediaType.APPLICATION_PDF)
                .body (new InputStreamResource (bis));
    }
}
