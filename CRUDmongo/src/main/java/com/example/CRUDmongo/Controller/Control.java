package com.example.CRUDmongo.Controller;

import com.example.CRUDmongo.Service.MyService;
import com.example.CRUDmongo.model.MessagePOJO;
import com.example.CRUDmongo.model.modelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class Control {

 @Autowired
 private final MyService serve;
 private static final Logger logger = LoggerFactory.getLogger(Control.class);


      public Control(MyService serve) {

         this.serve=serve;
        }


 @PostMapping("/signUp")
 public ResponseEntity<String> addNew(@RequestBody String PhoneNumber) {
  if(!serve.checkUser(PhoneNumber)){
   serve.newUser(PhoneNumber);
  return ResponseEntity.ok("New User created successfully");}
  else{
  return ResponseEntity.badRequest().body("User already exists");}
 }


 @PostMapping("/signIn")
 public ResponseEntity<String> login(@RequestBody String PhoneNumber) {
  if(serve.checkUser(PhoneNumber)){
   return ResponseEntity.ok("Login request is made successfully");}
  else{
   return ResponseEntity.badRequest().body("User doesn't exists");}
 }

 @PostMapping("/chat")
 @ResponseStatus(HttpStatus.CREATED)
 public ResponseEntity<String> sendChat(@RequestBody modelDTO Message){
  serve.newChat(Message);
  return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
 }

 @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
 public Flux<MessagePOJO> streamMessages(@RequestParam String uniqueId) {
  logger.info("Starting to stream messages");
  return serve.streamMessages(uniqueId)
          .doOnNext(message -> logger.info("Streaming message: {}", message))
          .doOnError(error -> logger.error("Error streaming messages", error));

 }
}
