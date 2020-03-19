package com.ahtisham.sagheer.trading2.Authantication.Model;

public class MemberData {

    String FirstName;
    String LastName;
    String Address;
    String City;
    String State;
    String ZipCode;
    String ApartmentNumber;
    String Email;

    public MemberData() {

    }

    public MemberData(String firstName, String lastName, String address, String city, String state, String zipCode, String apartmentNumber, String email) {
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        City = city;
        State = state;
        ZipCode = zipCode;
        ApartmentNumber = apartmentNumber;
        Email = email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getApartmentNumber() {
        return ApartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        ApartmentNumber = apartmentNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

}
