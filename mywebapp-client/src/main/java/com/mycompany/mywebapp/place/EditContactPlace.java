package com.mycompany.mywebapp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.mycompany.mywebapp.contracts.ContactDetails;

public class EditContactPlace extends Place {
    private String name;

    private ContactDetails contactDetails;

    public EditContactPlace(String token) {
        this.name = token;
    }

    public EditContactPlace(String name, ContactDetails contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }

    public String getName() {
        return name;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public static class Tokenizer implements PlaceTokenizer<EditContactPlace> {
        @Override
        public String getToken(EditContactPlace place) {
            return place.getName();
        }

        @Override
        public EditContactPlace getPlace(String token) {
            return new EditContactPlace(token);
        }
    }

}
