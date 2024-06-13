package com.example.CRUDmongo.Service;

import com.example.CRUDmongo.Repository.MessageRepository;
import com.example.CRUDmongo.Repository.Repo;
import com.example.CRUDmongo.model.MessagePOJO;
import com.example.CRUDmongo.model.ModelDTO;
import com.example.CRUDmongo.model.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class service {

    @Autowired
    private Repo userRepository;
    private MessagePOJO messagePOJO;
    private final MessageRepository messageRepository;

    @Autowired
    public service(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void newUser(String newUser) {
        model newuser = new model(newUser);
        userRepository.save(newuser);
    }
    public Boolean checkUser(String id) {
        return userRepository.existsById(id);
    }

    public void newChat(ModelDTO message){
        messagePOJO.setSender(message.getSender());
        messagePOJO.setMessage(message.getMessage());
        messagePOJO.setUniqueId(message.getUniqueId());
        messageRepository.save(messagePOJO).subscribe();
    }
    public Flux<MessagePOJO> streamMessages(String UniqueId) {
        return messageRepository.findWithTailableCursorByUniqueId(UniqueId);
    }




}
