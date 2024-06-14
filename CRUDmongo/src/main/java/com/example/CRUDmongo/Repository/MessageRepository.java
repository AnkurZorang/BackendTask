package com.example.CRUDmongo.Repository;

import com.example.CRUDmongo.model.MessagePOJO;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface MessageRepository extends ReactiveMongoRepository<MessagePOJO, String> {

    @Tailable
    Flux<MessagePOJO> findWithTailableCursorByUniqueId(String uniqueId);
}
