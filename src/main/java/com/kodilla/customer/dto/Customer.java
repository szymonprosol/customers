package com.kodilla.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CUSTOMERS")
public class Customer {
        @Id
        @Column(name="CUSTOMER_ID")
        @GeneratedValue
        private Long id;

        @Column(name = "FIRSTNAME")
        private String firstname;

        @Column(name = "LASTNAME")
        private String lastname;
}
