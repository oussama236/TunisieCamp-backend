package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Message;
import com.gladiators.pi_spring.Services.Interfaces.MessageInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Message")
public class MessageController {
    @Autowired
    MessageInterface messageInterface;
    @PostMapping("/addMessage")
    @ResponseBody
    public Message addMessage (@RequestBody Message message){
        messageInterface.addMessage(message);
        return message;
    }
    @DeleteMapping("/deleteM/{id_me}")
    public void deleteMessage(@PathVariable Long id_me){
        messageInterface.deleteMessage(id_me);
    }
    @GetMapping("/display/{id}")
    public List<Message> retrieveMessages(@PathVariable Long id){
         return messageInterface.retrieveAllMessageInGroup(id);
    }
}
