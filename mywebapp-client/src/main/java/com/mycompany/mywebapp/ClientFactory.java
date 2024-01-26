package com.mycompany.mywebapp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mycompany.mywebapp.contracts.Contact;
import com.mycompany.mywebapp.contracts.ContactDetails;
import com.mycompany.mywebapp.view.ContactsView;
import com.mycompany.mywebapp.view.EditContactView;

public interface ClientFactory
{
	EventBus getEventBus();
	PlaceController getPlaceController();

	ContactsView<ContactDetails> getContactView();

	EditContactView<Contact> getEditContactView();
}
