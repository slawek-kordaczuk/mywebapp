package com.mycompany.mywebapp.contracts;

import java.io.Serializable;

/*
import com.google.gwt.sample.contacts.client.ClientSerializable;
import com.google.gwt.sample.contacts.client.ClientSerializableField;
*/

@SuppressWarnings("serial")
public class Contact implements Serializable /*, ClientSerializable*/ {

    public String id;
    private String firstName;
    private String emailAddress;
    private String lastName;
    public Address address;

    public Contact() {
    }

    public Contact(String id, String firstName, String lastName, String emailAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public ContactDetails getLightWeightContact() {
        return new ContactDetails(id, getFullName());
    }

    //@ClientSerializableField("id")
    public String getId() {
        return id;
    }

    //@ClientSerializableField("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
