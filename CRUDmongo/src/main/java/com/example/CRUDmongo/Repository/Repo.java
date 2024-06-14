package com.example.CRUDmongo.Repository;

import com.example.CRUDmongo.model.userModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends MongoRepository<userModel,String>{


}