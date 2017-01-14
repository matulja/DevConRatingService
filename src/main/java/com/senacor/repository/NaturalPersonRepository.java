package com.senacor.repository;

import com.senacor.model.NaturalPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by saba on 14.01.17.
 */
@Repository
public interface NaturalPersonRepository extends MongoRepository<NaturalPerson, String> {

    NaturalPerson findByUserId(String userId);
}
