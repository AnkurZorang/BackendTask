package com.example.CRUDmongo.Repository;

import com.example.CRUDmongo.model.model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends MongoRepository<model,String>{


}