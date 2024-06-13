package com.example.CRUDmongo.Controller;

import com.example.CRUDmongo.Service.service;
import com.example.CRUDmongo.model.MessagePOJO;
import com.example.CRUDmongo.model.ModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import static org.springframework.http.ResponseEntity.status;

@RestController
public class Control {


 private final service serve;

 @Autowired
      public Control(service serve) {

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
 public ResponseEntity<String> sendChat(@RequestBody ModelDTO Message){
  serve.newChat(Message);
  return ResponseEntity.status(HttpStatus.OK).body("Message sent successfully");
 }

 @GetMapping(value = "/chat/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
 public Flux<MessagePOJO> streamMessages(@RequestBody String UniqueId) {
  return serve.streamMessages(UniqueId);
 }
}
