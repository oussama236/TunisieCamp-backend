package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Evaluation;
import com.gladiators.pi_spring.Services.Implementations.EvaluationServiceImple;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "Gestion des Evaluation")
@RequestMapping(value = "/api/evaluation", produces = MediaType.APPLICATION_JSON_VALUE)
public class EvaluationRestController {

    @Autowired
    EvaluationServiceImple evaluationServiceImple;


    @ApiOperation(value = "Récupérer la liste des Evaluation")
    @GetMapping("/retrieve-all-Evaluation")
    public List<Evaluation> getEvaluation() {
        List<Evaluation> listEvaluation = evaluationServiceImple.retrieveAllEvaluation ();
        return listEvaluation;
    }


    @PostMapping("/add-Evaluation")
    public Long ajouterEvaluation(@RequestBody Evaluation Ev){
        return evaluationServiceImple.AddEvaluation (Ev);
    }


    @DeleteMapping("/remove-evaluation/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable final Long id) {
        evaluationServiceImple.deleteEvaluationById (id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modify-evaluation/{ActivityId}")
    public ResponseEntity<Void> updateEvaluation(@RequestBody Evaluation EvaluationDTO ,  @PathVariable("ActivityId") Long ActivityId) {
        evaluationServiceImple.updateEvaluation (EvaluationDTO,ActivityId);
        return ResponseEntity.ok().build();

    }


    @GetMapping("/Evaluation-Activty/{noteValue}")
    public List<Evaluation> listeDeClientsParCategorie(@PathVariable("noteValue")Long ActivityId)  {
        return evaluationServiceImple.listeDeEvaluationParActivty((ActivityId) );
    }




    @PostMapping("/AddEvaToActivity/{ActivityId}")
    @ResponseBody
    public void assignEvaToActiv (@RequestBody Evaluation EVA, @PathVariable("ActivityId") Long ActivityId)

    {
        evaluationServiceImple.assignEvaToActivity (EVA,ActivityId);


    }
    @GetMapping("/mostEval")
    @ResponseBody
    public ResponseEntity<ResponseMessage> mostEval(){
        String message = "";
       message = evaluationServiceImple.mostEvaluation ();
       return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

}
