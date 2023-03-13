package com.gladiators.pi_spring.Services.Implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gladiators.pi_spring.Entities.Groupez;
import com.gladiators.pi_spring.Entities.Message;
import com.gladiators.pi_spring.Repositories.GroupRepository;
import com.gladiators.pi_spring.Repositories.MessageRepository;
import com.gladiators.pi_spring.Services.Interfaces.MessageInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageImp implements MessageInterface {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    GroupRepository groupRepository;
    @Override
    public Message addMessage(Message message){
        messageRepository.save(message);
        return message;
    }
    @Override
    public Message updateMessage(Message message){
        messageRepository.save(message);
        return message;
    }
    public void deleteMessage(Long id_me){
        messageRepository.deleteById(id_me);
    }
    public List<Message> retrieveAllMessageInGroup(Long id){
        Groupez g=groupRepository.findById(id).get();
        List<Message> messageList=g.getMessages();
        return messageList ;
    }
    @Override
    public void affectMessageToGroup(Long id,Long id_me){
        Message c = messageRepository.findById(id_me).get();
        Groupez p = groupRepository.findById(id).get();
        p.getMessages().add(c);
        messageRepository.save(c);
    }
}
