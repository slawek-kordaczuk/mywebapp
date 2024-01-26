package com.mycompany.mywebapp.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.mycompany.mywebapp.ClientFactory;
import com.mycompany.mywebapp.contracts.Contact;
import com.mycompany.mywebapp.contracts.ContactDetails;
import com.mycompany.mywebapp.contracts.ContactsServiceAsync;
import com.mycompany.mywebapp.place.ContactPlace;
import com.mycompany.mywebapp.place.EditContactPlace;
import com.mycompany.mywebapp.view.EditContactView;

public class EditContactActivity extends AbstractActivity implements EditContactView.Presenter<Contact> {

    private ClientFactory clientFactory;
    private String name;

    private Contact contact;

    private final ContactsServiceAsync rpcService;


    public EditContactActivity(ClientFactory clientFactory, EditContactPlace place, ContactsServiceAsync rpcService) {
        this.clientFactory = clientFactory;
        this.name = place.getName();
        this.rpcService = rpcService;
        ContactDetails contactDetails = place.getContactDetails();
        if (contactDetails != null) {
            rpcService.getContact(contactDetails.getId(), new AsyncCallback<Contact>() {
                public void onSuccess(Contact result) {
                    contact = result;
                }

                public void onFailure(Throwable caught) {
                    Window.alert("Error retrieving contact");
                }
            });
        }
    }

    @Override
    public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
        EditContactView<Contact> view;
        view = clientFactory.getEditContactView();
        view.setName(name);
        view.setActivity(this);
        containerWidget.setWidget(view.asWidget());
    }

    @Override
    public void onAddButtonClicked(Contact result) {
        contact = result;
        doSave();
    }

    @Override
    public void onCancelButtonClicked() {
        goTo(new ContactPlace(name));
    }

    @Override
    public void goTo(Place place) {
        clientFactory.getPlaceController().goTo(place);
    }

    private void doSave() {

        rpcService.updateContact(contact, new AsyncCallback<Contact>() {
            public void onSuccess(Contact result) {
                goTo(new ContactPlace(name));
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error updating contact");
            }
        });
    }
}
