package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Activity;
import com.gladiators.pi_spring.Entities.ActivityLiked;
import com.gladiators.pi_spring.Entities.Evaluation;
import com.gladiators.pi_spring.Repositories.ActivityRepository;

import com.gladiators.pi_spring.Services.Implementations.ActivityServiceImple;
import com.gladiators.pi_spring.export.ExportPdf;
import com.gladiators.pi_spring.export.QRCode;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController

@Api(tags = "Gestion des activites")
@RequestMapping(value = "/api/activitys", produces = MediaType.APPLICATION_JSON_VALUE)
public class ActivityRestController {


    @Autowired
    ActivityServiceImple activityServiceImple;


    //temchi
    @ApiOperation(value = "Récupérer la liste des activites")
    @GetMapping("/retrieve-all-Activty")
    public List<Activity> getActivity() throws IOException, WriterException {
        List<Activity> listClasses = activityServiceImple.retrieveAllActivity ();
        if (listClasses.size () != 0) {
            for (Activity activity : listClasses) {
                QRCode.generateQRCode (activity);
            }
        }
        return listClasses;
    }

    //temchi
    @GetMapping("/{id}")
    public Activity findById(@PathVariable("id") Long id) {
        return activityServiceImple.findById (id);
    }

//temchi
//    @PostMapping("/add-Activity")
//public Long ajouterActivite(@RequestBody Activity Ac){
//    return activityServiceImple.AddActivity (Ac);
//}

    //temchi
    @DeleteMapping("/remove-Activ/{id}")
    public void deleteActivity(@PathVariable Long id) {
        activityServiceImple.deleteActivityById (id);

    }

    //temchi
    @PutMapping("/modify-Activity/{userId}")
    public void updateActivity(@RequestBody Activity activityDTO, @PathVariable("userId") Long userId) {
        activityServiceImple.updateActivity (activityDTO, userId);

    }

//    @PostMapping("/add-Activity/{User-id}")
//    public Activity addActivityUser(@RequestBody Activity A, @PathVariable("User-id")  Long Userid) {
//
//        return activityServiceImple.AddActivityAndAffectUser (A, Userid);
//    }

    @GetMapping("/getByEvaluation/{idAcitivity}")
    public List<Evaluation> getByActivity(@PathVariable("idAcitivity") Long idActivity) {
        return activityServiceImple.getByEvalyation (idActivity);
    }

    @GetMapping("recherche/{keyword}")
    public List<Activity> recherche(@PathVariable("keyword") String keyword) {
        return activityServiceImple.recherche (keyword);
    }


    @PostMapping("/add-Like-post/{idActivity}/{userId}")
    @ResponseBody
    public ActivityLiked addLike_to_Post(@RequestBody ActivityLiked activityLiked, @PathVariable("idActivity") Long idActivity, @PathVariable("userId") Long userId) {
        ActivityLiked activityLiked1 = new ActivityLiked ();
        activityLiked1.setIsLiked (true);

        return activityServiceImple.addLikeToActivity (activityLiked1, idActivity, userId);
    }

    @DeleteMapping("/Delete-Like/{IdLike}/{idUser}")
    public ResponseEntity<?> Delete_Like(@PathVariable("IdLike") Long IdLike, @PathVariable("idUser") Long idUser) {
        return activityServiceImple.Delete_Like (IdLike, idUser);
    }


    //temchi
    @PostMapping("/AddActivityToUserAEva/{userId}")
    @ResponseBody
//    public void assignUserAndEvaToActiv (@RequestBody Activity Activ, @PathVariable("userId") Long userId, @PathVariable ("EvaluaId")Long EvaluationId)

    public void assignUserAndEvaToActiv(@RequestBody Activity Activ, @PathVariable("userId") Long userId) {
        activityServiceImple.assignUserAndEvaToActivity (Activ, userId);
//        activityServiceImple.assignUserAndEvaToActivity (Activ,userId,EvaluationId);

    }


    @Autowired
    ActivityRepository activityRepository;


    //temchi
    @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> ActivityReport(HttpServletResponse response) throws IOException {

        List<Activity> allActivtys = activityRepository.findAll ();

        ByteArrayInputStream bis = ExportPdf.ActivityReport (allActivtys);

        HttpHeaders headers = new HttpHeaders ();

        headers.add ("Content-Disposition", "attachment;filename=Activty.pdf");

        return ResponseEntity.ok ().headers (headers).contentType (MediaType.APPLICATION_PDF)
                .body (new InputStreamResource (bis));
    }


}