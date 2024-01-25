package com.mycompany.mywebapp.presenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.mycompany.mywebapp.contracts.Contact;
import com.mycompany.mywebapp.contracts.ContactsServiceAsync;
import com.mycompany.mywebapp.event.ContactUpdatedEvent;
import com.mycompany.mywebapp.event.EditContactCancelledEvent;
import com.mycompany.mywebapp.view.EditContactView;

public class EditContactPresenter implements Presenter, EditContactView.Presenter<Contact> {

    private Contact contact;
    private final ContactsServiceAsync rpcService;
    private final HandlerManager eventBus;

    private final EditContactView<Contact> view;

    public EditContactPresenter(ContactsServiceAsync rpcService, HandlerManager eventBus, EditContactView<Contact> view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.view = view;
        this.contact = new Contact();
        bind();
    }

    public EditContactPresenter(ContactsServiceAsync rpcService, HandlerManager eventBus, String id, EditContactView<Contact> view) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.view = view;
        bind();

        rpcService.getContact(id, new AsyncCallback<Contact>() {
            public void onSuccess(Contact result) {
                contact = result;
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving contact");
            }
        });

    }

    @Override
    public void onAddButtonClicked(Contact result) {
        contact = result;
        doSave();
    }

    @Override
    public void onCancelButtonClicked() {
        doCancel();
    }

    public void bind() {
        view.setPresenter(this);
    }

    public void go(final HasWidgets container) {
        container.clear();
        container.add(view.asWidget());
    }

    private void doCancel(){
        eventBus.fireEvent(new EditContactCancelledEvent());
    }

    private void doSave() {

        rpcService.updateContact(contact, new AsyncCallback<Contact>() {
            public void onSuccess(Contact result) {
                eventBus.fireEvent(new ContactUpdatedEvent(result));
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error updating contact");
            }
        });
    }

}
