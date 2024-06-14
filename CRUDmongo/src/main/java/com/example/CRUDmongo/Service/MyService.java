package com.example.CRUDmongo.Service;

import com.example.CRUDmongo.Repository.MessageRepository;
import com.example.CRUDmongo.Repository.Repo;
import com.example.CRUDmongo.model.MessagePOJO;
import com.example.CRUDmongo.model.modelDTO;
import com.example.CRUDmongo.model.userModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MyService {

    @Autowired
    private Repo userRepository;
    private static final Logger logger = LoggerFactory.getLogger(MyService.class);
    private final MessageRepository messageRepository;


    public MyService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void newUser(String newUser) {
        userModel Newmodel=new userModel(null);
        Newmodel.setId(newUser);
        userRepository.save(Newmodel);
    }
    public Boolean checkUser(String id) {
        return userRepository.existsById(id);
    }

    public void newChat(modelDTO message){
        MessagePOJO messagePOJO = new MessagePOJO();
        messagePOJO.setId(message.getId());
        messagePOJO.setSender(message.getSender());
        messagePOJO.setMessage(message.getMessage());
        messagePOJO.setUniqueId(message.getUniqueId());
        messageRepository.save(messagePOJO).subscribe();
    }
    public Flux<MessagePOJO> streamMessages(String uniqueId) {


        logger.info("Starting to stream messages for uniqueId: {}", uniqueId);
        return messageRepository.findWithTailableCursorByUniqueId(uniqueId)
                .doOnSubscribe(subscription -> logger.info("Subscribed to stream for uniqueId: {}", uniqueId))
                .doOnNext(message -> logger.info("Streaming message: {}", message))
                .doOnError(error -> logger.error("Error streaming messages", error))
                .doOnCancel(() -> logger.info("Stream cancelled for uniqueId: {}", uniqueId));

    }


}
