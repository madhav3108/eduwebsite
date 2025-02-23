package com.example.eduwebsite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String zip;

    public Address() {}

    public Address(Long id, String addressLine, String city, String state, String country, String zip) {
        this.id = id;
        this.addressLine = addressLine;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine='" + addressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
