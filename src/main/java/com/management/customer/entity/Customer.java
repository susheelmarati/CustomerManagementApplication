package com.management.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    private String id;

    private String firstName;

    private String middleName;

    private String lastName;

    @Column(unique = true)
    private String emailId;

    private String mobile;
}
