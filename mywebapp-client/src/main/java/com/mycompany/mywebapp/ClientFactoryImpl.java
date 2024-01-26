package com.mycompany.mywebapp;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.mycompany.mywebapp.contracts.Contact;
import com.mycompany.mywebapp.contracts.ContactDetails;
import com.mycompany.mywebapp.view.ContactsView;
import com.mycompany.mywebapp.view.ContactsViewImpl;
import com.mycompany.mywebapp.view.EditContactView;
import com.mycompany.mywebapp.view.EditContactViewImpl;

public class ClientFactoryImpl implements ClientFactory
{
	private static final EventBus eventBus = new SimpleEventBus();
	private static final PlaceController placeController = new PlaceController(eventBus);

	private static final ContactsView<ContactDetails> contactView = new ContactsViewImpl<>();

	private static final EditContactView<Contact> editContactView = new EditContactViewImpl<>();

	@Override
	public EventBus getEventBus()
	{
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController()
	{
		return placeController;
	}

	@Override
	public ContactsView<ContactDetails> getContactView() {
		return contactView;
	}

	@Override
	public EditContactView<Contact> getEditContactView() {
		return editContactView;
	}


}
