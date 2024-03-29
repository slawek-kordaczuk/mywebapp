package com.mycompany.mywebapp.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ContactPlace extends Place {
    private String name;

    public ContactPlace(String token) {
        this.name = token;
    }

    public String getName() {
        return name;
    }

    public static class Tokenizer implements PlaceTokenizer<ContactPlace> {

        @Override
        public String getToken(ContactPlace place) {
            return place.getName();
        }

        @Override
        public ContactPlace getPlace(String token) {
            return new ContactPlace(token);
        }

    }
}
