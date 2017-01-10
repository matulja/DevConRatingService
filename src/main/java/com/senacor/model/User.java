package com.senacor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by saba on 10.01.17.
 */
@Document
public class User {

    @Id
    String userId;

    public User() {

    }
}
