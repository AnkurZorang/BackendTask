package com.example.CRUDmongo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Messages")
public class MessagePOJO {

        @Id
        private String id;
        private String message;
        private String sender;
        @CreatedDate
        private Instant createdDate = Instant.now();
        private String uniqueId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String UniqueId) {
        uniqueId = UniqueId;
    }
}
