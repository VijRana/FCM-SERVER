package com.pwc.idb.IDBFCM;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationMessageRepository extends MongoRepository<NotificationMessage, String> {

}
