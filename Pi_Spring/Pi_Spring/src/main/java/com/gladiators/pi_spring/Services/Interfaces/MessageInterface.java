package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.Message;

import java.util.List;

public interface MessageInterface {
    public Message addMessage(Message message);
    public Message updateMessage(Message message);
    public void deleteMessage(Long id_me);
    public List<Message> retrieveAllMessageInGroup(Long id);
    public void affectMessageToGroup(Long id ,Long id_me);
}
