package jpa1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    // fields

    @Column(length = 512)
    String street;

    @Column(length = 512)
    String number;

    int zipCode;

    // constructor
    public Address() {
    }
    public Address(String street, String number, int zipCode){
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
    }


    // getter & setter



    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
